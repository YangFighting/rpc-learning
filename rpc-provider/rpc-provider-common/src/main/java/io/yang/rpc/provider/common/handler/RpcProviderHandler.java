package io.yang.rpc.provider.common.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.yang.rpc.common.helper.RpcServiceHelper;
import io.yang.rpc.common.threadpool.ServerThreadPool;
import io.yang.rpc.protocol.RpcProtocol;
import io.yang.rpc.protocol.enumeration.RpcStatus;
import io.yang.rpc.protocol.enumeration.RpcType;
import io.yang.rpc.protocol.header.RpcHeader;
import io.yang.rpc.protocol.request.RpcRequest;
import io.yang.rpc.protocol.response.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author zhangyang
 * @description RPC服务提供者的Handler处理类
 * @date 2023/03/01 22:46
 **/
public class RpcProviderHandler extends SimpleChannelInboundHandler<RpcProtocol<RpcRequest>> {
    private final Logger logger = LoggerFactory.getLogger(RpcProviderHandler.class);
    private final Map<String, Object> handlerMap;

    public RpcProviderHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcProtocol<RpcRequest> protocol) throws Exception {


        ServerThreadPool.submit(() -> {

            RpcHeader header = protocol.getHeader();
            header.setMsgType((byte) RpcType.RESPONSE.getType());
            RpcRequest request = protocol.getBody();
            logger.debug("Receive request " + header.getRequestId());
            RpcProtocol<RpcResponse> responseRpcProtocol = new RpcProtocol<RpcResponse>();
            RpcResponse response = new RpcResponse();

            try {
                Object result = handle(request);
                response.setResult(result);
                response.setAsync(request.getAsync());
                response.setOneway(request.getOneway());
                header.setStatus((byte) RpcStatus.SUCCESS.getCode());
            } catch (Throwable t) {
                response.setError(t.toString());
                header.setStatus((byte) RpcStatus.FAIL.getCode());
                logger.error("RPC Server handle request error", t);
            }
            responseRpcProtocol.setHeader(header);
            responseRpcProtocol.setBody(response);

            ctx.writeAndFlush(responseRpcProtocol).addListener((ChannelFutureListener) channelFuture ->
                    logger.debug("Send response for request " + header.getRequestId()));
        });


    }

    private Object handle(RpcRequest request) throws Throwable {

        String serviceKey = RpcServiceHelper.buildServiceKey(request.getClassName(), request.getVersion(), request.getGroup());
        Object serviceBean = handlerMap.get(serviceKey);
        if (serviceBean == null) {
            throw new RuntimeException(String.format("service not exist: %s:%s", request.getClassName(), request.getMethodName()));
        }

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        logger.debug(serviceClass.getName());
        logger.debug(methodName);

        if (parameterTypes != null && parameterTypes.length > 0) {
            for (Class<?> parameterType : parameterTypes) {
                logger.debug(parameterType.getName());
            }
        }

        if (parameters != null && parameters.length > 0) {
            for (Object parameter : parameters) {
                logger.debug(parameter.toString());
            }
        }

        return invokeMethod(serviceBean, serviceClass, methodName, parameterTypes, parameters);
    }


    //TODO 目前使用JDK动态代理方式，此处埋点
    private Object invokeMethod(Object serviceBean, Class<?> serviceClass, String methodName, Class<?>[] parameterTypes, Object[] parameters) throws Throwable {
        Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("server caught exception", cause);
        ctx.close();
    }
}


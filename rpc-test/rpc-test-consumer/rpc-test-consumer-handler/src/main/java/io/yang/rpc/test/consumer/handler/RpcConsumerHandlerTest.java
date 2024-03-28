package io.yang.rpc.test.consumer.handler;

import io.yang.rpc.consumer.common.RpcConsumer;
import io.yang.rpc.consumer.common.context.RpcContext;
import io.yang.rpc.consumer.common.future.RPCFuture;
import io.yang.rpc.protocol.RpcProtocol;
import io.yang.rpc.protocol.header.RpcHeaderFactory;
import io.yang.rpc.protocol.request.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangyang
 * @date: 2023/5/30 0:17
 * @description: 测试服务消费者
 */
public class RpcConsumerHandlerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcConsumerHandlerTest.class);

    public static void main(String[] args) throws Exception {
        RpcConsumer consumer = RpcConsumer.getInstance();
        // 同步，请求任务来自发送请求的返回
//        RPCFuture future = consumer.sendRequest(getRpcRequestProtocol());

        // 异步, 发送请求后， 请求任务来自上下文
//        consumer.sendRequest(getRpcRequestProtocol());
//        RPCFuture future = RpcContext.getContext().getRpcFuture();
//        LOGGER.info("从服务消费者获取到的数据===>>>" + future.get());

        // 单向调用，发送请求后 不需要关注返回
        consumer.sendRequest(getRpcRequestProtocol());

        consumer.close();

    }

    private static RpcProtocol<RpcRequest> getRpcRequestProtocol() {
        //模拟发送数据
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
        protocol.setHeader(RpcHeaderFactory.getRequestHeader("jdk"));
        RpcRequest request = new RpcRequest();
        request.setClassName("io.yang.rpc.test.api.DemoService");
        request.setGroup("yang");
        request.setMethodName("hello");
        request.setParameters(new Object[]{"yang"});
        request.setParameterTypes(new Class[]{String.class});
        request.setVersion("1.0.0");
        request.setAsync(true);
        request.setOneway(false);
        protocol.setBody(request);
        return protocol;
    }
}

package io.yang.rpc.test.consumer.handler;

import io.yang.rpc.consumer.common.RpcConsumer;
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
        Object result = consumer.sendRequest(getRpcRequestProtocol());
        LOGGER.info("从服务消费者获取到的数据===>>>" + result.toString());
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
        request.setAsync(false);
        request.setOneway(false);
        protocol.setBody(request);
        return protocol;
    }
}

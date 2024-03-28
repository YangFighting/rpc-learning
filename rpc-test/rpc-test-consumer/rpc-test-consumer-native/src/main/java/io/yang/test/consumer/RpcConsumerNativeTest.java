package io.yang.test.consumer;

import io.yang.rpc.consumer.RpcClient;
import io.yang.rpc.test.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangyang03
 * @Description 测试Java原生启动服务消费者
 * @create 2024-03-28 21:41
 */
public class RpcConsumerNativeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcConsumerNativeTest.class);

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient("1.0.0", "yang", "jdk", 3000, false, false);
        DemoService demoService = rpcClient.create(DemoService.class);
        String result = demoService.hello("yang");
        LOGGER.info("返回的结果数据===>>> " + result);
        rpcClient.shutdown();
    }
}

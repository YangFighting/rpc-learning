package io.yang.test.consumer;

import io.yang.rpc.consumer.RpcClient;
import io.yang.rpc.proxy.api.async.IAsyncObjectProxy;
import io.yang.rpc.proxy.api.future.RPCFuture;
import io.yang.rpc.test.api.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.yang.rpc.constants.ServerConfigConstants.REGISTRY_ADDRESS;

/**
 * @author zhangyang03
 * @Description 测试Java原生启动服务消费者
 * @create 2024-03-28 21:41
 */
public class RpcConsumerNativeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcConsumerNativeTest.class);


    private RpcClient rpcClient;


    @Before
    public void initRpcClient() {
        rpcClient = new RpcClient(REGISTRY_ADDRESS, "zookeeper", "random", "cglib", "1.0.0", "yang", "jdk", 3000, false, false);

    }

    @Test
    public void testInterfaceRpc() {
        DemoService demoService = rpcClient.create(DemoService.class);
        String result = demoService.hello("yang");
        LOGGER.info("返回的结果数据===>>> " + result);
        rpcClient.shutdown();

    }

    @Test
    public void testAsyncInterfaceRpc() throws Exception {
        IAsyncObjectProxy demoService = rpcClient.createAsync(DemoService.class);
        RPCFuture future = demoService.call("hello", "yang");
        LOGGER.info("返回的结果数据===>>> " + future.get());
        rpcClient.shutdown();
    }
}

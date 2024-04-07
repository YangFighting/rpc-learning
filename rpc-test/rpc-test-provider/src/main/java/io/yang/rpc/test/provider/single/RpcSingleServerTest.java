package io.yang.rpc.test.provider.single;

import io.yang.rpc.provider.RpcSingleServer;
import org.junit.Test;

import static io.yang.rpc.constants.ServerConfigConstants.REGISTRY_ADDRESS;

/**
 * Description:
 * date: 2023/5/28 20:12
 *
 * @author zhangyang
 */
public class RpcSingleServerTest {
    @Test
    public void startRpcSingleServer() {

        RpcSingleServer singleServer = new RpcSingleServer("127.0.0.1:27880", REGISTRY_ADDRESS, "zookeeper", "random", "io.yang.rpc.test", "cglib");
        singleServer.startNettyServer();
    }
}

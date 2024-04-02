package io.yang.rpc.test.provider.single;

import io.yang.rpc.provider.RpcSingleServer;
import org.junit.Test;

/**
 * Description:
 * date: 2023/5/28 20:12
 *
 * @author zhangyang
 */
public class RpcSingleServerTest {
    @Test
    public void startRpcSingleServer(){

        RpcSingleServer singleServer = new RpcSingleServer("127.0.0.1:27880", "10.58.36.19:2181","zookeeper","io.yang.rpc.test",  "cglib");
        singleServer.startNettyServer();
    }
}

package io.yang.rpc.provider;

import org.junit.Test;


/**
 * @author zhangyang
 * @description
 * @date 2023/03/01 23:14
 **/
public class RpcSingleServerTest {

    @Test
    public void startRpcSingleServer() {
        String scanPackage = RpcSingleServerTest.class.getPackage().getName();
        RpcSingleServer singleServer = new RpcSingleServer("127.0.0.1:27880", scanPackage);
        singleServer.startNettyServer();
    }

}
package io.yang.rpc.provider;

import io.yang.rpc.provider.common.server.base.BaseServer;
import io.yang.rpc.provider.common.service.RpcServiceScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangyang
 * @description
 * @date 2023/03/01 23:04
 **/
public class RpcSingleServer extends BaseServer {
    private static final Logger logger = LoggerFactory.getLogger(RpcSingleServer.class);

    public RpcSingleServer(String serverAddress, String registryAddress, String registryType, String registryLoadBalanceType, String scanPackage, String reflectType) {
        //调用父类构造方法
        super(serverAddress, registryAddress, registryType, reflectType, registryLoadBalanceType);

        try {
            this.handlerMap = RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService(host, port, scanPackage, registryService);
        } catch (Exception e) {
            logger.error("RPC Server init error", e);
        }


    }
}

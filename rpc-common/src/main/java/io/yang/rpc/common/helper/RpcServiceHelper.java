package io.yang.rpc.common.helper;

/**
 * @author zhangyang
 * @date: 2023/5/28 22:48
 * @description: RPC服务帮助类
 */
public class RpcServiceHelper {

    public static String buildServiceKey(String serviceName, String serviceVersion, String group) {
        return String.join("#", serviceName, serviceVersion, group);
    }
}

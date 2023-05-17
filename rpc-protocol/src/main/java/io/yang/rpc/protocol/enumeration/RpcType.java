package io.yang.rpc.protocol.enumeration;

/**
 * @author zhangyang
 * @description 协议的类型
 * @date 2023/05/18 0:04
 **/
public enum RpcType {

    //请求消息
    REQUEST(1),
    //响应消息
    RESPONSE(2),
    //心跳数据
    HEARTBEAT(3);

    private final int type;
    RpcType(int type) {
        this.type = type;
    }
    public static RpcType findByType(int type) {
        for (RpcType rpcType : RpcType.values()) {
            if (rpcType.getType() == type) {
                return rpcType;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }
}

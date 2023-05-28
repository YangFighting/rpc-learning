package io.yang.rpc.protocol.enumeration;

/**
 * Description: RPC服务状态
 * date: 2023/5/28 22:41
 *
 * @author zhangyang
 */
public enum RpcStatus {
    SUCCESS(0),
    FAIL(1);

    private final int code;

    RpcStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

package io.yang.rpc.protocol.response;

import io.yang.rpc.protocol.base.RpcMessage;

/**
 * @author zhangyang
 * @description RPC的响应类，对应的请求id在响应头中
 * @date 2023/05/18 0:24
 **/
public class RpcResponse extends RpcMessage {
    private static final long serialVersionUID = -1636900713097506997L;

    private String error;
    private Object result;

    public boolean isError() {
        return error != null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

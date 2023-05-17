package io.yang.rpc.protocol;

import io.yang.rpc.protocol.header.RpcHeader;

import java.io.Serializable;

/**
 * @author zhangyang
 * @description
 * @date 2023/05/18 0:25
 **/
public class RpcProtocol<T> implements Serializable {
    private static final long serialVersionUID = -8639209420733775139L;

    /**
     * 消息头
     */
    private RpcHeader header;
    /**
     * 消息体
     */
    private T body;

    public RpcHeader getHeader() {
        return header;
    }

    public void setHeader(RpcHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}

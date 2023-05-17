package io.yang.rpc.protocol.base;

import java.io.Serializable;

/**
 * @author zhangyang
 * @description 消息体基础类
 * @date 2023/05/17 23:59
 **/
public class RpcMessage implements Serializable {

    private static final long serialVersionUID = 3612024097805662277L;
    /**
     * 是否单向发送
     */
    private boolean oneway;

    /**
     * 是否异步调用
     */
    private boolean async;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }
}

package io.yang.rpc.consumer.common.context;

import io.yang.rpc.consumer.common.future.RPCFuture;

/**
 * @author zhangyang03
 * @Description 保存RPC上下文
 * @create 2024-03-28 16:58
 */
public class RpcContext {

    public RpcContext() {
    }

    /**
     * RpcContext的单例
     */
    private static final RpcContext AGENT = new RpcContext();

    /**
     * 存放RPCFuture的 不可继承线程池
     */
    private static final InheritableThreadLocal<RPCFuture> RPC_FUTURE_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static RpcContext getContext(){
        return AGENT;
    }

    /**
     *  将 RPCFuture 保存到线程的上下文
     * @param rpcFuture rpcFuture
     */
    public void setRpcFuture(RPCFuture rpcFuture){
        RPC_FUTURE_INHERITABLE_THREAD_LOCAL.set(rpcFuture);
    }
    /**
     * 获取RPCFuture
     */
    public RPCFuture getRpcFuture(){
        return RPC_FUTURE_INHERITABLE_THREAD_LOCAL.get();
    }

    /**
     * 移除 RPCFuture
     */
    public void removeRpcFuture(){
        RPC_FUTURE_INHERITABLE_THREAD_LOCAL.remove();
    }
}

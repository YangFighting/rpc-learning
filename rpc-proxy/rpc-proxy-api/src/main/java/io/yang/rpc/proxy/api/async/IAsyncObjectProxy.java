package io.yang.rpc.proxy.api.async;

import io.yang.rpc.proxy.api.future.RPCFuture;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-01 17:32
 */
public interface IAsyncObjectProxy {

    /**
     * 异步代理对象调用方法
     * @param funcName 方法名称
     * @param args 方法参数
     * @return 封装好的RPCFuture对象
     */
    RPCFuture call(String funcName, Object... args);
}

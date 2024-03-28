package io.yang.rpc.proxy.api.consumer;

import io.yang.rpc.protocol.RpcProtocol;
import io.yang.rpc.protocol.request.RpcRequest;
import io.yang.rpc.proxy.api.future.RPCFuture;

/**
 * @author zhangyang03
 * @Description 服务消费者
 * @create 2024-03-28 20:41
 */
public interface Consumer {
    /**
     * 消费者发送 request 请求
     */
    RPCFuture sendRequest(RpcProtocol<RpcRequest> protocol) throws Exception;
}

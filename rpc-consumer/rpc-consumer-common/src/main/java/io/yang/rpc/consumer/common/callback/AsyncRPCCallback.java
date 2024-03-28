package io.yang.rpc.consumer.common.callback;

/**
 * @author zhangyang03
 * @Description 异步回调接口
 * @create 2024-03-28 18:54
 */
public interface AsyncRPCCallback {

    /**
     * 成功后的回调方法
     */
    void onSuccess(Object result);

    /**
     * 异常的回调方法
     */
    void onException(Exception e);
}

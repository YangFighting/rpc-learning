package io.yang.rpc.proxy.api;

import io.yang.rpc.proxy.api.config.ProxyConfig;
import io.yang.rpc.spi.annotation.SPI;


/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-01 18:01
 */
@SPI
public interface ProxyFactory {

    /**
     * 获取代理对象
     */
    <T> T getProxy(Class<T> clazz);

    /**
     * 默认初始化方法
     */
    default <T> void init(ProxyConfig<T> proxyConfig){}
}

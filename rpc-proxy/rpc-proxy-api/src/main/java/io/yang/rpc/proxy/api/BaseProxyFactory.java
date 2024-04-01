package io.yang.rpc.proxy.api;

import io.yang.rpc.proxy.api.config.ProxyConfig;
import io.yang.rpc.proxy.api.object.ObjectProxy;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-01 18:02
 */
public abstract class BaseProxyFactory<T> implements ProxyFactory {

    protected ObjectProxy<T> objectProxy;

    @Override
    public <T> void init(ProxyConfig<T> proxyConfig) {
        this.objectProxy = new ObjectProxy(proxyConfig.getClazz(),
                proxyConfig.getServiceVersion(),
                proxyConfig.getServiceGroup(),
                proxyConfig.getSerializationType(),
                proxyConfig.getTimeout(),
                proxyConfig.getConsumer(),
                proxyConfig.getAsync(),
                proxyConfig.getOneway());
    }
}

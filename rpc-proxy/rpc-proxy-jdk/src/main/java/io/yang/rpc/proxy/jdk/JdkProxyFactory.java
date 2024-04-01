package io.yang.rpc.proxy.jdk;

import io.yang.rpc.proxy.api.BaseProxyFactory;
import io.yang.rpc.proxy.api.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * @author zhangyang03
 * @Description JDK动态代理
 * @create 2024-03-28 21:10
 */
public class JdkProxyFactory<T> extends BaseProxyFactory<T> implements ProxyFactory {

    @Override
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                objectProxy
        );
    }

}

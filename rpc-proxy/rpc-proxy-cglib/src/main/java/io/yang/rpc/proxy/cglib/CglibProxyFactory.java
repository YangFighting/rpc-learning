package io.yang.rpc.proxy.cglib;

import io.yang.rpc.proxy.api.BaseProxyFactory;
import io.yang.rpc.proxy.api.ProxyFactory;
import io.yang.rpc.spi.annotation.SPIClass;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-07 10:18
 */
@SPIClass
public class CglibProxyFactory<T> extends BaseProxyFactory<T> implements ProxyFactory {
    private final Logger logger = LoggerFactory.getLogger(CglibProxyFactory.class);

    private final Enhancer enhancer = new Enhancer();

    @Override
    public <T> T getProxy(Class<T> clazz) {
        logger.info("基于CGLib动态代理...");
        enhancer.setInterfaces(new Class[]{clazz});
        enhancer.setCallback((InvocationHandler) (o, method, objects) -> objectProxy.invoke(o, method, objects));
        return (T) enhancer.create();
    }
}

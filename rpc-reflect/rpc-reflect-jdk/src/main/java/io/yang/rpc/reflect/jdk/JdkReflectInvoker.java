package io.yang.rpc.reflect.jdk;

import io.yang.rpc.reflect.api.ReflectInvoker;
import io.yang.rpc.spi.annotation.SPIClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author zhangyang03
 * @Description JDK反射调用方法的类
 * @create 2024-04-07 11:08
 */
@SPIClass
public class JdkReflectInvoker implements ReflectInvoker {
    private final Logger logger = LoggerFactory.getLogger(JdkReflectInvoker.class);

    @Override
    public Object invokeMethod(Object serviceBean, Class<?> serviceClass, String methodName, Class<?>[] parameterTypes, Object[] parameters) throws Throwable {
        logger.info("use jdk reflect type invoke method...");
        Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);
    }
}

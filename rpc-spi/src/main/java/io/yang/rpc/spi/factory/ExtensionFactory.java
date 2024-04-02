package io.yang.rpc.spi.factory;

import io.yang.rpc.spi.annotation.SPI;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 15:56
 */
@SPI("spi")
public interface ExtensionFactory {
    /**
     * 获取扩展类对象
     * @param <T>  泛型类型
     * @param key  传入的key值
     * @param clazz Class类型对象
     * @return 扩展类对象
     */
    <T> T getExtension(String key, Class<T> clazz);
}

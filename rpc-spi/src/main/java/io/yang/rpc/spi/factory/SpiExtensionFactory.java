package io.yang.rpc.spi.factory;

import io.yang.rpc.spi.annotation.SPI;
import io.yang.rpc.spi.annotation.SPIClass;
import io.yang.rpc.spi.loader.ExtensionLoader;

import java.util.Optional;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 15:56
 */
@SPIClass
public class SpiExtensionFactory implements ExtensionFactory{
    @Override
    public <T> T getExtension(String key, Class<T> clazz) {
        return Optional.ofNullable(clazz).filter(Class::isInterface)
                .filter(cls->cls.isAnnotationPresent(SPI.class))
                .map(ExtensionLoader::getExtensionLoader)
                .map(ExtensionLoader::getDefaultSpiClassInstance)
                .orElse(null);
    }
}

package io.yang.rpc.serialization.api;

import io.yang.rpc.constants.RpcConstants;
import io.yang.rpc.spi.annotation.SPI;

/**
 * @author zhangyang
 * @description 序列化接口
 * @date 2023/05/18 23:03
 **/
@SPI(RpcConstants.SERIALIZATION_JDK)
public interface Serialization {

    /**
     * 序列化
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列化
     */
    <T> T deserialize(byte[] data, Class<T> cls);
}
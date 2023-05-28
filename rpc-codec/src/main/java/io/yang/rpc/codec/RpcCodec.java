package io.yang.rpc.codec;


import io.yang.rpc.serialization.api.Serialization;
import io.yang.rpc.serialization.jdk.JdkSerialization;

/**
 * @author zhangyang
 * @description
 * @date 2023/05/18 23:39
 **/
public interface RpcCodec {
    default Serialization getJdkSerialization(){
        return new JdkSerialization();
    }
}

package io.yang.rpc.serialization.jdk;

import io.yang.rpc.common.exception.SerializerException;
import io.yang.rpc.serialization.api.Serialization;
import io.yang.rpc.spi.annotation.SPIClass;

import java.io.*;

/**
 * @author zhangyang
 * @description Jdk 序列化
 * @date 2023/05/18 23:06
 **/
@SPIClass
public class JdkSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T obj) {
        if(obj == null){
            throw new SerializerException("serialize object is null");
        }
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(obj);
            return os.toByteArray();
        } catch (IOException e){
            throw new SerializerException(e.getMessage(),e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> cls) {
        if(data ==null){
            throw new SerializerException("deserialize data is null");
        }

        try{
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            ObjectInputStream in = new ObjectInputStream(is);
            return (T) in.readObject();

        } catch (Exception e) {
            throw new SerializerException(e.getMessage(), e);
        }
    }
}

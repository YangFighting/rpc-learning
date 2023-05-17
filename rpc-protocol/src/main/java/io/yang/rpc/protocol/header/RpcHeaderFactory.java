package io.yang.rpc.protocol.header;

import io.yang.rpc.common.id.IdFactory;
import io.yang.rpc.constants.RpcConstants;
import io.yang.rpc.protocol.enumeration.RpcType;

/**
 * @author zhangyang
 * @description RpcHeader的工厂类
 * @date 2023/05/18 0:07
 **/
public class RpcHeaderFactory {
    public static RpcHeader getRequestHeader(String serializationType){
        RpcHeader header = new RpcHeader();
        header.setMagic(RpcConstants.MAGIC);
        header.setRequestId(IdFactory.getId());
        header.setMsgType((byte) RpcType.REQUEST.getType());
        // 没有赋值  msgLen？？
        header.setStatus((byte) 0x1);
        header.setSerializationType(serializationType);
        return header;
    }
}

package io.yang.rpc.protocol;

import io.yang.rpc.constants.RpcConstants;
import io.yang.rpc.protocol.header.RpcHeader;
import io.yang.rpc.protocol.header.RpcHeaderFactory;
import io.yang.rpc.protocol.request.RpcRequest;

import static org.junit.Assert.*;

/**
 * @author zhangyang
 * @description
 * @date 2023/05/18 0:26
 **/
public class RpcProtocolTest {

    public void getRpcProtocol(){
        RpcHeader requestHeader = RpcHeaderFactory.getRequestHeader(RpcConstants.SERIALIZATION_JDK);
        RpcRequest body = new RpcRequest();
        body.setOneway(false);
        body.setAsync(false);
        body.setClassName("io.binghe.rpc.demo.RpcProtocol");
        body.setMethodName("hello");
        body.setGroup("binghe");
        body.setParameters(new Object[]{"binghe"});
        body.setParameterTypes(new Class[]{String.class});
        body.setVersion("1.0.0");
        RpcProtocol<RpcRequest> protocol = new RpcProtocol<>();
        protocol.setHeader(requestHeader);
        protocol.setBody(body);
    }

}
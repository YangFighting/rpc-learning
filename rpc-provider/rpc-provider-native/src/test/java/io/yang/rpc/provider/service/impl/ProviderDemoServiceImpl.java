package io.yang.rpc.provider.service.impl;

import io.yang.rpc.annotation.RpcService;
import io.yang.rpc.provider.service.DemoService;

/**
 * @author zhangyang
 * @description
 * @date 2023/03/01 23:16
 **/
@RpcService(interfaceClass = DemoService.class, interfaceClassName = "io.yang.rpc.provider.service.DemoService")
public class ProviderDemoServiceImpl implements DemoService {
}

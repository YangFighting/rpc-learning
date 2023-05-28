package io.yang.rpc.test.provider.service.impl;

import io.yang.rpc.annotation.RpcService;
import io.yang.rpc.test.provider.service.DemoService;

/**
 * Description: DemoService实现类
 * date: 2023/5/28 20:15
 *
 * @author zhangyang
 */
@RpcService(interfaceClass = DemoService.class, interfaceClassName = "io.yang.rpc.test.scanner.service.DemoService", version = "1.0.0", group = "yang")
public class ProviderDemoServiceImpl implements DemoService {
}

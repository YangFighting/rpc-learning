package io.yang.rpc.common.scanner.provider;

import io.yang.rpc.annotation.RpcService;
import io.yang.rpc.common.scanner.service.DemoService;

/**
 * @author zhangyang
 * @description
 * @date 2023/02/28 22:22
 **/
@RpcService(interfaceClass = DemoService.class, interfaceClassName = "io.yang.rpc.scanner.service.DemoService", version = "1.0.0", group = "binghe")
public class ProviderDemoServiceImpl implements DemoService {
}

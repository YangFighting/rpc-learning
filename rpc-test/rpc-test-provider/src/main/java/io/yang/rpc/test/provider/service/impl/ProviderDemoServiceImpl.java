package io.yang.rpc.test.provider.service.impl;

import io.yang.rpc.annotation.RpcService;
import io.yang.rpc.test.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Description: DemoService实现类
 * date: 2023/5/28 20:15
 *
 * @author zhangyang
 */
@RpcService(interfaceClass = DemoService.class, interfaceClassName = "io.yang.rpc.test.scanner.service.DemoService", version = "1.0.0", group = "yang")
public class ProviderDemoServiceImpl implements DemoService {

    private final Logger logger = LoggerFactory.getLogger(ProviderDemoServiceImpl.class);


    @Override
    public String hello(String name) {
        logger.info("调用hello方法传入的参数为===>>>{}", name);
        return "hello " + name;
    }
}

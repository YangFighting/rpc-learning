package io.yang.rpc.common.scanner.consumer.service.impl;

import io.yang.rpc.annotation.RpcReference;
import io.yang.rpc.common.scanner.consumer.service.ConsumerBusinessService;
import io.yang.rpc.common.scanner.service.DemoService;

/**
 * @author zhangyang
 * @description 服务消费者业务逻辑实现类
 * @date 2023/02/28 22:25
 **/
public class ConsumerBusinessServiceImpl implements ConsumerBusinessService {

    @RpcReference(registryType = "zookeeper", registryAddress = "127.0.0.1:2181", version = "1.0.0", group = "binghe")
    private DemoService demoService;
}

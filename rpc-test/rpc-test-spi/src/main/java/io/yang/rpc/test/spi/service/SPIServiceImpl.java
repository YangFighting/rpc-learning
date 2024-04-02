package io.yang.rpc.test.spi.service;

import io.yang.rpc.spi.annotation.SPIClass;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 16:09
 */
@SPIClass
public class SPIServiceImpl implements SPIService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}

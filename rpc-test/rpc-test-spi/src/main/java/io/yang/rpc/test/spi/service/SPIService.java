package io.yang.rpc.test.spi.service;

import io.yang.rpc.spi.annotation.SPI;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 16:08
 */
@SPI("spiService")
public interface SPIService {
    String hello(String name);
}

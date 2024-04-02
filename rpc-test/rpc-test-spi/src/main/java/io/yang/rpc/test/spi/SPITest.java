package io.yang.rpc.test.spi;

import io.yang.rpc.spi.loader.ExtensionLoader;
import io.yang.rpc.test.spi.service.SPIService;
import org.junit.Test;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 16:07
 */
public class SPITest {

    @Test
    public void testSpiLoader() {
        SPIService spiService = ExtensionLoader.getExtension(SPIService.class, "spiService");

        String result = spiService.hello("yang");

        System.out.println("result: " + result);
    }
}

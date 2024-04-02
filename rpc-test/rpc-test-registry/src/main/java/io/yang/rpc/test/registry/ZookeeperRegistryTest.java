package io.yang.rpc.test.registry;

import io.yang.rpc.protocol.meta.ServiceMeta;
import io.yang.rpc.registry.api.RegistryService;
import io.yang.rpc.registry.api.config.RegistryConfig;
import io.yang.rpc.registry.zookeeper.ZookeeperRegistryService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-01 19:11
 */
public class ZookeeperRegistryTest {
    private RegistryService registryService;
    private ServiceMeta serviceMeta;

    @Before
    public void init() throws Exception {
        RegistryConfig registryConfig = new RegistryConfig("127.0.0.1:2181", "zookeeper");
        this.registryService = new ZookeeperRegistryService();
        this.registryService.init(registryConfig);
        this.serviceMeta = new ServiceMeta(ZookeeperRegistryTest.class.getName(), "1.0.0", "127.0.0.1", 8080, "yang");

    }

    @Test
    public void testRegister() throws Exception {
        this.registryService.register(serviceMeta);
    }


    @Test
    public void testUnRegister() throws Exception {
        this.registryService.unRegister(serviceMeta);
    }

    @Test
    public void testDiscovery() throws Exception {
        this.registryService.discovery(RegistryService.class.getName(), "yang".hashCode());
    }

    @Test
    public void testDestroy() throws IOException {
        this.registryService.destroy();
    }
}

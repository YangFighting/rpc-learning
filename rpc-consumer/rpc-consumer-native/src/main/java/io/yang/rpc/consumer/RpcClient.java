package io.yang.rpc.consumer;

import io.yang.rpc.common.exception.RegistryException;
import io.yang.rpc.consumer.common.RpcConsumer;
import io.yang.rpc.proxy.api.ProxyFactory;
import io.yang.rpc.proxy.api.async.IAsyncObjectProxy;
import io.yang.rpc.proxy.api.config.ProxyConfig;
import io.yang.rpc.proxy.api.object.ObjectProxy;
import io.yang.rpc.registry.api.RegistryService;
import io.yang.rpc.registry.api.config.RegistryConfig;
import io.yang.rpc.registry.zookeeper.ZookeeperRegistryService;
import io.yang.rpc.spi.loader.ExtensionLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-03-28 21:36
 */
public class RpcClient {

    private final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    /**
     * 注册服务
     */
    private RegistryService registryService;

    /**
     * 服务版本
     */
    private String serviceVersion;
    /**
     * 服务分组
     */
    private String serviceGroup;
    /**
     * 序列化类型
     */
    private String serializationType;
    /**
     * 超时时间
     */
    private long timeout;

    /**
     * 代理
     */
    private String proxy;


    /**
     * 是否异步调用
     */
    private boolean async;

    /**
     * 是否单向调用
     */
    private boolean oneway;

    public RpcClient(String registryAddress, String registryType, String registryLoadBalanceType, String proxy, String serviceVersion, String serviceGroup, String serializationType, long timeout, boolean async, boolean oneway) {
        this.serviceVersion = serviceVersion;
        this.proxy = proxy;
        this.timeout = timeout;
        this.serviceGroup = serviceGroup;
        this.serializationType = serializationType;
        this.async = async;
        this.oneway = oneway;
        this.registryService = this.getRegistryService(registryAddress, registryType,registryLoadBalanceType);
    }

    private RegistryService getRegistryService(String registryAddress, String registryType, String registryLoadBalanceType) {
        if (StringUtils.isEmpty(registryType)) {
            throw new IllegalArgumentException("registry type is null");
        }
        //TODO 后续SPI扩展
        RegistryService registryService = new ZookeeperRegistryService();
        try {
            registryService.init(new RegistryConfig(registryAddress, registryType, registryLoadBalanceType));
        } catch (Exception e) {
            logger.error("RpcClient init registry service throws exception: ", e);
            throw new RegistryException(e.getMessage(), e);
        }

        return registryService;
    }

    public <T> T create(Class<T> interfaceClass) {
        ProxyFactory proxyFactory = ExtensionLoader.getExtension(ProxyFactory.class, proxy);
        proxyFactory.init(new ProxyConfig<>(interfaceClass, serviceVersion, serviceGroup, serializationType, timeout, registryService, RpcConsumer.getInstance(), async, oneway));
        return proxyFactory.getProxy(interfaceClass);
    }

    public <T> IAsyncObjectProxy createAsync(Class<T> interfaceClass) {
        return new ObjectProxy<>(interfaceClass, serviceVersion, serviceGroup, serializationType, timeout, registryService, RpcConsumer.getInstance(), async, oneway);
    }

    public void shutdown() {
        RpcConsumer.getInstance().close();
    }
}

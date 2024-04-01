package io.yang.rpc.proxy.api.config;

import io.yang.rpc.proxy.api.consumer.Consumer;

import java.io.Serializable;

/**
 * @author zhangyang03
 * @Description 代理配置类
 * @create 2024-04-01 17:57
 */
public class ProxyConfig<T> implements Serializable {

    private static final long serialVersionUID = 7814454819805790315L;

    /**
     * 接口的Class实例
     */
    private Class<T> clazz;
    /**
     * 服务版本号
     */
    private String serviceVersion;
    /**
     * 服务分组
     */
    private String serviceGroup;
    /**
     * 超时时间
     */
    private long timeout;
    /**
     * 消费者接口
     */
    private Consumer consumer;

    /**
     * 序列化类型
     */
    private String serializationType;

    /**
     * 是否异步调用
     */
    private boolean async;

    /**
     * 是否单向调用
     */
    private boolean oneway;

    public ProxyConfig(Class<T> clazz, String serviceVersion, String serviceGroup, String serializationType, long timeout, Consumer consumer, boolean async, boolean oneway) {
        this.clazz = clazz;
        this.serviceVersion = serviceVersion;
        this.serviceGroup = serviceGroup;
        this.timeout = timeout;
        this.consumer = consumer;
        this.serializationType = serializationType;
        this.async = async;
        this.oneway = oneway;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public String getSerializationType() {
        return serializationType;
    }

    public void setSerializationType(String serializationType) {
        this.serializationType = serializationType;
    }

    public boolean getAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public boolean getOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }
}

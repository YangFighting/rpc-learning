package io.yang.rpc.registry.api.config;

import java.io.Serializable;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-01 18:33
 */
public class RegistryConfig implements Serializable {
    private static final long serialVersionUID = -5197191312556459146L;

    /**
     * 注册地址
     */
    private String registryAddr;

    /**
     * 注册类型
     */
    private String registryType;

    public RegistryConfig(String registryAddr, String registryType) {
        this.registryAddr = registryAddr;
        this.registryType = registryType;
    }

    public RegistryConfig() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRegistryAddr() {
        return registryAddr;
    }

    public void setRegistryAddr(String registryAddr) {
        this.registryAddr = registryAddr;
    }

    public String getRegistryType() {
        return registryType;
    }

    public void setRegistryType(String registryType) {
        this.registryType = registryType;
    }
}

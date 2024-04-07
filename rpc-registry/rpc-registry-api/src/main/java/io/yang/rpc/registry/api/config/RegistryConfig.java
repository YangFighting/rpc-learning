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

    /**
     * 负载均衡类型
     */
    private String registryLoadBalanceType;

    public RegistryConfig(String registryAddr, String registryType, String registryLoadBalanceType) {
        this.registryAddr = registryAddr;
        this.registryType = registryType;
        this.registryLoadBalanceType = registryLoadBalanceType;
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

    public String getRegistryLoadBalanceType() {
        return registryLoadBalanceType;
    }

    public void setRegistryLoadBalanceType(String registryLoadBalanceType) {
        this.registryLoadBalanceType = registryLoadBalanceType;
    }
}

package io.yang.rpc.loadbalancer.api;

import java.util.List;

/**
 * @author zhangyang03
 * @Description 负载均衡接口
 * @create 2024-04-02 14:21
 */
public interface ServiceLoadBalancer<T> {

    /**
     * @param servers 服务列表
     * @param hashCode Hash值
     * @return 以负载均衡的方式选取一个服务节点
     */
    T select(List<T> servers, int hashCode);
}

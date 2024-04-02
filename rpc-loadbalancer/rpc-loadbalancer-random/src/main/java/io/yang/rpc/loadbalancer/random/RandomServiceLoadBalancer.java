package io.yang.rpc.loadbalancer.random;

import io.yang.rpc.loadbalancer.api.ServiceLoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * @author zhangyang03
 * @Description 基于随机算法的负载均衡策略
 * @create 2024-04-02 14:23
 */
public class RandomServiceLoadBalancer<T> implements ServiceLoadBalancer<T> {

    private final Logger logger = LoggerFactory.getLogger(RandomServiceLoadBalancer.class);

    @Override
    public T select(List<T> servers, int hashCode) {
        logger.info("基于随机算法的负载均衡策略");
        if (servers == null || servers.isEmpty()) {
            return null;
        }
        int index = new Random().nextInt(servers.size());
        return servers.get(index);
    }
}

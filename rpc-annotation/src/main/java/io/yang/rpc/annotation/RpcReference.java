package io.yang.rpc.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangyang
 * @description bhrpc服务消费者
 * @date 2023/02/27 23:17
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Autowired
public @interface RpcReference {
    /**
     * @return 版本号
     */
    String version() default "1.0.0";

    /**
     * @return 注册中心类型, 目前的类型包含：zookeeper、nacos、etcd、consul
     */
    String registryType() default "zookeeper";

    /**
     * @return 注册地址
     */
    String registryAddress() default "127.0.0.1:2181";

    /**
     * @return 负载均衡类型，默认基于ZK的一致性Hash
     */
    String loadBalanceType() default "zkconsistenthash";

    /**
     * @return 序列化类型，目前的类型包含：protostuff、kryo、json、jdk、hessian2、fst
     */
    String serializationType() default "protostuff";

    /**
     * @return 超时时间，默认5s
     */
    long timeout() default 5000;

    /**
     * @return 是否异步执行
     */
    boolean async() default false;

    /**
     * @return 是否单向调用
     */
    boolean oneway() default false;

    /**
     * @return 代理的类型，jdk：jdk代理， javassist: javassist代理, cglib: cglib代理
     */
    String proxy() default "jdk";

    /**
     * @return 服务分组，默认为空
     */
    String group() default "";

}

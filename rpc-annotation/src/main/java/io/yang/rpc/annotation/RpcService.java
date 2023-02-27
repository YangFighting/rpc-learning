package io.yang.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description bhrpc服务提供者注解
 * @author zhangyang
 * @date 2023/02/27 23:18
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
    /**
     * @return 接口的Class
     */
    Class<?> interfaceClass() default void.class;

    /**
     * @return 接口的ClassName
     */
    String interfaceClassName() default "";

    /**
     * @return 版本号
     */
    String version() default "1.0.0";

    /**
     * @return 服务分组，默认为空
     */
    String group() default "";
}

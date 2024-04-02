package io.yang.rpc.spi.annotation;

import java.lang.annotation.*;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 15:51
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {
    /**
     * 默认的实现方式
     */
    String value() default "";
}

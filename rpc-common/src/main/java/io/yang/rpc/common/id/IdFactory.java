package io.yang.rpc.common.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangyang
 * @description
 * @date 2023/05/18 0:18
 **/
public class IdFactory {
    private final static AtomicLong REQUEST_ID_GEN = new AtomicLong(0L);
    public static Long getId(){
        return REQUEST_ID_GEN.incrementAndGet();
    }
}

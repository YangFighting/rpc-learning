package io.yang.rpc.common.scanner.reference;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangyang
 * @description
 * @date 2023/02/28 22:11
 **/
public class RpcReferenceContext {
    private static volatile Map<String, Object> instance;

    static {
        instance = new ConcurrentHashMap<>();
    }

    public static void put(String key, Object value) {
        instance.put(key, value);
    }

    public static Object get(String key) {
        return instance.get(key);
    }
    public static Object remove(String key){
        return instance.remove(key);
    }
}

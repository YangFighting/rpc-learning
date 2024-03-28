package io.yang.rpc.common.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyang03
 * @Description 服务消费者线程池
 * @create 2024-03-28 18:56
 */
public class ClientThreadPool {
    private static final ThreadPoolExecutor threadPoolExecutor;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
    }

    public static void submit(Runnable task) {
        threadPoolExecutor.submit(task);
    }

    public static void shutdown() {
        threadPoolExecutor.shutdown();
    }
}

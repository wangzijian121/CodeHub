package 多线程.java并发编程.java共享模型_juc工具.线程池.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用Executors创建带有【缓冲】的线程池
 *
 * @author zijian Wang
 */

public class NewCacheThreadPoolTest {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    public static void main(String[] args) {
        //不建议使用Executors 创建线程池❌
        ExecutorService executorService = Executors.newCachedThreadPool((runnable) -> {
            Thread thread = new Thread(runnable);
            // 设置线程的一些属性，比如名称、优先级等等
            thread.setName("带有缓冲的线程池 - " + poolNumber.getAndIncrement());
            return thread;
        });
        executorService.execute(new Task());
        executorService.execute(new Task());
        executorService.shutdown();
    }
}


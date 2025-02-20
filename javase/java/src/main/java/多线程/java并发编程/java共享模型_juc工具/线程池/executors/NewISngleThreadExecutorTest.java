package 多线程.java并发编程.java共享模型_juc工具.线程池.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 该执行器使用在无界队列中运行的单个工作线程 <br/>
 * <p>
 * 线程执行失败后会创建新的线程，总数为1.<br/>
 * <p>
 * 返回的是一个FinalizableDelegatedExecutorService 无法重新设置核心数-代理模式。
 *
 * @author zijian Wang
 */

public class NewISngleThreadExecutorTest {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    public static void main(String[] args) {
        //不建议使用Executors 创建线程池❌
        ExecutorService executorService = Executors.newSingleThreadExecutor((runnable) -> {
            Thread thread = new Thread(runnable);
            // 设置线程的一些属性，比如名称、优先级等等
            thread.setName("固定长度的线程池 - " + poolNumber.getAndIncrement());
            return thread;
        });
        executorService.execute(new Task());
        executorService.execute(new Task());
        executorService.shutdown();
    }
}


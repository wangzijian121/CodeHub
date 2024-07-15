package 多线程.java并发编程.java共享模型_juc工具.线程池.thread_pool_executor;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 线程池
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.ThreadPoolExecutorTest")
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {


        final AtomicInteger mThreadNum = new AtomicInteger(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 50L,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10),
                r -> {
                    final Thread thread = new Thread(r);
                    thread.setName("wangzijian-" + mThreadNum.getAndIncrement());
                    return thread;
                },
                new ThreadPoolExecutor.DiscardPolicy());

        List<Callable<String>> tasks = new ArrayList<>();
        IntStream.range(0, 10).forEach(x -> {
            Task task = new Task();
//            threadPoolExecutor.submit(task);//通过submit 获取返回结果
            tasks.add(task);
        });

        try {
            List<Future<String>> futureList = threadPoolExecutor.invokeAll(tasks);
            log.debug("获取结果");
            for (Future<String> future : futureList) {
                log.debug(future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        threadPoolExecutor.shutdown();
    }

}

@Slf4j(topic = "c.Task")
class Task implements Callable {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     */
    @Override
    public Object call() {
        log.info(Thread.currentThread().getName() + " 执行中...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Thread.currentThread().getName() + " OK";
    }
}

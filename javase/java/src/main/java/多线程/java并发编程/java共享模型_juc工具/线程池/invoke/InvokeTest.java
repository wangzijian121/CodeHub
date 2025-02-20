package 多线程.java并发编程.java共享模型_juc工具.线程池.invoke;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * 使用submit 获取线程的返回接口
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.InvokeTest")
public class InvokeTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //不建议使用Executors 创建线程池❌
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).mapToObj(x -> (Callable<Integer>) () -> {
            System.out.println("线程" + x);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return x;
        }).collect(Collectors.toList());

        List<Future<Integer>> futureList = executorService.invokeAll(callableList);
        for (Future<Integer> integerFuture : futureList) {
            log.info(String.valueOf(integerFuture.get()));
        }
        executorService.shutdown();
    }
}

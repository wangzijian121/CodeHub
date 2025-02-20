package 多线程.java并发编程.java共享模型_juc工具.线程池.submit;

import java.util.concurrent.*;

/**
 * 使用submit 获取线程的返回接口
 *
 * @author zijian Wang
 */
public class SubmitTest {
    public static void main(String[] args) {
        //不建议使用Executors 创建线程池❌
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> submit = executorService.submit(() -> {

            TimeUnit.SECONDS.sleep(1);
            return "ok!";
        });
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

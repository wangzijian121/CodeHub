package 多线程.java并发编程.java线程基础.a_创建线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用 FutureTask 创建线程可以获取返回结果
 *
 * @author zijian wang
 */
@Slf4j(topic = "c.CreateThreadUsedFutureTask")
public class CreateThreadUsedFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("FutureTask创建线程,正在执行...");
                Thread.sleep(1000);
                return "wangzijian";
            }
        });
        Thread thread = new Thread(task, "线程01");
        thread.start();
        //get 为阻塞的
        log.debug("task的返回结果:" + task.get());
        log.debug("main线程");
    }
}


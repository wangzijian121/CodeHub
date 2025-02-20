package 多线程.java并发编程.java线程基础.a_创建线程;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

/**
 * @author zijian wang
 */
@Slf4j(topic = "c.CreateThreadUsedLambda")
public class CreateThreadUsedLambda {
    public static void main(String[] args) throws InterruptedException {
        //使用lambda 创建Runnable 类型的任务
        Runnable runnable = () -> log.debug("lambda create  thread used Runnable");

        //使用lambda 创建FutureTask 类型的任务
        FutureTask futureTask = new FutureTask(() -> {
            log.debug("lambda create thread used FutureTask ");
            return "success";
        });

        Thread thread1 = new Thread(runnable, "线程01");
        Thread thread2 = new Thread(futureTask, "线程02");
        thread1.start();
        thread2.start();
        Thread.sleep(99999);
        log.debug("lambda create thread over！");
    }
}

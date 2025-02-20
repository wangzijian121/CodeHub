package 多线程.java并发编程.java线程基础.b_thread的方法;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zijian Wang
 */

@Slf4j(topic = "c.Interrupt")
public class Interrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("run...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("run over...");
        }, "thread-01");
        Thread t2 = new Thread(() -> {
            log.info("run...");
            for(;;){

            }
        }, "thread-01");

        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        //1秒后执行打断
        t1.interrupt();
        t2.interrupt();
        log.error("【wait、sleep、join、】t1的打断状态：" + t1.isInterrupted());
        log.error("【正常方法】t2的打断状态：" + t2.isInterrupted());
        t1.interrupt();
        log.error("【wait、sleep、join、】t1再次打断状态：" + t1.isInterrupted());
    }
}

package 多线程.java并发编程.java线程基础.b_thread的方法;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zijian Wang
 */
@Slf4j(topic = "c.SleepAndYield")
public class SleepAndYield {

    public static void main(String[] args) {
/*        new Thread(() -> {
            log.info("run...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("run over...");
        }, "thread-01").start();*/

        new Thread(() -> {
            log.info("run...");
           Thread.yield();
            log.info("run over...");
        }, "thread-02").start();

    }
}

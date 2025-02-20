package 多线程.java并发编程.java线程基础.c_java中线程的六种状态;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author zijian Wang
 */
@Slf4j(topic = "c.ThreadStatus")
public class ThreadStatus {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "thread01");
        //new
        log.info("t1未start()前的状态：" + t1.getState());
        t1.start();

        //runnable
        log.info("t1 start()后状态：" + t1.getState());
        TimeUnit.SECONDS.sleep(1);

        //time_waiting
        log.info("t1 sleep 时的状态：" + t1.getState());

        Thread t2 = new Thread(() -> {
            while (true) {

            }
        });

        //time_waiting
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();
        t3.start();
        log.info("join后的状态：" + t3.getState());

//      TimeUnit.SECONDS.sleep(1);
//      log.info("t1 join后的状态：" + t1.getState());
    }
}

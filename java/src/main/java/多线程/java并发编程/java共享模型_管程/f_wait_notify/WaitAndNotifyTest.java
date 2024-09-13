package 多线程.java并发编程.java共享模型_管程.f_wait_notify;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * wait & notify
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.数据类型.Demo")
public class WaitAndNotifyTest {
    final static WaitAndNotifyTest WAIT_AND_NOTIFY_TEST = new WaitAndNotifyTest();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("线程1....");
            synchronized (WAIT_AND_NOTIFY_TEST) {
                try {
                    WAIT_AND_NOTIFY_TEST.wait();//进入到waitSet
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("t1被唤醒了！");
        });

        Thread t2 = new Thread(() -> {

            log.info("线程2....");
            synchronized (WAIT_AND_NOTIFY_TEST) {
                try {
                    WAIT_AND_NOTIFY_TEST.wait();//进入到waitSet
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("t2被唤醒了！");
        });

        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        synchronized (WAIT_AND_NOTIFY_TEST) {
//            demo.notify();//唤醒一个
            WAIT_AND_NOTIFY_TEST.notifyAll();//唤醒所有
        }
    }
}

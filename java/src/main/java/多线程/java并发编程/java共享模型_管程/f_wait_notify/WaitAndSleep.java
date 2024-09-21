package 多线程.java并发编程.java共享模型_管程.f_wait_notify;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zijian Wang
 */
@Slf4j(topic = "c.WaitAndSleepTest")
public class WaitAndSleep {
    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (obj) {
                log.info("线程t1获取锁！");
                try {
//                    TimeUnit.SECONDS.sleep(5);//5秒会一直阻塞，不释放锁
                    obj.wait();//执行wait会进入到waitSet 并立刻释放掉锁

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Thread.sleep(1000);
        synchronized (obj) {
            log.info("主线程获取锁！");
            obj.notifyAll();
        }
    }
}

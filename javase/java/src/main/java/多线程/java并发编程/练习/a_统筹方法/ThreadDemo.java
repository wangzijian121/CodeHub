package 多线程.java并发编程.练习.a_统筹方法;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 洗水壶 1分钟
 * 烧开水 5分钟
 * 洗茶壶、洗茶杯、拿茶叶 4分钟。
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.ThreadDemo")
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        Thread thread01 = new Thread(() -> {
            try {
                log.info("烧开水....");
                TimeUnit.SECONDS.sleep(10);
                log.info("洗水壶....");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "服务员1");

        Thread thread02 = new Thread(() -> {
            try {
                log.info("洗茶壶....");
                TimeUnit.SECONDS.sleep(1);
                log.info("洗茶杯....");
                TimeUnit.SECONDS.sleep(2);
                log.info("拿茶叶....");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "服务员2");

        thread01.start();
        thread02.start();
        thread01.join();
        thread02.join();
        long endTime = System.currentTimeMillis();
        System.out.println("完成,开始泡茶！" + "花费：" + (endTime - startTime) / 1000+"秒");

    }
}

package 多线程.java并发编程.java共享模型_juc工具.locks;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 线程执行状态的指令
 *
 * @author zijian Wang
 */
public class CountdownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        IntStream.range(0, 10).forEach(x -> new Thread(() -> {
            try {
                System.out.println("线程" + x + 1);
            } finally {
                countDownLatch.countDown();
            }
        }).start());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("10个线程全部执行完毕！");
        }
        System.out.println("over!");
    }
}

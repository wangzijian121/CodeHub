package 多线程.java并发编程.java共享模型_juc工具.locks;

import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class ReentrantLockTest {
    final static ReentrantLock reentrantLock = new ReentrantLock();
    static CountDownLatch countDownLatch = new CountDownLatch(10);
    private static int sum;


    @GuardedBy("reentrantLock")
    public static void main(String[] args) {

        IntStream.range(0, 10).forEach(x -> new Thread(() -> {
            reentrantLock.lock();
            try {
                for (int i = 0; i < 1000L; i++) {
                    sum++;
                }
                System.out.println("执行....");
            } finally {
                reentrantLock.unlock();
                countDownLatch.countDown();
            }
        }, "线程" + x).start());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sum);

    }
}


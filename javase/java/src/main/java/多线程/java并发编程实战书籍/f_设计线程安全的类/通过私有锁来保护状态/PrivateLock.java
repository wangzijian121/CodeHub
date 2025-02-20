package 多线程.java并发编程实战书籍.f_设计线程安全的类.通过私有锁来保护状态;

import java.util.stream.IntStream;

/**
 * 使用私有锁来保证线程安全性
 *
 * @author Administrator
 */
public class PrivateLock {
    private final Object lock = new Object();
    private long count = 0;

    public void add() {
        synchronized (lock) {
            this.count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            PrivateLock privateLock = new PrivateLock();
            Thread t1 = new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> {
                    privateLock.add();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

            });
            Thread t2 = new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> {
                    privateLock.add();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(privateLock.count);
        }
    }
}

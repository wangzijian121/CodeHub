package 多线程.java并发编程实战书籍.f_设计线程安全的类.线程安全的类;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.stream.IntStream;

/**
 * 使用Synchronized 保证类的安全性
 * @author zijian Wang
 */
@ThreadSafe
public class CounterUsedSynchronized {
    @GuardedBy("this")
    private long value = 0;


    public Long getValue() {
        return value;
    }

    /**
     * 使用synchronized 代码块
     */
 /*   public void increment() {
        synchronized (this) {
            this.value++;
        }
    }*/

    /**
     * 使用synchronized 修饰
     */
    public void increment() {
        this.value++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            CounterUsedSynchronized counterUsedAtomic = new CounterUsedSynchronized();
            Thread t1 = new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> {
                    counterUsedAtomic.increment();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
            Thread t2 = new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> {
                    counterUsedAtomic.increment();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(counterUsedAtomic.getValue());
        }
    }
}

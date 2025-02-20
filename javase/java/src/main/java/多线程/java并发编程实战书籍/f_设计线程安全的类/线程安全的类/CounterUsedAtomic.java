package 多线程.java并发编程实战书籍.f_设计线程安全的类.线程安全的类;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * 使用原子变量 保证类的安全性
 *
 * @author zijian Wang
 */
public class CounterUsedAtomic {
    private AtomicLong value = new AtomicLong(0);

    public Long getValue() {
        return value.get();
    }

    public void increment() {
        this.value.addAndGet(1);
    }

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            CounterUsedAtomic counterUsedAtomic = new CounterUsedAtomic();
            Thread t1 = new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> {
                    counterUsedAtomic.increment();
                    try {
                        Thread.sleep(1); // 增加一点延迟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
            Thread t2 = new Thread(() -> {
                IntStream.range(0, 1000).forEach(x -> {
                    counterUsedAtomic.increment();
                    try {
                        Thread.sleep(1); // 增加一点延迟
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

package 多线程.java并发编程.java共享模型_juc工具.locks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * 固定线程量执行同步器
 *
 * @author zijian Wang
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(30);
        IntStream.range(0, 10).forEach(x -> new Thread(() -> {
            try {
                cyclicBarrier.await();
                System.out.println(x+"执行");//小于30不会执行！
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }, "线程" + x).start());
    }
}

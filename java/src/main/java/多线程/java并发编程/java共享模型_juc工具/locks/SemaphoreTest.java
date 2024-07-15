package 多线程.java并发编程.java共享模型_juc工具.locks;

import utils.SleepUtils;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * 线程限制器
 *
 * @author zijian Wang
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        System.out.println("共有：" + semaphore.availablePermits() + "个许可证！");
        IntStream.range(0, 10).forEach(x -> new Thread(() -> {
            try {
                if (semaphore.availablePermits() > 0) {
                    semaphore.acquire();
                    System.out.println("线程" + x);
                    System.exit(0);
                } else {
                    System.out.println("容量没了！");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SleepUtils.sleep(100);
        }, "线程" + x).start());
    }
}

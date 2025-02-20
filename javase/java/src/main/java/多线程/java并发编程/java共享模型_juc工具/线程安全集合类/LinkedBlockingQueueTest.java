package 多线程.java并发编程.java共享模型_juc工具.线程安全集合类;

import utils.SleepUtils;
import utils.StringUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        int threadCount = 5;
        final AtomicInteger COUNT = new AtomicInteger(1);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        CountDownLatch count = new CountDownLatch(threadCount);
        ThreadPoolExecutor poll = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), (x) -> {
            Thread thread = new Thread(x);
            thread.setName("linkedBlockingQueueTest-" + COUNT.getAndIncrement());
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());
//        监视者
        //生产者
        IntStream.range(0, threadCount).forEach(x -> poll.submit(() -> {
            try {
                for (; ; SleepUtils.sleepMs(2)) {
                    String randomStr = StringUtils.getRandomStr(10);
//                    System.out.println(Thread.currentThread().getName() + "生成：" + randomStr);
                    linkedBlockingQueue.add(randomStr);
                    System.out.println("队列长度：" + linkedBlockingQueue.size());
                }
            } finally {
                count.countDown();
            }
        }));
        //消费者
        System.out.println("创建消费者！");
        IntStream.range(0, 3).forEach(x -> poll.submit(() -> {
            for (Object data; ; ) {
                data = linkedBlockingQueue.poll(1,TimeUnit.MILLISECONDS);
                if (data != null) {
                    System.out.println(Thread.currentThread().getName() + "获取到了：" + data);
                }
            }
        }));

        try {
            count.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(concurrentLinkedQueue.size());
        poll.shutdown();
    }
}

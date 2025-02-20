package 多线程.java并发编程.java共享模型_无锁.累加器LongAdder;

import lombok.extern.slf4j.Slf4j;
import 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.impl.MyLongAdder;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 累加器LongAdder 测试类
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.MyLongAdder")
public class MyLongAdderTestMain {

    public static void testLoad(MyAdder myAdder, int threadCount, int addCount) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        /**
         * 计算时间间隔
         */
//        LocalTime start = LocalTime.parse(LocalTime.now().toString());
        long startTime = System.currentTimeMillis();
        IntStream.range(0, threadCount).forEach(x -> {
            String name = "线程" + x;
            Thread thread = new Thread(() -> {
                try {
                    IntStream.range(0, addCount).forEach(y -> myAdder.add(1));
                } catch (Exception e) {
                } finally {
                    countDownLatch.countDown();
                }
            }, name);
            thread.start();
        });
        // 等待所有线程执行完成
        countDownLatch.await();
//        LocalTime end = LocalTime.parse(LocalTime.now().toString());
//        Duration duration = Duration.between(start, end);
        long endTime = System.currentTimeMillis();
//        System.out.println("🚀" + myAdder.getName() + "线程数" + threadCount + ", 累加:" + addCount + "次。用时:" + duration.toMillis() + "ms");
        System.out.println("🚀" + myAdder.getName() + "线程数" + threadCount + ", 累加:" + addCount + "次。用时:" + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 2;

        /**
         *
         */
        testLoad(new MyLongAdder(), threadCount, 10000);
        testLoad(new MyLongAdder(), threadCount, 100000);
        testLoad(new MyLongAdder(), threadCount, 1000000);
        testLoad(new MyLongAdder(), threadCount, 10000000);
        testLoad(new MyLongAdder(), threadCount, 100000000);
        testLoad(new MyLongAdder(), threadCount, 1000000000);
//        testLoad(new MyLongSerial(), 1, 10000);
//        testLoad(new MyLongSerial(), 1, 100000);
//        testLoad(new MyLongSerial(), 1, 1000000);
//        testLoad(new MyLongSerial(), 1, 10000000);
//        testLoad(new MyLongSerial(), 1, 100000000);
//        testLoad(new MyLongSerial(), 1, 1000000000);
//        testLoad(new MyAtomicLong(), threadCount, 10000);
//        testLoad(new MyAtomicLong(), threadCount, 100000);
//        testLoad(new MyAtomicLong(), threadCount, 1000000);
//        testLoad(new MyAtomicLong(), threadCount, 10000000);
//        testLoad(new MyAtomicLong(), threadCount, 100000000);
//        testLoad(new MyAtomicLong(), threadCount, 1000000000);
    }
}

package 多线程.java并发编程.java共享模型_juc工具.locks;

import utils.SleepUtils;

import java.time.LocalTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

import static 多线程.java并发编程.java共享模型_juc工具.locks.StampedLockTest.lock;

/**
 * 使用stamped戳的标记锁
 * 1.乐观读模式
 * 2.读锁模式
 * 3.写锁模式
 *
 * @author zijian Wang
 */
public class StampedLockTest {
    final static StampedLock lock = new StampedLock();
    static int num;

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger integer = new AtomicInteger(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 1000, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                r -> {
                    final Thread thread = new Thread(r);
                    thread.setName("wangzijian-" + integer.getAndIncrement());
                    return thread;
                }, new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0, 3).forEach(x -> threadPoolExecutor.execute(new WriteTaskUseStamped()));
//        SleepUtils.sleep(10);
        IntStream.range(0, 100).forEach(x -> threadPoolExecutor.execute(new ReadTaskUseStamped()));
        threadPoolExecutor.shutdown();
    }
}

class ReadTaskUseStamped implements Runnable {

    public int printTask() {
        long stamped = lock.tryOptimisticRead();
        if (!lock.validate(stamped)) {
            long stampedReadLock = lock.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取读锁！" + LocalTime.now());
            } finally {
                lock.unlock(stampedReadLock);
            }
        } else {
            System.out.println("无锁获取值！"+ " 时间 :"+LocalTime.now());
        }
        return StampedLockTest.num;
    }

    @Override
    public void run() {
        System.out.println("结果：" + printTask() + " 时间 :"+LocalTime.now());
    }
}

class WriteTaskUseStamped implements Runnable {

    public void addTask() {
        System.out.println(Thread.currentThread().getName() + "获取写锁！" + LocalTime.now());
        long Stamped = lock.writeLock();
        try {
            for (int i = 0; i < 100; i++) {
                StampedLockTest.num++;
            }
            SleepUtils.sleep(1);
        } finally {
            lock.unlock(Stamped);
        }
    }

    @Override
    public void run() {
        addTask();
    }
}
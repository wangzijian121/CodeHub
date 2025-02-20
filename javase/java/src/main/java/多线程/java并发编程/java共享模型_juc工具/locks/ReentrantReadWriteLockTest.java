package 多线程.java并发编程.java共享模型_juc工具.locks;

import utils.SleepUtils;

import java.time.LocalTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

import static 多线程.java并发编程.java共享模型_juc工具.locks.ReentrantReadWriteLockTest.lock;

/**
 * @author zijian Wang
 */
public class ReentrantReadWriteLockTest {
    final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static int num;

    public static void main(String[] args) throws InterruptedException {
        LocalTime now = LocalTime.now();
        final AtomicInteger integer = new AtomicInteger(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 1000, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                r -> {
                    final Thread thread = new Thread(r);
                    thread.setName("wangzijian-" + integer.getAndIncrement());
                    return thread;
                }, new ThreadPoolExecutor.DiscardOldestPolicy());
        IntStream.range(0, 3).forEach(x -> threadPoolExecutor.execute(new WriteTaskUseWriteLock()));
        IntStream.range(0, 500).forEach(x -> threadPoolExecutor.execute(new ReadTaskUseReadLock()));
        threadPoolExecutor.shutdown();
    }
}


class ReadTaskUseReadLock implements Runnable {

    final static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public int printTask() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取读锁！" + LocalTime.now());
        } finally {
            readLock.unlock();
        }
        return StampedLockTest.num;
    }

    @Override
    public void run() {
        System.out.println("结果：" + printTask() + " 时间 :" + LocalTime.now());
    }
}

class WriteTaskUseWriteLock implements Runnable {
    final static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void addTask() {
        System.out.println(Thread.currentThread().getName() + "获取写锁！" + LocalTime.now());
        writeLock.lock();
        try {
            for (int i = 0; i < 100; i++) {
                StampedLockTest.num++;
            }
            SleepUtils.sleep(1);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void run() {
        addTask();
    }
}




package 多线程.java并发编程.java共享模型_管程.i_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class ReentrantLockTest {


    private final ReentrantLock lock = new ReentrantLock();
    static int count = 0;

    public void add() {
        lock.lock();

        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("等待此锁的估计线程数:"+lock.getQueueLength());
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName());
            IntStream.range(0, 10).forEach(x -> count++);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread t1 = new Thread(() -> reentrantLockTest.add(), "线程1");
        Thread t2 = new Thread(() -> reentrantLockTest.add(), "线程2");
        t1.start();
        t2.start();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            ReentrantLock reentrantLock = reentrantLockTest.lock;
            boolean locked = reentrantLock.isLocked();
            System.out.println("是否被锁定:" + locked);
            if (locked == false) {
                System.out.println("尝试获取锁，是否获取到..." + reentrantLock.tryLock());
                System.out.println("尝试获取锁后判断，是否被锁定:" + reentrantLock.isLocked());
                reentrantLock.unlock();
                break;
            }
        }
    }
}

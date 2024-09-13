package 多线程.java并发编程.java共享模型_管程.i_reentrantlock.可打断性;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zijian Wang
 */
@Slf4j(topic = "c.数据类型.Demo")
public class LockInterruptibly {

    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(() -> {

            try {
                System.out.println("等待线程中...");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("等待的过程中被打断");
                throw new RuntimeException(e);
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        });
        lock.lock();
        try {
            System.out.println("占用上锁！");
        } finally {
            lock.unlock();
        }
        thread.start();

        try {
            thread.interrupt();
            System.out.println("打断锁！");
        } finally {
            lock.unlock();
        }

    }
}

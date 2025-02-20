package 多线程.java并发编程.java共享模型_管程.i_reentrantlock.可重入性;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zijian Wang
 */
public class Reentrant extends ReentrantLock {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        lock.lock();
        try {
            System.out.println("m1");
            m2();
        } finally {
            lock.unlock();
        }
    }

    public static void m2() {
        lock.lock();
        try {
            System.out.println("m2");
            m3();
        } finally {
            lock.unlock();
        }
    }

    public static void m3() {
        lock.lock();
        try {
            System.out.println("m3");
            System.out.println(lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}

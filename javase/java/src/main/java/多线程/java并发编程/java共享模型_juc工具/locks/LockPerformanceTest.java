package 多线程.java并发编程.java共享模型_juc工具.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class LockPerformanceTest {
    private static final int NUM_THREADS = 100;
    private static final int NUM_READERS = 90;
    private static final int NUM_WRITERS = 10;
    private static final int NUM_ITERATIONS = 1000000;

    private static int sharedResource = 0;
    private static ReentrantReadWriteLock rrLock = new ReentrantReadWriteLock();
    private static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) {
        System.out.println("Testing ReentrantReadWriteLock...");
        testReadWriteLock();

        System.out.println("\nTesting StampedLock...");
        testStampedLock();
    }

    private static void testReadWriteLock() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_READERS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    rrLock.readLock().lock();
                    try {
                        int value = sharedResource;
                        // Reading shared resource
                    } finally {
                        rrLock.readLock().unlock();
                    }
                }
            }).start();
        }

        for (int i = 0; i < NUM_WRITERS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    rrLock.writeLock().lock();
                    try {
                        sharedResource++;
                        // Writing to shared resource
                    } finally {
                        rrLock.writeLock().unlock();
                    }
                }
            }).start();
        }

        waitForThreadsToFinish();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time for ReentrantReadWriteLock: " + (endTime - startTime) + " ms");
    }

    private static void testStampedLock() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_READERS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    long stamp = stampedLock.tryOptimisticRead();
                    int value = sharedResource;
                    if (!stampedLock.validate(stamp)) {
                        stamp = stampedLock.readLock();
                        try {
                            value = sharedResource;
                            // Reading shared resource
                        } finally {
                            stampedLock.unlockRead(stamp);
                        }
                    }
                }
            }).start();
        }

        for (int i = 0; i < NUM_WRITERS; i++) {
            new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    long stamp = stampedLock.writeLock();
                    try {
                        sharedResource++;
                        // Writing to shared resource
                    } finally {
                        stampedLock.unlockWrite(stamp);
                    }
                }
            }).start();
        }

        waitForThreadsToFinish();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time for StampedLock: " + (endTime - startTime) + " ms");
    }

    private static void waitForThreadsToFinish() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

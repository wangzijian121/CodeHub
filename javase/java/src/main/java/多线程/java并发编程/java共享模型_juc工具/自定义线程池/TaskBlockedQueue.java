package 多线程.java并发编程.java共享模型_juc工具.自定义线程池;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 任务阻塞队列（放入任务、消费执行任务）
 *
 * @param <T>
 * @author zijian Wang
 */
@Slf4j(topic = "c.TaskBlockedQueue")
public class TaskBlockedQueue<T> {

    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionForGet = lock.newCondition();
    private Condition conditionForPut = lock.newCondition();
    private Deque<T> deque = new ArrayDeque();
    private DenyPolicy<T> denyPolicy;
    private int maxSize;

    public TaskBlockedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public DenyPolicy getDenyPolicy() {
        return denyPolicy;
    }

    public void setDenyPolicy(DenyPolicy denyPolicy) {
        this.denyPolicy = denyPolicy;
    }

    /**
     * 从阻塞队列中消费task
     *
     * @return
     */
    public T get(long timeout) {
        T t = null;
        long nanos = TimeUnit.MILLISECONDS.toNanos(timeout);
        lock.lock();
        try {
            while (deque.isEmpty()) {
                long timeRemaining = conditionForGet.awaitNanos(nanos);
                if (timeRemaining <= 0L) {
                    log.error("get时间超时！");
                    break;
                }
            }
            t = deque.pollFirst();
            conditionForPut.signal();
        } catch (Exception e) {
            log.error("消费task失败！");
        } finally {
            lock.unlock();
        }
        return t;
    }

    /**
     * 带超时的put
     *
     * @param task
     */
    public void putWithTimeout(T task, long timeout) {
        long nanos = TimeUnit.MILLISECONDS.toNanos(timeout);
        lock.lock();
        try {
            while (isFull()) {
                long timeRemaining = conditionForPut.awaitNanos(nanos);
                if (timeRemaining <= 0L) {
                    log.error("拒绝策略-超时❌");
                    break;
                }
            }
            if (!isFull() && deque.size() <= maxSize) {
                log.info("put到队列");
                if (deque.add(task)) {
                    conditionForGet.signal();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 异常的put
     *
     * @param task
     */
    public void putWithException(T task) {

        lock.lock();
        try {
            if (isFull()) {
                throw new RuntimeException("拒绝策略-异常！");
            }
            if (!isFull() && deque.size() <= maxSize) {
                log.info("put到队列");
                if (deque.add(task)) {
                    conditionForGet.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * put并一直等待
     *
     * @param task
     */
    public void putAndWait(T task) {
        lock.lock();
        try {
            while (isFull()) {
                conditionForPut.await();
            }
            if (deque.size() <= maxSize) {
                log.info("put到队列");
                deque.add(task);
                conditionForGet.signal();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * put 如果满了后创建新线程
     *
     * @param task
     */
    public void putUsedNewThread(T task) {
        lock.lock();
        try {
            if (isFull()) {
                System.out.println("使用新线程！");
                new Thread(() -> ((Runnable) task).run()).start();
            } else if (deque.size() <= maxSize) {
                log.info("put到队列");
                deque.add(task);
                conditionForGet.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isFull() {
        return deque.size() >= maxSize ? true : false;
    }
}

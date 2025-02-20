package 多线程.java并发编程.java共享模型_juc工具.自定义线程池;

/**
 * 针对 【队列】 和 【待放入任务】 的 拒绝策略
 *
 * @author zijian Wang
 */
@FunctionalInterface
public interface DenyPolicy<T> {
    /**
     * 加载
     * @param taskBlockedQueue
     * @param task
     */
    void load(TaskBlockedQueue<T> taskBlockedQueue, T task);
}

package 多线程.java并发编程.java共享模型_juc工具.自定义线程池.impl;

import 多线程.java并发编程.java共享模型_juc工具.自定义线程池.DenyPolicy;
import 多线程.java并发编程.java共享模型_juc工具.自定义线程池.TaskBlockedQueue;

/**
 * 一直等待策略
 * @author zijian Wang
 */
public class WaitPolicy<T> implements DenyPolicy<T> {

    @Override
    public void load(TaskBlockedQueue<T> taskBlockedQueue, T task) {
        taskBlockedQueue.putAndWait(task);
    }
}

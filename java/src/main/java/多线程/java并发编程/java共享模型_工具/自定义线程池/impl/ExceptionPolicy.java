package 多线程.java并发编程.java共享模型_工具.自定义线程池.impl;

import 多线程.java并发编程.java共享模型_工具.自定义线程池.DenyPolicy;
import 多线程.java并发编程.java共享模型_工具.自定义线程池.TaskBlockedQueue;

/**
 * 报出异常
 * @author zijian Wang
 */
public class ExceptionPolicy<T> implements DenyPolicy<T> {

    @Override
    public void load(TaskBlockedQueue<T> taskBlockedQueue, T task) {
        taskBlockedQueue.putWithException(task);
    }
}

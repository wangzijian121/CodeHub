package 多线程.java并发编程.java共享模型_juc工具.自定义线程池;

import org.junit.Assert;
import org.junit.Test;
import 多线程.java并发编程.java共享模型_juc工具.自定义线程池.impl.NewThreadPolicy;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TaskBlockedQueueTest {

    @Test
    public void get() {

        TaskBlockedQueue<Task> taskBlockedQueue = new TaskBlockedQueue<>(10);
        IntStream.range(0, 4).forEach(x -> taskBlockedQueue.putAndWait(new Task("任务" + x)));
        taskBlockedQueue.get(1000);
        taskBlockedQueue.get(1000);
        taskBlockedQueue.get(1000);
        taskBlockedQueue.get(1000);
        Task task = taskBlockedQueue.get(8000);
        Assert.assertNull(task);
    }

    @Test
    public void put() {
        TaskBlockedQueue<Task> taskBlockedQueue = new TaskBlockedQueue<>(5);
        IntStream.range(0, 8).forEach(x -> taskBlockedQueue.putAndWait(new Task("任务" + x)));

    }

    @Test
    public void executeTest() throws InterruptedException {
        //队列长度：5  核心数（worker数）：3
        ThreadPool<Task> threadPool = new ThreadPool<>(5, 3,  new NewThreadPolicy());
        IntStream.range(0, 20).forEach(x -> threadPool.execute(new Task("任务" + x)));
        TimeUnit.SECONDS.sleep(10000);
    }
}
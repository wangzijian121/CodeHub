package 多线程.java并发编程.java共享模型_juc工具.自定义线程池;

import java.util.concurrent.TimeUnit;

/**
 * 任务类
 *
 * @author zijian Wang
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println(name+" 执行中");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

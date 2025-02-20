package 多线程.java并发编程.java共享模型_juc工具.自定义线程池;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义线程池:
 * <br/>worker：使用线程工作 <br/>
 * queue: 任务队列。<br/>
 * task：任务单位。<br/>
 * scheduler：调度单位，调度队列与worker 的任务分配。<br/>
 *
 * @author zijian Wang
 */
public class ThreadPool<T> {

    /**
     * Worker集合
     */
    private Set<Worker> workers;
    /**
     * Task执行队列
     */
    private TaskBlockedQueue<Runnable> taskBlockedQueue;
    /**
     * Worker的数量
     */
    private int coreSize;

    public ThreadPool(int queueMaxSize, int coreSize, DenyPolicy denyPolicy) {
        workers = new HashSet<>(coreSize);
        this.taskBlockedQueue = new TaskBlockedQueue<>(queueMaxSize);
        this.coreSize = coreSize;
        new Thread(() -> scheduler()).start();
        taskBlockedQueue.setDenyPolicy(denyPolicy);
    }

    /**
     * 执行
     *
     * @param task
     */
    public void execute(Runnable task) {
        synchronized (this) {
            if (workers.size() < coreSize) {
                //创建worker
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else if (workers.size() >= coreSize) {
                //放入队列
                taskBlockedQueue.getDenyPolicy().load(taskBlockedQueue, task);
            }
        }
    }

    public void scheduler() {
        System.out.println("启动调度器！");
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Worker worker : workers) {
                if (!worker.haveTask()) {
                    Runnable task = taskBlockedQueue.get(30000);
                    worker.loadTask(task);
                    LockSupport.unpark(worker);
                }
            }
        }
    }
}

/**
 * task执行者
 *
 * @param <T>
 */
class Worker<T> extends Thread {
    private Runnable task;

    public Worker(Runnable task) {
        this.task = task;
    }

    public void loadTask(Runnable task) {
        this.task = task;
    }

    public boolean haveTask() {
        return task != null ? true : false;
    }

    @Override
    public void run() {
        while (haveTask()) {
            task.run();
            this.task = null;
            LockSupport.park();
        }
    }
}


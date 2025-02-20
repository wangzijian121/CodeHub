package 多线程.java并发编程实战书籍.a_创建线程.通过callable接口创建;

import java.util.concurrent.*;

/**
 * 通过Callable接口创建线程。
 * run方法有返回值
 */
public class CreateUseCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用Future
        ExecutorService service = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> submit = service.submit(task);
        //TODO 如何通过get 获取任务的返回值2的
        System.out.println("使用Future"+submit.get());

        //使用FutureTask
        ExecutorService service2 = Executors.newCachedThreadPool();
        Task task2 = new Task();
        FutureTask futureTask =new FutureTask(task2);
        service2.submit(futureTask);
        System.out.println("使用FutureTask"+futureTask.get());

    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            // 模拟计算需要一秒
            Thread.sleep(1000);
            return 2;
        }
    }
}

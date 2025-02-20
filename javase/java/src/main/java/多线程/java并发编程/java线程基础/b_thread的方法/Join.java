package 多线程.java并发编程.java线程基础.b_thread的方法;

import java.util.concurrent.TimeUnit;

/**
 * 等待线程执行结束
 *
 * @author zijian Wang
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        thread.start();
        thread.join(0);
        System.out.println("执行结束");
    }
}

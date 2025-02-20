package 多线程.java并发编程实战书籍.c_线程优先级;

import java.util.stream.IntStream;

/**
 * 验证线程优先级
 */
public class Demo {
    public static class T1 implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("当前执行的线程是：%s，优先级：%d",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getPriority()));
        }
    }

    public static void main(String[] args) {
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(new T1());
            thread.setPriority(i);
            thread.start();
        });
    }

}

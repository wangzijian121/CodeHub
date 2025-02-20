package 多线程.java并发编程.java线程基础.a_创建线程;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用Thread 创建线程
 *
 * @author Administrator
 */
@Slf4j(topic = "c.CreateThreadUsedThreadClass")
public class CreateThreadUsedThreadClass {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> System.out.println("thread1"));

        Thread thread2 = new Thread(() -> {

            System.out.println("thread2");
        });
        thread1.start();
        thread2.start();
    }
}

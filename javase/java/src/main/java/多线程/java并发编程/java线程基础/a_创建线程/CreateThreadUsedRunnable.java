package 多线程.java并发编程.java线程基础.a_创建线程;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用Runnable 创建线程
 *
 * @author Administrator
 */
@Slf4j(topic = "c.CreateThreadUsedRunnable")
public class CreateThreadUsedRunnable {
    public static void main(String[] args) {
        log.debug("wangzijian");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("wangzijian");
            }
        };
        //构造线程
        Thread thread = new Thread(runnable, "线程01");
        thread.start();

    }
}

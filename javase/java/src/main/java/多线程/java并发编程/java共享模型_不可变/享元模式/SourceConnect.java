package 多线程.java并发编程.java共享模型_不可变.享元模式;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 测试数据源连接类
 */
@Slf4j(topic = "c.SourceConnect")
public class SourceConnect {

    private String name;

    public SourceConnect(String name) {
        this.name = name;
    }

    public SourceConnect connect() {
        return this;

    }

    public void close() {
        System.out.println(Thread.currentThread().getName() + "-关闭👌");
    }

    public void work() {
        try {
            log.info(Thread.currentThread().getName() + "连接使用中...");
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package 多线程.java并发编程.java共享模型_juc工具.线程池.executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Task")
class Task extends Thread {

    @Override
    public void run() {
        log.info("task running...");
    }
}
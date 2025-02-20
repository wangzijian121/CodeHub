package 多线程.java并发编程.java线程基础.b_thread的方法;

import java.util.concurrent.TimeUnit;

/**
 * 生产-消费模型（读-写）
 */
class WaitAndNotify {
    private String content;
    private boolean empty = true;

    /**
     * 生产者线程调用此方法将消息写入共享对象 content
     */
    public synchronized void write(String content) {
        while (!empty) {
            try {
                // 如果共享对象不为空，等待消费者线程读取
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.content = content;
        empty = false;
        System.out.println("Producer writes: " + content);
        // 唤醒等待中的消费者线程
        notify();
    }

    // 消费者线程调用此方法读取消息
    public synchronized String read() {
        while (empty) {
            try {
                // 如果共享对象为空，等待生产者线程写入
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // 读取消息并将共享对象置为空
        empty = true;
        System.out.println("Consumer reads: " + content);
        // 唤醒等待中的生产者线程
        notify();
        return content;
    }


    public static void main(String[] args) {
        final WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                waitAndNotify.write("Message " + i);
                try {
                    //等待2秒
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                waitAndNotify.read();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
package 多线程.java并发编程.java共享模型_管程.j_JMM;

/**
 * @author zijian Wang
 */
public class Demo {
    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {
                // ....
            }
        });
        t.start();
        Thread.sleep(1);
        // 线程t不会如预想的停下来
        run = false;
    }
}

package 多线程.java并发编程实战书籍.a_创建线程.通过Runnable创建线程;

/**
 * 使用lambda 的方式实现Runnable
 */
public class CreateThreadUsedRunnableLambda {
    public static void main(String[] args) {
        //内部使用lambda表达式的方式，实现了Runnable
        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println("thread1...");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println("thread2...");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
    }
}

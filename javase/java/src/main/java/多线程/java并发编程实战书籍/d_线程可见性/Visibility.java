package 多线程.java并发编程实战书籍.d_线程可见性;

public class Visibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield(); // 让出CPU时间，尽量使得ready变量的改变对于该线程可见
            }
            System.out.println("Number: " + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000); // 等待一段时间，确保ReaderThread线程已经启动

        number = 42;
        ready = true; // 设置ready为true

        Thread.sleep(1000);
    }
}

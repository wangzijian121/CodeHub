package 集合.列表;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zijian Wang
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) throws InterruptedException {

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        long start = System.currentTimeMillis();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10000; i++) {
                    copyOnWriteArrayList.add("线程1:" + i);
                    System.out.println("线程1:" + i);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10000; i++) {
                    copyOnWriteArrayList.add("线程2:" + i);
                    System.out.println("线程2:" + i);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(copyOnWriteArrayList);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

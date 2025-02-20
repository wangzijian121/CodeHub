package 集合.列表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zijian Wang
 */
public class SynchronizedListDemo {
    public static void main(String[] args) throws InterruptedException {
        List list = Collections.synchronizedList(new ArrayList<>());

        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 10000; i++) {
                list.add("线程1:" + i);
                System.out.println("线程1:" + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i <= 10000; i++) {
                list.add("线程2:" + i);
                System.out.println("线程2:" + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(list);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

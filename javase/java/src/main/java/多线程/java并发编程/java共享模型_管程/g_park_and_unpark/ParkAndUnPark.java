package 多线程.java并发编程.java共享模型_管程.g_park_and_unpark;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zijian Wang
 */
public class ParkAndUnPark {

/*    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            System.out.println("线程1开启！");
            LockSupport.park();
            System.out.println("线程1完成了unpark!");
        });
        t1.start();

        LockSupport.unpark(t1);
        System.out.println("主线程执行了unpark！");
    }*/

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("线程1开启！");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.park();
            System.out.println("线程1完成了unpark!");
        });
        t1.start();
        //先执行unpark,后续执行的 park 并不会
        LockSupport.unpark(t1);
        System.out.println("主线程执行了unpark！");
    }
}

package 多线程.java并发编程实战书籍.d_线程可见性;

/**
 * 可见性Demo
 *
 * @author zijian Wang
 */
public class Visibility2 {

    //    private boolean status = true;
    private volatile boolean status = true;

    private void function() {
        while (status) {
            System.out.println(status);
        }
        System.out.println(status);
        System.out.println("执行结束！");
    }

    public static void main(String[] args) throws InterruptedException {

        Visibility2 visibility2 = new Visibility2();
        Thread t1 = new Thread(() -> {
            visibility2.function();
        });
        t1.start();
        Thread.sleep(1000);
        //当对status修改时,对线程t1不可见
        visibility2.status = false;
//        Collections.synchronizedList();
    }
}

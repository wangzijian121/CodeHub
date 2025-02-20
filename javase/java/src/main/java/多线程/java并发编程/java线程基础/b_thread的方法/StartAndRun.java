package 多线程.java并发编程.java线程基础.b_thread的方法;

/**
 * 线程的启动和停止
 *
 * @author zijian Wang
 */
public class StartAndRun {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.setName("my_thread");
        thread.run();//只使用main线程调用run方法
        thread.start();//使用thread.start()逻辑为使用新线程调用方法。
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("run....线程为：" + Thread.currentThread().getName());
    }
}
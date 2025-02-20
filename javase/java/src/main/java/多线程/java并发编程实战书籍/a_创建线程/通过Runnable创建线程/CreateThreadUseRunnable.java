package 多线程.java并发编程实战书籍.a_创建线程.通过Runnable创建线程;

/**
 * @author zijian Wang
 */
public class CreateThreadUseRunnable implements Runnable {

    private int num;

    public CreateThreadUseRunnable(int num) {
        this.num = num;
    }

    /**
     * 当使用对象实现接口 Runnable 创建线程时，启动线程会导致在该单独执行的线程中调用对象的方法 run 。
     * 该方法 run 的一般约定是它可以采取任何行动。
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Thread" + num);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) {
        CreateThreadUseRunnable createThreadUseRunnable = new CreateThreadUseRunnable(1);
        CreateThreadUseRunnable createThreadUseRunnable2 = new CreateThreadUseRunnable(2);
        //也是确保不了顺序
        Thread t1=new Thread(createThreadUseRunnable,"处理1");
        System.out.println(t1.getName());
        t1.start();
        new Thread(createThreadUseRunnable2,"处理2").start();
    }
}

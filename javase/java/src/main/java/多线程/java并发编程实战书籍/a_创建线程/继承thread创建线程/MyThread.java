package 多线程.java并发编程实战书籍.a_创建线程.继承thread创建线程;


class MyThread extends Thread {

    private int num;


    public MyThread(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("myThread" + num);
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread(1);
        MyThread myThread2 = new MyThread(2);

        //输出的顺序不能控制 可能12  也可能21
        myThread.start();
        myThread2.start();
    }
}
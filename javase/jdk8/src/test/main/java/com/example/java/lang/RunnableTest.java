package com.example.java.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class RunnableTest {

}

class TestRunnable implements Runnable {

    private String name;

    public TestRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int i = 0;

        for (; ; ) {
            i++;
            if (i >= 10) {
                break;
            }
            try {
                Thread.sleep(1000);
                System.out.println(name + "第" + i + "次输出！");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {
        TestRunnable testRunnable = new TestRunnable("线程1");
        TestRunnable testRunnable2 = new TestRunnable("线程2");

        //1
        Thread thread = new Thread(testRunnable);
        thread.start();

        //2
        Thread thread2 = new Thread(testRunnable2);
        thread2.start();
    }
}
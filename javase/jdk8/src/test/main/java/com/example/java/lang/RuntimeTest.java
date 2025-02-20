package com.example.java.lang;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RuntimeTest {

    @Test
    public void getRuntime() {
        System.out.println("Runtime Test...");
        System.out.println(Runtime.getRuntime());//
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);//7241MB
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);//489
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);//476
        //start  Google Chrome
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            System.out.println(process);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void exit() throws InterruptedException {
        Thread.sleep(3000);
        Runtime.getRuntime().exit(121);//进程已结束,退出代码121
    }

    @Test
    public void addShutdownHook() {
    }

    @Test
    public void removeShutdownHook() {
    }

    @Test
    public void halt() {
    }

    @Test
    public void runFinalizersOnExit() {
    }

    @Test
    public void exec() {
    }

    @Test
    public void testExec() {
    }

    @Test
    public void testExec1() {
    }

    @Test
    public void testExec2() {
    }

    @Test
    public void testExec3() {
    }

    @Test
    public void testExec4() {
    }

    @Test
    public void availableProcessors() {
    }

    @Test
    public void freeMemory() {
    }

    @Test
    public void totalMemory() {
    }

    @Test
    public void maxMemory() {
    }

    @Test
    public void gc() {
    }

    @Test
    public void runFinalization() {
    }

    @Test
    public void traceInstructions() {
    }

    @Test
    public void traceMethodCalls() {
    }

    @Test
    public void load() {
    }

    @Test
    public void load0() {
    }

    @Test
    public void loadLibrary() {
    }

    @Test
    public void loadLibrary0() {
    }

    @Test
    public void getLocalizedInputStream() {
    }

    @Test
    public void getLocalizedOutputStream() {
    }
}
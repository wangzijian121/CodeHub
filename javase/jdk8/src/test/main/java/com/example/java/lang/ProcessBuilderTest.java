package com.example.java.lang;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProcessBuilderTest {

    @Test
    public void testProcess() {
        try {
            //打开google 浏览器
            Process p = new ProcessBuilder("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe").start();
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                    System.out.println("p：" + p.isAlive());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
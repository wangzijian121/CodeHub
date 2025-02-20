package com.example.java.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClassNotFoundExceptionTest {

    @Test
    public void getException() {

        try {
            Class c = Class.forName("java.lang.Object_wangzijian");
            System.out.println("c:" + c);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getCause() {
    }
}
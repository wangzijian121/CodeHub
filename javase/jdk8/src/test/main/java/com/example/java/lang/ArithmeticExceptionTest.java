package com.example.java.lang;

import org.junit.Test;

public class ArithmeticExceptionTest {

    /**
     *java.lang.ArithmeticException: / by zero
     * 	at com.example.lang.ArithmeticExceptionTest.testException(ArithmeticExceptionTest.java:12)
     */
    @Test
    public void testException() {

        System.out.println(10 / 0);
    }

}
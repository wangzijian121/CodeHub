package com.example.java.lang;

import org.junit.Test;

public class ArrayStoreExceptionTest {
    @Test
    public void dummy() {
        Object[] x = new String[3];
        x[0] = 0;//java.lang.ArrayStoreException: java.lang.Integer
    }
}
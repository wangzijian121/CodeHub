package com.example.java.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class AssertionErrorTest {

    @Test
    public void name() {
        Integer a = 0;
        String name = "wangzijian";
        assert name.equals(a);//java.lang.AssertionError
    }
}
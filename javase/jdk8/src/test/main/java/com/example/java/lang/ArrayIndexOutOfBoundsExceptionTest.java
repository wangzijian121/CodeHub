package com.example.java.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayIndexOutOfBoundsExceptionTest {

    @Test
    public void testArrayIndexOutOfBoundsException() {
        List list = new ArrayList();
        list.add("wangzijian");
        System.out.println(list.get(-1));//java.lang.ArrayIndexOutOfBoundsException: -1
    }

}
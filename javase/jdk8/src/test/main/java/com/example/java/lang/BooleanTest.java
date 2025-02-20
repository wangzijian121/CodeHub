package com.example.java.lang;


import org.junit.Before;
import org.junit.Test;



public class BooleanTest {
    Boolean a;
    Boolean b;
    Boolean c;

    @Before
    public void setUp() throws Exception {

        a = true;
        b = true;
        c = false;

    }

    @Test
    public void parseBoolean() {
    }

    @Test
    public void booleanValue() {
    }

    @Test
    public void valueOf() {
        System.out.println(a.booleanValue());
        System.out.println(b.booleanValue());
        System.out.println(c.booleanValue());
    }

    @Test
    public void testValueOf() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testToString1() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testHashCode1() {
    }

    @Test
    public void testEquals() {
        System.out.println(a.equals(b));//true
        System.out.println(a.equals(c));//false
    }

    @Test
    public void getBoolean() {
        System.setProperty("wangzijian", "true");
        System.out.println(Boolean.getBoolean("wangzijian"));

    }

    @Test
    public void compareTo() {
        System.out.println(a.compareTo(b));//0
        System.out.println(a.compareTo(c));//1
    }

    @Test
    public void compare() {
        System.out.println(Boolean.compare(a, b));//0
        System.out.println(Boolean.compare(a, c));//1
    }

    @Test
    public void logicalAnd() {
        System.out.println(Boolean.logicalAnd(a, b));//true
        System.out.println(Boolean.logicalAnd(a, c));//false

    }

    @Test
    public void logicalOr() {
        System.out.println(Boolean.logicalOr(a, b));//true
        System.out.println(Boolean.logicalOr(a, c));//true
    }

    @Test
    public void logicalXor() {
        System.out.println(Boolean.logicalXor(a, b));//false
        System.out.println(Boolean.logicalXor(a, c));//true
    }
}
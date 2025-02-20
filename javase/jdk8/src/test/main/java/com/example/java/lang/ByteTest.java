package com.example.java.lang;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ByteTest {


    @Before
    public void setUp() throws Exception {
        Byte b=new Byte("12");

    }

    @Test
    public void testToString() {
        Byte b=127;
        System.out.println(b);
    }

    @Test
    public void valueOf() {

        Byte a= Byte.valueOf("123");
        System.out.println(a);
    }

    @Test
    public void parseByte() {
    }

    @Test
    public void testParseByte() {
    }

    @Test
    public void testValueOf() {
    }

    @Test
    public void testValueOf1() {
    }

    @Test
    public void decode() {
    }

    @Test
    public void byteValue() {
    }

    @Test
    public void shortValue() {
    }

    @Test
    public void intValue() {
    }

    @Test
    public void longValue() {
    }

    @Test
    public void floatValue() {
    }

    @Test
    public void doubleValue() {
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
    }

    @Test
    public void compareTo() {
    }

    @Test
    public void compare() {
    }

    @Test
    public void toUnsignedInt() {
    }

    @Test
    public void toUnsignedLong() {
    }
}
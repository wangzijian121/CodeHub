package com.example.java.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerTest {

    Integer integer = new Integer("121");

    @Test
    public void testToString() {

        System.out.println(integer.toString());//121
    }

    @Test
    public void toUnsignedString() {
    }

    @Test
    public void toHexString() {
    }

    @Test
    public void toOctalString() {
    }

    @Test
    public void toBinaryString() {
    }

    @Test
    public void formatUnsignedInt() {
    }

    @Test
    public void testToString1() {
    }

    @Test
    public void testToUnsignedString() {
    }

    @Test
    public void getChars() {
    }

    @Test
    public void stringSize() {
    }

    @Test
    public void parseInt() {
    }

    @Test
    public void testParseInt() {
    }

    @Test
    public void parseUnsignedInt() {
    }

    @Test
    public void testParseUnsignedInt() {
    }

    @Test
    public void valueOf() {
    }

    @Test
    public void testValueOf() {
    }

    @Test
    public void testValueOf1() {
    }

    @Test
    public void byteValue() {
        //返回此值 Integer为 byte的基本收缩转换后
        System.out.println(integer.byteValue());
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
    public void testToString2() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testHashCode1() {
    }

    @Test
    public void testEquals() {
        System.out.println(integer.equals(121));//123
    }

    @Test
    public void getInteger() {

    }

    @Test
    public void testGetInteger() {
    }

    @Test
    public void testGetInteger1() {
    }

    @Test
    public void decode() {
        //将 String解码成 Integer 。
        System.out.println(Integer.decode("123"));//123
        System.out.println(Integer.decode("abc"));//NumberFormatException
    }

    @Test
    public void compareTo() {

        System.out.println(integer.compareTo(100));//1
    }

    @Test
    public void compare() {
        // 1是否比2 大
        System.out.println(Integer.compare(1, 2));//-1
        System.out.println(Integer.compare(2, 1));//1
    }

    @Test
    public void compareUnsigned() {
    }

    @Test
    public void toUnsignedLong() {
    }

    @Test
    public void divideUnsigned() {
    }

    @Test
    public void remainderUnsigned() {
    }

    @Test
    public void highestOneBit() {
    }

    @Test
    public void lowestOneBit() {
    }

    @Test
    public void numberOfLeadingZeros() {
    }

    @Test
    public void numberOfTrailingZeros() {
    }

    @Test
    public void bitCount() {
        //返回指定的int值的二进制补码二进制表示中的 int数。
        System.out.println(Integer.bitCount(integer));
    }

    @Test
    public void rotateLeft() {
    }

    @Test
    public void rotateRight() {
    }

    @Test
    public void reverse() {
    }

    @Test
    public void signum() {
    }

    @Test
    public void reverseBytes() {
    }

    @Test
    public void sum() {
    }

    @Test
    public void max() {
    }

    @Test
    public void min() {
    }
}
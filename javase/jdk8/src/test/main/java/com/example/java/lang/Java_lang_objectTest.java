package com.example.java.lang;

import org.junit.Test;

public class Java_lang_objectTest {

    class Demo {
        String name = "wangzijian";

        public Demo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    @Test
    public void testGetClass() {
        Demo demo = new Demo("123");
        System.out.println(demo.getClass());//class java.lang.Java_lang_objectTest$Demo
    }

    @Test
    public void test_equals() {
        Demo demo = new Demo("111");
        Demo demo2 = new Demo("111");
        //Demo 没有重写equals 所以比较的是2个对象的引用。
        System.out.println(demo.equals(demo2));//false
        System.out.println(demo == demo2);//false

        //String类中重写了equals方法，判断是2个对象的值
        String str =new String("111");
        String str2 =new String("111");
        System.out.println(str==str2);//false
        System.out.println(str.equals(str2));//true

    }

}

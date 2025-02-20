package com.example.java.lang;

import org.junit.Test;

public class java_lang_string_test {


    @Test
    public void value_test(){

        String name ="wangzijian";
        String name2 ="wangzijian";
        System.out.println(name.hashCode());
        System.out.println(name2.hashCode());
    }


    @Test
    public void sub_test(){

        String name ="wangzijian";
        System.out.println(name.substring(1));//angzijian
        System.out.println(name.substring(2,4));//ng
        System.out.println(name.subSequence(2,4));//ng
    }


}



package com.zjyun.map_reduce.自定义分区_自定义排序;


import org.junit.Test;

public class FlowBeanTest {

    @Test
    public void compareTo() {
        FlowBean flowBean = new FlowBean(100,100,200);
        FlowBean flowBean2 = new FlowBean(200,200,400);
        System.out.println(flowBean.compareTo(flowBean2));//-1
        System.out.println(flowBean2.compareTo(flowBean));//1
        System.out.println(flowBean.compareTo(flowBean));//0
    }
}
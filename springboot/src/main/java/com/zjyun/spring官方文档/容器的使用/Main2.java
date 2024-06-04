package com.zjyun.spring官方文档.容器的使用;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */
public class Main2 {
    public static void main(String[] args) {
        //最灵活的变体是GenericApplicationContext与读取器委托相结合
        GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("容器的使用/service1.xml");
        context.refresh();
        System.out.println(context.getBean("bean1"));
        System.out.println(context.getBean("bean2"));
    }
}

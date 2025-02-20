package com.example.j2ee.service;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 添加一个监视器，监控Session属性的添加和修改操作。
 * @author zijian Wang
 */
@WebListener(value = "我的监听器")
public class MyListener implements HttpSessionAttributeListener {

    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("【监听器】：添加了Session值");
        System.out.println("添加的值：" + event.getSession().getAttribute("person"));
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("【监听器】：修改了Session值");
        System.out.println("修改的值：" + event.getSession().getAttribute("person"));
    }

}
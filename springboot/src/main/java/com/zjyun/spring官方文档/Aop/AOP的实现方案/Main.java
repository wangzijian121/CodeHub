package com.zjyun.spring官方文档.Aop.AOP的实现方案;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean(AppConfig.class);
        applicationContext.registerBean(MyPostProcessor.class);
        //register后自动刷新
        applicationContext.refresh();
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("🥔" + definitionName);
        }

        System.out.println(applicationContext.getBean("userServiceImpl").getClass());//代理类：class org.springframework.cglib.proxy.Proxy$ProxyImpl$$EnhancerByCGLIB$$fd5480aa
        Object userServiceImpl =  applicationContext.getBean(IService.class);
      /*  for (Method method : userServiceImpl.getClass().getMethods()) {
            System.out.println(method.getName());
        }*/
        userServiceImpl.getClass().getMethod("s1").invoke(userServiceImpl);//使用反射调用代理类中的方法.
    }
}

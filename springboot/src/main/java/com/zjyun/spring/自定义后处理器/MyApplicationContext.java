package com.zjyun.spring.自定义后处理器;

import com.zjyun.spring.自定义后处理器.bean.Bean1;
import com.zjyun.spring.自定义后处理器.config.AppConfig;
import com.zjyun.spring.自定义后处理器.自定义BeanFactoryPostProcessor后处理器.MyBeanFactoryPostProcessor;
import com.zjyun.spring.自定义后处理器.自定义PostProcessor后处理器.MyPostProcessor;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/29
 */
public class MyApplicationContext {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(AppConfig.class);
        //内置的BeanFactory处理器
        //context.addBeanFactoryPostProcessor(new ConfigurationClassPostProcessor());
        context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());

        //Spring 的Bean后处理器（解析@Autowired、@Resource等）
        //context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        //context.registerBean(CommonAnnotationBeanPostProcessor.class);
        MyPostProcessor postProcessor=new MyPostProcessor();
        postProcessor.setBeanFactory(context.getDefaultListableBeanFactory());
        context.registerBean(MyPostProcessor.class);

        context.refresh();

        for (String definitionName : context.getBeanDefinitionNames()) {
            System.out.println("容器内的bean定义："+definitionName);
        }

        System.out.println("获取bean:"+context.getBean(Bean1.class.getName()));
    }
}

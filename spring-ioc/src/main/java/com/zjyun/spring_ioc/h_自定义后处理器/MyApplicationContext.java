package com.zjyun.spring_ioc.h_自定义后处理器;

import com.zjyun.spring_ioc.h_自定义后处理器.bean.Bean1;
import com.zjyun.spring_ioc.h_自定义后处理器.config.AppConfig;
import com.zjyun.spring_ioc.h_自定义后处理器.自定义BeanFactoryPostProcessor后处理器.MyBeanFactoryPostProcessor;
import com.zjyun.spring_ioc.h_自定义后处理器.自定义PostProcessor后处理器.MyPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 自定义的BeanFactoryPostProcessor 和 自定义的BeanPostProcessor 与Spring内置的对比。
 * @Author: Wang Zijian
 * @Date: 2024/5/29
 */
public class MyApplicationContext {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean(AppConfig.class);
        //内置的BeanFactory处理器
        //context.addBeanFactoryPostProcessor(new ConfigurationClassPostProcessor());
        //自定义BeanFactory后处理器
        context.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());

        //Spring 的Bean后处理器（解析@Autowired、@Resource等）
        //context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        //context.registerBean(CommonAnnotationBeanPostProcessor.class);

        //自定义Bean后处理器
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

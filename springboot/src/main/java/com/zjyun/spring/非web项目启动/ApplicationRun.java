package com.zjyun.spring.非web项目启动;

import com.zjyun.spring.非web项目启动.config.AppConfig;
import com.zjyun.spring.非web项目启动.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/29
 */

public class ApplicationRun  {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(AppConfig.class);
        for (String definitionName : context.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }
        context.refresh();
        MyService service = context.getBean(MyService.class);
        service.sayHello();
    }
}

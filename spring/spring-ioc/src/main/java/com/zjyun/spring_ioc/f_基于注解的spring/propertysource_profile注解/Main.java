package com.zjyun.spring_ioc.f_基于注解的spring.propertysource_profile注解;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/4
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("🥔"+definitionName);
        }
        Environment env = applicationContext.getEnvironment();
        for (String activeProfile : env.getActiveProfiles()) {
            System.out.println("环境👨‍🔧："+activeProfile);
        }
        System.out.println(applicationContext.getBean("person"));
        //System.out.println(applicationContext.getBean("getPerson1"));
        //System.out.println(applicationContext.getBean("getPerson2"));
    }

}

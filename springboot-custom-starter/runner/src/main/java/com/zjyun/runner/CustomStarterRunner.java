package com.zjyun.runner;

import com.zjyun.runner.service.StarterRunnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 需要打包runner后移除runner模块
 */
@SpringBootApplication
public class CustomStarterRunner {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CustomStarterRunner.class, args);
        //for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
        //    System.out.println("🥔" + beanDefinitionName);
        //}
        StarterRunnerService bean = applicationContext.getBean(StarterRunnerService.class);
        bean.printDate();
    }

}
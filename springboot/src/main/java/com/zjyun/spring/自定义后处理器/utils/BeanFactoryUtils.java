package com.zjyun.spring.自定义后处理器.utils;

import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
public class BeanFactoryUtils {

    public static void printBeansDefinitionNames(GenericApplicationContext application) {
        for (String beansDefinitionNames : application.getBeanDefinitionNames()) {
            System.out.println(beansDefinitionNames);
        }
    }

}

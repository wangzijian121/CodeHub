package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.service.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
public class MyController  implements ApplicationContextAware {

    @Autowired
    private MyService myService;

    @Autowired
    private WebApplicationContext webApplicationContext;



    @RequestMapping("/show")
    public void show() {
        System.out.println("MyController#show(),myService:" + myService);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("🧐Controller："+applicationContext);
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("Controller🥔"+definitionName);
        }
    }
}

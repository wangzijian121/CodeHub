package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.service.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
@RequestMapping("/my")
public class MyController implements ApplicationContextAware {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String  show() {
        System.out.println("MyController#show(),myService:" + myService);
        return "index.jsp";
    }

    @GetMapping("/show2")
    public String show2() {
        System.out.println("MyController#show2(),myService:" + myService);
        return "index.jsp";
    }

    @PostMapping("/show3")
    public String show3() {
        System.out.println("MyController#show3(),myService:" + myService);
        return "index.jsp";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("🧐Controller：" + applicationContext);
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("Controller🥔" + definitionName);
        }
    }
}

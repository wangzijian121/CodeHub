package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.model.User;
import com.zjyun.b_spring快速入门.service.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
@RequestMapping("/user")
public class UserController implements ApplicationContextAware {

    @RequestMapping("/show")
    public String show(User user) {
        System.out.println(user);
        return "../index.jsp";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("🧐Controller：" + applicationContext);
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("Controller🥔" + definitionName);
        }
    }
}

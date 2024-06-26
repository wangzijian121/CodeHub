package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.model.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "index";
    }

    /*
    包装为对象
     */
    @RequestMapping("/show2")
    public String show2(User user) {
        System.out.println(user);
        return "../index.jsp";
    }

    /**
     * @param id
     * @param string
     * @return
     * @PathVariable 通过路径获取ID
     */
    @RequestMapping(value = "/find_user/{id}/{str}", method = RequestMethod.GET)
    public String findUserById(@PathVariable("id") String id, @PathVariable("str") String string) {
        System.out.println("findUserById👉" + id);
        System.out.println("findUserById👉" + string);
        return "../../index.jsp";
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("WebApplicationContext中的Bean🥔" + definitionName);
        }
    }
}

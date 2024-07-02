package com.zjyun.b_spring快速入门.controller;

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
@RequestMapping("/my")
public class MyController implements ApplicationContextAware {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam("name") String username) {
        System.out.println(username);
        System.out.println("MyController#show(),myService:" + myService);
        return "index";
    }

    @GetMapping("/show2")
    public String show2() {
        System.out.println("MyController#show2(),myService:" + myService);
        return "index";
    }

    @PostMapping("/show3")
    public String show3() {
        System.out.println("MyController#show3333(),myService:" + myService);
        return "index";
    }

    @GetMapping("/show4")
    public String show4(String[] list) {
        for (String string : list) {
            System.out.println("show4:" + string);
        }
        return "index";
    }

    @GetMapping("/show5")
    public String show5(Map<String, String> map) {
        for (String string : map.keySet()) {
            System.out.println("show5:" + string);
        }
        return "index";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //System.out.println("🧐Controller："+applicationContext);
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            //System.out.println("Controller🥔" + definitionName);
        }
    }
}

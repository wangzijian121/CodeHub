package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.model.Address;
import com.zjyun.b_spring快速入门.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@RestController
@RequestMapping("/res")
public class ResponseController {

    /**
     * 2.回写数据模型
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/res1", method = RequestMethod.GET)
    public ModelAndView res1(ModelAndView modelAndView) {
        //封装模型数据和视图名
        //视图名：转发的路径
        //模型数据：Bean对象的数据
        User user = new User();
        user.setName("wang");
        user.setAge(18);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 3.直接回写字符串
     *
     * @return
     */
    @RequestMapping(value = "/res2", method = RequestMethod.GET)
    @ResponseBody
    public String res2() {
        return "wangzijian的@ResponseBody";
    }

    /**
     * 4.异步的回写 REST  返回json
     *
     * @return
     */
    @RequestMapping(value = "/res3", method = RequestMethod.GET)
    //@ResponseBody
    public User res3() {
        User user = new User();
        user.setName("wangzijian");
        user.setAge(18);
        user.setAddress(new Address("Beijing"));
        return user;
    }
}

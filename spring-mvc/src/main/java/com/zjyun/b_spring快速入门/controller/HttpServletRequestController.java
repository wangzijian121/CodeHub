package com.zjyun.b_spring快速入门.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
@RequestMapping("/request")
public class HttpServletRequestController {


    @RequestMapping(value = "/request1", method = RequestMethod.GET)
    public String getHttpServletRequest1(HttpServletRequest request) {
        request.setAttribute("name", "王子健121");
        //TODO 跳转报错
        return "/request2";
    }

    @RequestMapping(value = "/request2", method = RequestMethod.GET)
    public String getHttpServletRequest2(@RequestAttribute("name") String name) {
        System.out.println("getHttpServletRequest2 获取到了+" + name);
        return "index";
    }


    @RequestMapping(value = "/request3", method = RequestMethod.GET)
    public String getHttpServletRequest3(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        System.out.println(response);
        return "index";
    }
}

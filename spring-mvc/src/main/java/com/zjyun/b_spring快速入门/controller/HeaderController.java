package com.zjyun.b_spring快速入门.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
@RequestMapping("/header")
public class HeaderController {

    /**
     * 获取Header
     *
     * @param header
     * @return
     */
    @RequestMapping(value = "/getHeaderValue", method = RequestMethod.GET)
    public String getHeaderValue(@RequestHeader Map<String, String> header) {
        header.keySet().forEach(x -> System.out.println(x + ":" + header.get(x)));
        return "index";
    }
    /**
     * 获取cookie
     *
     * @param cookie
     * @return
     */
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookiesValue(@CookieValue(value = "JSESSIONID") String  cookie) {
        System.out.println(cookie);
        return "index";
    }

}

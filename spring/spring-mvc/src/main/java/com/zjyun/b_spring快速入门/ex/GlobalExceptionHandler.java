package com.zjyun.b_spring快速入门.ex;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = java.lang.Exception.class)
    public ModelAndView handleExceptionDefault(RuntimeException e) {
        System.out.println(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @ExceptionHandler(value = java.lang.ArithmeticException.class)
    @ResponseBody
    public String handleException(RuntimeException e) {
        System.out.println(e.getMessage());
        return "系统异常了！";
    }

}

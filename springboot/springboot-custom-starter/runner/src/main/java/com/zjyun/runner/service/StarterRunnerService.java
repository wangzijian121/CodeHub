package com.zjyun.runner.service;

import com.zjyun.starter.utils.WoniuUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/8/5
 */
@Service
public class StarterRunnerService {


    @Resource
    private  WoniuUtil woniuUtil;

    public  void  printDate(){
        System.out.println(new Date());
        System.out.println(woniuUtil.dateToStr(new Date()));
    }
}

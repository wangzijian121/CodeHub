package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.model.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
@RequestMapping("/file")
public class FileController implements ApplicationContextAware {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestBody MultipartFile file) {
        String outputPath = "D:\\";
        try {
            System.out.println("文件📜" + file.getOriginalFilename());
            file.transferTo(new File(outputPath + file.getOriginalFilename().replace(".", LocalDate.now() + ".")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.getClass().getPackage().getName());
        return "index";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            //System.out.println("Controller🥔" + definitionName);
        }
    }
}

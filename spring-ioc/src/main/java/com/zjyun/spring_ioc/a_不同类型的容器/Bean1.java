package com.zjyun.spring_ioc.a_不同类型的容器;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Bean1 {

    @Autowired
    private Bean2 bean2;

    private String beanName;

}
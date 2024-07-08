package com.zjyun.spring_ioc.a_不同类型的容器;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wang Zijian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Bean1 {

    private Bean2 bean2;

    @Autowired
    public Bean1(Bean2 bean2) {
        this.bean2 = bean2;
    }

    private String beanName;

}
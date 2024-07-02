package com.zjyun.spring_ioc.f_基于注解的spring.循环依赖;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class X {

	Y y;

	public X(){
		System.out.println("X create");
	}
	@Autowired
	public void setY(Y y) {
		this.y = y;
	}
}

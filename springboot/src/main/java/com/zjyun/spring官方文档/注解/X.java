package com.zjyun.spring官方文档.注解;

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

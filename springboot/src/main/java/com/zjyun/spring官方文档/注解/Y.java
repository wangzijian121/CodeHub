package com.zjyun.spring官方文档.注解;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Y {

	X x;

	public Y(){
		System.out.println("Y create");
	}
	@Autowired
	public void setX(X x) {
		this.x = x;
	}
}


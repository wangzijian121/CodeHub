package com.zjyun.spring官方文档.Aop.AOP的应用.日志记录.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Aspect
@Component
public class MyLogAspect {

    @Pointcut("execution(void com.zjyun.spring官方文档.Aop.AOP的应用.日志记录.MyServiceImpl.fun1())")
    public void pointcut1(){}

    @Pointcut("execution(void com.zjyun.spring官方文档.Aop.AOP的应用.日志记录.MyServiceImpl.fun2())")
    public void pointcut2() {}

    @Before("pointcut1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("代理对象：" + joinPoint.getThis().getClass());
        System.out.println("[INFO]" + this.getClass() + ":执行到了方法前！");
    }

    @After("pointcut1()")
    public void after(JoinPoint joinPoint) {
        System.out.println("代理对象：" + joinPoint.getThis().getClass());
        System.out.println("[INFO]" + this.getClass() + ":执行到了方法后！");
    }
    @AfterThrowing("pointcut2()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[ERROR]"+joinPoint.getThis().getClass()+"异常！");
    }

}

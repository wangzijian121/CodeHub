package com.zjyun.spring官方文档.Aop.AOP表达式的配置;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 增强类，内部提供增强方法
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Component
@Aspect
public class UserServiceAdvice {

    @Pointcut("execution(void com.zjyun.spring官方文档.Aop.AOP表达式的配置.UserServiceImpl.s1())")
    public void pointCutDefine() {

    }

    @Before("UserServiceAdvice.pointCutDefine()")
    public void before(JoinPoint joinPoint) {
        System.out.println("连接点 切入点：" + joinPoint.getTarget());
        System.out.println("XML配置AOP before 增强！！");
    }

    @AfterReturning("UserServiceAdvice.pointCutDefine()")
    public void afterReturning() {
        System.out.println("XML配置AOP  afterReturning 增强！！");
    }

    @Around("UserServiceAdvice.pointCutDefine()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前增强！");
        Object res = proceedingJoinPoint.proceed();
        System.out.println("环绕后增强！");
        return res;
    }

    @AfterThrowing(value = "UserServiceAdvice.pointCutDefine()", throwing = "myZeroThrow")
    public void afterThrow(MyZeroThrow myZeroThrow) {
        System.out.println("异常了！");
    }

    @After(value = "UserServiceAdvice.pointCutDefine()")
    public void after() {
        System.out.println("XML配置AOP  after 增强！！");
    }
}

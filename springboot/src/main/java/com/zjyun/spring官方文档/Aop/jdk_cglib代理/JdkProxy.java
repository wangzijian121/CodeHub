package com.zjyun.spring官方文档.Aop.jdk_cglib代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    public static void main(String[] args) {
        UserServiceImpl target = new UserServiceImpl();
        
        IService proxy = (IService) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Before 增强！！");
                    Object result = method.invoke(target, args);
                    System.out.println("After 增强！！");
                    return result;
                }
            }
        );
        proxy.s1();
    }
}

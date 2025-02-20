package zjyun.spring_aop.a_aop原理_jdk_cglib代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过JDK 的代理对象 Proxy.newProxyInstance代理对象。
 */
public class JdkProxy {
    public static void main(String[] args) {
        UserServiceImpl target = new UserServiceImpl();

        IService proxy = (IService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JdkProxy-Before 增强！！");
                        Object result = method.invoke(target, args);
                        System.out.println("JdkProxy-After 增强！！");
                        return result;
                    }
                }
        );
        // 增强的目标方法
        proxy.s1();
    }
}

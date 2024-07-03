package zjyun.spring_aop.a_aop原理_jdk_cglib代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
public class CglibProxy {

    public static void main(String[] args) {
        // 创建 Enhancer 对象
        Enhancer enhancer = new Enhancer();
        // 设置目标类为 UserService
        enhancer.setSuperclass(UserServiceImpl.class);
        // 设置回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("CglibProxy-Before method: " + method.getName());
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("CglibProxy-After method: " + method.getName());
                return result;
            }
        });

        // 创建代理对象
        UserServiceImpl userServiceProxy = (UserServiceImpl) enhancer.create();
        // 增强的目标方法
        userServiceProxy.s1();
    }
}

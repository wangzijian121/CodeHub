package zjyun.spring_aop.AOP表达式的配置;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean(AppConfig.class);
        //register后自动刷新
        applicationContext.refresh();
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("🥔" + definitionName);
        }
        //
        //System.out.println(applicationContext.getBean("userServiceImpl").getClass());//代理类：class org.springframework.cglib.proxy.Proxy$ProxyImpl$$EnhancerByCGLIB$$fd5480aa
        //Object userServiceImpl = applicationContext.getBean(IService.class);
        //userServiceImpl.getClass().getMethod("s1").invoke(userServiceImpl);//使用反射调用代理类中的方法.

        applicationContext.getBean(IService.class).s1();
    }
}

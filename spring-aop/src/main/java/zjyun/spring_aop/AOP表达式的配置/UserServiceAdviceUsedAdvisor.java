package zjyun.spring_aop.AOP表达式的配置;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 使用接口实现 AOP 的切面配置。
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Component
public class UserServiceAdviceUsedAdvisor implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }
}

package zjyun.spring_aop.AOP表达式的配置;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * 用户服务类（待增强）
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Service
public class UserServiceImpl implements IService {

    @Pointcut
    public void s1() {
        System.out.println("@Pointcut s1....");
        //int i = 10 / 0;//模拟异常

    }

    public void s2() {
        System.out.println("s2....");
    }
}

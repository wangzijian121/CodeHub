package zjyun.spring_aop.AOP的应用.日志记录;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Service
public class MyServiceImpl implements MyService {
    public void fun1() {
        System.out.println(this.getClass() + "👉fun1()方法");
    }

    public void fun2() {
        System.out.println(this.getClass() + "👉fun2()方法");
        int i = 0 / 0;
    }
}

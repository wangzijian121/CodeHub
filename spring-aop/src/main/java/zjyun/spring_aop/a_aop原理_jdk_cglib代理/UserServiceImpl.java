package zjyun.spring_aop.a_aop原理_jdk_cglib代理;

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

    public void s1() {
        System.out.println("s1....");
    }

    public void s2() {
        System.out.println("s2....");
    }
}


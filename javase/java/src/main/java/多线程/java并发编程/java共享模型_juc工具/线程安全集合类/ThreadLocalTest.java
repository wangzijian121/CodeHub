package 多线程.java并发编程.java共享模型_juc工具.线程安全集合类;

import utils.StringUtils;

import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        IntStream.range(0,10).forEach(x->new Thread(()->{
            threadLocal.set(StringUtils.getRandomStr(5));
            System.out.println("线程配置完成！");
            System.out.println(threadLocal.get());
        }).start());
    }
}
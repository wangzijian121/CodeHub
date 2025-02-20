package 多线程.java并发编程.java共享模型_无锁.原子类;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 *
 * @author zijian Wang
 */
public class MyAtomicClass {
    public static void main(String[] args) {


        //递增 递减
        System.out.println(new AtomicInteger(10).getAndIncrement());//先获取输出在递增1
        System.out.println(new AtomicInteger(10).incrementAndGet());//先递增1在获取输出

        System.out.println(new AtomicInteger(10).getAndDecrement());//先获取并输出10 在递减
        System.out.println(new AtomicInteger(10).addAndGet(1));//先递减10-1在获取输出
        //加
        System.out.println(new AtomicInteger(10).getAndAdd(1));//先获取并输出10在加
        System.out.println(new AtomicInteger(10).addAndGet(1));//先加：10+1在获取输出

        //乘法
        System.out.println(new AtomicInteger(1).getAndUpdate(x -> 2 * x));
        System.out.println(new AtomicInteger(1).updateAndGet(x -> 2 * x));
    }
}

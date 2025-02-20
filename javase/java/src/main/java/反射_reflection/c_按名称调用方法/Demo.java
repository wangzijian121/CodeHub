package 反射_reflection.c_按名称调用方法;

import java.lang.reflect.Method;

/**
 * 按名称调用对象中的方法
 *
 * @author zijian Wang
 */
public class Demo {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("反射_reflection.demo.c_按名称调用方法.Method2");
            //方法
            Method meth = cls.getMethod("add", Integer.TYPE, Integer.TYPE);
            //传入 对象，参数
            Object retObj = meth.invoke(new Demo(), 37, 47);
            Integer retVal = (Integer) retObj;
            System.out.println(retVal.intValue());
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
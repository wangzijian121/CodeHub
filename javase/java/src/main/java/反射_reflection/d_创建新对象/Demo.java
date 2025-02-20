package 反射_reflection.d_创建新对象;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过构造函数创建对象
 *
 * @author zijian Wang
 */
public class Demo {

    public Demo() {
    }

    public Demo(int a, int b) {
        System.out.println(a + "---" + b);
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        String className = "反射_reflection.demo.d_创建新对象.数据类型.Demo";
        Class cls = Class.forName(className);
        Constructor constructor = cls.getConstructor(Integer.TYPE, Integer.TYPE);
        try {
            Object obj = constructor.newInstance(2, 3);
            System.out.println(obj);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

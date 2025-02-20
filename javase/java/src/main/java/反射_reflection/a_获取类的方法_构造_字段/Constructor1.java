package 反射_reflection.a_获取类的方法_构造_字段;

import java.lang.reflect.Constructor;

/**
 * 通过反射获取构造函数
 *
 * @author zijian Wang
 */
public class Constructor1 {
    public Constructor1() {
    }

    protected Constructor1(int i, double d) {
    }

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("反射_reflection.a_获取类的方法_构造_字段.Constructor1");

            Constructor ctorlist[]
                    = cls.getDeclaredConstructors();
            for (int i = 0; i < ctorlist.length; i++) {
                Constructor ct = ctorlist[i];
                System.out.println("方法名= " + ct.getName());
                System.out.println("类路径 = " +
                        ct.getDeclaringClass());
                Class pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++) {
                    System.out.println("参数 #" + j + " " + pvec[j]);
                }
                Class evec[] = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++) {
                    System.out.println("异常类型 #" + j + " " + evec[j]);
                }
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
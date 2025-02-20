package 反射_reflection.a_获取类的方法_构造_字段;

import java.lang.reflect.Method;

/**
 * 通过反射获取方法
 *
 * @author zijian Wang
 */
public class Method1 {
    private int f1(Object p, int x) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException();
        }
        return x;
    }

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("反射_reflection.a_获取类的方法_构造_字段.Method1");

            Method methlist[] = cls.getDeclaredMethods();
            for (int i = 0; i < methlist.length;
                 i++) {
                Method m = methlist[i];
                System.out.println("方法名= " + m.getName());
                System.out.println("decl class = " + m.getDeclaringClass());
                Class pvec[] = m.getParameterTypes();
                for (int j = 0; j < pvec.length; j++) {
                    System.out.println("参数 #" + j + "" + pvec[j]);
                }
                Class evec[] = m.getExceptionTypes();
                for (int j = 0; j < evec.length; j++) {
                    System.out.println("异常类型 #" + j + " " + evec[j]);
                }
                System.out.println("return type = " + m.getReturnType());
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
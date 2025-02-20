package 反射_reflection.a_获取类的方法_构造_字段;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 通过反射获取字段
 * @author zijian Wang
 */
public class Field1 {
    private double d;
    public static final int i = 37;
    String s = "testing";

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("反射_reflection.a_获取类的方法_构造_字段.Field1");

            Field fieldlist[]
                    = cls.getDeclaredFields();
            for (int i
                 = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                System.out.println("字段名= " + fld.getName());
                System.out.println("类引用 = " + fld.getDeclaringClass());
                System.out.println("返回类型= " + fld.getType());
                int mod = fld.getModifiers();
                System.out.println("修饰符 = " + Modifier.toString(mod));
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
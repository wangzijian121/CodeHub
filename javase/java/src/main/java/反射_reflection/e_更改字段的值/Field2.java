package 反射_reflection.e_更改字段的值;

import java.lang.reflect.Field;

/**
 * 通过反射修改对象中的值
 *
 * @author zijian Wang
 */
public class Field2 {
    public double d;

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("反射_reflection.e_更改字段的值.Field2");
            Field fld = cls.getField("d");
            Field2 f2obj = new Field2();
            System.out.println("d = " + f2obj.d);
            fld.setDouble(f2obj, 12.34);
            System.out.println("d = " + f2obj.d);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
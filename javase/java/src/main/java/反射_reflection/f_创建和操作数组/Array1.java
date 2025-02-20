package 反射_reflection.f_创建和操作数组;

import java.lang.reflect.Array;

/**
 * 此示例创建一个 10 长的字符串数组，然后将数组中的位置 5 设置为字符串值。检索并显示该值。
 *
 * @author zijian Wang
 */
public class Array1 {

    public static void main(String args[]) {
        try {
            Class cls = Class.forName("java.lang.String");
            Object arr = Array.newInstance(cls, 10);
            Array.set(arr, 5, "this is a test");
            String s = (String) Array.get(arr, 5);
            System.out.println(s);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
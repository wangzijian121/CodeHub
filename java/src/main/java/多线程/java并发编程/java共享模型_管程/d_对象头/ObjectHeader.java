package 多线程.java并发编程.java共享模型_管程.d_对象头;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zijian Wang
 */
public class ObjectHeader {
    public static void main(String[] args) {
        MyObject object = new MyObject();
        //普通对象header 12字节（8字节的Mark Word+4字节的klass Pointer)
        System.out.println("------------普通对象----------------");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.out.println("------------普通对象----------------");
        System.out.println(ClassLayout.parseInstance(new Integer(1)).toPrintable());
        //数组对象header 有16字节（8字节的Mark Word+4字节的klass Pointer+4字节的array length）
        System.out.println("------------数组对象----------------");
        int[] arrs = new int[5];
        System.out.println(ClassLayout.parseInstance(arrs).toPrintable());
    }
}

class MyObject {
    private String name;
    private int age;
}
package 数据类型;

/**
 * @author zijian Wang
 */
public class DataType {
    public static void main(String[] args) {
        //1字节
        byte b = 127;
        //2字节
        short s = 32767;
        //4字节
        int i = 2147483647;
        //8字节
        long l = 9223372036854775807L;
        System.out.println(b);
        System.out.println(b);
        System.out.println(i);
        System.out.println(l);

        //4字节
        float f = 3.1415926535747f;
        //8字节
        double d = 3.1415926535747d;
        System.out.println(f);
        System.out.println(d);

        //2字节
        char c = 65535;
        char name = '王';
        //1字节
        boolean bo = false;
        System.out.println(c);
        System.out.println((int) c);
        System.out.println(bo);

    }
}

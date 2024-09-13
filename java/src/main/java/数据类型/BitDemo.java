package 数据类型;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/13
 */
public class BitDemo {
    public static void main(String[] args) {

        //左移
        //1: 0000 0001
        System.out.println("1左移1次：" + (1 << 1));//0000 0010 = 2
        System.out.println("2左移1次：" + (2 << 1));//0000 0100 = 4
        System.out.println("4左移1次：" + (4 << 1));//0000 1000 = 8
        System.out.println("8左移1次：" + (8 << 1));//0001 0000 = 16
        System.out.println("16左移1次：" + (16 << 1));//0010 0000 = 32
        System.out.println("32左移1次：" + (32 << 1));//0100 0000 = 64
        System.out.println("64左移1次：" + (64 << 1));//1000 0000 = 64
        //左移多位
        System.out.println("左移7次：" + (1 << 7));//0000 0010 = 2

        //右位移
        System.out.println("1右移1次：" + (1 >> 1));//0000 0000 = 0
        System.out.println("1右移1次：" + (1 >>> 1));//0000 0000 = 0
        System.out.println("128右移7次：" + (128 >>> 7));//0000 0000 = 0

        //-1
        System.out.println("-1左移1次：" + (-1 << 1));//1000 0010 = -2
        System.out.println("-2左移1次：" + (-2 << 1));//1000 0100 = -4
        System.out.println("-4左移1次：" + (-4 << 1));//1000 1000 = -8


        //
        System.out.println("-5无符号右移1次：" + (-5 >>> 1));//1000 0010 = -2
    }
}

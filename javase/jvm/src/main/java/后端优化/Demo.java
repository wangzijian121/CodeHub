package 后端优化;

/**
 * 查看编译后的Class文件
 */
public class Demo {


    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int result = add(a, b);
        System.out.println(result);
    }

    private static int add(int x, int y) {
        return x + y;
    }


}

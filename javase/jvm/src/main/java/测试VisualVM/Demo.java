package 测试VisualVM;


public class Demo {

    private static int num;

    private String name = "wangzijian";

    public static void main(String[] args) {
        test();
    }

    public static int test() {
        Demo demo = new Demo();
        System.out.println(demo.name);
        return ++num;
    }
}

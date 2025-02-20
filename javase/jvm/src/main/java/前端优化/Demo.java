package 前端优化;

public class Demo {

/*    public static void main(String[] args) throws InterruptedException {

        *//**
         * 编译后只会执行这一行  System.out.println("111");
         *//*
        if (true) {
            System.out.println("111");
        } else {

            System.out.println("222");
        }
        //条件编译
        // 编译后会 int a = 7;
        int a = 3 + 4;
        System.out.println(a);
    }*/


    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println("c = " + c);
        // 下面这一行是死代码，会被前端编译时优化掉。
        int d = a * b;
    }

}

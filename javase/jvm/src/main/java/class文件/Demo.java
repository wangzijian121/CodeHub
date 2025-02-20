package class文件;

/**
 * 通过javac编译后，使用javap 和 JclassLib
 */
public class Demo {

    public final static int NUM = 1;

    public static void main(String[] args) {

        Demo demo = new Demo();

        demo.get_Str("Wang Zijian");
    }

    public String get_Str(String name) {
        System.out.println("NUM:" + NUM);
        return name + "1";
    }

}

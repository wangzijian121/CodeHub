package java常用类.stringbuffer_builder;

/**
 * 使用cfr 反编译 java -jar  .\cfr-0.152.jar  .\数据类型.Demo.class   --stringbuilder false
 * @author zijian Wang
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        for (; ; ) {
            str += "w";
            Thread.sleep(1000);
            System.out.println(str);
        }
    }
}

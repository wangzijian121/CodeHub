package java常用类.StringBuffer_StringBuilder使用;

/**
 * StringBuffer、StringBuilder 的使用
 *
 * @author zijian Wang
 */
public class Demo {
    public static void main(String[] args) {

        //StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("wangzijian").append(" hello!!!");
        System.out.println(stringBuffer);

        //StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("wangzijian").append(" hello!!!");
        System.out.println(stringBuilder);
    }
}

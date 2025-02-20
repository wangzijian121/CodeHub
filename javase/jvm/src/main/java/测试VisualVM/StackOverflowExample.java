package 测试VisualVM;

/**
 * 堆栈 溢出错误
 * Exception in thread "main" java.lang.StackOverflowError
 * at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:449)
 * at java.lang.StringBuilder.append(StringBuilder.java:142)
 */
public class StackOverflowExample {
    public static void main(String[] args) {
        recursiveMethod(0);
    }

    private static void recursiveMethod(int i) {
        System.out.println("i = " + i);
        recursiveMethod(i + 1);
    }

}
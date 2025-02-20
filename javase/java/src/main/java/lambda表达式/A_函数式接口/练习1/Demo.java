package lambda表达式.A_函数式接口.练习1;

/**
 * 题目：编写一个方法 printMessage，该方法接受一个字符串和一个函数式接口作为参数，
 * 并使用Lambda表达式在控制台上打印出字符串。
 * <p>
 * 要求：
 * <p>
 * 函数式接口的定义为 MessagePrinter，它包含一个抽象方法 void print(String message)。
 * 方法 printMessage 的签名为 void printMessage(String message, MessagePrinter printer)。
 *
 * @author zijian Wang
 */
public class Demo {
    public static void printMessage(String message, MessagePrinter printer) {
        printer.print(message);
    }

    public static void main(String[] args) {
        printMessage("wangzijian", System.out::println);
    }
}

package lambda表达式.A_函数式接口.练习3;

/**
 * 题目：编写一个方法 calculate，该方法接受两个整数和一个操作的函数式接口作为参数，并返回根据操作计算后的结果。
 * <p>
 * 要求：
 * <p>
 * 函数式接口的定义为 Operation，它包含一个抽象方法 int perform(int a, int b)，用于执行具体的操作并返回结果。
 * 方法 calculate 的签名为 int calculate(int a, int b, Operation operation)。
 */
public class Demo {
    public static int calculate(int a, int b, Operation operation) {
        return operation.perform(a, b);

    }

    public static void main(String[] args) {
        System.out.println(calculate(1, 2, (x, y) -> x + y));
    }
}

package lambda表达式.A_函数式接口.练习2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目：编写一个方法 filterNamesByCondition，
 * 该方法接受一个字符串列表和一个条件判断的函数式接口作为参数，
 * 并返回满足条件的字符串列表。
 * <p>
 * 要求：
 * <p>
 * 函数式接口的定义为 NameCondition，它包含一个抽象方法 boolean check(String name)，用于判断字符串是否满足条件。
 * 方法 filterNamesByCondition 的签名为
 * List<String> filterNamesByCondition(List<String> names, NameCondition condition)。
 *
 * @author zijian Wang
 */
public class Demo {
    public static List<String> filterNamesByCondition(List<String> names, NameCondition condition) {
        return names.stream()
                .filter(x -> condition.check(x))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a2", "A2", "a4", "a6", "B5");
        System.out.println(filterNamesByCondition(strings, x -> x.startsWith("a")));
    }
}

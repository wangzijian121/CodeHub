package lambda表达式.E_流.练习2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目：给定一个字符串列表，编写一个方法 filterAndSortStrings，该方法接受字符串列表作为参数，并按照以下规则进行处理：
 * <p>
 * 过滤掉长度小于等于3的字符串。
 * 将剩余的字符串转换为大写形式。
 * 按照字符串的长度进行升序排序。
 * 返回处理后的字符串列表。
 * 要求：
 * <p>
 * 使用流式操作来实现过滤、转换和排序。
 * 方法 filterAndSortStrings 的签名为 List<String> filterAndSortStrings(List<String> strings)。
 *
 * @author zijian Wang
 */
public class Demo {

    static List<String> filterAndSortStrings(List<String> strings) {
        return strings.stream()
                .filter(x -> x.length() > 3)
                .map(x -> x.toUpperCase())
                .sorted((x, y) -> x.length() - y.length())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("abc", "bcde", "asljdflas", "rtyydfjalkjdlfkajfd", "wang");

        System.out.println(filterAndSortStrings(strings));
    }
}

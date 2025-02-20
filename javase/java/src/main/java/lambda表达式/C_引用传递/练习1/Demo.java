package lambda表达式.C_引用传递.练习1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目：编写一个方法 convertToUpperCase，该方法接受一个字符串列表，并将列表中的每个字符串转换为大写形式后返回。
 *
 * 要求：
 *
 * 使用方法引用来实现字符串的转换。
 * 方法 convertToUpperCase 的签名为 List<String> convertToUpperCase(List<String> strings)。
 */
public class Demo {
    static List<String> convertToUpperCase(List<String> strings){
        return  strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("wang","he");
        System.out.println(convertToUpperCase(list));
    }
}

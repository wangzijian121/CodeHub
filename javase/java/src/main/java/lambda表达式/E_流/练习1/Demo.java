package lambda表达式.E_流.练习1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目：编写一个方法 getEvenNumbers，该方法接受一个整数列表，并返回列表中所有的偶数。
 * <p>
 * 要求：
 * <p>
 * 使用流式操作来筛选出偶数。
 * 方法 getEvenNumbers 的签名为 List<Integer> getEvenNumbers(List<Integer> numbers)
 */
public class Demo {
    static List<Integer> getEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 == 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        System.out.println(getEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}

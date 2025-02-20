package lambda表达式.E_流;

import java.util.Arrays;
import java.util.List;

/**
 * @author zijian Wang
 */
public class Demo {
    public static void main(String[] args) {

        //使用流式过滤和输出
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream()
                .filter(x -> x > 3)
                .forEach(System.out::println);
    }
}

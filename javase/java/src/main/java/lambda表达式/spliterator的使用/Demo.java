package lambda表达式.spliterator的使用;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * spliterator的使用
 *
 * @author zijian Wang
 */
public class Demo {

    public static void main(String[] args) {

        final long count = 200_000_000;
        Random random = new Random();
        //创建2亿条的list
        List<Integer> list = Stream.generate(() -> random.nextInt(20))
                .limit(count)
                .collect(Collectors.toList());
        
    }
}

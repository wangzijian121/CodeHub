package lambda表达式.并行流;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 并行流
 *
 * @author zijian Wang
 */
public class StreamPerformanceExample1 {

    public static void main(String[] args) {

        final long count = 200_000_000L;
        Random random = new Random();
        //创建2亿条的list
        List<Integer> list = Stream.generate(() -> random.nextInt(20))
                .limit(count)
                .collect(Collectors.toList());

        // 顺序处理
        long startTime = System.currentTimeMillis();
        list.stream()
                .map(n -> n * 2).forEach(e -> {
                });
        long endTime = System.currentTimeMillis();
        System.out.println("顺序处理耗时：" + (endTime - startTime) + "ms");

        // 并行处理
        startTime = System.currentTimeMillis();
        list.parallelStream()
                .map(n -> n * 2)
                .forEach(e -> {
                });
        endTime = System.currentTimeMillis();
        System.out.println("并行处理耗时：" + (endTime - startTime) + "ms");
    }
}

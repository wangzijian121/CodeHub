package lambda表达式.并行流;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 并行流案例2
 * @author zijian Wang
 */
public class StreamPerformanceExample2 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10_000_000; i++) {
            numbers.add(i);
        }

        long startTime = System.currentTimeMillis();

        // 使用顺序流
        Spliterator<Integer> sequentialSpliterator = numbers.spliterator();
        Stream<Integer> sequentialStream = StreamSupport.stream(sequentialSpliterator, false);
        long sumSequential = sequentialStream.mapToLong(Integer::longValue).sum();

        long endTime = System.currentTimeMillis();
        long sequentialTime = endTime - startTime;
        System.out.println("顺序流求和结果：" + sumSequential);
        System.out.println("顺序流耗时：" + sequentialTime + " 毫秒");

        startTime = System.currentTimeMillis();

        // 使用并行流
        Spliterator<Integer> parallelSpliterator = numbers.spliterator();
        Stream<Integer> parallelStream = StreamSupport.stream(parallelSpliterator, true);
        long sumParallel = parallelStream.mapToLong(Integer::longValue).sum();

        endTime = System.currentTimeMillis();
        long parallelTime = endTime - startTime;
        System.out.println("并行流求和结果：" + sumParallel);
        System.out.println("并行流耗时：" + parallelTime + " 毫秒");
    }
}
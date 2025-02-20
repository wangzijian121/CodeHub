package lambda表达式.F_方法测试;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMethods {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /**
         *筛选
         */
        filter(numbers);
        /**
         *
         */
        map(numbers);
        //
        flatMap(numbers);
        /**
         * 去重
         */
        distinct(numbers);
        /**
         * 排序
         */
        sorted(numbers);
        //
        peek(numbers);
        //
        limit(numbers);
        //
        skip(numbers);
        //
        forEach(numbers);
        //
        toArray(numbers);
        //
        reduce(numbers);
        //
        collect(numbers);
        //
        anyMatch(numbers);
        //
        allMatch(numbers);
        //
        noneMatch(numbers);
        findFirst(numbers);
        findAny(numbers);
        count(numbers);

        min(numbers);
        max(numbers);
        sum(numbers);
        average(numbers);
    }

    // filter 方法：筛选出大于 5 的元素
    private static void filter(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .filter(num -> num > 5)
                .collect(Collectors.toList());

        System.out.println("Filter Result: " + result);
    }

    // map 方法：将每个元素乘以 2
    private static void map(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .map(num -> num * 2)
                .collect(Collectors.toList());

        System.out.println("Map Result: " + result);
    }

    // flatMap 方法：将每个元素转换成一个 Stream，并合并成一个新的 Stream
    private static void flatMap(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .flatMap(num -> Stream.of(num, num * 2))
                .collect(Collectors.toList());

        System.out.println("FlatMap Result: " + result);
    }

    // distinct 方法：去重
    private static void distinct(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Distinct Result: " + result);
    }

    // sorted 方法：按照升序排序
    private static void sorted(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted Result: " + result);
    }

    // peek 方法：对每个元素执行自定义操作
    private static void peek(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println("Peek Result: " + result);
    }

    // limit 方法：限制最多只取前 3 个元素
    private static void limit(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Limit Result: " + result);
    }

    // skip 方法：跳过前 2 个元素
    private static void skip(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .skip(2)
                .collect(Collectors.toList());

        System.out.println("Skip Result: " + result);
    }

    // forEach 方法：对每个元素执行自定义操作
    private static void forEach(List<Integer> numbers) {
        numbers.stream()
                .forEach(System.out::println);
    }

    // toArray 方法：将流中的元素转换为数组
    private static void toArray(List<Integer> numbers) {
        Integer[] result = numbers.stream()
                .toArray(Integer[]::new);

        System.out.println("ToArray Result: " + Arrays.toString(result));
    }

    // reduce 方法：将流中的元素进行归约操作
    private static void reduce(List<Integer> numbers) {
        int sum = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println("Reduce Result: " + sum);
    }

    // collect 方法：将结果收集到一个新的 List 中
    private static void collect(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .collect(Collectors.toList());

        System.out.println("Collect Result: " + result);
    }

    // anyMatch 方法：判断是否有元素匹配给定条件
    private static void anyMatch(List<Integer> numbers) {
        boolean result = numbers.stream()
                .anyMatch(num -> num > 5);

        System.out.println("AnyMatch Result: " + result);
    }

    // allMatch 方法：判断是否所有元素都匹配给定条件

    private static void allMatch(List<Integer> numbers) {
        boolean result = numbers.stream()
                .allMatch(num -> num > 0);

        System.out.println("AllMatch Result: " + result);
    }

    // noneMatch 方法：判断是否没有元素匹配给定条件
    private static void noneMatch(List<Integer> numbers) {
        boolean result = numbers.stream()
                .noneMatch(num -> num < 0);

        System.out.println("NoneMatch Result: " + result);
    }

    // findFirst 方法：获取第一个元素
    private static void findFirst(List<Integer> numbers) {
        numbers.stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    // findAny 方法：获取任意一个元素
    private static void findAny(List<Integer> numbers) {
        numbers.stream()
                .findAny()
                .ifPresent(System.out::println);
    }

    // count 方法：统计元素个数
    private static void count(List<Integer> numbers) {
        long result = numbers.stream()
                .count();

        System.out.println("Count Result: " + result);
    }

    // min 方法：获取最小值
    private static void min(List<Integer> numbers) {
        numbers.stream()
                .min(Integer::compareTo)
                .ifPresent(System.out::println);
    }

    // max 方法：获取最大值
    private static void max(List<Integer> numbers) {
        numbers.stream()
                .max(Integer::compareTo)
                .ifPresent(System.out::println);
    }

    // sum 方法：求和
    private static void sum(List<Integer> numbers) {
        int result = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum Result: " + result);
    }

    // average 方法：求平均值
    private static void average(List<Integer> numbers) {
        double result = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        System.out.println("Average Result: " + result);
    }
}
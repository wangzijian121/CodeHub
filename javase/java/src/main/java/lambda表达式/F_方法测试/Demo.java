package lambda表达式.F_方法测试;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zijian Wang
 */
public class Demo {
    static List<Integer> numbers = Arrays.asList(2, 10, 3, 1, 6, 2, 4, 1, 7, 9);

    public static void main(String[] args) {

        //allMatch 全部匹配
        System.out.println("\nallMatch:" + numbers.stream()
                .allMatch(x -> x > 0));

        //anyMatch 部分匹配
        System.out.println("\nanyMatch:" + numbers.stream()
                .anyMatch(x -> x > 0));

        //builder
        Stream.Builder<Object> builder = Stream.builder();
        builder.add(1);
        builder.add(2);
        builder.add(3);
        builder.build()
                .collect(Collectors.toList())
                .forEach(x -> System.out.println("builder:" + x));

        //collect
        numbers.stream()
                .collect(Collectors.toList())
                .forEach(x -> System.out.println("collect:" + x));
        //count 数量
        System.out.println("count:" + numbers.stream().count());

        //distinct
        System.out.println("distinct");
        numbers.stream()
                .distinct()
                .forEach(System.out::print);

        //empty 空
        Stream stream = Stream.empty();
        System.out.println(stream.collect(Collectors.toList()));

        //filter 过滤
        System.out.println(numbers.stream().filter(x -> x > 5 && x < 8)
                .collect(Collectors.toList()));
        //findFirst
        numbers.stream()
                .findFirst()
                .ifPresent(x -> System.out.println("findFirst" + x));

        //findAny
        numbers.stream()
                .findAny()
                .ifPresent(x -> System.out.println("findAny:" + x));

        //map 将流中的每个元素转为另一个元素
        System.out.println("map:" + numbers.stream().map(x -> x * 2).collect(Collectors.toList()));


        //生成 hashmap
        System.out.println("hashmap:" + numbers.stream()
                .collect(Collectors.toMap(x -> new Random().nextInt(1000), x -> x)));

        //flatMap
        System.out.println("flatMap:" + numbers.stream()
                .flatMap((x -> Stream.of(x, x * 2)))
                .collect(Collectors.toList()));

        //创建List
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toList());


        //concat 关联stream
        System.out.println("concat：" + Stream.concat(
                        numbers.stream().filter(x -> x < 3),
                        numbers.stream().filter(x -> x > 7))
                .collect(Collectors.toList()));


        //peek 偷看
        System.out.println("peek");
        numbers.stream()
                .filter(x -> x > 5)
                .peek(x -> System.out.println("peek:" + x))
                .collect(Collectors.toList())
                .forEach(System.out::print);


        //max 最大值
        numbers.stream()
                .max(Integer::compareTo)
                .ifPresent(System.out::println);

        Stream.of(
                        new Student("赵丽颖", 58, 59),
                        new Student("杨颖", 56, 88),
                        new Student("迪丽热巴", 56, 99),
                        new Student("柳岩", 52, 77))
                .max(Comparator.comparingInt(Student::getAge)).ifPresent(System.out::println);


        //min 最小值
        numbers.stream()
                .min(Integer::compareTo)
                .ifPresent(System.out::println);
        //求和
        System.out.println("求和：" + numbers.stream().reduce(0, Integer::sum));

        //skip 截取
        numbers.stream().skip(3).forEach(System.out::print);


        //toArray 转为数组
        System.out.println("\ntoArray:" + numbers.stream().toArray().length);


        //generate 生成(随机数)
        Stream.generate(
                        () -> new Random().nextInt(100))
                .limit(10)
                .forEach(System.out::println);

        //reduce
        System.out.println("reduce");
        numbers.stream().reduce(Integer::sum).ifPresent(System.out::println);
        //sorted
        System.out.println("sorted:" + numbers.stream().sorted().collect(Collectors.toList()));

        //collect

        //1.收集到list
        numbers.stream().collect(Collectors.toList());
        //2.转为set
        numbers.stream().collect(Collectors.toSet());

        //转为数组
        numbers.stream().toArray();
        //聚合操作
        numbers.stream()
                .collect(Collectors.maxBy(Integer::compareTo))
                .ifPresent(x -> System.out.println("maxBy:" + x));


        //分组 group
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 58, 95),
                new Student("杨颖", 56, 88),
                new Student("迪丽热巴", 56, 99),
                new Student("柳岩", 52, 77));
        //按照年龄分组
        Map<Integer, List<Student>> collect1 = studentStream.collect(Collectors.groupingBy(Student::getAge));
        collect1.forEach((key, value) -> System.out.println(key + "-" + value));
        //按条件分组
        Stream.of(
                new Student("赵丽颖", 58, 59),
                new Student("杨颖", 56, 88),
                new Student("迪丽热巴", 56, 99),
                new Student("柳岩", 52, 77)).collect(Collectors.groupingBy(x -> {
            if (x.getScore() >= 60) {
                return "及格";
            } else {
                return "不及格";
            }
        })).forEach((key, value) -> System.out.println(key + "-" + value));
        //分区
        Stream.of(
                        new Student("赵丽颖", 58, 59),
                        new Student("杨颖", 56, 88),
                        new Student("迪丽热巴", 56, 99),
                        new Student("柳岩", 52, 77))
                .collect(Collectors.partitioningBy(s -> s.getScore() > 60))
                .forEach((key, value) -> {
                    System.out.println(key + "--分区-->" + value);
                });

    }
}

package 多线程.java并发编程.java共享模型_juc工具.线程安全集合类;

import utils.SleepUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>(16);
        concurrentHashMap.put("count", 0);
        //10个线程分别对这个map中的countkey加1.
        IntStream.range(0, 10).forEach(x ->
                new Thread(() -> {
                    for (int i = 0; i < 100L; i++) {
                        concurrentHashMap.computeIfPresent("count", (key, val) -> (int) val + 1);
                    }
                }
                ).start());
        SleepUtils.sleep(1);
        concurrentHashMap.put("count2", 2000);
        //get
        System.out.println(concurrentHashMap.get("count"));
        //forEachKey
        concurrentHashMap.forEachKey(3, x -> System.out.print(x));
        //forEachValue
        concurrentHashMap.forEachValue(3, x -> System.out.print("---" + x));
        //	isEmpty
        System.out.println("是否为空：" + concurrentHashMap.isEmpty());
        //keys
        System.out.println(concurrentHashMap.keys());
        //keySet
        concurrentHashMap.keySet().forEach(x -> System.out.println(x));
        System.out.println(concurrentHashMap.toString());
        //values
        System.out.println(concurrentHashMap.values());

        //search 搜索key和value
        Object search = concurrentHashMap.search(1, (x, y) -> {
            if (("count").equals(x) && (int) y == 1000) {
                System.out.println("key:" + x + "  value:" + y);
                return x;
            } else {
                return null;
            }
        });
        System.out.println(search.toString());

        //reduce 对所有value进行累加。
        Integer integer = concurrentHashMap.reduceValues(3, Integer::sum);
        System.out.println(integer);
        //按类型计算输出：将所有v*2 然后按long累加输出。
        System.out.println(concurrentHashMap.reduceToLong(3, (k, v) -> v * 2, 0L, Long::sum));
    }
}

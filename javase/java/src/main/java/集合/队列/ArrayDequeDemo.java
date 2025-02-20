package 集合.队列;

import java.util.ArrayDeque;

/**
 * 使用数组实现的双向队列
 * @author zijian Wang
 */
public class ArrayDequeDemo {
    public static void main(String[] args) {

        ArrayDeque queue = new ArrayDeque();
        queue.add("wangzijian1");
        queue.add("wangzijian2");
        queue.add("wangzijian3");
        queue.add("wangzijian4");
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.pollLast();
        System.out.println(queue);
    }
}

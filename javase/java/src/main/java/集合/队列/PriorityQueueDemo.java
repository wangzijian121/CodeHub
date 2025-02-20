package 集合.队列;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 优先级队列
 * 使用小顶堆实现
 *
 * @author zijian Wang
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {

        //没有定义优先级的,走的是默认实现的Comparator方法
        // 创建一个小顶堆的 Priority Queue
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 插入元素
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(4);
        priorityQueue.offer(2);

        // 弹出最小元素
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

        //定义优先级，符合优先级的在堆顶
        PriorityQueue customPriorityQueue = new PriorityQueue(
                Comparator.comparing(String::length));
        customPriorityQueue.offer("aaa");
        customPriorityQueue.offer("aa");
        customPriorityQueue.offer("aaaaaa");
        customPriorityQueue.offer("aaaa");
        System.out.println(customPriorityQueue);
        customPriorityQueue.poll();
        customPriorityQueue.poll();
        System.out.println(customPriorityQueue);


    }
}

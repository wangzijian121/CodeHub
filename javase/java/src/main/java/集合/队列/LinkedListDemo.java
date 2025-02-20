package 集合.队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zijian Wang
 */
public class LinkedListDemo {
    public static void main(String[] args) {

        Queue queue = new LinkedList();
        queue.add("wangzijian1");
        queue.add("wangzijian2");
        queue.add("wangzijian3");
        queue.add("wangzijian4");
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
    }
}

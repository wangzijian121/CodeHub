package 集合.列表;

import java.util.LinkedList;

/**
 * @author zijian Wang
 */
public class LinkedListDemo {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        list.add("wangzijian1");
        list.add("wangzijian2");
        list.add("wangzijian3");

        //索引
        System.out.println(list.indexOf("wangzijian1"));
        //出队
        System.out.println("出队：" + list.poll());
        System.out.println(list);
    }
}

package 集合.列表;

import java.util.LinkedHashSet;

/**
 * @author zijian Wang
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        LinkedHashSet linkedHashSet =new LinkedHashSet();
        linkedHashSet.add("wangzijian1");
        linkedHashSet.add("wangzijian2");
        linkedHashSet.add("wangzijian3");
        linkedHashSet.add("wangzijian3");
        System.out.println(linkedHashSet);
        linkedHashSet.add(null);
        linkedHashSet.add(null);
        System.out.println(linkedHashSet);
    }
}

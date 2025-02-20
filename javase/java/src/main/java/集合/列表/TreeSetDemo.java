package 集合.列表;

import java.util.TreeSet;

/**
 * @author zijian Wang
 */
public class TreeSetDemo {
    public static void main(String[] args) {

        TreeSet treeSet =new TreeSet<>();
        treeSet.add("wangzijian1");
        treeSet.add("wangzijian3");
        treeSet.add("wangzijian2");
        treeSet.add("wangzijian2");
        System.out.println(treeSet);
        treeSet.add(null);
        treeSet.add(null);
        System.out.println(treeSet);
    }
}

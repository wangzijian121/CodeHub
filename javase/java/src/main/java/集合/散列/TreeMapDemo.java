package 集合.散列;

import java.util.TreeMap;

/**
 * @author Administrator
 */
public class TreeMapDemo {

    public static void main(String[] args) {

        TreeMap treeMap = new TreeMap();
        treeMap.put("7", "123");
        treeMap.put("3", "123");
        treeMap.put("5", "123");

        System.out.println(treeMap.get("wangzijian"));
        //对key进行排序
        System.out.println(treeMap.descendingKeySet());

    }
}

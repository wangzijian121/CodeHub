package 集合.散列;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author zijian Wang
 */
public class WeekHashMap {
    public static void main(String[] args) {

        WeakHashMap weakHashMap = new WeakHashMap();
        String str1 = new String("wang1");
        String str2 = new String("wang2");
        weakHashMap.put(str1, "wangzijian1");
        weakHashMap.put(str2, "wangzijian2");
        System.out.println(weakHashMap);
        str1=null;
        System.gc();
        System.out.println(weakHashMap);
        System.out.println("WeakHashMap的key为弱引用，当符合弱引用的垃圾回收条件时就会被回收！");

        System.out.println("----------------HashMap👇-----------");
        HashMap map = new HashMap();
        String str3 = new String("wang1");
        String str4 = new String("wang2");
        map.put(str3, "wangzijian1");
        map.put(str4, "wangzijian2");
        System.out.println(map);
        str3=null;
        System.gc();
        System.out.println(map);
        System.out.println("HashMap的key为强引用，无法被回收掉！");
    }
}

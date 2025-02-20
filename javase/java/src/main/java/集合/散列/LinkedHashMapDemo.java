package 集合.散列;

import java.util.LinkedHashMap;

/**
 * @author Administrator
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap linkedHashMap =new LinkedHashMap();

        linkedHashMap.put(1,"111");
        linkedHashMap.put(2,"222");
        linkedHashMap.put(3,"333");
        linkedHashMap.put(4,"444");
        System.out.println(linkedHashMap);
    }
}

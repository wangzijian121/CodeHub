package 集合.散列;

import java.util.HashMap;

/**
 * @author zijian Wang
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("wangzijian","wangzijian1");
        hashMap.put("wangzijian","wangzijian2");
        hashMap.put("wangzijian3","wangzijian2");
        System.out.println(hashMap);
    }
}

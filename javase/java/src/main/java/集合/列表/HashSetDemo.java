package 集合.列表;

import java.util.HashSet;

/**
 * @author zijian Wang
 */
public class HashSetDemo {
    public static void main(String[] args) {
        HashSet hashSet =new HashSet();

        String str1="wangzijian1";
        String str2="wangzijian2";
        String str3="wangzijian3";
        String str4="wangzijian1";

        hashSet.add(str1);
        hashSet.add(str2);
        hashSet.add(str3);
        hashSet.add(str4);
        hashSet.add(null);
        hashSet.add(null);
        System.out.println(hashSet);
    }
}

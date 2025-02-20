package 集合.列表;

import java.util.List;
import java.util.Vector;

/**
 * @author zijian Wang
 */
public class VectorDemo {
    public static void main(String[] args) {
        List vector =new Vector();
        vector.add("wangzijian1");
        vector.add("wangzijian2");
        vector.add("wangzijian3");
        vector.add(null);
        vector.add(null);
        vector.add(null);
        System.out.println(vector);
    }

}

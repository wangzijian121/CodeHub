package 反射_reflection.b_通过反射模拟模拟instanceof;


/**
 * 模拟instanceof
 *
 * @author zijian Wang
 */
public class Instance1 {
    public static void main(String args[]) {
        try {
            Class cls = Class.forName("java.lang.String");
            boolean b1 = cls.isInstance(new Integer(37));
            System.out.println(b1);
            boolean b2 = cls.isInstance(new String());
            System.out.println(b2);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
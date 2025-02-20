package 反射_reflection;

/**
 * @author zijian Wang
 */
public class Demo {
    public static void main(String[] args) {
        String className = "java.lang.String";
        try {
            Class cls = Class.forName(className);
            System.out.println(cls.getName());
            System.out.println(cls.getGenericSuperclass());
            System.out.println(cls.getDeclaredMethods()[0]);
            System.out.println(cls.getDeclaredConstructors()[0]);
            System.out.println(cls.getDeclaredFields()[0]);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

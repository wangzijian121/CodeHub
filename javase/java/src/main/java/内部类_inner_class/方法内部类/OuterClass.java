package 内部类_inner_class.方法内部类;

/**
 * 方法内部类
 *
 * @author zijian Wang
 */
public class OuterClass {


    public static void fun() {
        /**
         * 方法内部类
         **/
        class InnerClass {
            private String name;
            private Integer age;

            @Override
            public String toString() {
                return "InnerClass{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }
        InnerClass innerClass = new InnerClass();
        innerClass.name = "wangzijian";
        innerClass.age = 18;
        System.out.println(innerClass);
    }

    public static void main(String[] args) {
        fun();
    }
}

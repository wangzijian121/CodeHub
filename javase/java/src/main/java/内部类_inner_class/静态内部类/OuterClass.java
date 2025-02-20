package 内部类_inner_class.静态内部类;


/**
 * 静态内部类
 *
 * @author zijian Wang
 */
public class OuterClass {
    /**
     * 静态内部类
     **/
    static class InnerClass {
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

    public static void main(String[] args) {
        OuterClass.InnerClass innerClass = new OuterClass.InnerClass();
        innerClass.name="wangzijian";
        innerClass.age=18;
        System.out.println(innerClass);

    }
}

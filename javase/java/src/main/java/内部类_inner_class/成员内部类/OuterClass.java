package 内部类_inner_class.成员内部类;

/**
 * 成员内部类
 *
 * @author zijian Wang
 */
public class OuterClass {
    /**
     * 成员内部类
     */
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

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.name = "wangzijian";
        innerClass.age = 18;
        System.out.println(innerClass);

    }
}

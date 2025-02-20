package 内部类_inner_class.匿名内部类;


/**
 * 匿名内部类
 *
 * @author zijian Wang
 */
public class OuterClass {


    public static void main(String[] args) {

        Person person = new Person() {
            @Override
            public void speak(String name) {
                System.out.println(name);
            }
        };

        Person person2 = name -> System.out.println(name);

        person.speak("hello world!");
        person2.speak("hello world!");
    }
}

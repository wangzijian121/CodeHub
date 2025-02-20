package lambda表达式.C_引用传递;

/**
 * @author zijian Wang
 */
public class Demo {

    public static void say(Say say) {
        say.say("wangzijian-ref");
    }

    public static void main(String[] args) {

        //使用lambda
        Say say = name -> System.out.println(name);
        say.say("wangzijian");

        //使用lambda
        say(name -> System.out.printf(name));

        //使用方法引用
        say(System.out::println);
    }
}
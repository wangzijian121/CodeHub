package lambda表达式.A_函数式接口;

/**
 * @author zijian Wang
 */
public class StreamExample {
    public static void main(String[] args) {

        Say say = null;
        //初始化对象方式
        Student student = new Student();
        System.out.println(student.say("wangzijian"));

        //匿名内部类的方式
        say = new Say() {
            @Override
            public String say(String name) {
                return "我的名字是：" + name;
            }
        };
        System.out.println(say.say("wangzijian"));

        //使用lambda
        say = name -> {
            return "我叫" + name;
        };
        System.out.println(say.say("wangzijian"));
    }
}
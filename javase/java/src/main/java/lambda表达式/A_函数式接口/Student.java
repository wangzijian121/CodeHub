package lambda表达式.A_函数式接口;

/**
 * @author zijian Wang
 */
public class Student implements  Say{
    @Override
    public String say(String name) {
        return "我的名字是："+name;
    }
}

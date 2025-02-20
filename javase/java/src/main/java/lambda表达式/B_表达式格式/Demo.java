package lambda表达式.B_表达式格式;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author zijian Wang
 */
public class Demo {
    public static void main(String[] args) {
        //无参数 返回结构
        Supplier supplier = () -> {
            return new String();
        };
        //有参数 无返回值
        Consumer consumer = (name) -> {
            System.out.println("打印了：" + name);
        };

        Predicate predicate = (name) -> {
            return name.equals("wangzijian") ? true : false;
        };

        //输出String类型的类
        System.out.println(supplier.get().getClass());

        //输出到控制台
        consumer.accept("wangzijian");

        //判断是否为wangzijian
        System.out.println(predicate.test("wangzijian"));
    }
}

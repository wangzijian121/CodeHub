package 设计模式_模板方法模式;

/**
 * @Description: 模板方法：将流程中不确定的步骤，（对注解的解析）抽象成接口，保存在list中，在流程中加载运行
 * 自定义工厂，固定的bean周期外，添加后处理器处理注解。
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
public class MethodTemplate {

    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcess(() -> System.out.println("初始化之前的处理！"));
        beanFactory.addBeanPostProcess(() -> System.out.println("初始化之后的处理！"));
        beanFactory.getBean();

    }
}

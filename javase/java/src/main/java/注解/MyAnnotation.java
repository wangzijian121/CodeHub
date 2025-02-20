package 注解;

/**
 * @author zijian Wang
 */

@Wang(id = 1, name = "类的注解")
public class MyAnnotation {

    @Wang(id = 2, name = "成员变量的注解")
    public String name = "王子健";


    @Wang(id = 3, name = "方法的注解")
    public String getName() {
        return this.name;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<MyAnnotation> clazz = MyAnnotation.class;

        /**
         * 获取这个类上的所有注解
         */
        Wang annotationOnClass = clazz.getAnnotation(Wang.class);
        System.out.println(annotationOnClass.id());
        System.out.println(annotationOnClass.name());
        /**
         *获取成员标量的注解
         */
        //getField必须获取public
        Wang annotationOnFiled = clazz.getField("name").getAnnotation(Wang.class);
        System.out.println(annotationOnFiled.id());
        System.out.println(annotationOnClass.name());
        /**
         *获取方法的注解
         */
        Wang annotationOnMethod = clazz.getMethod("getName").getAnnotation(Wang.class);
        System.out.println(annotationOnMethod.id());
        System.out.println(annotationOnMethod.name());

    }
}
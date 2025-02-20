package 运行时数据区域;

//MyClass: 类的定义，在 JVM 中存储为 Class 对象，在堆中
public class MyClass {

    //x :  这一行是实例变量的定义，在 JVM 中存储为对象的字段 在堆中
    private int x;

    //MyClass: 这一行是构造函数的定义，在 JVM 中存储为方法区的方法字节码 （方法区、元空间）
    public MyClass(int x) {
        //x:局部变量-> 线程栈中
        this.x = x;
    }

    // 在 JVM 中存储为方法区的方法字节码 （方法区、元空间）
    public void setX(int x) {
        //x:局部变量-> 线程栈中
        this.x = x;
    }

    //getX: 在 JVM 中存储为方法区的方法字节码 （方法区、元空间）
    public int getX() {
        return x;
    }
}

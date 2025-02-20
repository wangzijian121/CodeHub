package gc回收示例代码;

/**
 * 强引用
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        StrongReferenceTest1 test1= new StrongReferenceTest1();
        test1=null;//设置为null后，Test2对象即为不可达对象
        StrongReferenceTest2 test2=new StrongReferenceTest2();
    }
}
class StrongReferenceTest1 {byte[] arr = new byte[200000000];{System.out.println("实例Test2");}}

class StrongReferenceTest2 {byte[] arr = new byte[300000000];{System.out.println("实例Test3");}
}
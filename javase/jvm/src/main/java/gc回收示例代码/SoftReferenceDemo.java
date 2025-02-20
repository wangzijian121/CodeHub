package gc回收示例代码;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) throws InterruptedException {

        //加载为软引用对象
        SoftReference<SoftReferenceTest1> test1SoftReference = new SoftReference<SoftReferenceTest1>(new SoftReferenceTest1());

        System.out.println(test1SoftReference.get());
        SoftReferenceTest1 softReferenceTest1 =new SoftReferenceTest1();
        System.out.println(test1SoftReference.get());



        //SoftReferenceTest2 softReferenceTest2 = new SoftReferenceTest2();

/*        SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[300000000]);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());

        byte[] bytes = new byte[300000000];
        System.out.println(softReference.get());*/
    }
}

class SoftReferenceTest1 {
    private byte[] arr = new byte[300000000];

}

class SoftReferenceTest2 {
    byte[] arr2 = new byte[300000000];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("SoftReferenceTest2 被回收了");
    }

}

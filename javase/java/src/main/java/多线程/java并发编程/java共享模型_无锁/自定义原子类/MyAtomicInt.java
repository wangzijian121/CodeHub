package 多线程.java并发编程.java共享模型_无锁.自定义原子类;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.stream.IntStream;

/**
 * 使用UNSAFE实现自己的原子类
 */
public class MyAtomicInt {
    private volatile int value;
    static final long offset;
    private static Unsafe unsafe;

    static {
        try {
            //1.获取unsafe对象
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            //2.通过类对象和字段
            offset = unsafe.objectFieldOffset(MyAtomicInt.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public MyAtomicInt(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    /**
     * 添加
     *
     * @param addValue
     * @return
     */
    private boolean add(int addValue) {

        /*3.CAS 循环判断 并更新*/
        int prev;
        do {
            prev = value;
        } while (!unsafe.compareAndSwapInt(this, offset, prev, prev + addValue));
        return true;
    }

    public static void main(String[] args) {
        MyAtomicInt myAtomicInt = new MyAtomicInt(1);
        IntStream.range(0, 1000).forEach(x -> {
            Thread thread = new Thread(() -> {
                myAtomicInt.add(1);
            });
            thread.start();
        });
        System.out.println("1000线程分别+1结果：" + myAtomicInt.get());
    }
}

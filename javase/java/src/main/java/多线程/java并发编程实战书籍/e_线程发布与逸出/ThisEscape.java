package 多线程.java并发编程实战书籍.e_线程发布与逸出;

/**
 * 线程的发布和逸出
 * @author zijian Wang
 */
public class ThisEscape {
    private int value;

    public ThisEscape() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在构造函数中将this引用传递给其他线程
                doSomething();
            }
        }).start();

        // 假设在此处做了一些初始化操作
        value = 42;
    }

    private void doSomething() {
        System.out.println("Value: " + this.value);
    }

    public static void main(String[] args) {
        ThisEscape escape = new ThisEscape();
    }
}

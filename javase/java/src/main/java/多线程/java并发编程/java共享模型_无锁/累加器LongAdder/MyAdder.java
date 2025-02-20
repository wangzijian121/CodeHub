package 多线程.java并发编程.java共享模型_无锁.累加器LongAdder;

public interface MyAdder {


    /**
     * 累加
     *
     * @param i
     */
    void add(long i);


    /**
     * 获取累加器结果
     *
     * @return
     */
    long get();

    /**
     * 获取类型名
     * @return
     */
    String  getName();
}

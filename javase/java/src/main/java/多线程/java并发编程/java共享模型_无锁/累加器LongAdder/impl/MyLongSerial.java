package 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.impl;

import 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.MyAdder;

public class MyLongSerial implements MyAdder {
    private static final String NAME = "long ";
    private long sum = 0;


    public MyLongSerial() {
    }

    public String getName() {
        return NAME;
    }

    @Override
    public void add(long i) {
        sum += i;
    }

    @Override
    public long get() {
        return this.sum;
    }
}

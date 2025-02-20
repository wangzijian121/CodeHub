package 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.impl;

import 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.MyAdder;

import java.util.concurrent.atomic.LongAdder;

public class MyLongAdder implements MyAdder {
    private static final String NAME = "LongAdder";
    private LongAdder longAdder;


    public MyLongAdder() {
        this.longAdder = new LongAdder();
    }

    public String getName() {
        return NAME;
    }

    @Override
    public void add(long i) {
        longAdder.add(i);
    }

    @Override
    public long get() {
        return longAdder.sum();
    }
}

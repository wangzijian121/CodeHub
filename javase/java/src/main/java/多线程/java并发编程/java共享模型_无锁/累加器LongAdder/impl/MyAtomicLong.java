package 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.impl;

import 多线程.java并发编程.java共享模型_无锁.累加器LongAdder.MyAdder;

import java.util.concurrent.atomic.AtomicLong;

public class MyAtomicLong implements MyAdder {
    private  static  final  String NAME="AtomicLong";
    private AtomicLong atomicLong;

    public MyAtomicLong() {
        this.atomicLong = new AtomicLong();
    }

    public String getName() {
        return NAME;
    }

    @Override
    public void add(long i) {
        atomicLong.getAndSet(i);
    }

    @Override
    public long get() {
        return atomicLong.get();
    }
}

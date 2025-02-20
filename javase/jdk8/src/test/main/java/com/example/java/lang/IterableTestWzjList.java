package com.example.java.lang;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 通过 实现Iterable 接口实现forEach 输出测试
 * @param <E>
 */
public class IterableTestWzjList<E> implements Iterable<E> {

    final Object[] elementData = new Integer[]{1, 2, 3, 4, 5};


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

        final E[] elementData = (E[]) this.elementData;
        for (int i = 0; i <= elementData.length - 1; i++) {
            action.accept(elementData[i]);
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }


    public static void main(String[] args) {

        IterableTestWzjList list = new IterableTestWzjList();
        list.forEach(x -> System.out.println(x));
    }
}
package 集合.使用Comparable的排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 基于Comparable实现的 优先比较器
 *
 * @author zijian Wang
 */
public class Person implements Comparable<Person> {


    private int age;
    private boolean handicappedOrNot;

    public Person(int age) {
        this.age = age;
    }

    /**
     * 年龄最大的优先。
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Person o) {
        int p1 = this.age;
        int p2 = o.age;
        return (p1 < p2) ? -1 : (p1 == p2) ? 0 : 1;
    }

    public static void main(String[] args) {
        Person person1 = new Person(80);
        Person person2 = new Person(60);
        Person person3 = new Person(100);
        //-1
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        //原来的list 80，60，100
        System.out.println(list);
        Collections.sort(list);
        //60 80 100
        System.out.println(list);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", handicappedOrNot=" + handicappedOrNot +
                '}';
    }
}

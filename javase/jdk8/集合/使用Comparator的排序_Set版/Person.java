package 集合.使用Comparator的排序_Set版;


import java.util.HashSet;
import java.util.Set;

/**
 * 基于Comparable实现的 比较器
 *
 * @author zijian Wang
 */
public class Person {


    private int age;

    private int height;

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }


    public Person(int age, int height) {
        this.age = age;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return age == person.age;
    }

    @Override
    public int hashCode() {
        return new Integer(age).hashCode();
    }

    public static void main(String[] args) {
        Person person1 = new Person(80, 160);
        Person person2 = new Person(80, 170);
        Person person3 = new Person(60, 190);
        Person person4 = new Person(100, 150);
        //-1
        Set<Person> list = new HashSet<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        //原来的list80，60，100
        System.out.println("原始数据：" + list);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                '}';
    }
}

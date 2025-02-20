package 集合.使用Comparator的排序_内部类实现;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public static void main(String[] args) {
        Person person1 = new Person(80, 160);
        Person person2 = new Person(60, 190);
        Person person3 = new Person(100, 150);
        //-1
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        //原来的list80，60，100
        System.out.println("原始数据：" + list);

        //60 80 100
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getAge() < p2.getAge() ? -1 : p1.getAge() == p2.getAge() ? 0 : 1;
            }
        });
        System.out.println("按年龄排序：" + list);


        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getHeight() < o2.getHeight() ? -1 : o1.getHeight() == o2.getHeight() ? 0 : 1;
            }
        });
        System.out.println("按身高排序：" + list);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                '}';
    }
}

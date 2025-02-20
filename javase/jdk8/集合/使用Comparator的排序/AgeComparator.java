package 集合.使用Comparator的排序;

import java.util.Comparator;

/**
 * @author zijian Wang
 */
public class AgeComparator implements Comparator<Person> {
    /**
     * 比较仪 的比较方法
     *
     * @param p1 要比较的第一个对象。
     * @param p2 要比较的第二个对象。
     * @return
     */
    @Override
    public int compare(Person p1, Person p2) {

        return p1.getAge() < p2.getAge() ? -1 : p1.getAge() == p2.getAge() ? 0 : 1;
    }
}

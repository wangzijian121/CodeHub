package 集合.使用Comparator的排序;

import java.util.Comparator;

/**
 * 身高比较仪
 *
 * @author zijian Wang
 */
public class HeightComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getHeight() < o2.getHeight() ? -1 : o1.getHeight() == o2.getHeight() ? 0 : 1;
    }
}

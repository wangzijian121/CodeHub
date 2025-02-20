

package java.lang;

/***
 * 这个接口对实现它的每个类的对象施加了一个总的排序。这种排序被称为类的自然排序，而类的compareTo方法被称为其自然比较方法.
 * 实现此接口的对象的列表(和数组)可以按集合自动排序。sort(和Arrays.sort)。实现此接口的对象可以用作排序映射中的键或排序集合中的元素，而不需要指定比较器。
 * 类C的自然顺序被认为是与等于一致的，当且仅当
 * e1. compareto (e2) == 0对于类C的每个e1和e2具有与e1.equals(e2)相同的布尔值。
 * 注意，null不是任何类的实例，即使e.equals(null)返回false, e.compareTo(null)也应该抛出NullPointerException。
 * 强烈建议(尽管不是必需的)自然排序与等号一致。这是因为没有显式比较器的排序集(和排序映射)在与自然顺序与等号不一致的元素(或键)一起使用时表现得“奇怪”。
 * 特别是，这样的排序集(或排序映射)违反了set(或映射)的一般契约，该契约是根据equals方法定义的。
 * 例如，如果将两个键a和b相加，使得(!a.equals(b) && a. compareto (b) == 0)到一个不使用显式比较器的排序集，则第二个add操作返回false(并且排序集的大小不增加)，因为从排序集的角度来看，a和b是等效的。
 * 实际上，所有实现Comparable的Java核心类都具有与equals一致的自然顺序。
 * 一个例外是java.math。BigDecimal，其自然排序使BigDecimal对象具有相等的值和不同的精度(例如4.0和4.00)。
 *
 * 从compareTo的契约可以立即得出，商是C上的等价关系，而自然序是C上的全序。当我们说一个类的自然序与相等一致时，我们的意思是自然序的商是由类的equals(Object)方法定义的等价关系:
 * {(x, y)使得x = (y)}。
 * 该接口是Java集合框架的成员。
 */

public interface Comparable<T> {
    /**
     * 将此对象与指定对象进行顺序比较。当此对象小于、等于或大于指定对象时，返回负整数、零或正整数。
     * 实现者必须确保sgn(x.p areto (y)) == -sgn(y.p areto (x))对于所有x和y。(这意味着x.p areto (y)必须在y.p areto (x)抛出异常时抛出异常。)
     * 实现者还必须确保关系是可传递的:(x.p areto (y)>0 && y.p areto (z)>0)意味着x.p areto (z)>0。
     * 最后，实现者必须确保x.compareTo(y)==0意味着sgn(x.compareTo(z)) == sgn(y.compareTo(z))，对于所有z。
     * 强烈建议(x.compareTo(y)==0) == (x.equals(y))，但不是严格要求。一般来说，任何实现Comparable接口并违反此条件的类都应该清楚地指出这一事实。
     * 推荐的语言是“注意:这个类具有与等号不一致的自然顺序”。
     * 在前面的描述中，符号sgn(表达式)表示数学上的sgn函数，它被定义为根据表达式的值是负、零还是正返回-1、0或1中的一个。
     * <p>
     * 返回值:
     * 负整数、零或正整数，因为该对象小于、等于或大于指定对象
     */
    public int compareTo(T o);
}



package java.lang;

/**
 * 一个类实现了Cloneable接口，以指示Object.clone()方法，该方法对该类的实例进行逐个字段的复制是合法的。
 * 在没有实现Cloneable接口的实例上调用Object的clone方法将导致抛出CloneNotSupportedException异常。
 * 按照约定，实现这个接口的类应该覆盖Object。使用公共方法克隆(受保护的)。有关重写此方法的详细信息，请参阅Object.clone()。
 * 注意，这个接口不包含clone方法。因此，不可能仅仅因为对象实现了这个接口就克隆它。即使以反射方式调用clone方法，也不能保证它一定会成功。
 */
public interface Cloneable {
}

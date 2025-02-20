

package java.lang;

/**
 * 被抛出以指示调用了Object类中的clone方法来克隆对象，但该对象的类没有实现Cloneable接口。
 * 覆盖clone方法的应用程序也可以抛出此异常，以指示不能或不应该克隆对象
 */
public
class CloneNotSupportedException extends Exception {
    private static final long serialVersionUID = 5195511250079656443L;

    /**
     * Constructs a <code>CloneNotSupportedException</code> with no
     * detail message.
     */
    public CloneNotSupportedException() {
        super();
    }

    /**
     * Constructs a <code>CloneNotSupportedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public CloneNotSupportedException(String s) {
        super(s);
    }
}



package java.lang;

/**
 * Runnable接口应该由任何类实现，其实例将由线程执行。类必须定义一个名为run的无参数方法。
 * 该接口旨在为希望在活动状态下执行代码的对象提供公共协议。例如，Runnable是由Thread类实现的。处于活动状态仅仅意味着线程已经启动并且尚未停止。
 * 此外，Runnable提供了在不子类化Thread的情况下使类处于活动状态的方法。
 * 实现Runnable的类可以通过实例化Thread实例并将其自身作为目标传入，而无需子类化Thread即可运行。
 * 在大多数情况下，如果您只是计划重写run()方法而不重写其他Thread方法，则应该使用Runnable接口。
 * 这一点很重要，因为除非程序员打算修改或增强类的基本行为，否则类不应该被子类化
 */
@FunctionalInterface
public interface Runnable {

    /**
     * 当使用实现接口Runnable的对象来创建线程时，启动线程会导致在单独执行的线程中调用对象的run方法。
     * 方法运行的一般约定是它可以执行任何操作。
     */
    public abstract void run();
}

/*
 * Copyright (c) 2009, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang;

/**
 * An object that may hold resources (such as file or socket handles)
 * until it is closed. The {@link #close()} method of an {@code AutoCloseable}
 * object is called automatically when exiting a {@code
 * try}-with-resources block for which the object has been declared in
 * the resource specification header. This construction ensures prompt
 * release, avoiding resource exhaustion exceptions and errors that
 * may otherwise occur.
 *
 * @apiNote
 * <p>It is possible, and in fact common, for a base class to
 * implement AutoCloseable even though not all of its subclasses or
 * instances will hold releasable resources.  For code that must operate
 * in complete generality, or when it is known that the {@code AutoCloseable}
 * instance requires resource release, it is recommended to use {@code
 * try}-with-resources constructions. However, when using facilities such as
 * {@link java.util.stream.Stream} that support both I/O-based and
 * non-I/O-based forms, {@code try}-with-resources blocks are in
 * general unnecessary when using non-I/O-based forms.
 *
 * @author Josh Bloch
 * @since 1.7
 */

/**
 * 一个对象，它可以保存资源(如文件句柄或套接字句柄)，直到它被关闭。
 * AutoCloseable对象的close()方法在退出一个在资源规范头文件中声明了该对象的try-with-resources块时自动调用。
 *
 * 这种构造确保了及时释放，避免了资源耗尽异常和可能发生的错误。
 *
 * API:
 * 一个基类实现AutoCloseable是可能的，而且实际上很常见，即使不是它的所有子类或实例都持有可释放的资源。
 * 对于必须完全通用性操作的代码，或者当知道AutoCloseable实例需要资源释放时，建议使用try-with-resources构造。
 * 但是，当使用java.util.stream.Stream等工具同时支持基于I/ o和非基于I/ o的表单时，在使用非基于I/ o的表单时，通常不需要尝试使用资源块。
 * 自:
 */
public interface AutoCloseable {
    /**
     * 关闭此资源，放弃所有底层资源。此方法在由try-with-resources语句管理的对象上自动调用。
     * 虽然该接口方法被声明为抛出Exception，但强烈建议实现者声明close方法的具体实现来抛出更具体的异常，或者在close操作不能失败的情况下根本不抛出异常。
     * 关闭操作可能失败的情况需要实现者仔细注意。
     *
     * 强烈建议在抛出异常之前放弃底层资源，并在内部将资源标记为关闭。
     * close方法不太可能被调用超过一次，因此这确保了资源被及时释放。
     * 此外，它还减少了资源包装或被另一个资源包装时可能出现的问题。
     *
     * 强烈建议该接口的实现者不要使用close方法抛出InterruptedException。
     * 此异常与线程的中断状态交互，如果InterruptedException被抑制，则可能发生运行时错误行为。
     * 更一般地说，如果抑制异常会导致问题，则AutoCloseable。Close方法不应该抛出它。
     * 注意，与java.io的close方法不同。可关闭的，这个关闭方法不需要是幂等的。
     *
     * 换句话说，多次调用这个close方法可能会产生一些可见的副作用，这与Closeable不同。关闭，如果多次调用则要求没有效果。但是，强烈建议此接口的实现者使其封闭方法幂等。
     * 抛出:
     * 异常-如果这个资源不能被关闭
     * @throws Exception
     */
    void close() throws Exception;
}

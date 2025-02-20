/*
 * Copyright (c) 1994, 2011, Oracle and/or its affiliates. All rights reserved.
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
 * Thrown when an exceptional arithmetic condition has occurred. For
 * example, an integer "divide by zero" throws an
 * instance of this class.
 * <p>
 * {@code ArithmeticException} objects may be constructed by the
 * virtual machine as if {@linkplain java.lang.Throwable#Throwable(String,
 * Throwable, boolean, boolean) suppression were disabled and/or the
 * stack trace was not writable}.
 *
 * @author unascribed
 * @since JDK1.0
 */

/**
 * 发生异常算术条件时抛出。例如，整数“除零”抛出该类的一个实例。
 * 虚拟机可以在禁用抑制和/或堆栈跟踪不可写的情况下构造算术异常对象。
 * 测试类 {@link com.example.lang.ArithmeticExceptionTest}
 */
public class ArithmeticException extends RuntimeException {
    private static final long serialVersionUID = 2256477558314496007L;

    /**
     * Constructs an {@code ArithmeticException} with no detail
     * message.
     */
    public ArithmeticException() {
        super();
    }

    /**
     * Constructs an {@code ArithmeticException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public ArithmeticException(String s) {
        super(s);
    }
}

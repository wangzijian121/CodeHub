/*
 * Copyright (c) 1994, 2008, Oracle and/or its affiliates. All rights reserved.
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
 * Thrown to indicate that an array has been accessed with an
 * illegal index. The index is either negative or greater than or
 * equal to the size of the array.
 *
 * @author  unascribed
 * @since   JDK1.0
 */

/**
 * 抛出以指示使用非法索引访问数组。该索引要么为负值，要么大于或等于数组的大小。
 * {@link  com.example.lang.ArrayIndexOutOfBoundsExceptionTest}
 */
public
class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = -5116101128118950844L;

    /**
     * Constructs an <code>ArrayIndexOutOfBoundsException</code> with no
     * detail message.
     */
    public ArrayIndexOutOfBoundsException() {
        super();
    }

    /**
     * Constructs a new <code>ArrayIndexOutOfBoundsException</code>
     * class with an argument indicating the illegal index.
     *
     * @param   index   the illegal index.
     */
    public ArrayIndexOutOfBoundsException(int index) {
        super("Array index out of range: " + index);
    }

    /**
     * Constructs an <code>ArrayIndexOutOfBoundsException</code> class
     * with the specified detail message.
     *
     * @param   s   the detail message.
     */
    public ArrayIndexOutOfBoundsException(String s) {
        super(s);
    }
}

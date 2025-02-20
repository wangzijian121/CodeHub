/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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
//当Java虚拟机检测到正在加载的类的超类层次结构中存在循环时抛出。

/**
 * ClassCircularityError 错误通常在 Java 类的循环依赖关系导致的加载顺序问题时被触发。
 * 在 JDK 8中，这种错误通常发生在以下情况下：
 * 1. 当两个或多个类相互依赖，且它们都位于同一个包中时，可能会出现 ClassCircularityError 错误。
 *      这种情况下，当尝试加载其中一个类时，它会试图加载依赖它的另一个类，但由于后者还没有完全加载，因此会导致错误。
 * 2. 另一种常见的情况是，当一个类依赖于其子类时，也可能会触发 ClassCircularityError 错误。
 *      这种情况下，当尝试加载父类时，它需要先加载子类，但子类又依赖于父类，因此会导致错误。
 * 为了避免这种错误，可以尝试使用适当的设计模式来解耦类之间的依赖关系，或者通过重构代码来消除循环依赖。此外，也可以尝试使用不同的类加载器来解决这个问题。
 */
public class ClassCircularityError extends LinkageError {
    private static final long serialVersionUID = 1054362542914539689L;

    /**
     * Constructs a {@code ClassCircularityError} with no detail message.
     */
    public ClassCircularityError() {
        super();
    }

    /**
     * Constructs a {@code ClassCircularityError} with the specified detail
     * message.
     *
     * @param  s
     *         The detail message
     */
    public ClassCircularityError(String s) {
        super(s);
    }
}

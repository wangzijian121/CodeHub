/*
 * Copyright (c) 2005, 2010, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Collection;
import java.util.IdentityHashMap;

/*
 * Class to track and run user level shutdown hooks registered through
 * <tt>{@link Runtime#addShutdownHook Runtime.addShutdownHook}</tt>.
 *
 * @see java.lang.Runtime#addShutdownHook
 * @see java.lang.Runtime#removeShutdownHook
 */

/**
 * ApplicationShutdownHooks是Java中的一个机制，可以在应用程序关闭时执行特定的代码。当应用程序被关闭时，Java虚拟机将自动运行注册的所有ApplicationShutdownHooks。
 * 这些钩子可以用于执行一些清理操作，如关闭数据库连接、保存临时数据或释放资源等。
 *
 * 注册一个ApplicationShutdownHooks的方式是通过Runtime类的addShutdownHook()方法。该方法接受一个Thread对象作为参数，该线程将在应用程序关闭时运行。
 * 通常，这个线程对象会调用一个专门的shutdown()方法，以便在应用程序关闭时执行清理操作。
 *
 * 需要注意的是，注册的ApplicationShutdownHooks应该是线程安全的，因为它们可能会在多个线程同时调用。
 * 此外，注册ApplicationShutdownHooks的顺序可能会影响它们的执行顺序，因此需要谨慎考虑。
 *
 * 总之，ApplicationShutdownHooks提供了一个方便的机制，可以在应用程序关闭时执行一些清理操作，以确保应用程序正常关闭并释放资源。
 */
class ApplicationShutdownHooks {
    /* The set of registered hooks */
    private static IdentityHashMap<Thread, Thread> hooks;
    static {
        try {
            Shutdown.add(1 /* shutdown hook invocation order */,
                false /* not registered if shutdown in progress */,
                new Runnable() {
                    public void run() {
                        runHooks();
                    }
                }
            );
            hooks = new IdentityHashMap<>();
        } catch (IllegalStateException e) {
            // application shutdown hooks cannot be added if
            // shutdown is in progress.
            hooks = null;
        }
    }


    private ApplicationShutdownHooks() {}

    /* Add a new shutdown hook.  Checks the shutdown state and the hook itself,
     * but does not do any security checks.
     */
    static synchronized void add(Thread hook) {
        if(hooks == null)
            throw new IllegalStateException("Shutdown in progress");

        if (hook.isAlive())
            throw new IllegalArgumentException("Hook already running");

        if (hooks.containsKey(hook))
            throw new IllegalArgumentException("Hook previously registered");

        hooks.put(hook, hook);
    }

    /* Remove a previously-registered hook.  Like the add method, this method
     * does not do any security checks.
     */
    static synchronized boolean remove(Thread hook) {
        if(hooks == null)
            throw new IllegalStateException("Shutdown in progress");

        if (hook == null)
            throw new NullPointerException();

        return hooks.remove(hook) != null;
    }

    /* Iterates over all application hooks creating a new thread for each
     * to run in. Hooks are run concurrently and this method waits for
     * them to finish.
     */
    static void runHooks() {
        Collection<Thread> threads;
        synchronized(ApplicationShutdownHooks.class) {
            threads = hooks.keySet();
            hooks = null;
        }

        for (Thread hook : threads) {
            hook.start();
        }
        for (Thread hook : threads) {
            while (true) {
                try {
                    hook.join();
                    break;
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}

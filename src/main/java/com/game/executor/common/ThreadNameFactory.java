package com.game.executor.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 文江 on 2017/7/9.
 */
public class ThreadNameFactory implements ThreadFactory {
    private ThreadGroup threadGroup;
    private AtomicInteger threaNumber;
    private String name;

    public ThreadNameFactory(String name) {
        SecurityManager securityManager = System.getSecurityManager();
      threadGroup= (securityManager!=null)? securityManager.getThreadGroup(): Thread.currentThread().getThreadGroup();
        this.name=name;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, name
                + threaNumber.getAndIncrement(), 0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}

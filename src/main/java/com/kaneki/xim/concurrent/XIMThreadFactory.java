package com.kaneki.xim.concurrent;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yueqian
 * @since 2016/12/19
 * 线程工厂
 */
public class XIMThreadFactory implements ThreadFactory {

    private static final AtomicInteger threadNumber = new AtomicInteger(1);

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    private final String prefix;

    private final boolean daemonThread;

    private final ThreadGroup threadGroup;

    public XIMThreadFactory() {
        this("loginService-threadpool-" + threadNumber.getAndIncrement(), false);
    }

    public XIMThreadFactory(String prefix) {
        this(prefix, false);
    }

    public XIMThreadFactory(String prefix, boolean daemon) {
        this.prefix = StringUtils.isNotEmpty(prefix) ? prefix + "-thread-" : "";
        daemonThread = daemon;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    public Thread newThread(Runnable runnable) {
        String name = prefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(threadGroup, runnable, name, 0);
        ret.setDaemon(daemonThread);
        return ret;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }
}


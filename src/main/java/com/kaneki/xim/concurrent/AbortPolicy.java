package com.kaneki.xim.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yueqian
 * @since 2016/12/19
 * AbortPolicy功能模块
 */
public class AbortPolicy extends ThreadPoolExecutor.AbortPolicy {
    private static final Logger LOG = LoggerFactory.getLogger(AbortPolicy.class);

    private String threadName;

    public AbortPolicy() {
        this(null);
    }

    public AbortPolicy(String threadName) {
        this.threadName = threadName;
    }

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        if (threadName != null) {
            LOG.error("XIM Thread pool [{}] is exhausted, executor={}", threadName, executor.toString());
        }
        String msg = String.format("XIM["
                        + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
                        + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)]",
                threadName, executor.getPoolSize(), executor.getActiveCount(), executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getLargestPoolSize(),
                executor.getTaskCount(), executor.getCompletedTaskCount(), executor.isShutdown(), executor.isTerminated(), executor.isTerminating());
        System.out.println(msg);
        super.rejectedExecution(runnable, executor);
    }
}


package com.kaneki.xim.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yueqian
 * @since 2016/12/19
 * 线程池
 */
public class XIMThreadPool {

    private static ThreadPoolExecutor executor;

    public static Executor getExecutor(int threads) {
        String name = "XIMThreadPool";
        executor = new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new XIMThreadFactory(name, true), new AbortPolicy());
        return executor;
    }
}


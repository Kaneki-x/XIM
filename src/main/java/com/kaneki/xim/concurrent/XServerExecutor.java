package com.kaneki.xim.concurrent;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/26
 * @email yueqian@mogujie.com
 */
public class XServerExecutor {

    private final static int threadNums = 16;

    volatile private static ListeningExecutorService threadPoolExecutor;

    private static class XServerExecutorHolder {
        static final XServerExecutor instance = new XServerExecutor();
    }

    public static XServerExecutor getInstance() {
        return XServerExecutor.XServerExecutorHolder.instance;
    }

    public static void submit(Callable<Boolean> task) {
        if (threadPoolExecutor == null) {
            synchronized (XServerExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = MoreExecutors.listeningDecorator((ThreadPoolExecutor) XIMThreadPool.getExecutor(threadNums));
                }
            }
        }

        ListenableFuture<Boolean> listenableFuture = threadPoolExecutor.submit(task);
        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean result) {
            }

            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, threadPoolExecutor);
    }
}

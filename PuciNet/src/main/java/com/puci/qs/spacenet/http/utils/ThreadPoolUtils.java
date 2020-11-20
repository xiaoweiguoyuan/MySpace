package com.puci.qs.spacenet.http.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPoolUtils {

    private static ExecutorService nftp = Executors.newFixedThreadPool(10);
    private static ExecutorService singTp = Executors.newSingleThreadExecutor();

    /**
     * 有上线线程池 生命周期一分钟
     * @param runnable
     */
    public static void exeOnNewFixedThreadPool(Runnable runnable){
        nftp.execute(runnable);
    }

    /**
     * 单线程
     * @param runnable
     */
    public static void exeOnNewSingleThread(Runnable runnable){
        singTp.execute(runnable);
    }
}

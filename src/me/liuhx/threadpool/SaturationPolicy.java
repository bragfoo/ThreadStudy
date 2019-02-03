package me.liuhx.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2019/01/14 09:56
 */
public class SaturationPolicy {
    public static void main(String[] args) {
//        policy(new ThreadPoolExecutor.AbortPolicy());  //直接抛出异常
//        policy((new ThreadPoolExecutor.CallerRunsPolicy()));  //既不抛弃任务也不抛出异常，而是将某些任务回退到调用者。不会在线程池的线程中执行新的任务，而是在调用exector的线程中运行新的任务。
//        policy(new ThreadPoolExecutor.DiscardPolicy());//新任务被抛弃
        policy(new ThreadPoolExecutor.DiscardOldestPolicy());//抛弃老任务
    }

    public static void policy(RejectedExecutionHandler handler) {
        //基本线程2个，最大线程数为3，工作队列容量为5
        ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 3, 0l, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(5));
        if (handler != null) {
            exec.setRejectedExecutionHandler(handler);//设置饱和策略
        }
        for (int i = 0; i < 10; i++) {
            int id = i;
            exec.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);//休眠3秒
                } catch (InterruptedException e) {
                    System.err.println("线程被中断" + e.getMessage());
                }
                System.out.println(" 任务：" + id + "\t 工作线程: " + Thread.currentThread().getName() + " 执行完毕");
            });//提交任务
        }
        exec.shutdown();
    }
}

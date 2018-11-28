package me.liuhx.threadpool.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2018/11/27 21:05
 */
public class Main {
    public static void main(String[] args) {

        ThreadPoolGather.testOneThreadPool = Executors.newFixedThreadPool(10);
        ThreadPoolGather.testTwoThreadPool = Executors.newFixedThreadPool(10);
        ThreadPoolGather.teacherThreadPool = Executors.newSingleThreadExecutor();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        ExecuteMethod executeMethod = new ExecuteMethod();
        long initialDelay1 = 0;
        long period1 = 60;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        service.scheduleAtFixedRate(
                new Scheduled(executeMethod), initialDelay1,
                period1, TimeUnit.SECONDS);


    }
}

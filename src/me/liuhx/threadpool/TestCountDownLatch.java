package me.liuhx.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program thread
 * @description: 计数器试用
 * @author: liuhx
 * @create: 2018/11/23 14:50
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        int treadNum = 4;
        final CountDownLatch countDownLatch = new CountDownLatch(treadNum);
        for (int i = 0; i < treadNum; i++) {
            new Thread(testExcutors("线程：" + i, countDownLatch)).start();
        }
        System.out.println("等待子线程...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程执行完毕...");
    }

    public static String testExcutors(String message, CountDownLatch latch) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() ->
                {
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(message);
                }
        );
        latch.countDown();
        executorService.shutdown();
        return message;
    }
}

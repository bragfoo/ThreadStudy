package me.liuhx.threadpool;

import java.util.concurrent.Semaphore;

/**
 * @program thread
 * @description: Semaphore test
 * @author: liuhx
 * @create: 2018/11/23 15:11
 */
public class TestSemaphore {
    public static void main(String[] args) {
        //顾客人数
        int num = 10;
        //餐位
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= num; i++)
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("顾客" + num + "占用一个餐位在吃饭中...");
                    Thread.sleep(2000);
                    System.out.println("顾客" + num + "吃完释放出餐位");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
    }
}

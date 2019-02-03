package me.liuhx.threadpool.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program thread
 * @description: 示例
 * @author: liuhx
 * @create: 2019/01/07 20:46
 */
public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1024);
        new Thread(() -> {
            try {
                blockingQueue.put("1");
                Thread.sleep(1000);
                blockingQueue.put("2");
                Thread.sleep(1000);
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}


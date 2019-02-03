package me.liuhx.threadpool.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program thread
 * @description: LinkedBlockingQueue 示例
 * @author: liuhx
 * @create: 2019/01/07 21:52
 */
public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try {
                linkedBlockingQueue.put(1);
                linkedBlockingQueue.put(2);
                Thread.sleep(10 * 1000);
                linkedBlockingQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println(linkedBlockingQueue.take());
                System.out.println(linkedBlockingQueue.take());
                System.out.println(linkedBlockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

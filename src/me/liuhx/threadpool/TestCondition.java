package me.liuhx.threadpool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program thread
 * @description: 条件锁 condition
 * @author: liuhx
 * @create: 2018/11/23 16:26
 */
public class TestCondition {
    static Lock lock = new ReentrantLock();
    final static Condition insertCondition = lock.newCondition();
    final static Condition deleteCondition = lock.newCondition();
    static int num = 50;
    static AtomicInteger AtomicInteger = new AtomicInteger(0);

    static ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue();

    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) {
        for (int i = 0; i < num; i++) {
            new Thread(new insertJob()).start();
            new Thread(new deleteJob()).start();

        }
    }

    static class insertJob implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            lock.lock();
            try {
                while (concurrentLinkedQueue.size() == 5) {
                    System.out.println(Thread.currentThread().getName() + "：当前容器已满，等待有元素删除时继续执行！");
                    //当队列的元素等于5时，容器已经达到最大值无法继续进行插入操作
                    insertCondition.await();
                }
                AtomicInteger.getAndIncrement();
                concurrentLinkedQueue.add("我是元素");
                Thread.sleep(100);
                countDownLatch.countDown();
                //容器新增元素后，唤醒删除锁，表示可以进行删除操作；
                System.out.println(Thread.currentThread().getName() + "元素插入成功，当前队列大小：" + concurrentLinkedQueue.size());
                deleteCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class deleteJob implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            lock.lock();

            try {
                while (concurrentLinkedQueue.size() == 0) {
                    //当队列的元素等于0时，容器为空无法继续进行删除操作
                    System.out.println(Thread.currentThread().getName() + "：当前容器已空，等待有元素插入时继续执行！");
                    deleteCondition.await();
                }
                concurrentLinkedQueue.poll();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "元素移除成功，当前队列大小：" + concurrentLinkedQueue.size());
                //容器删除元素后，唤醒增加锁，表示可以进行插入操作；
                insertCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

package me.liuhx.threadpool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program thread
 * @description: 线程间通信
 * @author: liuhx
 * @create: 2018/11/26 11:58
 */
public class TestCondition2 {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(() -> threadExecute(business, "sub")
        ).start();
        threadExecute(business,"main");
    }

    public static void threadExecute(Business business, String threadType) {
        for (int i = 0; i < 100; i++) {
            try {
                if ("main".equals(threadType)) {
                    business.main(i);
                } else {
                    business.sub(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Business {
        private boolean bool = true;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void main(int loop) throws InterruptedException {
            lock.lock();
            try {
                while (bool) {
                    condition.await();
                }
                for (int i = 0; i < 100; i++) {
                    System.out.println("main thread seq of " + i + ", loop of " + loop);
                }
                bool = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub(int loop) throws InterruptedException {
            lock.lock();
            try {
                while (!bool) {
                    condition.await();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("sub thread seq of " + i + ", loop of " + loop);
                }
                bool = false;
                condition.signal();
            } finally {
                lock.unlock();
            }

        }
    }
}

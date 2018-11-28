package me.liuhx.threadpool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2018/11/23 16:06
 */
public class TestLock {
    //这里的锁要设置为全员变量，切勿设置为局部变量



    public static void main(String str[]) {
        int threadNum = 3;
        Lock l = new ReentrantLock();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(new LockJob(l)).start();
        }
    }

    static class LockJob implements Runnable {
        private Lock lock;
        public LockJob(Lock lock){
            this.lock=lock;
        }
        @Override
        public void run() {
            lock.lock(); //获取锁位于try块的外面
            try {
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                lock.unlock();
            }
        }
    }
}

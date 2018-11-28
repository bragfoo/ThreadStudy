package me.liuhx.threadpool;

import java.util.concurrent.Semaphore;

/**
 * @program thread
 * @description: Semaphore test
 * @author: liuhx
 * @create: 2018/11/23 15:11
 */
public class TestSemaphore {
    public static void main(String[] args){
        //顾客人数
        int num = 10;
        //餐位
        Semaphore semaphore = new Semaphore(5);
        for(int i=1;i<=num;i++)
            new Thread(new Worker(i,semaphore)).start();
    }

    static class Worker implements Runnable{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("顾客"+this.num+"占用一个餐位在吃饭中...");
                Thread.sleep(2000);
                System.out.println("顾客"+this.num+"吃完释放出餐位");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package me.liuhx.threadpool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program thread
 * @description: CyclicBarrier 例子
 * @author: liuhx
 * @create: 2019/01/08 10:15
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2,()-> System.out.println("BarrierAction 1 executed "));
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2,()-> System.out.println("BarrierAction 2 executed "));

        new Thread(() ->{
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                        " waiting at barrier 1");
                cyclicBarrier1.await();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                        " waiting at barrier 2");
                cyclicBarrier2.await();
                System.out.println(Thread.currentThread().getName() +
                        " done!");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() ->{
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() +
                        " waiting at barrier 1");
                cyclicBarrier1.await();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() +
                        " waiting at barrier 2");
                cyclicBarrier2.await();
                System.out.println(Thread.currentThread().getName() +
                        " done!");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

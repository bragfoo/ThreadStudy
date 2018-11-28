package me.liuhx.threadpool;

/**
 * @program thread
 * @description: join 方法test
 * @author: liuhx
 * @create: 2018/11/27 12:05
 */
public class TestJoin {
    public static void main(String[] args) {
        System.out.println("进入线程" + Thread.currentThread().getName());
        Thread thread1 = new Thread(() -> {
            System.out.println("进入线程" + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
        });
        thread1.start();
        System.out.println("线程"+Thread.currentThread().getName()+"等待");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"继续执行");
    }
}

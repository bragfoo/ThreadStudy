package me.liuhx.threadpool;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2019/01/12 06:22
 */
public class ThreadTest extends Thread {

    public static void main(String[] args) {

        ThreadTest threadTest1 = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();
        threadTest1.setName("呵呵1");
        threadTest2.setName("呵呵2");
        threadTest2.setPriority(Thread.MAX_PRIORITY);
        threadTest1.setPriority(Thread.MIN_PRIORITY);
        threadTest1.start();
        try {
            threadTest1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadTest2.start();
        System.out.println(Thread.currentThread().getName());
    }
    public void run(){
        System.out.println(this.getName());
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

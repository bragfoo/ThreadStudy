package me.liuhx.threadpool.synchronizedtest;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2019/01/12 13:59
 */
public class HasSynchronized {

    public static void main(String[] args) {
        Bean bean = new Bean();
        Thread thread1 = new Thread(() -> {
            synchronized (bean) {
                for (int i = bean.getJ(); i > 0; i--) {
                    bean.down();
                    System.out.println(bean.getJ());
                }
                bean.notify();
            }
        }, "A");
        synchronized (bean) {
            System.out.println("等待 thread1 完成计算。。。");
            try {
                thread1.start();
                Thread.sleep(10*1000);
                bean.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 完成计算bean.j=" + bean.getJ());
        }


    }

}

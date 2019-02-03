package me.liuhx.threadpool.synchronizedtest;

/**
 * @program thread
 * @description: 无synchronized代码
 * @author: liuhx
 * @create: 2019/01/12 10:34
 */
public class Nosynchronized implements Runnable {
    private Bean bean = new Bean();

    public static void main(String[] args) {
        Nosynchronized nosynchronized = new Nosynchronized();
        Thread thread1 = new Thread(nosynchronized, "A");
        Thread thread2 = new Thread(nosynchronized, "B");
        thread1.start();
        thread2.start();
    }

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
        while (bean.i > 0) {
            bean.down();
            System.out.println(Thread.currentThread().getName() + "___" + bean.i);
        }
    }

    class Bean {
        private int i = 100;

        private void down() {
            if (i > 0) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        }

    }
}

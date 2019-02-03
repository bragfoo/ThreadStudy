package me.liuhx.threadpool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program thread
 * @description: Exchanger 例子
 * @author: liuhx
 * @create: 2019/01/08 10:37
 */
public class TestExchanger {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "Thread-0数据",0);
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "Thread-1数据",2000);
        ExchangerRunnable exchangerRunnable3 = new ExchangerRunnable(exchanger, "Thread-2数据",3000);
        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
        new Thread(exchangerRunnable3).start();
    }
}

class ExchangerRunnable implements Runnable {


    Exchanger<String> exchanger = null;
    String object = null;
    int sleeptime = 0;

    ExchangerRunnable(Exchanger<String> exchanger, String object,int sleepTime) {
        this.exchanger = exchanger;
        this.object = object;
        this.sleeptime = sleepTime;
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
        String previous = this.object;
        try {
            Thread.sleep(this.sleeptime);
            this.object = exchanger.exchange(this.object,3, TimeUnit.SECONDS);
            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
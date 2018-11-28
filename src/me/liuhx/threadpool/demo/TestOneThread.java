package me.liuhx.threadpool.demo;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

/**
 * @program thread
 * @description: 考试1线程
 * @author: liuhx
 * @create: 2018/11/28 11:41
 */
public class TestOneThread implements Runnable {
    private Student student;
    private Phaser phaser;
    private CountDownLatch countDownLatch;

    public TestOneThread(Student student, Phaser phaser, CountDownLatch countDownLatch) {
        super();
        this.phaser = phaser;
        this.student = student;
        this.countDownLatch = countDownLatch;
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
        System.out.println("学号：" + student.getNum() + "进入考场");
        phaser.arriveAndAwaitAdvance();
        System.out.println("学号：" + student.getNum() + "开始第一次考试");
        long duration = (long) (Math.random() * 10);
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        student.setTimeOne((int)duration);
        phaser.arriveAndAwaitAdvance();
        System.out.println("学号：" + student.getNum() + "考试完毕");
        phaser.arriveAndAwaitAdvance();
        countDownLatch.countDown();
    }

}

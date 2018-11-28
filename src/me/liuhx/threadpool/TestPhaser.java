package me.liuhx.threadpool;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @program thread
 * @description: Phaser测试代码
 * @author: liuhx
 * @create: 2018/11/25 18:13
 */
public class TestPhaser {
    public static void main(String[] args) {
        Phaser phaser = new Myphaser();
        phaser.bulkRegister(4);
        for (int i = 1; i <= 4; i++) {
//            phaser.register();
            new Thread(new StudentTask(phaser,i)).start();
        }
        System.out.println(phaser.awaitAdvance(4));

    }
}

class StudentTask implements Runnable {
    private Phaser phaser;
    private int i;
    public StudentTask(Phaser phaser,int i) {
        this.phaser = phaser;
        this.i = i;
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
        System.out.println(Thread.currentThread().getName() + "--到达考试");
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() + "--第一场语文开考...");
        doExam1(i);
        System.out.println(Thread.currentThread().getName() + "--第一场语文考完...");
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() + "--第二场数学开考...");
        doExam2(i);
        System.out.println(Thread.currentThread().getName() + "--第二场数学考完...");
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() + "--第三场综合开考...");
        doExam3(i);
        System.out.println(Thread.currentThread().getName() + "--第三场综合考完...");
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() + "--第四场英语开考...");
        doExam4(i);
        System.out.println(Thread.currentThread().getName() + "--第四场英语考完...");
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + "--高考结束");
    }

    private void doExam1(int i) {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
            if (i==1){
                TimeUnit.SECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExam2(int i) {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExam3(int i) {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void doExam4(int i) {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Myphaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        //在每个阶段执行完成后回调的方法
        switch (phase) {
            case 0:
                return studentArrived();
            case 1:
                return finishFirstExam();
            case 2:
                return finishSecondExam();
            case 3:
                return finishThreeExam();
            case 4:
                return finishFourExam();
            case 5:
                return finishExam();
            default:
                return true;
        }

    }

    private boolean studentArrived() {
        System.out.println("学生准备好了,学生人数：" + getRegisteredParties());
        return false;
    }

    private boolean finishFirstExam() {
        System.out.println("第一场语文所有学生考完");
        return false;
    }

    private boolean finishSecondExam() {
        System.out.println("第二场语文所有学生考完");
        return false;
    }

    private boolean finishThreeExam() {
        System.out.println("第三场语文所有学生考完");
        return false;
    }

    private boolean finishFourExam() {
        System.out.println("第四场英语所有学生考完");
        return false;
    }

    private boolean finishExam() {
        System.out.println("第三题所有学生做完，结束考试");
        return true;
    }
}

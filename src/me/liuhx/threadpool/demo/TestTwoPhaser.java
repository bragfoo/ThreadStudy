package me.liuhx.threadpool.demo;

import java.util.concurrent.Phaser;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2018/11/28 15:17
 */
public class TestTwoPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        //在每个阶段执行完成后回调的方法
        switch (phase) {
            case 0:
                return studentSecondArrived();
            case 1:
                return startSecondExam();
            case 2:
                return finishSecondExam();
            default:
                return true;
        }

    }

    private boolean studentSecondArrived() {
        System.out.println("学生准备好参加第二场考试,学生人数：" + getRegisteredParties());
        return false;
    }

    private boolean startSecondExam() {
        System.out.println("第二场所有学生开始考试");
        return false;
    }


    private boolean finishSecondExam() {
        System.out.println("第二场所有学生做完，结束考试");
        return true;
    }
}

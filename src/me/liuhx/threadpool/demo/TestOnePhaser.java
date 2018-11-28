package me.liuhx.threadpool.demo;

import java.util.concurrent.Phaser;

/**
 * @program thread
 * @description: 考试1phaser
 * @author: liuhx
 * @create: 2018/11/28 14:00
 */
public class TestOnePhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        //在每个阶段执行完成后回调的方法
        switch (phase) {
            case 0:
                return studentFirstArrived();
            case 1:
                return startFirstExam();
            case 2:
                return finishFirstExam();
            default:
                return true;
        }

    }

    private boolean studentFirstArrived() {
        System.out.println("学生准备好参加第一场考试,学生人数：" + getRegisteredParties());
        return false;
    }

    private boolean startFirstExam() {
        System.out.println("第一场所有学生开始考试");
        return false;
    }


    private boolean finishFirstExam() {
        System.out.println("第一场所有学生做完，结束考试");
        return true;
    }
}

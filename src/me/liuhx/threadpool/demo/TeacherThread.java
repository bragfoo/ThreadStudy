package me.liuhx.threadpool.demo;

import lombok.Data;


/**
 * @program thread
 * @description: 教师线程
 * @author: liuhx
 * @create: 2018/11/28 10:42
 */
@Data
public class TeacherThread implements Runnable {
    private Student student;

    public TeacherThread(Student student) {
        super();
        this.student = student;
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
        student.setScoreOne(student.getTimeOne()*10);
        student.setScoreTwo(student.getTimeTwo()*10);
        System.out.println("num="+student.getNum()+"第一科成绩为"+student.getScoreOne()+"第二科成绩为"+student.getScoreTwo());
    }
}

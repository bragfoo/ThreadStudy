package me.liuhx.threadpool.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

/**
 * @program thread
 * @description: 执行方法
 * @author: liuhx
 * @create: 2018/11/28 12:25
 */
public class ExecuteMethod {
    public void StudentTest() {
        Phaser phaser1 = new TestOnePhaser();
        int studentNum = 10;
        phaser1.bulkRegister(studentNum);
        CountDownLatch countDownLatchOne = new CountDownLatch(studentNum);
        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= studentNum; i++) {
            Student student = Student.builder().num(i).build();
            studentList.add(student);
            ThreadPoolGather.testOneThreadPool.execute(new TestOneThread(student, phaser1, countDownLatchOne));
        }
        try {
            countDownLatchOne.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatchTwo = new CountDownLatch(studentNum);
        Phaser phaser2 = new TestTwoPhaser();
        phaser2.bulkRegister(studentNum);
        for (int i = 0; i < studentList.size(); i++) {
            ThreadPoolGather.testTwoThreadPool.execute(new TestTwoThread(studentList.get(i), phaser2, countDownLatchTwo));
        }
        try {
            countDownLatchTwo.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Student s : studentList
        ) {
            ThreadPoolGather.teacherThreadPool.execute(new TeacherThread(s));
        }
    }
}
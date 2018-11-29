package me.liuhx.threadpool.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

/**
 * @program thread
 * @description: 定时任务执行方法
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
            ThreadPoolGather.testOneThreadPool.execute(() -> {
                System.out.println("学号：" + student.getNum() + "进入考场");
                phaser1.arriveAndAwaitAdvance();
                System.out.println("学号：" + student.getNum() + "开始第一次考试");
                long duration = (long) (Math.random() * 10);
                try {
                    Thread.sleep(duration * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                student.setTimeOne((int) duration);
                phaser1.arriveAndAwaitAdvance();
                System.out.println("学号：" + student.getNum() + "考试完毕");
                phaser1.arriveAndAwaitAdvance();
                countDownLatchOne.countDown();
            });
        }

        try {
            countDownLatchOne.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatchTwo = new CountDownLatch(studentNum);
        Phaser phaser2 = new TestTwoPhaser();
        phaser2.bulkRegister(studentNum);
        for (Student student : studentList) {
            ThreadPoolGather.testTwoThreadPool.execute(() -> {
                System.out.println("学号：" + student.getNum() + "进入第二次考场");
                phaser2.arriveAndAwaitAdvance();
                System.out.println("学号：" + student.getNum() + "开始第二次考试");
                long duration = (long) (Math.random() * 10);
                try {
                    Thread.sleep(duration * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                student.setTimeTwo((int) duration);
                phaser2.arriveAndAwaitAdvance();
                System.out.println("学号：" + student.getNum() + "第二次考试完毕");
                phaser2.arriveAndAwaitAdvance();
                countDownLatchTwo.countDown();
            });
        }
        try {
            countDownLatchTwo.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Student s : studentList
        ) {
            ThreadPoolGather.teacherThreadPool.execute(()->{
                s.setScoreOne(s.getTimeOne()*10);
                s.setScoreTwo(s.getTimeTwo()*10);
                System.out.println("num="+s.getNum()+"第一科成绩为"+s.getScoreOne()+"第二科成绩为"+s.getScoreTwo());
            });
        }
    }
}
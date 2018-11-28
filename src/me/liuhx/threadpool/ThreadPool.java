package me.liuhx.threadpool;

import java.util.concurrent.*;

/**
 * @program thread
 * @description: 两种线程池实现方式
 * @author: liuhx
 * @create: 2018/11/23 13:45
 */
public class ThreadPool {
    public static void main(String[] args) {

//        testExcutors();
//        testThreadPool();
        testNewSingleThreadExecutor();
    }

    public static void testExcutors() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            final int j = i;
            executorService.execute(() ->
                    {
                        try {
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("a" + j);
                    }

            );

        }
        executorService.shutdown();
    }

    public static void testThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), (r, executor) -> {
            //如果线程池添加线程失败，则暂停1s后再同步线程
            try {
                System.out.println("线程添加失败");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 10; i++) {
            final int j = i;
            threadPoolExecutor.execute(() -> System.out.println("b" + j));
        }
        threadPoolExecutor.shutdown();
    }

    public static void testNewCachedThreadPool() {
        ExecutorService executorService =  Executors.newCachedThreadPool();
        for (int i=0;i<20;i++){
            final int j=i;
            executorService.execute(() ->
            {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a" + j);
            });
        }
    }
    public static void testNewSingleThreadExecutor(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0;i<20;i++){
            final int j=i;
            executorService.execute(() ->
            {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a" + j);
            });
        }
    }
    public static  void testNewScheduledThreadPool(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    }
}

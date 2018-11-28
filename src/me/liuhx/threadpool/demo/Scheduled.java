package me.liuhx.threadpool.demo;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2018/11/27 20:58
 */
public class Scheduled implements Runnable {
    private ExecuteMethod jobName;

    public Scheduled(ExecuteMethod job) {
        super();
        this.jobName = job;
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
        jobName.StudentTest();
    }
}

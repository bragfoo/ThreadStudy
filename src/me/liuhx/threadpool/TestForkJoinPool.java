package me.liuhx.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @program thread
 * @description: ForkjoinPool 例子
 * @author: liuhx
 * @create: 2019/01/08 14:34
 */
public class TestForkJoinPool {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(40);
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(240);
        forkJoinPool.invoke(myRecursiveAction);
    }
}

class MyRecursiveAction extends RecursiveAction {


    private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }


    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {
        if (this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<MyRecursiveAction> subtasks =
                    new ArrayList<>();
            subtasks.addAll(createSubtasks());
            for (RecursiveAction subtask : subtasks) {
                subtask.fork();
            }
        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subtasks =
                new ArrayList<>();
        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }
}

package me.liuhx.threadpool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program thread
 * @description: 读写锁定
 * @author: liuhx
 * @create: 2019/01/08 15:03
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        int threadNum = 3;
        ReadWriteLock l = new ReentrantReadWriteLock();

        for (int i = 1; i <= threadNum; i++) {
            new Thread(()->{

            }).start();
        }
    }
}

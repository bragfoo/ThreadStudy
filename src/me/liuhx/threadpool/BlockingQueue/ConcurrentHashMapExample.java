package me.liuhx.threadpool.BlockingQueue;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program thread
 * @description: ConcurrentHashMap示例
 * @author: liuhx
 * @create: 2019/01/07 22:19
 */
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.put("5", "e");
        map.put("6", "f");
        map.put("7", "g");
        map.put("8", "h");

        new Thread(()->{
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.remove("6");
        }).start();

        new Thread(()->{
            Set set = map.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                System.out.println(map.get(iterator.next()));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

package me.liuhx.threadpool.synchronizedtest;

/**
 * @program thread
 * @description:
 * @author: liuhx
 * @create: 2019/01/12 14:51
 */

public class Bean{
    private int j=100;
    public void down(){
        j--;
    }
    public int getJ(){
        return j;
    }
}

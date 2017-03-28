package com.ronin.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/3/28.
 */

public class SafeIncNumBean {
    private int count =0;
    private ReentrantLock lock = new ReentrantLock();

    public void inc(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount(){
        return count;
    }
}

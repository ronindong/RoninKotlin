package com.ronin.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2017/4/18.
 */

public class CustomTask implements Callable<Integer> {

    private int mCount = 10;

    public CustomTask(int count) {
        if (count > 10) {
            this.mCount = count;
        }
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("CustomTask call ...");
        int sum = 0;
        for (int i = 0; i < mCount; i++) {
            sum += i;
        }

        return sum;
    }
}

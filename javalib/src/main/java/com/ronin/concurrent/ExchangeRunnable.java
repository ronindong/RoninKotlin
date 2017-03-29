package com.ronin.concurrent;

import java.util.concurrent.Exchanger;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ExchangeRunnable implements Runnable {
    private Exchanger exchanger;
    private Object obj = null;

    public ExchangeRunnable(Exchanger exchanger, Object obj) {
        this.exchanger = exchanger;
        this.obj = obj;
    }

    @Override
    public void run() {
        Object preivous = this.obj;
        try {
            this.obj = exchanger.exchange(obj);
            System.out.println(Thread.currentThread().getName()+"--previous:"
                    + preivous + ",after:" + this.obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

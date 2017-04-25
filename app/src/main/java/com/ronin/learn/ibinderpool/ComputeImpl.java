package com.ronin.learn.ibinderpool;

import android.os.RemoteException;

import com.ronin.eventbus.kotlin.ICompute;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("a+b=" + (a + b));
        return a + b;
    }
}

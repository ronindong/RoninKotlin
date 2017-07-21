package com.ronin.learn.activity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.ronin.eventbus.kotlin.ICompute;
import com.ronin.eventbus.kotlin.ILogin;
import com.ronin.learn.ibinderpool.BinderPool;
import com.ronin.learn.ibinderpool.ComputeImpl;
import com.ronin.learn.ibinderpool.LoginImpl;

import java.util.concurrent.Executors;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityManager

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                testBinderPool();
            }
        });

    }

    private void testBinderPool() {
        BinderPool binderPool = BinderPool.getInstance(this);
        IBinder binder = binderPool.queryBinder(BinderPool.CODE_LOGIN);

        ILogin login = LoginImpl.asInterface(binder);
        try {
            login.login("asfas", "325325s");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("---------------compute---------");
        ICompute compute = ComputeImpl
                .asInterface(binderPool.queryBinder(BinderPool.CODE_COMPUTE));
        try {
            compute.add(2, 5);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}

package com.ronin.learn.ibinderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.ronin.eventbus.kotlin.IBinderPool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/4/25.
 */

public final class BinderPool {

    public static final int CODE_COMPUTE = 0X001;
    public static final int CODE_LOGIN = 0X002;

    private Context mContext;
    private static volatile BinderPool sBinderPool;
    private IBinderPool mBinderPool;
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);

    private IBinder.DeathRecipient mDeatchRecip = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mBinderPool.asBinder().unlinkToDeath(mDeatchRecip, 0);
            mBinderPool = null;

            connService();
        }
    };

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);

            try {
                mBinderPool.asBinder().linkToDeath(mDeatchRecip, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connService();
    }

    public static BinderPool getInstance(Context context) {
        if (sBinderPool == null) {
            synchronized (BinderPool.class) {
                if (sBinderPool == null) {
                    sBinderPool = new BinderPool(context);
                }
            }
        }
        return sBinderPool;
    }


    public IBinder queryBinder(int code) {
        IBinder iBinder = null;

        try {
            if (mBinderPool != null) {
               iBinder =  mBinderPool.queryBinder(code);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return iBinder;
    }

    /**
     * 绑定服务
     */
    private void connService() {
        mCountDownLatch = new CountDownLatch(1);

        Intent intent = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);

        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static class BinderPoolImpl extends IBinderPool.Stub {
        @Override
        public IBinder queryBinder(int code) throws RemoteException {

            IBinder iBinder = null;
            switch (code) {
                case CODE_COMPUTE:
                    iBinder = new ComputeImpl();
                    break;
                case CODE_LOGIN:
                    iBinder = new LoginImpl();
                    break;
            }
            return iBinder;
        }
    }

}

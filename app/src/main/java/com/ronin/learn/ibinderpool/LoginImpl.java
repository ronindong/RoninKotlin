package com.ronin.learn.ibinderpool;

import android.os.RemoteException;

import com.ronin.eventbus.kotlin.ILogin;

/**
 * Created by Administrator on 2017/4/25.
 */

public class LoginImpl extends ILogin.Stub {
    @Override
    public boolean login(String name, String psw) throws RemoteException {
        System.out.println("name="+name+",psw="+psw);
        return false;
    }
}

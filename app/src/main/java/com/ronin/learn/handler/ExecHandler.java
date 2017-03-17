package com.ronin.learn.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/3/17.
 */

public class ExecHandler extends Handler {
    private final WeakReference<Callback> mCallback;

    ExecHandler() {
        mCallback = null;
    }

    ExecHandler(WeakReference<Handler.Callback> callback) {
        mCallback = callback;
    }

    ExecHandler(Looper looper) {
        super(looper);
        mCallback = null;
    }

    ExecHandler(Looper looper, WeakReference<Handler.Callback> callback) {
        super(looper);
        mCallback = callback;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (mCallback == null) {
            return;
        }
        final Handler.Callback callback = mCallback.get();
        if (callback == null) { // Already disposed
            return;
        }
        callback.handleMessage(msg);
    }
}

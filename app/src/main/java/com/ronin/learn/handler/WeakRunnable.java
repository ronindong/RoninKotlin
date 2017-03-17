package com.ronin.learn.handler;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/3/17.
 */


public class WeakRunnable implements Runnable {
    private final WeakReference<Runnable> mDelegate;
    private final WeakReference<ChainedRef> mReference;

    WeakRunnable(WeakReference<Runnable> delegate, WeakReference<ChainedRef> reference) {
        mDelegate = delegate;
        mReference = reference;
    }

    @Override
    public void run() {
        final Runnable delegate = mDelegate.get();
        final ChainedRef reference = mReference.get();
        if (reference != null) {
            reference.remove();
        }
        if (delegate != null) {
            delegate.run();
        }
    }
}

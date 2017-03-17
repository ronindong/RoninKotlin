package com.ronin.learn.handler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2017/3/17.
 */

public class ChainedRef {
    @Nullable
    ChainedRef next;
    @Nullable
    ChainedRef prev;
    @NonNull
    final Runnable runnable;
    @NonNull
    final WeakRunnable wrapper;

    @NonNull
    Lock lock;

    public ChainedRef(@NonNull Lock lock, @NonNull Runnable r) {
        this.runnable = r;
        this.lock = lock;
        this.wrapper = new WeakRunnable(new WeakReference<>(r), new WeakReference<>(this));
    }

    public WeakRunnable remove() {
        lock.lock();
        try {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        } finally {
            lock.unlock();
        }
        return wrapper;
    }

    public void insertAfter(@NonNull ChainedRef candidate) {
        lock.lock();
        try {
            if (this.next != null) {
                this.next.prev = candidate;
            }

            candidate.next = this.next;
            this.next = candidate;
            candidate.prev = this;
        } finally {
            lock.unlock();
        }
    }

    @Nullable
    public WeakRunnable remove(Runnable obj) {
        lock.lock();
        try {
            ChainedRef curr = this.next; // Skipping head
            while (curr != null) {
                if (curr.runnable == obj) { // We do comparison exactly how Handler does inside
                    return curr.remove();
                }
                curr = curr.next;
            }
        } finally {
            lock.unlock();
        }
        return null;
    }
}

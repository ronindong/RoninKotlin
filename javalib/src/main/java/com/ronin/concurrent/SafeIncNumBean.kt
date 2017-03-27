package com.ronin.concurrent

import java.util.concurrent.locks.ReentrantLock

/**
 * Created by Administrator on 2017/3/27.
 */
class SafeIncNumBean {
    private var count: Int = 0
    val lock: ReentrantLock = ReentrantLock()

    fun inc() {
        lock.lock()
        try {
            count++
        } finally {
            lock.unlock()
        }
    }

    fun getCount(): Int {
        return count
    }
}
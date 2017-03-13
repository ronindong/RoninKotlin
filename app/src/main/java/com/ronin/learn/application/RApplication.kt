package com.ronin.learn.application

import android.app.Application

/**
 * Created by Administrator on 2017/3/13.
 */
class RApplication : Application() {

    companion object {
        private var instance: RApplication? = null
        fun instance() = instance!!

    }


    override fun onCreate() {
        super.onCreate()
        instance = this


    }
}
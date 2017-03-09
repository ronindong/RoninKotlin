package com.ronin.learn.inheritance

/**
 * Created by Administrator on 2017/3/9.
 */
abstract  class IJson(private val name: String) {
    private val info = "IJson"

    init {
//        println("IJson param name:" + name)
    }

    open fun parse(jsonStr: String): String {
        return info
    }

    abstract fun printInfo()

}
package com.ronin.learn.inheritance

/**
 * Created by Administrator on 2017/3/9.
 */
data class Fastson(val name: String) : IJson(name) {

    override fun printInfo() {
        println("Fastson info:" + name)
    }

    override fun parse(jsonStr: String): String {
        return jsonStr
    }
}
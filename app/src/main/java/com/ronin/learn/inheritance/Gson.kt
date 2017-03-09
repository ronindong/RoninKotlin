package com.ronin.learn.inheritance
/**
 * Created by Administrator on 2017/3/9.
 */
data class Gson(val name: String) : IJson(name) {
    override fun printInfo() {
        println("Gson info:" + name)
    }

    override fun parse(jsonStr: String): String {
        return jsonStr
    }

    fun parse2(jsonStr: String = "", tag: String = ""): String {

        return jsonStr + tag
    }
}
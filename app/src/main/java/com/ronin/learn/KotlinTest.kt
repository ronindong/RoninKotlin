package com.ronin.learn

import com.ronin.learn.dagger2.ComponentInject
import com.ronin.learn.inheritance.Fastson
import com.ronin.learn.inheritance.Gson
import com.ronin.learn.inheritance.IJson

/**
 * Created by Administrator on 2017/3/9.
 */
class KotlinTest {


    companion object {
        private val instance: KotlinTest? = null
        fun instance() = instance!!
    }

    fun testKotlinInheritance() {
        var json: IJson = Fastson("alibaba fastson")
        json.printInfo()
        println(json.parse("Fastson"))

        json = Gson("google gson")
        json.printInfo()
        println(json.parse("gson"))
        println(json.parse2("kotlin method overload parse2"))
    }

    fun testKotlinArray() {
        val jsonList = listOf<IJson>(Fastson("fastjson"), Gson("gson"))
        for (json in jsonList) {
            json.printInfo()
        }
        println("******分割线***********")
        jsonList.forEach(::println)

    }


    fun testDagger2() {
        ComponentInject().init()
        sum(2)
    }

    fun get(a: Int): Int {

        return a + 10
    }

    fun  sum(a: Int, b: Int = get(a) ){

        println("get:" + (a + get(a)))
    }

}



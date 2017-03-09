package com.ronin.learn

import com.ronin.learn.inheritance.Fastson
import com.ronin.learn.inheritance.Gson
import com.ronin.learn.inheritance.IJson

/**
 * Created by Administrator on 2017/3/9.
 */
class KotlinTest {

    fun testKotlinInheritance() {
        var json: IJson = Fastson("alibaba fastson")
        json.printInfo()
        println(json.parse("Fastson"))

        json = Gson("google gson")
        json.printInfo()
        println(json.parse("gson"))
    }

    fun testKotlinArray() {
        val jsonList = listOf<IJson>(Fastson("fastjson"), Gson("gson"))
        for (json in jsonList) {
            json.printInfo()
        }
        println("******分割线***********")
        jsonList.forEach(::println)

    }
}

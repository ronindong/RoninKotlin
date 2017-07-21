package com.ronin.learn.activity

import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import com.ronin.eventbus.kotlin.R
import com.ronin.learn.util.XHandler
import kotlinx.android.synthetic.main.activity_second.*

class SecActivity : AppCompatActivity() {

    lateinit var age: SecActivity
    //使用name的时候才会被赋值
    private val name: Int by lazy {
        1
    }

    var x: Int = 0
        set(value) {
            field = value
        }
        get() = field

    var names: Array<String> = arrayOf("1", "2")
    var ints = intArrayOf(1, 2)
    var code = if (false) 1 else 2

    fun testVarargs(vararg name: String) {}

    fun testGetClass() {
        val clazz = SecActivity::class.java
        val cls = SecActivity().javaClass
    }

    var xhandler1: XHandler = object : XHandler() {
        override fun handleMessageOnWorker(msg: Message?) {
            super.handleMessageOnWorker(msg)
            println("1-->handleMessageOnWorker")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv_info.text = if (intent == null)
            "intent null"
        else
            intent.extras["from"]?.toString()+intent.extras["num"]?.toString()

        xhandler1.sendEmptyMessageOnWorker(0)
        /*relativeLayout {
            textView {
                text = if (intent == null)
                    "intent null"
                else
                    intent.extras["from"]?.toString()

            }
        }*/

    }


}

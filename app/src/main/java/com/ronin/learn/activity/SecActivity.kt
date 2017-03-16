package com.ronin.learn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ronin.eventbus.kotlin.R
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext

class SecActivity : AppCompatActivity() {

    lateinit var age:SecActivity

    //使用name的时候才会被赋值
    private val name :Int by lazy {
        1
    }

    var x: Int = 0
        set(value) {
            field = value
        }
        get() = field

    var names:Array<String> = arrayOf("1","2")
    var ints = intArrayOf(1,2)
    var code = if(false) 1 else 2

    fun testVarargs(vararg name:String){}

    fun testGetClass(){
        val clazz = SecActivity::class.java
        val cls = SecActivity().javaClass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

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

inline fun testInline() {
   var coroutine: CoroutineContext
    var cont:Continuation<String>
}

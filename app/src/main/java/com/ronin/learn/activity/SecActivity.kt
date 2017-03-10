package com.ronin.learn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.textView

class SecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            textView {
                text = if (intent == null)
                    "intent null"
                else
                    intent.extras["from"]?.toString()

            }
        }

    }


}

inline fun testInline() {}

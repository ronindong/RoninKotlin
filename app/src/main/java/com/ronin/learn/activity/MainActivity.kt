package com.ronin.learn.activity

import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.ronin.eventbus.kotlin.R
import com.ronin.learn.adapter.MyAdapter
import com.ronin.learn.entity.Status
import com.ronin.learn.util.XHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.mockwebserver.MockWebServer
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val list: List<String> = listOf("adf", "2sdfas", "sfa", "sfga", "wdvh", "ksdg")
    var mAdapter: MyAdapter = MyAdapter()
    lateinit var mockWebSocket: MockWebServer

    var xhandler1: XHandler = object : XHandler() {
        override fun handleMessageOnWorker(msg: Message?) {
            super.handleMessageOnWorker(msg)
            println("1-->handleMessageOnWorker")
        }
    }

    var xhandler2: XHandler = object : XHandler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            println("2-->handleMessage")
        }
    }

    var xhandler3: XHandler = object : XHandler() {
        override fun handleMessageOnWorker(msg: Message?) {
            super.handleMessageOnWorker(msg)
            println("3-->handleMessageOnWorker")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initView()

        xhandler2.sendEmptyMessage(0)
        xhandler1.sendEmptyMessageOnWorker(0)
        xhandler3.sendEmptyMessageOnWorker(0)

    }

    private fun initView() {
        mAdapter.openLoadAnimation()
        mAdapter.setNotDoAnimationCount(3)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            var content: String? = "not click view"
            val status: Status = adapter.getItem(position) as Status
            val resId = view.id
            when (resId) {

                R.id.imgView -> {
                    content = "img:" + status.userAvatar
                    startActivity<SecActivity>("from" to "MainActivity", "num" to 1)
                }

                R.id.tweetName -> {
                    content = "username:" + status.userName
                }
                R.id.tweetText
                -> {
                    content = "text:" + status.text
                }
            }
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
            true
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter

    }


}


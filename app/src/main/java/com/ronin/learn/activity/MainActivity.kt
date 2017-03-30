package com.ronin.learn.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.ronin.eventbus.kotlin.R
import com.ronin.learn.adapter.MyAdapter
import com.ronin.learn.entity.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.mockwebserver.MockWebServer

class MainActivity : AppCompatActivity() {

    private val list: List<String> = listOf("adf", "2sdfas", "sfa", "sfga", "wdvh", "ksdg")
    var mAdapter: MyAdapter = MyAdapter()
    lateinit var mockWebSocket: MockWebServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        setSupportActionBar(toolbar)


//        textView.onClick {
//            Toast.makeText(this,"onclick",Toast.LENGTH_SHORT).show()
//            startActivity<SecActivity>("from" to "MainActivity","num" to 1)
//        }

        mAdapter.openLoadAnimation()
        mAdapter.setNotDoAnimationCount(3)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            var content: String? = "not click view"
            val status: Status = adapter.getItem(position) as Status
            val resId = view.id
            when (resId) {

                R.id.imgView -> {
                    content = "img:"+status.userAvatar
                }

                R.id.tweetName -> {
                    content = "username:"+status.userName
                }
                R.id.tweetText
                -> {
                    content = "text:"+status.text
                }
            }
            Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter


    }

}


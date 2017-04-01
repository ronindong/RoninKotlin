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
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val list: List<String> = listOf("adf", "2sdfas", "sfa", "sfga", "wdvh", "ksdg")
    var mAdapter: MyAdapter = MyAdapter()
    lateinit var mockWebSocket: MockWebServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initView()
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


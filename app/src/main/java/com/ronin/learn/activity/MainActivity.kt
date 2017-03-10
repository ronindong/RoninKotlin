package com.ronin.learn.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.ronin.eventbus.kotlin.R
import com.ronin.learn.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val list: List<String> = listOf("adf", "2sdfas", "sfa", "sfga", "wdvh", "ksdg")
    var mAdapter: MyAdapter = MyAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        setSupportActionBar(toolbar)

        fab.setOnClickListener(
                View.OnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                }
        )

        textView.onClick {
            Toast.makeText(this,"onclick",Toast.LENGTH_SHORT).show()
            startActivity<SecActivity>("from" to "MainActivity")
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        testInline()

    }

}


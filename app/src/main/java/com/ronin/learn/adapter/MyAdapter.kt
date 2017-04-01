package com.ronin.learn.adapter

import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ronin.eventbus.kotlin.R
import com.ronin.learn.data.DataServer
import com.ronin.learn.entity.Status

/**
 * Created by Administrator on 2017/3/10.
 */
class MyAdapter
    : BaseQuickAdapter<Status, BaseViewHolder>(R.layout.layout_animation,
        DataServer.getSimpleData(100)) {

    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View?) {
            Toast.makeText(mContext, "触发 ClickableSpan", Toast.LENGTH_SHORT).show()
        }

        override fun updateDrawState(ds: TextPaint?) {
            super.updateDrawState(ds)
            ds!!.color = mContext.resources.getColor(android.R.color.holo_red_dark);
            ds!!.isUnderlineText = true
        }
    }


    override fun convert(helper: BaseViewHolder, item: Status?) {
        helper.addOnClickListener(R.id.imgView)
                .addOnClickListener(R.id.tweetName)
                .addOnClickListener(R.id.tweetText)

        when (helper.layoutPosition % 3) {
            0 -> helper.setImageResource(R.id.imgView, R.drawable.animation_img1)
            1 -> helper.setImageResource(R.id.imgView, R.drawable.animation_img2)
            2 -> helper.setImageResource(R.id.imgView, R.drawable.animation_img3)
        }

        helper.getView<TextView>(R.id.tweetName)?.text = "Hoteis in Rio de Janeiro"
        val msg = "\"He was one of Australia's most of distinguished artistes, renowned for his portraits\""
        helper.getView<TextView>(R.id.tweetText)?.text = msg
        helper.getView<TextView>(R.id.tweetText)?.movementMethod = LinkMovementMethod()

    }

}

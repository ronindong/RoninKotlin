package com.ronin.eventbus.kotlin

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * Created by Administrator on 2017/3/8.
 */
internal class HandlerPoster(private val eventBus: EventBus,
                    looper: Looper,
                    private val maxMillsInsideHandlerMessage: Int) : Handler(looper) {

    private val queue: PendingPostQueue
    private var handlerActive: Boolean = false

    init {
        queue = PendingPostQueue()
    }

    fun enqueue(subscription: Subscription, event: Any) {
        var pendingPost = PendingPost.obtainPendingPost(subscription, event)
        synchronized(this) {
            queue.enqueue(pendingPost)
            if (!handlerActive) {
                handlerActive = true
                if (!sendMessage(obtainMessage())) {
                    throw IllegalStateException()

                }
            }
        }
    }


    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)

    }
}
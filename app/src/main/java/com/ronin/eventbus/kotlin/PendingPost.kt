package com.ronin.eventbus.kotlin

/**
 * Created by Administrator on 2017/3/8.
 */
internal class PendingPost private constructor(var event: Any?, var subscription: Subscription?) {
    var next: PendingPost? = null

    companion object {
        val pendingPostPool = ArrayList<PendingPost>()

        fun obtainPendingPost(subscription: Subscription, event: Any): PendingPost {

            synchronized(pendingPostPool) {
                val size = pendingPostPool.size
                if (size > 0) {
                    val pendingPost = pendingPostPool.removeAt(size - 1)
                    pendingPost.event = event
                    pendingPost.subscription = subscription
                    pendingPost.next = null
                }
            }
            return PendingPost(event, subscription)
        }

        fun releasePendingPost(pendingPost: PendingPost) {
            pendingPost.event = null
            pendingPost.subscription = null
            pendingPost.next = null
            synchronized(pendingPostPool) {
                if (pendingPostPool.size < 10000) {
                    pendingPostPool.add(pendingPost)

                }
            }

        }


    }

}
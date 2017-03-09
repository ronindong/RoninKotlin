package com.ronin.eventbus.kotlin

/**
 * Created by ronindong on 2017/3/8.
 */
internal class PendingPostQueue {

    private var head:PendingPost? = null
    private var tail:PendingPost? = null

    @Synchronized
    fun enqueue(pendingPost: PendingPost?) {
        if (pendingPost == null) {
            throw NullPointerException("null cannot be enqueued")
        }
        if (tail != null) {
            tail!!.next = pendingPost
            tail = pendingPost
        } else if (head == null) {
            head = pendingPost
            tail = pendingPost
        } else {
            throw IllegalStateException("Head present, but no tail")
        }
    }
    @Synchronized
    fun poll(): PendingPost? {
        val pendingPost = head
        if (head != null) {
            head = head!!.next
            if (head == null) {
                tail = null
            }
        }
        return pendingPost
    }


    @Synchronized @Throws(InterruptedException::class)
    fun poll(maxMillisToWait: Int): PendingPost? {
        if (head == null) {
            throw NullPointerException("null cannot be poll")
        }
        return poll()
    }


}

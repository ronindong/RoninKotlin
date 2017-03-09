package com.ronin.eventbus.kotlin

/**
 * Created by Administrator on 2017/3/8.
 */
class NoSubscriberEvent(
        /** The [EventBus] instance to with the original event was posted to.  */
        val eventBus: EventBus,
        /** The original event that could not be delivered to any subscriber.  */
        val originalEvent: Any)
package com.ronin.learn.data

import com.ronin.learn.entity.Status

/**
 * Created by Administrator on 2017/3/30.
 */
object DataServer {
    fun getSimpleData(length: Int): List<Status> {
        var listStatus: MutableList<Status> = mutableListOf()

        for (i: Int in 0..length) {
            var status = Status(i % 2 == 0,
                    "BaseRecyclerViewAdpaterHelper https://www.recyclerview.org",
                    "Chad" + i,
                    "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460",
                    "04/05/" + i)
            listStatus.add(status)
        }
        return listStatus
    }
}
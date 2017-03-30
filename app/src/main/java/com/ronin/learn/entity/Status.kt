package com.ronin.learn.entity

/**
 * Created by Administrator on 2017/3/30.
 */
data class Status(var isRetweet: Boolean = false,
                  var text: String,
                  var userName: String,
                  var userAvatar: String,
                  var createdAt: String)
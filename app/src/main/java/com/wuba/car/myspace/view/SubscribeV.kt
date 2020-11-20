package com.wuba.car.myspace.view

import com.wuba.car.myspace.entity.SubscribeBean

open interface SubscribeV {

    fun onResult(subscribeBean: SubscribeBean?, isRefresh: Boolean, hasNext: Boolean)
}
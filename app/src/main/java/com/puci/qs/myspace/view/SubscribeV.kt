package com.puci.qs.myspace.view

import com.puci.qs.myspace.entity.SubscribeBean

open interface SubscribeV {

    fun onResult(subscribeBean: SubscribeBean?, isRefresh: Boolean, hasNext: Boolean)
}
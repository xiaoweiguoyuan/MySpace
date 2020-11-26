package com.puci.qs.myspace.view

import com.puci.qs.myspace.entity.SubscribeBean
import retrofit2.Call

open interface SubscribeV {

    fun onResult(subscribeBean: SubscribeBean?, isRefresh: Boolean, hasNext: Boolean)
    fun onFail(call: Call<SubscribeBean>, t: Throwable)
}
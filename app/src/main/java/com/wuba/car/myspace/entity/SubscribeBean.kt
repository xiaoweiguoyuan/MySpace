package com.wuba.car.myspace.entity

open class SubscribeBean {

    var page_size: Int = 0 //default 10
    var cursor: String = ""
    var episodes: List<EpisodeDetail.CurEpisode>? = null
}
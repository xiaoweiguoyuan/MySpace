package com.wuba.car.myspace.view

import com.wuba.car.myspace.entity.CommentBean
import com.wuba.car.myspace.entity.EpisodeDetail

open interface PlayingV {

    fun onComments(lst: ArrayList<CommentBean>)

    fun onEpisodeDetail(detail: EpisodeDetail)
}
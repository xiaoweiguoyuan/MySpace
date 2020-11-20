package com.puci.qs.myspace.view

import com.puci.qs.myspace.entity.CommentBean
import com.puci.qs.myspace.entity.EpisodeDetail

open interface PlayingV {

    fun onComments(lst: ArrayList<CommentBean>)

    fun onEpisodeDetail(detail: EpisodeDetail)
}
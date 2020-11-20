package com.wuba.car.myspace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.wuba.car.basedependencies.entity.Episode
import com.wuba.car.myspace.controller.PlayListViewFrg
import com.wuba.car.myspace.controller.PlayingViewFrg
import com.wuba.car.myspace.entity.CommentBean
import com.wuba.car.myspace.entity.EpisodeDetail
import com.wuba.car.myspace.entity.PlayingInfo


open class PlayAdapterNew : PagerAdapter {


    private var mContext: Context
    private var playingViewFrg: PlayingViewFrg
    private var playListViewFrg: PlayListViewFrg

    constructor(context: Context) {
        mContext = context
        playingViewFrg = PlayingViewFrg(mContext)
        playListViewFrg = PlayListViewFrg(mContext)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        if (position == 0) {
            val view = playingViewFrg.onCreateView()
            container.addView(view)
            return view
        } else {
            val view = playListViewFrg.onCreateView()
            container.addView(view)
            return view
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    fun onPlayStart(position: Int, episode: Episode) {
        playingViewFrg.onPlayStart(position, episode)
        playListViewFrg.addToList(episode)
    }

    fun addComments(comments: ArrayList<CommentBean>) {
        playingViewFrg.addComments(comments)
    }

    fun showCommentLl(b: Boolean) {
        playingViewFrg.showCommentLl(b)
    }

    fun onEpisodeDetail(detail: EpisodeDetail) {
        playingViewFrg.onEpisodeDetail(detail)
    }

    fun updateProgress(data: PlayingInfo?) {
        playListViewFrg.updateProgress(data)
    }

}
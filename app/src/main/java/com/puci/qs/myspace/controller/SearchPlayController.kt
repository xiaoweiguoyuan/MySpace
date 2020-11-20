package com.puci.qs.myspace.controller

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.View
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.myspace.base.CommonCodeEvent
import com.puci.qs.myspace.dialog.SearchPlayDialog
import com.puci.qs.myspace.entity.CommentBean
import com.puci.qs.myspace.entity.EpisodeDetail
import com.puci.qs.myspace.entity.PlayingInfo
import com.puci.qs.myspace.presenter.PlayPresenterNew
import com.puci.qs.myspace.service.MediaService
import com.puci.qs.myspace.utils.Constants
import com.puci.qs.myspace.view.PlayingV
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.ArrayList

open class SearchPlayController: PlayingV {

    private var mOldCursor: String? = ""
    private var mLastCursor: Double? = null
    private lateinit var mCurrentEpisode: Episode
    private var timer: Timer? = null
    open var isShow: Boolean? = false
    private lateinit var playDialog: SearchPlayDialog
    private lateinit var rootView: View
    private lateinit var mContext: Context
    private lateinit var presenter: PlayPresenterNew
    private var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (playDialog.isShow()) {
                mCurrentEpisode.id?.let { presenter.requestComments(it, mLastCursor) }
                mCurrentEpisode.id?.let { presenter.episodeDetail(it) }
            }
        }
    }

    constructor(context: Context) {
        mContext = context
        init()
    }

    fun init() {
        playDialog = SearchPlayDialog(mContext)
        presenter = PlayPresenterNew(mContext, this)
        if (timer == null) {
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handler.sendEmptyMessage(1)
                }

            }, 0, 3000)
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEventBusMessage(event: CommonCodeEvent<CommentBean>) {
        when(event.code) {
            Constants.MediaCons.COMMENT_SUCCESS -> {
                mLastCursor = event.data.cursor
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onPlayMessage(event: CommonCodeEvent<PlayingInfo>) {
        when(event.code) {
            Constants.MediaCons.PLAY_NEXT_INFO -> {
                //播放下一曲喽
                val data = event.data
                data.index?.let { data.episode?.let {
                    mCurrentEpisode = it
                    playDialog.onNextPlayStart(it)
//                        it1 -> adapter?.onPlayStart(it, it1)
//                    playController.onPlayNext(it1)
                } }
            }
        }
    }

    fun playEpisode(episode: Episode) {
        mCurrentEpisode = episode
        playDialog.playEpisode(episode)
    }

    fun addEpisodeToPlayList(episode: Episode) {
        playDialog.addEpisodeToPlayList(episode)
    }

    fun expand() {
        playDialog.show()
        isShow = true
    }

    fun onDestroy() {
//        timer?.cancel()
        mContext.stopService(Intent(mContext, MediaService::class.java))
        playDialog.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun onBackPressed() {
        playDialog.onBackPressed()
    }

    override fun onComments(lst: ArrayList<CommentBean>) {
        mLastCursor = lst.last().cursor
        playDialog.addComments(lst)
    }

    override fun onEpisodeDetail(detail: EpisodeDetail) {
        playDialog.onEpisodeDetail(detail)
    }
}

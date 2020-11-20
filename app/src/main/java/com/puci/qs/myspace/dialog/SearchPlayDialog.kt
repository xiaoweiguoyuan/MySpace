package com.puci.qs.myspace.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.myspace.adapter.PlayAdapterNew
import com.puci.qs.myspace.base.CommonCodeEvent
import com.puci.qs.myspace.controller.PlayControllerNew
import com.puci.qs.myspace.entity.CommentBean
import com.puci.qs.myspace.entity.EpisodeDetail
import com.puci.qs.myspace.entity.PlayingInfo
import com.puci.qs.myspace.utils.Constants.MediaCons.*
import com.puci.qs.qishuier.R
import com.puci.qs.spacenet.http.utils.StatusBarUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.ArrayList


open class SearchPlayDialog :  View.OnClickListener {

    private var full_ll: View? = null
    private lateinit var timer: Timer
    private var adapter: PlayAdapterNew? = null
    private var miniDialog: PlayMiniDialog? = null
    private var playView: View? = null
    private var viewPager: ViewPager? = null
    private lateinit var playController: PlayControllerNew

    private lateinit var rootView: View
    private lateinit var dialog: Dialog
    private lateinit var mContext: Context


    constructor(context: Context){
        mContext = context
        dialog = Dialog(mContext, R.style.Dialog_Fullscreen)
        rootView = View.inflate(mContext, R.layout.layout_search_play_controller, null)
        dialog.setContentView(rootView)
        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //一定要在setContentView之后调用，否则无效
        dialog.getWindow()?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        initview()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEventBusMessage(event: CommonCodeEvent<PlayingInfo>) {
        when(event.code) {
            PLAY_PROGRESS_UPDATE -> {
                val data = event.data
                adapter?.updateProgress(data)
                playController.updateProgress(data.maxSize, data.progress)
            }
            BUFFER_PROGRESS_UPDATE -> {
                val data = event.data
                playController.updateBuffSize(data.maxSize, data.buffSize)
            }
//            PLAY_NEXT_INFO -> {
//                //播放下一曲喽
//                val data = event.data
//                data.index?.let { data.episode?.let {
//                        it1 -> adapter?.onPlayStart(it, it1)
//                    playController.onPlayNext(it1)
//                } }
//            }
            SHOW_PLAY_CONTROLLER -> {
                playView?.visibility = View.VISIBLE
            }
            HIDE_PLAY_CONTROLLER -> {
                playView?.visibility = View.GONE
            }
            COMMENT_SUCCESS -> {

            }
            THEME_CHANGE -> {
                event.data.themeColor?.let {
                    full_ll?.setBackgroundColor(it)
                    var cntxt = playView?.context
                    if (cntxt is Activity) {
                        StatusBarUtil.setColor(cntxt, it)
                    }
                }
            }
            PLAY_COMPLETE -> {
                //播放下一曲喽
            }
        }
    }

    private fun initview() {
        full_ll = rootView.findViewById<View>(R.id.full_ll)
        playView = rootView.findViewById<View>(R.id.play_ll)
        playController = PlayControllerNew(mContext, playView)


        playView?.setOnClickListener(this)
        rootView.findViewById<View>(R.id.playing_tab).setOnClickListener(this)
        rootView.findViewById<View>(R.id.play_list_tab).setOnClickListener(this)
        rootView.findViewById<View>(R.id.collapse_btn).setOnClickListener(this)

        viewPager = rootView.findViewById<ViewPager>(R.id.vp)
        val value: FragmentActivity = mContext as FragmentActivity
        adapter = PlayAdapterNew(mContext)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        rootView.findViewById<View>(R.id.play_line).visibility = View.VISIBLE
                        rootView.findViewById<View>(R.id.play_list_line).visibility = View.INVISIBLE
                        rootView.findViewById<View>(R.id.play_ll).visibility = View.VISIBLE
                    }
                    1 -> {
                        rootView.findViewById<View>(R.id.play_line).visibility = View.INVISIBLE
                        rootView.findViewById<View>(R.id.play_list_line).visibility = View.VISIBLE
                        rootView.findViewById<View>(R.id.play_ll).visibility = View.GONE
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }

    fun playEpisode(episode: Episode) {
        adapter?.onPlayStart(0, episode)
        playController.playEpisode(episode)
    }

    /**
     * 接收service 播放下一曲的通知
     */
    fun onNextPlayStart(episode: Episode) {
        adapter?.onPlayStart(0, episode)
        playController.playEpisode(episode)
    }

    fun addEpisodeToPlayList(episode: Episode) {
        playController.addEpisode(episode)
    }

    fun show() {
        dialog.show()
    }

    @Override
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.playing_tab -> viewPager?.currentItem = 0
            R.id.play_list_tab -> viewPager?.currentItem = 1
            R.id.play_ll -> {
                playView?.visibility = View.GONE
                adapter?.showCommentLl(true)
            }
            R.id.collapse_btn -> {
                dialog.cancel()
            }
        }
    }

    fun onBackPressed() {
    }

    fun addComments(comments: ArrayList<CommentBean>) {
        adapter?.addComments(comments)
    }

    fun onDestroy() {
        EventBus.getDefault().unregister(this)
    }

    fun isShow(): Boolean {
        return dialog.isShowing
    }

    fun onEpisodeDetail(detail: EpisodeDetail) {
        adapter?.onEpisodeDetail(detail)
    }

}
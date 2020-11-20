package com.puci.qs.myspace.activity

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.mediaplayer.base.QSPlayer
import com.puci.qs.myspace.adapter.PlayAdapter
import com.puci.qs.myspace.base.BaseActivity
import com.puci.qs.myspace.controller.DefaultPlayController
import com.puci.qs.myspace.presenter.PlayPresenter
import com.puci.qs.qishuier.R

class PlayActivity : BaseActivity<PlayPresenter>(), View.OnClickListener {

    private var episode: Episode? = null
    private var playView: View? = null
    private var viewPager: ViewPager? = null
    private lateinit var playController: DefaultPlayController
    private var player: QSPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(): Int {
        return R.layout.activity_play2
    }

    override fun initView() {
        presenter = PlayPresenter(this, null)

        playView = findViewById<View>(R.id.play_ll)

//        player = QSPlayer(this)
        playController = DefaultPlayController(this, player, playView)
        viewPager = findViewById<ViewPager>(R.id.vp)
        var adapter: PlayAdapter = PlayAdapter(supportFragmentManager)
        viewPager!!.adapter = adapter
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        findViewById<View>(R.id.play_line).visibility = View.VISIBLE
                        findViewById<View>(R.id.play_list_line).visibility = View.INVISIBLE
                        findViewById<View>(R.id.play_ll).visibility = View.VISIBLE
                    }
                    1 -> {
                        findViewById<View>(R.id.play_line).visibility = View.INVISIBLE
                        findViewById<View>(R.id.play_list_line).visibility = View.VISIBLE
                        findViewById<View>(R.id.play_ll).visibility = View.GONE
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    override fun initData() {
        episode = intent.getSerializableExtra("episode") as Episode?
        player?.setPath(episode?.url)
        playController?.setPath(episode?.url)

        playController.start()
        //处理播放数据
        //处理评论数据
        //处理其他数据
    }

    override fun onDestroy() {
        super.onDestroy()
        if (playController != null) {
            playController?.release()
        }
    }

    @OnClick(R.id.playing_tab, R.id.play_list_tab)
    @Override
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.playing_tab -> viewPager?.currentItem = 0
            R.id.play_list_tab -> viewPager?.currentItem = 1
        }
    }
}
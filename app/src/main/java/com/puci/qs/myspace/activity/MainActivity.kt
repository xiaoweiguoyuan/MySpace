package com.puci.qs.myspace.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.viewpager.widget.ViewPager
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.myspace.adapter.VPAdapter
import com.puci.qs.myspace.controller.SearchPlayController
import com.puci.qs.myspace.utils.Constants
import com.puci.qs.myspace.utils.EpisodeUtils
import com.puci.qs.myspace.utils.SpUtils
import com.puci.qs.qishuier.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var searchPlayController: SearchPlayController? = null
    private var adapter: VPAdapter? = null
    private var seekProgress: Int = 0
    private var seekAdvanceable: Boolean = true
    private var progressBar: AppCompatSeekBar? = null
    private lateinit var search_tab: TextView
    private lateinit var subscribe_tab: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE)

        search_tab = findViewById(R.id.search_tab)
        subscribe_tab = findViewById(R.id.subscribe_tab)

        var vp: ViewPager = findViewById(R.id.vp)
        adapter = VPAdapter(supportFragmentManager)
        vp.adapter = adapter

        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        search_tab.setTextColor(resources.getColor(R.color.color_5cc93b))
                        subscribe_tab.setTextColor(resources.getColor(R.color.color_aaaaaa))
                    }
                    1 -> {
                        search_tab.setTextColor(resources.getColor(R.color.color_aaaaaa))
                        subscribe_tab.setTextColor(resources.getColor(R.color.color_5cc93b))
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        playLastUnfinished()
    }

    fun playEpisode(episode: Episode) {
        if (searchPlayController == null) {
            searchPlayController = SearchPlayController(this)
        }
        searchPlayController?.expand()
        searchPlayController?.playEpisode(episode)
    }

    fun addEpisodeToPlayList(episode: Episode) {
        if (searchPlayController == null) {
            searchPlayController = SearchPlayController(this)
        }
        searchPlayController?.addEpisodeToPlayList(episode)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchPlayController?.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        adapter?.onBackPressed()
        searchPlayController?.onBackPressed()
    }

    private fun playLastUnfinished() {
        //获取之前播放到一半的曲目，直接进行播放
        val id = SpUtils.getString(Constants.MediaSaveCons.EPISODE_INFO)
        val let = id?.let {
            SpUtils.getString(it)
        }
        val episodeFromStr = EpisodeUtils.episodeFromStr(let)
        if (episodeFromStr != null) {
            playEpisode(episodeFromStr)
        }
    }


    override fun onClick(v: View?) {
        when(v?.id) {
        }
    }
}
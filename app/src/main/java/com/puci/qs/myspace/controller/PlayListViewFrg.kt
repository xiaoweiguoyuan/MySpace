package com.puci.qs.myspace.controller

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.myspace.adapter.PlayListAdapter
import com.puci.qs.myspace.entity.PlayingInfo
import com.puci.qs.myspace.utils.CommonDecoration
import com.puci.qs.qishuier.BuildConfig
import com.puci.qs.qishuier.R

open class PlayListViewFrg(var context: Context) {
    private lateinit var view: View
    private lateinit var recyclerView: RecyclerView

    fun onCreateView(): View {
        view = View.inflate(context, R.layout.fragment_play_list, null)

        initView()
        return view
    }

    private fun initView() {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        (recyclerView.itemAnimator as SimpleItemAnimator?)?.setSupportsChangeAnimations(false)
        recyclerView.addItemDecoration(CommonDecoration(0, 18, 0, 0))
        getDatas()
        recyclerView.adapter = PlayListAdapter(context, episodeList)

    }

    private fun getDatas(): List<Episode>? {
        return episodeList
    }

    private val episodeList: ArrayList<Episode> = java.util.ArrayList()
    fun addToList(episode: Episode) {
        if (!isInclude(episode)) {
            episodeList.add(episode)
            if (this::recyclerView.isInitialized) {
                var adapter: PlayListAdapter = recyclerView.adapter as PlayListAdapter
                adapter.mDatas = episodeList
            }
        }
    }

    open fun isInclude(episode: Episode): Boolean {
        if (null == episode) {
            return false
        }
        for (i in episodeList.indices) {
            if (episodeList[i].id == episode.id) {
                return if (BuildConfig.DEBUG) {
                    if (episodeList[i].url == episode.url) {
                        true
                    } else {
                        false
                    }
                } else true
            }
        }
        return false
    }

    fun updateProgress(data: PlayingInfo?) {
        if (null == data || data.episode == null) return
        var adapter: PlayListAdapter = recyclerView.adapter as PlayListAdapter
        var index = getIndex(data.episode!!)
        if (index >= 0 && index < adapter.itemCount) {
//            val get = adapter.mDatas?.get(index)
//            get?.progress = data?.episode?.progress
//            get?.maxSize = data?.episode?.maxSize
            adapter.mDatas = episodeList
            adapter.notifyDataSetChanged()
//            adapter.notifyItemChanged(index)
        }
    }

    private fun getIndex(episode: Episode): Int {
        val id = episode.id
        for (i in episodeList.indices) {
            if (episodeList.get(i).id == id) {
                episodeList.get(i).progress = episode.progress
                episodeList.get(i).maxSize = episode.maxSize
                return i
            }
        }
        return 0
    }
}
package com.puci.qs.myspace.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.puci.qs.myspace.activity.MainActivity
import com.puci.qs.myspace.adapter.SubscribeAdapter
import com.puci.qs.myspace.base.BaseFragment
import com.puci.qs.myspace.entity.EpisodeDetail
import com.puci.qs.myspace.entity.SubscribeBean
import com.puci.qs.myspace.presenter.SubscribePresenter
import com.puci.qs.myspace.utils.CommonDecoration
import com.puci.qs.myspace.utils.EpisodeUtils
import com.puci.qs.myspace.view.SubscribeV
import com.puci.qs.qishuier.R
import com.puci.qs.spacenet.http.utils.ToastUtils
import retrofit2.Call

class SubscribeFragment : BaseFragment<SubscribePresenter?>(), SubscribeV, OnRefreshListener, OnLoadMoreListener {

    var recyclerView: RecyclerView? = null
    var refresh_layout: SmartRefreshLayout? = null

    override fun inflaterLayout(): Int {
        return R.layout.fragment_subscribe
    }

    override fun initViews(view: View) {
        refresh_layout = view.findViewById(R.id.refresh_layout)
        refresh_layout?.setOnRefreshListener(this)
        refresh_layout?.setOnLoadMoreListener(this)
        refresh_layout?.setEnableRefresh(true)
        refresh_layout?.setEnableLoadMore(true)
        recyclerView = view.findViewById(R.id.recyclerView)
        val subscribeAdapter = context?.let { SubscribeAdapter(it, null) }
        subscribeAdapter?.setOnItemViewClickListener(object : SubscribeAdapter.OnItemViewClickListener {
            override fun onItemViewClick(episode: EpisodeDetail.CurEpisode, pos: Int, view: View) {
                EpisodeUtils.episodeFromCurEpisode(episode)?.let {
                    (activity as MainActivity).playEpisode(
                        it
                    )
                }
            }

        })
        recyclerView?.adapter = context?.let { subscribeAdapter }
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.addItemDecoration(CommonDecoration(0, 20, 0, 0))
        presenter = SubscribePresenter(context, this)
    }

    override fun initViewData() {
        presenter?.getSubscribes(true)
    }
    override fun onClick(v: View) {}
    override fun onResult(subscribeBean: SubscribeBean?, isRefresh: Boolean, hasNext: Boolean) {
        val subscribeAdapter = recyclerView?.adapter as SubscribeAdapter
        if (isRefresh) {
            subscribeAdapter.mDatas = subscribeBean?.episodes
        } else {
            subscribeBean?.episodes?.let { subscribeAdapter.addDatas(it) }
        }
        subscribeAdapter.notifyDataSetChanged()
        refresh_layout?.finishRefresh()
        refresh_layout?.finishLoadMore()
        if (!hasNext) {
            refresh_layout?.setEnableLoadMore(false)
        }
    }

    override fun onFail(call: Call<SubscribeBean>, t: Throwable) {
        refresh_layout?.finishRefresh()
        refresh_layout?.finishLoadMore()
        ToastUtils.show(t.message)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        presenter?.getSubscribes(true)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        presenter?.getSubscribes(false)
    }
}
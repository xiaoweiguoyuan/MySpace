package com.wuba.car.myspace.presenter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wuba.car.myspace.adapter.SearchChildAdapter
import com.wuba.car.myspace.adapter.SubscribeAdapter
import com.wuba.car.myspace.base.BaseFragment
import com.wuba.car.qishuier.R

open class SearchChildFragment : BaseFragment<SearchPresenter>() {
    var recyclerView: RecyclerView? = null
    override fun onClick(v: View?) {

    }

    override fun inflaterLayout(): Int {
        return R.layout.fragment_search_child
    }

    override fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val subscribeAdapter = context?.let { SearchChildAdapter(it, null) }
        recyclerView?.adapter = subscribeAdapter
    }

    override fun initViewData() {
    }
}
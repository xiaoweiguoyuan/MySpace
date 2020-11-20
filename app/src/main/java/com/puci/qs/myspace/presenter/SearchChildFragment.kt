package com.puci.qs.myspace.presenter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puci.qs.basedependencies.entity.Podcast
import com.puci.qs.myspace.adapter.SearchChildAdapter
import com.puci.qs.myspace.base.BaseFragment
import com.puci.qs.qishuier.BuildConfig
import com.puci.qs.qishuier.R
import java.util.ArrayList

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
        val subscribeAdapter = context?.let { SearchChildAdapter(it, getDatas()) }
        recyclerView?.adapter = subscribeAdapter
    }

    private fun getDatas(): List<Podcast>? {
        if (BuildConfig.DEBUG) {
            var daas = ArrayList<Podcast>()
            for(i in 1..10) {
                var item = Podcast()
                daas.add(item)
            }
            return daas
        }
        return null
    }

    override fun initViewData() {
    }
}
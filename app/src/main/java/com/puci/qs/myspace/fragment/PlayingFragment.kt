package com.puci.qs.myspace.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.puci.qs.myspace.adapter.ChaZuiAdapter
import com.puci.qs.myspace.base.BaseFragment
import com.puci.qs.myspace.presenter.PlayPresenter
import com.puci.qs.qishuier.R
import kotlinx.android.synthetic.main.fragment_playing_layout.*

open class PlayingFragment : BaseFragment<PlayPresenter>() {

    override fun onClick(v: View?) {
    }

    override fun inflaterLayout(): Int {
        return R.layout.fragment_playing_layout
    }

    override fun initViews(view: View?) {

    }

    override fun onStart() {
        super.onStart()
        cast_title?.setText("「脱口秀大会 3 特辑」，我就是天才怎么了")
        topic_name.setText("车间访谈")
        avatars_recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        avatars_recyclerview.adapter = context?.let { ChaZuiAdapter(it, null) }
    }

    override fun initViewData() {

    }
}
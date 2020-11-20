package com.wuba.car.myspace.fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import com.wuba.car.myspace.adapter.ChaZuiAdapter
import com.wuba.car.myspace.base.BaseFragment
import com.wuba.car.myspace.presenter.PlayPresenter
import com.wuba.car.qishuier.R
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
package com.wuba.car.myspace.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.wuba.car.myspace.adapter.VPAdapter
import com.wuba.car.myspace.base.BaseFragment
import com.wuba.car.myspace.presenter.SearchChildFragment
import com.wuba.car.myspace.presenter.SearchPresenter
import com.wuba.car.qishuier.R
import java.util.ArrayList

open class SearchFragment : BaseFragment<SearchPresenter>() {
    lateinit var vp:ViewPager

    override fun onClick(v: View?) {

    }

    override fun inflaterLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initViews(view: View?) {
        vp = view?.findViewById(R.id.vp)!!
        vp.adapter = VPAdapter(parentFragmentManager, getFragments())
    }

    private fun getFragments(): ArrayList<Fragment> {
        var commonWebFragments: ArrayList<Fragment> = ArrayList<Fragment>()
        commonWebFragments.add(SearchChildFragment())
        commonWebFragments.add(SearchChildFragment())
        commonWebFragments.add(SearchChildFragment())
        return commonWebFragments
    }

    override fun initViewData() {
    }

}
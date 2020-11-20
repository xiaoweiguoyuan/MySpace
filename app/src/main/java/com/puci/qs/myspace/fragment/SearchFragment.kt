package com.puci.qs.myspace.fragment

import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.puci.qs.myspace.adapter.VPAdapter
import com.puci.qs.myspace.base.BaseFragment
import com.puci.qs.myspace.presenter.SearchChildFragment
import com.puci.qs.myspace.presenter.SearchPresenter
import com.puci.qs.qishuier.R
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
        vp.adapter = VPAdapter(childFragmentManager, getFragments())
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
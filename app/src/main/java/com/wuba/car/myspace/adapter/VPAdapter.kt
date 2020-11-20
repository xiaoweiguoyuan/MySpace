package com.wuba.car.myspace.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wuba.car.myspace.fragment.SearchFragment
import com.wuba.car.myspace.fragment.SearchWebFragment
import com.wuba.car.myspace.fragment.SubscribeFragment

open class VPAdapter:FragmentStatePagerAdapter{

    private lateinit var commonWebFragments: ArrayList<Fragment>

    constructor(fm: FragmentManager) : super(fm) {
        commonWebFragments = ArrayList<Fragment>()
        commonWebFragments.add(SearchWebFragment())
        commonWebFragments.add(SubscribeFragment())
        commonWebFragments.add(SearchFragment())
    }

    constructor(fm: FragmentManager, fragments: ArrayList<Fragment>) : super(fm) {
        commonWebFragments = fragments
    }


    override fun getCount(): Int {
        return commonWebFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return commonWebFragments[position]
    }

    fun onBackPressed() {
        (commonWebFragments[0] as SearchWebFragment).onBackPressed()
    }
}
package com.puci.qs.myspace.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.puci.qs.myspace.fragment.PlayListFragment
import com.puci.qs.myspace.fragment.PlayingFragment


open class PlayAdapter : FragmentPagerAdapter {

    lateinit var fragments : Array<Fragment>

    constructor(fm: FragmentManager) : super(fm) {
        fragments = Array(2, {i: Int -> getFragment(i)})
    }

    private fun getFragment(i: Int): Fragment {
        if (i == 0) {
            return PlayingFragment()
        } else{
            return PlayListFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment = null
//        if (position >= fragments.size) return null
        return fragments[position]

    }
}
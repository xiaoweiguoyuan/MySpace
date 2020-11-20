package com.puci.qs.myspace.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.puci.qs.myspace.base.BaseFragment
import com.puci.qs.myspace.presenter.PlayPresenter
import com.puci.qs.qishuier.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class PlayListFragment : BaseFragment<PlayPresenter>() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onClick(v: View?) {

    }

    override fun inflaterLayout(): Int {
        return R.layout.fragment_play_list
    }

    override fun initViews(view: View?) {
    }

    override fun initViewData() {
    }

}
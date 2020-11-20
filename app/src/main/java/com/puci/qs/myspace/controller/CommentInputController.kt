package com.puci.qs.myspace.controller

import android.view.View
import android.widget.EditText
import com.puci.qs.qishuier.R

open class CommentInputController : View.OnClickListener {

    private var inputview: View?
    private var edit_input: EditText? = null
    private var send_btn: View? = null
    private var avatar_input: View? = null
    var oldLoc = IntArray(2)

    constructor(view: View?) {
        inputview = view

        initview()
    }

    private fun initview() {
        send_btn = inputview?.findViewById(R.id.send_btn)
        avatar_input = inputview?.findViewById(R.id.avatar_input)
//        send_btn?.setOnClickListener(this)
        edit_input = inputview?.findViewById(R.id.edit_input)
        edit_input?.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                var loc = IntArray(2)
                edit_input?.getLocationOnScreen(loc)
                if (loc[1] > oldLoc[1]) {
                    // 折叠
//                    send_btn?.visibility = View.GONE
//                    avatar_input?.visibility = View.VISIBLE
//                    inputview?.invalidate()
                } else if (loc[1] < oldLoc[1]) {
                    //展开
//                    send_btn?.visibility = View.VISIBLE
//                    avatar_input?.visibility = View.GONE
//                    inputview?.invalidate()
                }
                oldLoc = loc
            }

        })

    }

    override fun onClick(v: View?) {

    }
}
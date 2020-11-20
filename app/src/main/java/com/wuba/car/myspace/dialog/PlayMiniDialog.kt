package com.wuba.car.myspace.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.wuba.car.qishuier.R

open class PlayMiniDialog {

    private lateinit var onCancelListener: OnCancelListener
    private lateinit var rootView: View
    private lateinit var dialog: Dialog
    private lateinit var mContext: Context

    constructor(context: Context){
        mContext = context
        dialog = Dialog(mContext)
        rootView = View.inflate(mContext, R.layout.layout_play_mini_dialog, null)
        dialog.setContentView(rootView)
        dialog.setOnCancelListener(object : DialogInterface.OnCancelListener {
            override fun onCancel(dialog: DialogInterface?) {
                if (onCancelListener != null) {
                    onCancelListener.onCancel()
                }
            }

        })
        initview()
        val wm: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.getDefaultDisplay().getMetrics(metrics)
        if (metrics.heightPixels < 300) {
            dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, 300)
        }
    }

    private fun initview() {


    }

    fun setOnCancelListener(onCancelListener: OnCancelListener) {
        this.onCancelListener = onCancelListener
    }

    fun show() {
        dialog.show()
    }

    interface OnCancelListener {
        fun onCancel()
    }
}
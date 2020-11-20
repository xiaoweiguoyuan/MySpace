package com.puci.qs.hybrid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wendu.dsbridge.DWebView

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        var webview:DWebView = findViewById(R.id.dwebview)
        val jsApi = JsApi()
        webview.addJavascriptObject(jsApi, null)
//        jsApi.setPlayCallBack(object : PlayCallBack{
//            override fun playEpisode(
//                id: String?,
//                url: String?,
//                title: String?,
//                showNotes: String?
//            ) {
//                //跳转到播放页面
//            }
//
//            override fun addEpisodeToPlayList(
//                id: String?,
//                url: String?,
//                title: String?,
//                showNotes: String?
//            ) {
//                //添加到播放列表里面
//            }
//
//        })
    }
}
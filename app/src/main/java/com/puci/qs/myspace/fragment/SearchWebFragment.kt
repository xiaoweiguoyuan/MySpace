package com.puci.qs.myspace.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
import androidx.fragment.app.Fragment
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.hybrid.JsApi
import com.puci.qs.hybrid.QSWebView
import com.puci.qs.myspace.activity.MainActivity
import com.puci.qs.qishuier.R
import org.json.JSONObject

open class SearchWebFragment : Fragment() {
//    private var searchPlayController: SearchPlayController? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_common_web, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {

        var webview: QSWebView = view?.findViewById(R.id.webview)!!
        val jsApi = JsApi()
        val webSettings: WebSettings = webview!!.getSettings()
        webSettings.setJavaScriptEnabled(true) //允许使用js
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowFileAccess = true
        webSettings.blockNetworkImage = true
        webSettings.cacheMode = LOAD_CACHE_ELSE_NETWORK

        webview?.addJavascriptObject(jsApi, null)
        jsApi.setPlayCallBack(object : JsApi.PlayCallBack {

            override fun playEpisode(json: String?) {
                var episode = episodeFromJs(json)

                activity?.runOnUiThread(Runnable {
                    (activity as MainActivity).playEpisode(episode)
                })
            }

            override fun addEpisodeToPlayList(toString: String?) {
                val episodeFromJs = episodeFromJs(toString)
                (activity as MainActivity).addEpisodeToPlayList(episodeFromJs)
            }

        })
        webview?.webChromeClient = QSWebChromeClient()
        webview?.webViewClient = QSWebViewClient()
//        webview?.loadUrl("https://blog.csdn.net/mountain_hua")
        webview?.loadUrl("http://182.92.4.164/app/search")
//        webview?.loadUrl("file:android_asset/test.html")
    }

    private fun episodeFromJs(json: String?): Episode {
        val `object` = JSONObject(json.toString())
        val url = `object`.optString("url")
        val id = `object`.optString("id")
        val title = `object`.optString("title")
        val showNotes = `object`.optString("showNotes")
        var episode = Episode()
        episode.id = id
        episode.url = url
        episode.title = title
        episode.showNotes = showNotes
        //解析json字段
        var json = `object`.optJSONObject("json")
        episode.author = json.optString("author")
        episode.image = json.optString("image")
        episode.feed = json.optString("feed")
        episode.category = json.optString("category")
        episode.podcast_id = json.optString("podcast")
        episode.itune_id = json.optString("itune_id")
        episode.subtitle = json.optString("subtitle")
        episode.episode_art_url = json.optString("episode_art_url")
        episode.published = json.optString("published")
        episode.link = json.optString("link")
        episode.total_time = json.optLong("total_time")
        episode.format = json.optString("format")
        episode.like_cnt = json.optInt("like_cnt")
        return episode
    }

    override fun onDestroy() {
        super.onDestroy()
//        searchPlayController?.onDestroy()
    }

    fun onBackPressed() {
//        searchPlayController?.onBackPressed()
    }
}
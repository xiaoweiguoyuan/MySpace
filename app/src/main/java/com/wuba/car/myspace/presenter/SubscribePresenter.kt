package com.wuba.car.myspace.presenter

import android.content.Context
import android.text.TextUtils
import com.wuba.car.basedependencies.utils.SaveLog
import com.wuba.car.myspace.base.BasePresenter
import com.wuba.car.myspace.entity.EpisodeDetail
import com.wuba.car.myspace.entity.SubscribeBean
import com.wuba.car.myspace.entity.User
import com.wuba.car.myspace.net.HttpEngine
import com.wuba.car.myspace.view.SubscribeV
import com.wuba.car.qishuier.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class SubscribePresenter(context: Context?, view: SubscribeV?) :
    BasePresenter<SubscribeV>(context, view) {
    private var TAG = this.javaClass.simpleName
    private var mPageSize: Int = 10
    var mCursor: String? = ""
    fun getSubscribes(isRefresh: Boolean) {
        if (isRefresh) {
            mCursor = ""
        }
        SaveLog.i(TAG, "cursor: " + mCursor + ", pagesize: " + mPageSize)
        HttpEngine.getInstance().getSubscribes(mCursor, object: Callback<SubscribeBean> {
            override fun onResponse(
                call: Call<SubscribeBean>,
                response: Response<SubscribeBean>
            ) {
                val bean = response.body()
                if (null == bean) return

                bean?.cursor?.let { mCursor = it }
                bean?.page_size?.let { mPageSize = it }
                if ((bean.episodes == null || bean.episodes!!.size == 0) && BuildConfig.DEBUG) {
                    bean.episodes = getEpisodes()
                }
                var hasNext = true
                if (TextUtils.isEmpty(bean.cursor)) {
                    mCursor = "-1"
                    hasNext = false
                }
                view.onResult(bean, isRefresh, hasNext)
            }

            override fun onFailure(call: Call<SubscribeBean>, t: Throwable) {

            }

        })
    }

    private fun getEpisodes(): List<EpisodeDetail.CurEpisode>? {
        var list = ArrayList<EpisodeDetail.CurEpisode>()
        for(i in 1..10) {
            var episode = EpisodeDetail.CurEpisode()
            episode.episode_art_url = "https://img.ivsky.com/img/tupian/pre/202004/24/lake_powell-007.jpg"
            episode.id = i.toString()
            episode.total_time = 120000
            episode.author = "中央文艺广播"
            episode.title = "拉的方式；福卡戴珊；放假快乐阿斯顿了；饭店附近卡；离放假开始的；拉开始的肌肤；啊东方大厦大发方式接口"
            episode.description = "道格拉斯·亚当斯是英国科幻作家，代表作有《银河系漫游指南》系列，风格以“欠”著称，和同时代的很多科幻作家形成了鲜明对比。他是一个梗驱动型作家。他创…"
            episode.like_cnt = 1223
            episode.listening_users = getListenerUsers()
            list.add(episode)
        }
        return list
    }

    private fun getListenerUsers(): List<User>? {
        var list = ArrayList<User>()
        for(i in 1..3) {
            var user = User()
            user.avatar = "https://i02picsos.sogoucdn.com/1bfc27d6f354ae45"
            list.add(user)
        }
        return list
    }

}
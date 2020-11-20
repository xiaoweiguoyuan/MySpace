package com.puci.qs.myspace.presenter

import android.content.Context
import android.text.TextUtils
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.basedependencies.entity.Podcast
import com.puci.qs.basedependencies.utils.SaveLog
import com.puci.qs.myspace.base.BasePresenter
import com.puci.qs.myspace.entity.CommentBean
import com.puci.qs.myspace.entity.EpisodeDetail
import com.puci.qs.myspace.entity.User
import com.puci.qs.myspace.net.HttpEngine
import com.puci.qs.myspace.utils.KotinDigitHandle
import com.puci.qs.myspace.view.PlayingV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class PlayPresenterNew(context: Context, v: PlayingV) : BasePresenter<PlayingV>(context, v) {

    private lateinit var mCurDpisodeDetail: EpisodeDetail
    private var mOldCursor: String? = ""
    private var mLastCursor: Double? = null
    private lateinit var mCurrentEpisode: Episode

    fun requestComments(id: String, cursor: Double?) {
        if (mLastCursor != null && mLastCursor == cursor) {
            return
        }
        val instance = HttpEngine.getInstance()
        var map: HashMap<String, Any> = HashMap<String, Any>()
        mLastCursor?.let { map.put("cursor", it) }
        map.put("page_size", 10)
        map.put("direction", "next")
        instance.getComments(id, map, object : Callback<ArrayList<CommentBean>> {
            override fun onResponse(
                call: Call<ArrayList<CommentBean>>,
                response: Response<ArrayList<CommentBean>>
            ) {
                response.body()?.let {
                    if (it.size > 0) {
                        view.onComments(it)
                        mLastCursor = it.last().cursor
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<CommentBean>>, t: Throwable) {
            }

        });

    }

    fun episodeDetail(id: String) {
        if (this::mCurDpisodeDetail.isInitialized && null != mCurDpisodeDetail && mCurDpisodeDetail.episode != null && KotinDigitHandle.isDigitEquals(id, mCurDpisodeDetail.episode!!.id)) {
            return
        }

        HttpEngine.getInstance().episodeDetail(id, object : Callback<EpisodeDetail> {
            override fun onResponse(call: Call<EpisodeDetail>, response: Response<EpisodeDetail>) {
                var detail: EpisodeDetail? = response.body()
//                SaveLog.w("@@@-test", "respose: " + response.body())
                if (detail != null) {
                    mCurDpisodeDetail = detail
                    view.onEpisodeDetail(detail)
                }
            }

            override fun onFailure(call: Call<EpisodeDetail>, t: Throwable) {
                SaveLog.w("@@@-test", "onFailure: " + t.message)
            }

        })

    }

    private fun idEquals(id: String): Boolean {
        if (TextUtils.isEmpty(id) && (
                    !this::mCurDpisodeDetail.isInitialized || mCurDpisodeDetail == null
                            || mCurDpisodeDetail.episode == null
                            || TextUtils.isEmpty(mCurDpisodeDetail.episode!!.id))
        ) {
            return false
        }
        if (TextUtils.isEmpty(id)) {
            return true
        }
        if (!this::mCurDpisodeDetail.isInitialized || mCurDpisodeDetail == null
            || mCurDpisodeDetail.episode == null
            || TextUtils.isEmpty(mCurDpisodeDetail.episode!!.id)) {
            return true
        }
        var idInt: Int = id.toInt()
        var idCur: Int = mCurDpisodeDetail.episode!!.id!!.toInt()
        if (idInt == idCur) {
            return true
        }

        return false

//        return mCurDpisodeDetail.episode?.id.equals(id)
    }

    private fun parsePodCast(map: Map<String, Any>): Podcast? {
        if (map == null) {
            return null
        }
        var podcast = Podcast()
        podcast.id = map.get("id") as String?
        podcast.itune_id = map.get("itune_id") as String?
        podcast.name = map.get("name") as String?
        podcast.author = map.get("author") as String?
        podcast.url = map.get("url") as String?
        podcast.feed = map.get("feed") as String?
        podcast.category = map.get("category") as String?
        podcast.image = map.get("image") as String?
        return podcast
    }

    private fun parseCurrentUser(map: Map<String, Any>): User? {
        if (map == null) {
            return null
        }
        var user = User()
        user.username = map.get("username") as String
        user.id = map.get("id").toString()
        user.avatar = map.get("avatar") as String
        user.email = map.get("email") as String
        return user
    }

    private fun parseListenerUsers(arrayList: java.util.ArrayList<Any>): List<User>? {
        if (arrayList == null || arrayList.size == 0) {
            return null
        }
        var users = ArrayList<User>()
        val i1 = 0
        (i1..arrayList?.size!! - 1).forEach { i ->
            var map: Map<String, Any> = arrayList.get(i) as Map<String, String>
            var user = User()
            user.avatar = map.get("avatar") as String
            user.id = map.get("id").toString()
            user.username = map.get("username") as String
            user.email = map.get("email") as String
            users.add(user)
        }
        return users
    }
}
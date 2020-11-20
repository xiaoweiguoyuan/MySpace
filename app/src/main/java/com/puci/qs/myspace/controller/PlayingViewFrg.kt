package com.puci.qs.myspace.controller

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.puci.qs.basedependencies.entity.Episode
import com.puci.qs.basedependencies.utils.SaveLog
import com.puci.qs.myspace.adapter.ChaZuiAdapter
import com.puci.qs.myspace.adapter.PlayAvatarsAdapter
import com.puci.qs.myspace.base.CommonCodeEvent
import com.puci.qs.myspace.entity.*
import com.puci.qs.myspace.enum.PattleEnum
import com.puci.qs.myspace.net.HttpEngine
import com.puci.qs.myspace.utils.*
import com.puci.qs.qishuier.BuildConfig
import com.puci.qs.qishuier.R
import com.puci.qs.spacenet.http.utils.ToastUtils
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class PlayingViewFrg(var context: Context) : View.OnClickListener{

    private var img_iv: ImageView? = null
    private lateinit var avatarsAdapter: PlayAvatarsAdapter
    private var refresh_layout: SmartRefreshLayout? = null
    private var avatars_recyclerview: RecyclerView? = null
    private var topic_name: TextView? = null
    private var cast_title: TextView? = null
    private lateinit var view: View
    private var comment_recyclerview: RecyclerView? = null
    private var comment_input_ll: View? = null
    private var avatar_input: ImageView? = null
    private var edit_input: EditText? = null
    private var send_btn: ImageView? = null
    private var episode: Episode? = null

    fun onCreateView(): View {
        view = View.inflate(context, R.layout.fragment_playing_layout, null)
        initview()
        return view
    }

    private fun initview() {
        cast_title = view?.findViewById<TextView>(R.id.cast_title)
        topic_name = view?.findViewById<TextView>(R.id.topic_name)
        avatar_input = view?.findViewById(R.id.avatar_input)
        comment_input_ll = view?.findViewById(R.id.comment_input_ll)
        img_iv = view?.findViewById<ImageView>(R.id.img_iv)
        val commentInputController = CommentInputController(comment_input_ll)
        edit_input = view?.findViewById(R.id.edit_input)
        send_btn = view?.findViewById(R.id.send_btn)
        send_btn?.setOnClickListener(this)

        avatars_recyclerview = view?.findViewById<RecyclerView>(R.id.avatars_recyclerview)
        avatars_recyclerview?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        avatarsAdapter = PlayAvatarsAdapter(context, getAvatars())
        avatars_recyclerview?.adapter = PlayAvatarsAdapter(context, getAvatars())
        avatars_recyclerview?.addItemDecoration(AvatarsDecoration())

        refresh_layout = view?.findViewById<SmartRefreshLayout>(R.id.refresh_layout)
        comment_recyclerview = view?.findViewById(R.id.recyclerView)
        comment_recyclerview?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        comment_recyclerview?.adapter = context?.let { ChaZuiAdapter(it, null) }
        comment_recyclerview?.addItemDecoration(CommonDecoration(0, 9, 0, 18))
        if (BuildConfig.DEBUG) {
            Glide.with(context)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603724295070&di=b4ffc95f3fcd40345e6835bf3383fd07&imgtype=0&src=http%3A%2F%2Fpic3.zhimg.com%2F50%2Fv2-6f1c492cbdfe3c24aae44e935a796d5a_hd.jpg")
                .into(avatar_input!!)
        }
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val b = comment_input_ll?.visibility == View.VISIBLE
                showCommentLl(!b)
            }

        })
    }

    private fun getAvatars(): MutableList<String>? {
        if (!BuildConfig.DEBUG) {
            return null
        }
        var avatars = ArrayList<String>()
        for (i in 1..10) {
            avatars.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603724295070&di=b4ffc95f3fcd40345e6835bf3383fd07&imgtype=0&src=http%3A%2F%2Fpic3.zhimg.com%2F50%2Fv2-6f1c492cbdfe3c24aae44e935a796d5a_hd.jpg")
        }
        return avatars.toMutableList()
    }

    fun onPlayStart(position: Int, episode: Episode) {
        this.episode = episode
        cast_title?.text = episode.title
        cast_title?.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        cast_title?.setSingleLine(true);
        cast_title?.setSelected(true);
        cast_title?.setFocusable(true);
        cast_title?.requestFocus()
        cast_title?.setFocusableInTouchMode(true);

//        cast_title?.ellipsize = TextUtils.TruncateAt.MARQUEE
//        cast_title?.marqueeRepeatLimit = 10
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            cast_title?.focusable = true
        }
//        cast_title?.isFocusableInTouchMode = true
        topic_name?.text = episode.showNotes

    }

    fun showCommentLl(b: Boolean) {
        if (b) {
            comment_input_ll?.visibility = View.VISIBLE
            EventBus.getDefault().post(
                CommonCodeEvent(
                    Constants.MediaCons.HIDE_PLAY_CONTROLLER,
                    null
                )
            )
        } else {
            comment_input_ll?.visibility = View.GONE
            EventBus.getDefault().post(
                CommonCodeEvent(
                    Constants.MediaCons.SHOW_PLAY_CONTROLLER,
                    null
                )
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.send_btn -> {
                KeyboardUtils.hideSoftKeyboard(context as Activity, edit_input)
                showCommentLl(false)
                var episoid: String = ""
                if (null != episode) {
                    episoid = episode!!.id.toString()
                }
                HttpEngine.getInstance().sendComment(episoid, edit_input?.text.toString(), object :
                    Callback<CommentEpisodeRes> {
                    override fun onResponse(call: Call<CommentEpisodeRes>, response: Response<CommentEpisodeRes>) {
                        SaveLog.i("@@@-onResponse", response.body().toString())
                        if (response.body()?.status == 201) {
                            edit_input?.setText("")
                        }
                    }

                    override fun onFailure(call: Call<CommentEpisodeRes>, t: Throwable) {
                        SaveLog.e("@@@-onFailure", t.message)
//                        edit_input?.setText("")
                        ToastUtils.show(context, t.message)
                    }

                })
            }
        }
    }

    fun addComments(comments: ArrayList<CommentBean>) {
        if ((comments == null || comments.size == 0) && !BuildConfig.DEBUG) {
            return
        }
        val adapter: ChaZuiAdapter? = comment_recyclerview?.adapter as ChaZuiAdapter?
        var datas = comments
        var cnt = adapter?.itemCount
        if (BuildConfig.DEBUG && (datas == null || datas.size == 0)) {
            for (i in 1..2) {
                cnt = cnt?.plus(1)
                val commentBean = CommentBean()
                var user = User()
                user.avatar = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603724295070&di=b4ffc95f3fcd40345e6835bf3383fd07&imgtype=0&src=http%3A%2F%2Fpic3.zhimg.com%2F50%2Fv2-6f1c492cbdfe3c24aae44e935a796d5a_hd.jpg"
                user.username = "范冰冰/李晨: " + cnt
                commentBean.content = "我过上了理想生活，但……\",\"showNotes\":\"这期播客，是吕太阳和 "
                commentBean.user = user
                datas.add(commentBean)
            }
        }
        adapter?.addDatas(datas)
        adapter?.notifyDataSetChanged()
        adapter?.itemCount?.minus(1)?.let { comment_recyclerview?.smoothScrollToPosition(it) }
    }

    fun onEpisodeDetail(detail: EpisodeDetail) {
        if (detail != null) {
            //更新背景色
            if (null != img_iv) {
                var options = RequestOptions()
                    .transform(MultiTransformation(RoundedCorners(30)))
                Glide.with(context)
                    .asBitmap()
                    .load(detail.episode?.episode_art_url)
                    .apply(options)
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            val blurBitmap = BlurBitmapUtil.blurBitmap(context, resource, 3f)
                            img_iv?.setImageBitmap(blurBitmap)
//                            //取色
                            HSLUtils.generatePalett(resource, context.resources.getColor(R.color.color_ffF8B7), PattleEnum.mutedColor,
                                object : HSLUtils.HSLCallBack{
                                    override fun onHSL(color: Int) {
                                        var playInfo = PlayingInfo()
                                        playInfo.themeColor = color
                                        val event = CommonCodeEvent(
                                            Constants.MediaCons.THEME_CHANGE,
                                            playInfo
                                        )
                                        EventBus.getDefault().post(event)
                                    }

                                }
                            )
                        }

                    })
            }

            //更新正在收听内容
            var i = 0
            var avartars = ArrayList<String>()
            (i..detail.episode?.listening_users?.size!!).forEach {
                avartars.add(detail.episode!!.listening_users!!.get(i).avatar)
            }
            val adapter:PlayAvatarsAdapter = avatars_recyclerview?.adapter as PlayAvatarsAdapter
            adapter.mDatas = avartars
            adapter.notifyDataSetChanged()
        }
    }

}
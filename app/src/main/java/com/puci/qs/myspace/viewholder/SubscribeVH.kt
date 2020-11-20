package com.puci.qs.myspace.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puci.qs.myspace.adapter.BaseViewHolder
import com.puci.qs.myspace.adapter.PlayAvatarsAdapter
import com.puci.qs.myspace.adapter.SubscribeAdapter
import com.puci.qs.myspace.entity.EpisodeDetail
import com.puci.qs.myspace.utils.AvatarsDecoration
import com.puci.qs.myspace.utils.DurationUtils
import com.puci.qs.myspace.utils.ImageLoadUtils
import com.puci.qs.qishuier.R

open class SubscribeVH(itemView: View) : BaseViewHolder<EpisodeDetail.CurEpisode>(itemView) {
    var itemclickListener: SubscribeAdapter.OnItemViewClickListener? = null
    lateinit var coverIv: ImageView
    lateinit var title_tv: TextView
    lateinit var author_tv: TextView
    lateinit var desc_tv: TextView
    lateinit var play_btn: ImageView
    lateinit var avatars_recyclerview: RecyclerView
    lateinit var listener_cnt: TextView
    lateinit var time_tv: TextView

    override fun initView() {
        coverIv = itemView.findViewById(R.id.cover_iv)
        title_tv = itemView.findViewById(R.id.title_tv)
        author_tv = itemView.findViewById(R.id.author_tv)
        desc_tv = itemView.findViewById(R.id.desc_tv)
        play_btn = itemView.findViewById(R.id.play_btn)
        avatars_recyclerview = itemView.findViewById(R.id.avatars_recyclerview)
        avatars_recyclerview.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

        avatars_recyclerview?.adapter = PlayAvatarsAdapter(itemView.context, null)
        avatars_recyclerview?.addItemDecoration(AvatarsDecoration())
        listener_cnt = itemView.findViewById(R.id.listener_cnt)
        time_tv = itemView.findViewById(R.id.time_tv)
    }

    override fun bindData(pos: Int, t: EpisodeDetail.CurEpisode) {
        t.episode_art_url?.let { ImageLoadUtils.loadCornerPic(it, coverIv, 12) }
        title_tv.text = t.title
        desc_tv.text = t.description
        listener_cnt.text = itemView.context.resources.getString(R.string.subscribe_listener_cnt, t.comment_cnt, t.play_cnt)
        var utils = DurationUtils()
        time_tv.text = t.total_time?.let { itemView.context.resources.getString(R.string.subscribe_time, utils.getCurTime(it), 1) }
        play_btn.setColorFilter(itemView.context.resources.getColor(R.color.color_5cc93b))
        author_tv.text = t.podcast?.author
        //设置头像
        var avatars = ArrayList<String>()
        if (null != t && null != t.listening_users) {
            for(u in t.listening_users!!) {
                avatars.add(u.avatar)
            }
            val playAvatarsAdapter = avatars_recyclerview.adapter as PlayAvatarsAdapter
            playAvatarsAdapter.mDatas = avatars
            playAvatarsAdapter.notifyDataSetChanged()
        }
        play_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (itemclickListener != null) {
                    itemclickListener?.onItemViewClick(t, pos, play_btn)
                }
            }

        })
    }
}
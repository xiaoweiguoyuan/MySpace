package com.puci.qs.myspace.entity

import com.puci.qs.basedependencies.entity.Podcast

open class EpisodeDetail : BaseType() {
    var episode: CurEpisode? = null
    var current_user: User? = null

    class CurEpisode: BaseType() {

        var subtitle: String? = null
        var itune_id: String? = null
        var id : String? = null
        var url : String? = null
        var title: String? = null
        var showNotes: String? = null
        var author: String? = null
        var feed: String? = null
        var category: String? = null
        var image: String? = null
        var duration: Int? = 0

        var description: String? = null
        var episode_art_url: String? = null
        var published: String? = null //发表时间戳
        var link: String? = null//音频链接
        var format: String? = null//音频格式
        var total_time: Long? = 0
        var podcast_id: String? = null
        var like_cnt: Int? = 0//点赞喜欢的人数
        var podcast: Podcast? = null
        var listening_cnt: Int? = 0//点赞喜欢的人数
        var listening_users: List<User>? = null
        var art_url: ArtUrl? = null
        var comment_cnt: Int? = null//插嘴条数
        var play_cnt: Int? = null//播放次数


//    podcast: {...}
//    like_cnt: 111,
//    listening_cnt: 111,
//    listening_users: [
//    # User模型
//    ]
    }

    class ArtUrl : BaseType() {
        var image_small: String? = null
        var image_middle: String? = null
        var image_big: String? = null
    }

}
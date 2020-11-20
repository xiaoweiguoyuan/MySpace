package com.wuba.car.basedependencies.entity

/**
 * 单集
 */
open class Episode : BaseType() {
    var id : String? = null
    var url : String? = null
    var title: String? = null
    var subtitle: String? = null
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
    var listening_cnt: Int? = 0
    var itune_id: String? = null

    //播放控制
    var maxSize: Int? = 0
    var progress: Int? = 0
    var buffSize: Int = 0

}
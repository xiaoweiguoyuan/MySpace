package com.wuba.car.basedependencies.entity

/**
 * 节目
 */
open class Podcast : BaseType() {
    var id: String? = null
    var itune_id: String? = null
    var name: String? = null
    var author: String? = null
    var url: String? = null
    var feed: String? = null
    var category: String? = null
    var image: String? = null
    var episode_count: Int = 0

//    long id,
//    string name,
//    string author,
//    string url,  // https://podcasts.apple.com/us/podcast/id473501583
//    string feed, // https://since1989.org/feed/wasai
//    string category,
//    string image,
//    int episode_count
}
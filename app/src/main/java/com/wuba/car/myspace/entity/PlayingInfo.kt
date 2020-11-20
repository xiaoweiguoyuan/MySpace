package com.wuba.car.myspace.entity

import com.wuba.car.basedependencies.entity.Episode

open class PlayingInfo : BaseType() {
    var buffSize: Int = 0
    var episode: Episode? = null
    var maxSize: Int? = 0
    var progress: Int? = 0
    var index: Int? = 0
    var listLength: Int? = 0
    var themeColor: Int? = 0
}
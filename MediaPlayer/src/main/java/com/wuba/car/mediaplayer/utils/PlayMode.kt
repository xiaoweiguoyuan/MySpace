package com.wuba.car.mediaplayer.utils

enum class PlayMode(value: Int) {
    linear(0),//顺序播放
    radom(1),//随机播放
    loop_single(2),//单曲循环播放
    loop_linear(3),//列表循环播放
    ;
}
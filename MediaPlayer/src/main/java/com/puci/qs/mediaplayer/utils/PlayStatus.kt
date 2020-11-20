package com.puci.qs.mediaplayer.utils

enum class PlayStatus(value: Int) {
    LOADING(0),
    PLAYING(1),
    PAUSED(2),
    STOP(3),
    FINISH(4),
    UNKOWN(5),
    ;
}
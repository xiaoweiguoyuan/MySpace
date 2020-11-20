package com.wuba.car.mediaplayer.controller;

import android.view.View;

import com.wuba.car.mediaplayer.base.QSPlayer;

/**
 * 播放UI及逻辑控制
 */
public abstract class BasePlayController {

    public final View playView;
    public QSPlayer player;

    public BasePlayController(View playView) {
        this.playView = playView;
    }
    public abstract QSPlayer initPlayer();
}

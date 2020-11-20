package com.puci.qs.mediaplayer.listener;

public interface PlayListener {
    void onProgress(long maxsize, long progress);

    void onBufferUpdate(long maxsize, long buffSize);

    void onPrepared();

    void onAutoCompletion();

    void onCompletion();

    void onBufferingUpdate(int percent);

    void onSeekComplete(long currentPosition);

    void onError(int what, int extra);

    void onInfo(int what, int extra);

    void onVideoSizeChanged();
}

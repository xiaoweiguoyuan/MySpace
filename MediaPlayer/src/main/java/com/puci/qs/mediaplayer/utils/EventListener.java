package com.puci.qs.mediaplayer.utils;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.puci.qs.basedependencies.utils.SaveLog;

public class EventListener implements Player.EventListener {

    private final String TAG = EventListener.class.getSimpleName() + "-";

    @Override
    public void onTimelineChanged(Timeline timeline, int reason) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

    }

    @Override
    public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onIsLoadingChanged(boolean isLoading) {
        SaveLog.d(TAG + "onIsLoadingChanged", "isLoading: " + isLoading);
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

        SaveLog.d(TAG + "onLoadingChanged", "isLoading: " + isLoading);
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlaybackStateChanged(int state) {

        SaveLog.d(TAG + "onLoadingChanged", "state: " + state);
    }

    @Override
    public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {

    }

    @Override
    public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {

    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {

        SaveLog.d(TAG + "onIsPlayingChanged", "isPlaying: " + isPlaying);
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

        SaveLog.d(TAG + "onSeekProcessed");
    }

    @Override
    public void onExperimentalOffloadSchedulingEnabledChanged(boolean offloadSchedulingEnabled) {

    }
}

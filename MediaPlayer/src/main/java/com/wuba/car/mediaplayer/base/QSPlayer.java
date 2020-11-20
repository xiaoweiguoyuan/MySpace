package com.wuba.car.mediaplayer.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.wuba.car.basedependencies.entity.Episode;
import com.wuba.car.mediaplayer.listener.PlayListener;
import com.wuba.car.mediaplayer.utils.PlayMode;
import com.wuba.car.mediaplayer.utils.PlayStatus;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.wuba.car.mediaplayer.Constants.MediaCons.BUFFER_PROGRESS_UPDATE;
import static com.wuba.car.mediaplayer.Constants.MediaCons.PLAY_PROGRESS_UPDATE;

public class QSPlayer implements PlayListener {

    private int rawId;
    private PlayStatus status = PlayStatus.UNKOWN;
    private AudioPlayer player;
    private List<String> mPaths;
    protected ProgressTimerTask mProgressTimerTask;
    private Timer timer;
    private PlayListener playListener;
    PlayMode playMode = PlayMode.linear;//默认顺序播放
    private List<Episode> episodeList = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PLAY_PROGRESS_UPDATE:
                    long curTime = player.getCurrentPosition();
//                    SaveLog.w("@@@", "getCurrentPosition: " + curTime);
                    onProgress(player.duration()/1000, curTime/1000);
                    break;
                case BUFFER_PROGRESS_UPDATE:
//                    SaveLog.w("@@@", "getTotalBufferedDuration: " + player.getTotalBufferedDuration() + ", bufferedPos: " + player.getBufferedPosition() + ", contentBuffredPos: " + player.getContentBufferedPosition());
                    onBufferUpdate(player.duration()/1000, player.getTotalBufferedDuration()/1000);
                    break;
                default:
                    break;
            }
        }
    };


    public QSPlayer(Context context) {
        player = new AudioPlayer(context);
    }

    public void setPlayListener(@NotNull PlayListener playListener) {
        this.playListener = playListener;
    }

    @Override
    public void onProgress(final long maxsize, final long progress) {
        playListener.onProgress(maxsize, progress);
    }

    @Override
    public void onBufferUpdate(final long maxsize, final long buffSize) {
        playListener.onBufferUpdate(maxsize, buffSize);
    }

    @Override
    public void onPrepared() {
    }

    @Override
    public void onAutoCompletion() {
        playListener.onAutoCompletion();
    }

    @Override
    public void onCompletion() {
        playListener.onCompletion();
    }

    @Override
    public void onBufferingUpdate(int percent) {

    }

    @Override
    public void onSeekComplete(long currentPosition) {
        playListener.onSeekComplete(currentPosition);
    }

    @Override
    public void onError(int what, int extra) {

    }

    @Override
    public void onInfo(int what, int extra) {

    }

    @Override
    public void onVideoSizeChanged() {

    }

    @Deprecated
    public void setPath(String path) {
        player.setMediaPath(path);
    }

    public void addPath(String path) {
        List<String> paths = new ArrayList<>();
        paths.add(path);
        player.addMediaPaths(paths);
    }

    /**
     * 重置播放列表
     * @param paths
     */
    public void setPaths(List<String> paths) {
        mPaths = paths;
        player.addMediaPaths(paths);
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
        player.setRawId(rawId);
    }

    public void start() {
        if (status == PlayStatus.LOADING || status == PlayStatus.PLAYING) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
            mProgressTimerTask = new ProgressTimerTask();
            timer.schedule(mProgressTimerTask,0,300);
        }
        player.start();
        status = PlayStatus.PLAYING;
    }


    //下一曲
    public void next() {
        player.next();
    }

    //上一曲
    public void pre() {
        player.pre();
    }

    public void seekTo(int num) {
        player.seekTo(num * 1000);
    }

    public void seekToDefaultPosition(int index) {
        player.seekToDefaultPosition(index - 1);
        start();
    }

    public void release() {
        player.release();
    }

    public void stop() {
        player.stop();
        timer.cancel();
    }

    public void pause() {
        player.pause();
        status = PlayStatus.PAUSED;
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public int getMediaItemCount() {
        return player.getMediaItemCount();
    }

    public void seekBy(int seekBy) {
        long pos = player.getCurrentPosition();
        player.seekTo((int) (pos + seekBy * 1000));
    }

    public void reset() {
//        player.stop();
        status = PlayStatus.UNKOWN;
        player.stop();
    }

    public void prepare() {
        player.prepare();
    }

    /**
     * 设置缓存进度  --将缓存进度置零
     */
    class ProgressTimerTask extends TimerTask {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = PLAY_PROGRESS_UPDATE;
            handler.sendMessage(msg);
            Message msg2 = new Message();
            msg2.what = BUFFER_PROGRESS_UPDATE;
            handler.sendMessage(msg2);
        }
    }

}

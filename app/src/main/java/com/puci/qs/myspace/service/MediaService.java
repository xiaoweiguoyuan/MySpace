package com.puci.qs.myspace.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.puci.qs.basedependencies.entity.Episode;
import com.puci.qs.basedependencies.utils.SaveLog;
import com.puci.qs.mediaplayer.base.QSPlayer;
import com.puci.qs.mediaplayer.listener.PlayListener;
import com.puci.qs.myspace.MyApplication;
import com.puci.qs.myspace.base.CommonCodeEvent;
import com.puci.qs.myspace.entity.PlayingInfo;
import com.puci.qs.myspace.net.HttpEngine;
import com.puci.qs.myspace.utils.Constants;
import com.puci.qs.qishuier.BuildConfig;
import com.puci.qs.spacenet.http.response.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.puci.qs.myspace.utils.Constants.MediaCons.PLAY_NEXT_INFO;


/**
 *
 */
public class MediaService extends Service implements PlayListener {

    public static final String EXTRA_PARAM1 = "com.wuba.car.myspace.service.extra.PARAM1";

    public static final String ACTION_MUSIC_PLAY = "com.wuba.car.myspace.service.action.MUSIC_PLAY";
    public static final String ACTION_MUSIC_ADD = "com.wuba.car.myspace.service.action.MUSIC_ADD";
    public static final String ACTION_MUSIC_DELETE = "com.wuba.car.myspace.service.action.MUSIC_DELETE";
    public static final String ACTION_MUSIC_PRE = "com.wuba.car.myspace.service.action.MUSIC_PRE";
    public static final String ACTION_MUSIC_NEXT = "com.wuba.car.myspace.service.action.MUSIC_NEXT";
    public static final String ACTION_MUSIC_SEEK = "com.wuba.car.myspace.service.action.MUSIC_SEEK";
    public static final String ACTION_MUSIC_PAUSE = "com.wuba.car.myspace.service.action.MUSIC_PAUSE";
    public static final String EXTRA_EPISODE = "extra_episode";
    public static final String EXTRA_SEEK = "extra_seek";
    public static final String EXTRA_SEEK_BY = "extra_seek_by";
    public static final String ACTION_MUSIC_SEEK_BY = "com.wuba.car.myspace.service.action.SEEK_BY";
    public final String TAG = MediaService.class.getSimpleName();

    private List<Episode> episodeList = new ArrayList<>();

    private int lastMediaIndex = -1;
    private int currentMediaIndex = -1;
    public QSPlayer player;
    private long mMaxSize;
    private long mProgress;
    private long mBuffSize;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        onHandleIntent(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_MUSIC_PLAY.equals(action)) {
                handleMusicPlay(intent);
            } else if (ACTION_MUSIC_ADD.equals(action)) {
                handleMusicAdd(intent);
            } else if (ACTION_MUSIC_PRE.equals(action)) {
                handleMusicPre(intent);
            } else if (ACTION_MUSIC_NEXT.equals(action)) {
                handleMusicNext(intent);
            } else if (ACTION_MUSIC_SEEK.equals(action)) {
                handleMusicSeek(intent);
            } else if (ACTION_MUSIC_PAUSE.equals(action)) {
                handleMusicPause(intent);
            } else if (ACTION_MUSIC_SEEK_BY.equals(action)) {
                handleMusicSeekBy(intent);
            } else if (ACTION_MUSIC_DELETE.equals(action)) {
                handleMusicDelete(intent);
            }
        }
    }

    private void handleMusicDelete(Intent intent) {
        // TODO: 2020/11/17 有一种情况没有写进来，就是删除项是正在播放的曲目时
        Episode episode = (Episode) intent.getSerializableExtra(EXTRA_EPISODE);
        if (episode == null) return;
        for (int i = 0; i < episodeList.size(); i++) {
            if (episodeList.get(i).getId().equals(episode.getId())) {
                //删除
                episodeList.remove(i);
                //上传当前列表
                uploadPlayList();
                break;
            }
        }
    }

    private void uploadPlayList() {
        ArrayList<Integer> ids = new ArrayList<>();
        for(int i = 0; i < episodeList.size(); i++) {
            ids.add(Integer.parseInt(episodeList.get(i).getId()));
        }
        HttpEngine.getInstance().uploadEpisodeList(ids, new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                SaveLog.i("@@@-uploadPlayList", response);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                SaveLog.e("@@@-uploadPlayList", t.getMessage());
            }
        });
    }

    private void handleMusicSeekBy(Intent intent) {

        int seekBy = intent.getIntExtra(EXTRA_SEEK_BY, 0);
        player.seekBy(seekBy);
    }

    private void handleMusicPause(Intent intent) {
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.start();
        }
    }

    private void handleMusicSeek(Intent intent) {
        int progress = intent.getIntExtra(EXTRA_SEEK, 0);
        player.seekTo(progress);
    }

    private void handleMusicNext(Intent intent) {
        if (currentMediaIndex == episodeList.size() - 1) return;
        lastMediaIndex = currentMediaIndex;
        currentMediaIndex++;
        startPlay(true);
    }

    private void startPlay(boolean shouldBroadcast) {
        //先保存上一段播放的进度 begin
//        if (lastMediaIndex > 0 && lastMediaIndex < episodeList.size()) {
//            Episode episode = episodeList.get(lastMediaIndex);
//            episode.setProgress((int) mProgress);
//            //todo 尚未保存到本地
//            String value = EpisodeUtils.Companion.episodeToStr(episode);
//            SpUtils.Companion.saveString(episode.getId(), value);
//            //todo 尚未实现从本地获取
//        }
        //end
        player.reset();
        Episode episode = episodeList.get(currentMediaIndex);
        String path = episode.getUrl();
        if (TextUtils.isEmpty(path)) {
            path = episode.getLink();
        }
        player.setPath(path);
        player.start();
        if (episode.getProgress() != 0) {
            // TODO: 2020/11/19 目前无效，因为刚播放的时候，播放总长度是负数，播放1～3秒后，才有duration，此时seekTo才有效，目前无有效解决办法
            player.seekTo(episode.getProgress() / 1000);
        }
        //保存当前播放曲目的id，方便下一次打开app后，直接进行播放
//        SpUtils.Companion.saveString(Constants.MediaSaveCons.EPISODE_INFO, episode.getId());
//        SpUtils.Companion.saveString(episode.getId(), EpisodeUtils.Companion.episodeToStr(episode));
        if (shouldBroadcast) {
            PlayingInfo info = new PlayingInfo();
            info.setEpisode(episode);
            info.setIndex(currentMediaIndex);
            CommonCodeEvent<PlayingInfo> event = new CommonCodeEvent<PlayingInfo>(PLAY_NEXT_INFO, info);
            EventBus.getDefault().post(event);
        }
    }

    private void handleMusicPre(Intent intent) {
        if (currentMediaIndex == 0) return;
        lastMediaIndex = currentMediaIndex;
        currentMediaIndex--;
        startPlay(true);
    }

    private void handleMusicAdd(Intent intent) {
        getPlayer();
        Episode episode = (Episode) intent.getSerializableExtra(EXTRA_EPISODE);
        if (episode == null) return;
        if (!isInclude(episode)) {
            episodeList.add(episode);
        }
    }

    private void handleMusicPlay(Intent intent) {
        getPlayer();
        Episode episode = (Episode) intent.getSerializableExtra(EXTRA_EPISODE);
        if (episode == null || TextUtils.isEmpty(episode.getId())) return;
        //正在播放中的
        if (episodeList.size() > 0 && episodeList.get(currentMediaIndex).getId().equals(episode.getId())) return;
        if (episode != null) {
            if (!isInclude(episode)) {
                if (currentMediaIndex < 0) {
                    currentMediaIndex = 0;
                } else {
                    lastMediaIndex = currentMediaIndex;
                    currentMediaIndex++;
                }
                if (currentMediaIndex >= episodeList.size()) {
                    episodeList.add(episode);
                } else {
                    episodeList.add(currentMediaIndex, episode);
                }
                //播放列表更新，上传到服务区
                uploadPlayList();
            } else {
                lastMediaIndex = currentMediaIndex;
                currentMediaIndex = caculateIndex(episode);
            }

            startPlay(false);
        }
    }

    private void getPlayer() {
        if (player == null) {
            this.player = new QSPlayer(MyApplication.getInstance());
            player.setPlayListener(this);
        }
    }

    private int caculateIndex(Episode episode) {
        for (int i = 0; i < episodeList.size(); i++) {
            if (episodeList.get(i).getId().equals(episode.getId())) {
                return i;
            }
        }
        return 0;
    }

    private boolean isInclude(Episode episode) {
        for (int i = 0; i < episodeList.size(); i++) {
            if (episodeList.get(i).getId().equals(episode.getId())) {
                if (BuildConfig.DEBUG) {
                    if (episodeList.get(i).getUrl().equals(episode.getUrl())) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onProgress(long maxsize, long progress) {
        mMaxSize = maxsize;
        mProgress = progress;

        PlayingInfo info = new PlayingInfo();
        info.setIndex(currentMediaIndex);
        Episode episode = episodeList.get(currentMediaIndex);
        episode.setProgress((int) mProgress);
        episode.setMaxSize((int) mMaxSize);
        info.setEpisode(episode);
        info.setListLength(episodeList.size());
        info.setMaxSize((int) mMaxSize);
        info.setProgress((int) mProgress);
        CommonCodeEvent<PlayingInfo> event = new CommonCodeEvent<PlayingInfo>(Constants.MediaCons.PLAY_PROGRESS_UPDATE, info);
        EventBus.getDefault().post(event);
        if (progress >= maxsize && maxsize > 0) {
            //播放结束
            onPlayEpisodeComplete();
        }
    }

    private void onPlayEpisodeComplete() {
//        PlayingInfo info = new PlayingInfo();
//        info.setIndex(currentMediaIndex);
//        Episode episode = episodeList.get(currentMediaIndex);
//        info.setEpisode(episode);
//        info.setListLength(episodeList.size());
//        info.setMaxSize((int) mMaxSize);
//        info.setProgress((int) mProgress);
//        CommonCodeEvent<PlayingInfo> event = new CommonCodeEvent<PlayingInfo>(Constants.MediaCons.PLAY_COMPLETE, info);
//        EventBus.getDefault().post(event);
        handleMusicNext(null);
    }

    @Override
    public void onBufferUpdate(long maxsize, long buffSize) {
        mBuffSize = mBuffSize + buffSize;
        PlayingInfo info = new PlayingInfo();
        info.setIndex(currentMediaIndex);
        Episode episode = episodeList.get(currentMediaIndex);
        info.setEpisode(episode);
        info.setListLength(episodeList.size());
        info.setBuffSize((int) mBuffSize);
        CommonCodeEvent<PlayingInfo> event = new CommonCodeEvent<PlayingInfo>(Constants.MediaCons.BUFFER_PROGRESS_UPDATE, info);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onPrepared() {

    }

    @Override
    public void onAutoCompletion() {
        PlayingInfo info = new PlayingInfo();
        info.setIndex(currentMediaIndex);
        Episode episode = episodeList.get(currentMediaIndex);
        info.setEpisode(episode);
        info.setListLength(episodeList.size());
        info.setMaxSize((int) mMaxSize);
        info.setProgress((int) mProgress);
        CommonCodeEvent<PlayingInfo> event = new CommonCodeEvent<PlayingInfo>(Constants.MediaCons.PLAY_COMPLETE, info);
        EventBus.getDefault().post(event);
        handleMusicNext(null);
    }

    @Override
    public void onCompletion() {
//        PlayingInfo info = new PlayingInfo();
//        info.setIndex(currentMediaIndex);
//        Episode episode = episodeList.get(currentMediaIndex);
//        info.setEpisode(episode);
//        info.setListLength(episodeList.size());
//        info.setMaxSize((int) mMaxSize);
//        info.setProgress((int) mProgress);
//        CommonCodeEvent<PlayingInfo> event = new CommonCodeEvent<PlayingInfo>(Constants.MediaCons.PLAY_COMPLETE, info);
//        EventBus.getDefault().post(event);
//        handleMusicNext(null);
    }

    @Override
    public void onBufferingUpdate(int percent) {

    }

    @Override
    public void onSeekComplete(long currentPosition) {

    }

    @Override
    public void onError(int what, int extra) {
        SaveLog.e(TAG, "onError- what: " + what + ", extra: " + extra);
    }

    @Override
    public void onInfo(int what, int extra) {

    }

    @Override
    public void onVideoSizeChanged() {

    }
}

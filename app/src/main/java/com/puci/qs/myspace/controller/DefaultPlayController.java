package com.puci.qs.myspace.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.puci.qs.basedependencies.entity.Episode;
import com.puci.qs.mediaplayer.base.QSPlayer;
import com.puci.qs.mediaplayer.controller.BasePlayController;
import com.puci.qs.mediaplayer.listener.PlayListener;
import com.puci.qs.myspace.MyApplication;
import com.puci.qs.myspace.utils.DurationUtils;
import com.puci.qs.qishuier.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.puci.qs.mediaplayer.Constants.MediaCons.BUFFER_PROGRESS_UPDATE;
import static com.puci.qs.mediaplayer.Constants.MediaCons.PLAY_PROGRESS_UPDATE;

public class DefaultPlayController extends BasePlayController implements View.OnClickListener {

    private final Context mContext;
    private ImageView iv;
    private AppCompatSeekBar seekBar;
    private boolean seekable;
    private int seekProgress;
    private TextView time_tv;
    private TextView duration_tv;
    private DurationUtils durationUtils;
    private List<Episode> episodeList = new ArrayList<>();
    private int currentMediaIndex = -1;
    private PlayControllerCallback playControllerCallback;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PLAY_PROGRESS_UPDATE:
                    time_tv.setText(durationUtils.getCurTime(msg.arg1));
                    duration_tv.setText(durationUtils.getCurTime(msg.arg2));
                    break;
                case BUFFER_PROGRESS_UPDATE:
                    seekBar.setSecondaryProgress(msg.arg2);
                    break;
                default:
                    break;
            }
        }
    };
    private boolean isShow = true;

    public DefaultPlayController(Context context, QSPlayer player, View playView) {
        super(playView);
        mContext = context;
        this.player = player;
        if (player == null) {
            this.player = initPlayer();
        }
        initview();
    }

    @Override
    public QSPlayer initPlayer() {
        return new QSPlayer(MyApplication.getInstance());
    }

    private void initview() {
        playView.findViewById(R.id.setting_btn).setOnClickListener(this);
        playView.findViewById(R.id.pre_btn).setOnClickListener(this);
        playView.findViewById(R.id.play_btn).setOnClickListener(this);
        playView.findViewById(R.id.next_btn).setOnClickListener(this);
        playView.findViewById(R.id.like_btn).setOnClickListener(this);
        time_tv = playView.findViewById(R.id.time_tv);
        duration_tv = playView.findViewById(R.id.duration_tv);
        durationUtils = new DurationUtils();

        iv = (ImageView) playView.findViewById(R.id.play_btn);
        seekBar = (AppCompatSeekBar) playView.findViewById(R.id.progress);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                SaveLog.w("@@@-onProgressChanged", "progress: " + progress + ", fromUser: " + fromUser);
                if (fromUser) {
                    seekProgress = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                SaveLog.w("@@@-onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                SaveLog.w("@@@-onStopTrackingTouch", "seekProgress: " + seekProgress);
                player.seekTo(seekProgress);
            }
        });

        player.setPlayListener(new PlayListener() {
            @Override
            public void onProgress(long maxsize, long progress) {
//                Message msg = new Message();
//                msg.what = PLAY_PROGRESS_UPDATE;
//                msg.arg1 = (int) maxsize;
//                msg.arg2 = (int) progress;
                try {
                    if (isShow) {
                        time_tv.setText(durationUtils.getCurTime(progress));

                        duration_tv.setText(durationUtils.getCurTime(maxsize));
                        seekBar.setProgress((int) progress);
                        seekBar.setMax((int) maxsize);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBufferUpdate(long maxsize, long buffSize) {
//                Message msg = new Message();
//                msg.what = BUFFER_PROGRESS_UPDATE;
//                msg.arg1 = (int) maxsize;
//                msg.arg2 = (int) buffSize;
//                seekBar.setSecondaryProgress((int) buffSize);
            }

            @Override
            public void onPrepared() {

            }

            @Override
            public void onAutoCompletion() {

            }

            @Override
            public void onCompletion() {
                next();
            }

            @Override
            public void onBufferingUpdate(int percent) {

            }

            @Override
            public void onSeekComplete(long currentPosition) {

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
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_btn:
                if (player.isPlaying()) {
                    player.pause();
                    iv.setImageResource(R.drawable.play_ic);
                } else {
                    player.start();
                    iv.setImageResource(R.drawable.pause_ic);
                }
                break;
            case R.id.pre_btn:
                pre();
                break;
            case R.id.next_btn:
                next();
                break;
            case R.id.setting_btn:
                break;

        }
    }

    private void next() {
        if (currentMediaIndex == episodeList.size() - 1) return;
        currentMediaIndex++;
        player.setPath(episodeList.get(currentMediaIndex).getUrl());
        start();
    }

    private void pre() {
        if (currentMediaIndex == 0) return;
        currentMediaIndex--;
        player.setPath(episodeList.get(currentMediaIndex).getUrl());
        start();
    }

    public void start() {
        player.start();
        if (playControllerCallback != null) {
            playControllerCallback.onstart(currentMediaIndex, episodeList.get(currentMediaIndex));
        }
    }

    public void release() {
        player.release();
    }

    @Deprecated
    public void setPath(@Nullable String url) {
        player.setPath(url);
    }

    public void playEpisode(@NotNull Episode episode) {
        if (!isInclude(episode)) {
            if (currentMediaIndex < 0) {
                currentMediaIndex = 0;
            } else {
                currentMediaIndex++;
            }
            if (currentMediaIndex >= episodeList.size()) {
                episodeList.add(episode);
            } else {
                episodeList.add(currentMediaIndex, episode);
            }
        } else {
            currentMediaIndex = caculateIndex(episode);
        }
//        player.addPath(episode.getUrl());
//        start(player.getMediaItemCount());
        player.setPath(episodeList.get(currentMediaIndex).getUrl());
        start();
    }

    private int caculateIndex(Episode episode) {
        for(int i = 0; i < episodeList.size(); i++) {
            if (episodeList.get(i).getId().equals(episode.getId())) {
                return i;
            }
        }
        return 0;
    }

    private boolean isInclude(Episode episode) {
        for(int i = 0; i < episodeList.size(); i++) {
            if (episodeList.get(i).getId().equals(episode.getId())) {
                return true;
            }
        }
        return false;
    }

    private void start(Episode episode) {
        player.setPath(episode.getUrl());
        start();
    }

    private void start(int mediaItemCount) {
        player.seekToDefaultPosition(mediaItemCount);
    }

    public void addEpisode(Episode episode) {
        if (isInclude(episode)) return;
        episodeList.add(episode);
    }

    public void addEpisodeToPlayList(@NotNull List<? extends Episode> episodes) {
        episodeList.addAll(episodes);
//        for (Episode episode : episodes) {
//            player.addPath(episode.getUrl());
//        }
    }

    public void setPlayControllerCallback(PlayControllerCallback playControllerCallback) {
        this.playControllerCallback = playControllerCallback;
    }

    public void collapse() {
        isShow = false;
    }

    public void show() {
        isShow = true;
    }

    public void updateProgress(@Nullable Integer maxSize, @Nullable Integer progress) {
        if (isShow) {
            time_tv.setText(durationUtils.getCurTime(progress));

            duration_tv.setText(durationUtils.getCurTime(maxSize));
            seekBar.setProgress(progress);
            seekBar.setMax(maxSize);
        }
    }

    public interface PlayControllerCallback {
        void onstart(int position, Episode episode);
    }
}

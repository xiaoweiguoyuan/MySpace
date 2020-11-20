package com.wuba.car.myspace.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.wuba.car.basedependencies.entity.Episode;
import com.wuba.car.myspace.net.HttpEngine;
import com.wuba.car.myspace.service.MediaService;
import com.wuba.car.myspace.utils.Constants;
import com.wuba.car.myspace.utils.DurationUtils;
import com.wuba.car.myspace.utils.EpisodeUtils;
import com.wuba.car.myspace.utils.SpUtils;
import com.wuba.car.qishuier.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wuba.car.myspace.service.MediaService.EXTRA_EPISODE;

public class PlayControllerNew implements View.OnClickListener {

    private final Context mContext;
    private final View playView;
    private ImageView iv;
    private AppCompatSeekBar seekBar;
    private boolean seekable;
    private int seekProgress;
    private TextView time_tv;
    private TextView duration_tv;
    private DurationUtils durationUtils;
//    private List<Episode> episodeList = new ArrayList<>();
//    private int currentMediaIndex = -1;
    private PlayControllerCallback playControllerCallback;
    private boolean isShow = true;
    private boolean isPlaying;
    private ImageView likeBtn;
    private Episode mCurrentEpisode;
    private TextView like_tv;

    public PlayControllerNew(Context context,  View playView) {
        this.playView = playView;
        mContext = context;
        initview();
//        EventBus.getDefault().register(this);
    }

    private void initview() {
        playView.findViewById(R.id.setting_btn).setOnClickListener(this);
        playView.findViewById(R.id.pre_btn).setOnClickListener(this);
        playView.findViewById(R.id.play_btn).setOnClickListener(this);
        playView.findViewById(R.id.next_btn).setOnClickListener(this);
        likeBtn = playView.findViewById(R.id.like_btn);
        likeBtn.setOnClickListener(this);
        time_tv = playView.findViewById(R.id.time_tv);
        duration_tv = playView.findViewById(R.id.duration_tv);
        durationUtils = new DurationUtils();
        like_tv = playView.findViewById(R.id.like_text);

        iv = (ImageView) playView.findViewById(R.id.play_btn);
        seekBar = playView.findViewById(R.id.progress);
        seekBar.setPadding(15, 0, 15, 0);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                SaveLog.w("@@@-onProgressChanged", "progress: " + progress + ", fromUser: " + fromUser);
                if (fromUser) {
                    seekProgress = progress;
                    time_tv.setText(durationUtils.getCurTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                SaveLog.w("@@@-onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                SaveLog.w("@@@-onStopTrackingTouch", "seekProgress: " + seekProgress);
                Intent intent = new Intent(mContext, MediaService.class);
                intent.setAction(MediaService.ACTION_MUSIC_SEEK);
                intent.putExtra(MediaService.EXTRA_SEEK, seekProgress);
                mContext.startService(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_btn:
                Intent intent = new Intent(mContext, MediaService.class);
                intent.setAction(MediaService.ACTION_MUSIC_PAUSE);
                mContext.startService(intent);
                isPlaying = !isPlaying;
                updatePlayStatus();
                break;
            case R.id.pre_btn:
//                pre();
                seekBy(15);
                break;
            case R.id.next_btn:
//                next();
                seekBy(-15);
                break;
            case R.id.setting_btn:
                break;
            case R.id.like_btn:
                HttpEngine.getInstance().like(mCurrentEpisode.getId(), new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        boolean selected = likeBtn.isSelected();
                        likeBtn.setSelected(!selected);
                        if (selected) {
                            //设置为未选中状态
                            likeBtn.setColorFilter(null);
                        } else {
                            likeBtn.setColorFilter(Color.RED);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
                break;

        }
    }

    private void updatePlayStatus() {
//        isPlaying = !isPlaying;
        iv.setImageResource(isPlaying? R.drawable.pause_ic : R.drawable.play_ic);
    }

    private void seekBy(int i) {
        isPlaying = true;
        Intent intent = new Intent(mContext, MediaService.class);
        intent.setAction(MediaService.ACTION_MUSIC_SEEK_BY);
        intent.putExtra(MediaService.EXTRA_SEEK_BY, i);
        mContext.startService(intent);
    }

    private void next() {
        isPlaying = true;
        updatePlayStatus();
        Intent intent = new Intent(mContext, MediaService.class);
        intent.setAction(MediaService.ACTION_MUSIC_NEXT);
        mContext.startService(intent);
    }

    private void pre() {
        isPlaying = true;
        updatePlayStatus();
        Intent intent = new Intent(mContext, MediaService.class);
        intent.setAction(MediaService.ACTION_MUSIC_PRE);
        mContext.startService(intent);
    }

    @Deprecated
    public void setPath(@Nullable String url) {
//        player.setPath(url);
    }

    public void playEpisode(@NotNull Episode episode) {
        mCurrentEpisode = episode;
        duration_tv.setText(durationUtils.getCurTime(episode.getTotal_time()));
        like_tv.setText(String.valueOf(episode.getLike_cnt()));
        isPlaying = true;
        updatePlayStatus();
        Intent intent = new Intent(mContext, MediaService.class);
        intent.setAction(MediaService.ACTION_MUSIC_PLAY);
        intent.putExtra(EXTRA_EPISODE, episode);
        mContext.startService(intent);
    }

    public void addEpisode(Episode episode) {
        Intent intent = new Intent(mContext, MediaService.class);
        intent.setAction(MediaService.ACTION_MUSIC_ADD);
        intent.putExtra(EXTRA_EPISODE, episode);
        mContext.startService(intent);
    }

    public void updateProgress(@Nullable Integer maxSize, @Nullable Integer progress) {
        if (isShow) {
            time_tv.setText(durationUtils.getCurTime(progress));

//            duration_tv.setText(durationUtils.getCurTime(maxSize));
            seekBar.setProgress(progress);
            seekBar.setMax(maxSize);
            mCurrentEpisode.setProgress(progress);
            SpUtils.Companion.saveString(Constants.MediaSaveCons.EPISODE_INFO, mCurrentEpisode.getId());
            SpUtils.Companion.saveString(mCurrentEpisode.getId(), EpisodeUtils.Companion.episodeToStr(mCurrentEpisode));
        }
    }

    public void updateBuffSize(@Nullable Integer maxSize, @Nullable Integer progress) {
        if (isShow) {
            seekBar.setSecondaryProgress(progress);
        }
    }

    public void onDestroy() {
//        EventBus.getDefault().unregister(this);
    }

    public void onPlayNext(@NotNull Episode episode) {
        duration_tv.setText(durationUtils.getCurTime(episode.getTotal_time()));
        like_tv.setText(String.valueOf(episode.getLike_cnt()));
        isPlaying = true;
    }

    public interface PlayControllerCallback {
        void onstart(int position, Episode episode);
    }
}

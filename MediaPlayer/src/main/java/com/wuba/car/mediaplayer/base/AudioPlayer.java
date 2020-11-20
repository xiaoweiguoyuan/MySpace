package com.wuba.car.mediaplayer.base;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.wuba.car.mediaplayer.utils.EventListener;
import com.wuba.car.mediaplayer.utils.LoadControl;

import java.util.List;

public class AudioPlayer {

    private final Context context;
    private final LoadControl loadControl;
    private SimpleExoPlayer player;
    private MediaItem mediaItem;
    ConcatenatingMediaSource concatenatedSource =new ConcatenatingMediaSource();//该类可以合成视频或音频资源
    private boolean isPlaying;
    private int rawId;

    public AudioPlayer(Context context) {
        this.context = context;

        TrackSelector trackSelector = new DefaultTrackSelector(context);
        loadControl = new LoadControl();
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        player.addListener(new EventListener());
//        player = new SimpleExoPlayer.Builder(context).build();
    }

    @Deprecated
    public void setMediaPath(String path) {
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
//                Util.getUserAgent(context, "汽水人"));
//        MediaSource firstSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(path));
//        LoopingMediaSource firstSourceTwice = new LoopingMediaSource(firstSource, 1);
//        concatenatedSource.addMediaSource(firstSourceTwice);
        mediaItem = new MediaItem.Builder()
                .setUri(path)
                .setMimeType(MimeTypes.AUDIO_WEBM)
                .build();
        player.setMediaItem(mediaItem);
////        // Prepare the player.
        player.prepare();
    }

    public MediaSource buildDataSource(String uri) {
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent(context, this.getClass().getSimpleName()));
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(context, defaultHttpDataSourceFactory);
        return new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(getProxyUrl(uri)));
    }

    /**
     * 创建视频加载代理
     */
//    private HttpProxyCacheServer createProxyCacheServer(){
//        return HttpProxyCacheServer.Builder(context)
//                .cacheDirectory(getDiskCacheDirectory(context)) // 设置磁盘存储地址
//                .maxCacheSize(1024 * 1024 * 1024)     // 设置可存储1G资源
//                .build();
//    }
//
    private String getProxyUrl(String uri) {
        return uri;
//        return createProxyCacheServer().getProxyurl(uri);
    }

    public void setMediaPaths(List<String> paths) {
        player.clearMediaItems();
        addMediaPaths(paths);
    }

    public void addMediaPaths(List<String> paths) {
        for(String path: paths) {
            if (isInclude(path)) {
                continue;
            }
            MediaItem item = MediaItem.fromUri(path);
            player.addMediaItem(item);
        }
    }

    private boolean isInclude(String path) {
        for(int i = 0; i < getMediaItemCount(); i++) {
            MediaItem item = player.getMediaItemAt(i);
            if (item.playbackProperties.uri.toString().equals(path)) {
                return true;
            }
        }
        return false;
    }

    //构建 raw 文件
    public void prepareRaw() {
        try {
            DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(rawId));
            final RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(context);
            rawResourceDataSource.open(dataSpec);
            DataSource.Factory factory = new DataSource.Factory() {
                @Override
                public DataSource createDataSource() {
                    return rawResourceDataSource;
                }
            };

            ExtractorMediaSource mediaSource = new ExtractorMediaSource(rawResourceDataSource.getUri(),
                    factory, new DefaultExtractorsFactory(), null, null);
            LoopingMediaSource loopingMediaSource = new LoopingMediaSource(mediaSource);
            player.prepare(loopingMediaSource);
            player.addListener(new EventListener());
//            player.setPlayWhenReady(true);
        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (rawId != 0) {
            player.release();
            prepareRaw();
        }
        player.stop();
        player.prepare();
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public void stop() {
        player.stop(true);
    }

    public void release() {
        player.release();
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public int getRawId() {
        return rawId;
    }

    public void next() {
        player.next();
    }

    public void pre() {
        player.previous();
    }

    public void seekTo(int num) {
        if (num < 0 || num >= player.getDuration()) {
            return;
        }
        player.seekTo(num);
    }

    public void seekToDefaultPosition(int index) {
        player.seekToDefaultPosition(index);
    }

    public long duration() {
        return player.getDuration();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public int getMediaItemCount() {
        return player.getMediaItemCount();
    }

    public long getCurrentPosition() {
        return player.getCurrentPosition();
    }

    public long getTotalBufferedDuration() {
        return player.getTotalBufferedDuration();
    }

    public long getBufferedPosition() {
        return player.getBufferedPosition();
    }

    public long getContentBufferedPosition() {
        return player.getContentBufferedPosition();
    }

    public void playIndex(int index) {
    }

    public void clearMediaItems() {
        player.clearMediaItems();
    }

    public void prepare() {
        player.prepare();
    }
}

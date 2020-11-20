package com.puci.qs.spacenet.http.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.api.widget.Widget;

import java.util.ArrayList;

public class MediaPickerUtils {

    /**
     * 选择图片
     *
     * @param context
     * @param action
     */
    public static void selectImage(Context context, Action<ArrayList<AlbumFile>> action) {
        Album.image(context) // Image selection.
                .multipleChoice()
                .widget(getWidget(context, "选择图片"))
                .camera(false)
                .columnCount(3)
                .selectCount(9)
                .onResult(action)
                .start();
    }

    public static void selectImage(Context context, Action<ArrayList<AlbumFile>> action, int max) {
        Album.image(context) // Image selection.
                .multipleChoice()
                .widget(getWidget(context, "选择图片"))
                .camera(false)
                .columnCount(3)
                .selectCount(max)
                .onResult(action)
                .start();
    }

    /**
     * 选择视频
     *
     * @param context
     * @param action
     */
    public static void selectVideo(Context context, Action<ArrayList<AlbumFile>> action) {
        Album.video(context) // Video selection.
                .multipleChoice()
                .widget(getWidget(context, "选择视频"))
                .camera(true)
                .columnCount(2)
                .selectCount(9)
                .onResult(action)
                .start();
    }

    /**
     * 选择视频
     *
     * @param context
     * @param action
     */
    public static void selectVideo(Context context, Action<ArrayList<AlbumFile>> action, int max) {
        Album.video(context) // Video selection.
                .multipleChoice()
                .widget(getWidget(context, "选择视频"))
                .camera(true)
                .columnCount(2)
                .selectCount(max)
                .onResult(action)
                .start();
    }

    /**
     * 初始化只执行一次
     */
    public static void init(Context context) {
        Album.initialize(AlbumConfig.newBuilder(context)
                .setAlbumLoader(new MediaLoader())
                .build());
    }

    private static Widget getWidget(Context context, String title) {
        return Widget.newDarkBuilder(context)
                .title(title)
//                .statusBarColor(context.getResources().getColor(R.color.color_f754ef))
//                .toolBarColor(context.getResources().getColor(R.color.color_f754ef))
//                .mediaItemCheckSelector(context.getResources().getColor(R.color.color_f754ef), context.getResources().getColor(R.color.color_f754ef))
                .build();
    }

    public static class MediaLoader implements AlbumLoader {

        @Override
        public void load(ImageView imageView, AlbumFile albumFile) {
            load(imageView, albumFile.getPath());
        }

        @Override
        public void load(ImageView imageView, String url) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }
}

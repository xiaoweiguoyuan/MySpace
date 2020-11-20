package com.wuba.car.spacenet.http.utils;

import android.app.Activity;
import android.content.Intent;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

public class ImagePicUtils {
    private static void setImagePicker(boolean crop, int maxCount) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        if (crop) {
            imagePicker.setSaveRectangle(true);
            imagePicker.setFocusHeight(1000);
            imagePicker.setFocusWidth(1000);
            imagePicker.setOutPutX(1000);
            imagePicker.setOutPutY(1000);
            imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        }

        imagePicker.setSelectLimit(maxCount);
        imagePicker.setMultiMode(maxCount > 1);
    }


    public static void openGallery(boolean crop, int maxCount, int requestCode) {
//        setImagePicker(crop, maxCount);
//        Activity activity = ActivityLifecycleManager.getInstance().getCurrentActivity();
//        Intent intent1 = new Intent(activity, ImageGridActivity.class);
//        intent1.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
//        activity.startActivityForResult(intent1, requestCode);
    }


    public static ArrayList<ImageItem> onActivityResult(int resultCode, Intent data) {
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            return (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
        }
        return null;
    }
}

package com.wuba.car.spacenet.http.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    /**
     * 加载本地图片
     */
    public static final int REQUEST_CODE_PIC = 1;
    /**
     * 百度人脸识别
     */
    public static final int BADU_FACE_RQ = 2;

    public static final String WX_APP_ID = "wx1774a286a790dd40";

    /**
     * Intent extra参数
     */
    public static final String EXTRA_PAY_MONEY = "pay_money";
    public static final String EXTRA_PAY_KEY = "pay_key";
    public static final String EXTRA_PAY_BUNDLE = "pay_bundle";
    public static final String EXTRA_URL = "extra_url";

    /**
     * 图片选择
     */
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    /***
     * 支付相关
     */
    public static final int HNDLER_ALI_PAY = 1001;
    /**高斯模糊值，值越大模糊成都越高，需要设计定一下，暂时用70*/
    public static final int BLUR_VALUE = 70;


    /**http://39.97.191.120:8000/episodes/1*/
//    http://39.97.191.120:8000/episodes/30/comments
    public static final String EPISODE_DETAIL_URL = "episodes/";

    public static final String SUBSCRIBES = "subscriptions";
    public static final String UPLOAD_EPISODE_LIST = "playlist/";


//    public static class FaceConstants {
//        public static List<LivenessTypeEnum> livenessList = new ArrayList<LivenessTypeEnum>();
//        public static boolean isLivenessRandom = false;
//    }
}

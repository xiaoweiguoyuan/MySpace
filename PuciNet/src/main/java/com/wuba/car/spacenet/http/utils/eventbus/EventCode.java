package com.wuba.car.spacenet.http.utils.eventbus;

public class EventCode {
    private static int BASE_COUNT = 0;
    public static final int LOCATION_SUCCESS = BASE_COUNT++;     //定位成功
    public static final int GENDER_SORT = BASE_COUNT++;     //性别过滤
    public static final int PUBLISH_THEME = BASE_COUNT++;     //发布主题话题
    public static final int REFRESH_MESSAGE_LIST = BASE_COUNT++;//刷新会话窗口消息列表
    public static final int SEARCH_SORT = BASE_COUNT++;    //搜索模糊查询
    public static final int SORT_ONLINE = BASE_COUNT++; //在线排序发生变更
    public static final int REFRESH_ME = BASE_COUNT++;//刷新我的页面
    public static final int MESSAGE_UPDATE = BASE_COUNT++;
    public static final int SYSTEM_MESSAGE_UPDATE = BASE_COUNT++;
    public static final int SEND_BANANA_RED_PACKET = BASE_COUNT++;//发送香蕉币红包
    public static final int SAVE_LOVE_SUCCESS = BASE_COUNT++;
    public static final int CANCEL_LOVE_SUCCESS = BASE_COUNT++;
    public static final int SAVE_LOVE_FAIL = BASE_COUNT++;
    public static final int CANCEL_LOVE_FAIL = BASE_COUNT++;
    public static final int SEND_READ_BURN = BASE_COUNT++;
    public static final int SEND_LOCATION = BASE_COUNT++;
}

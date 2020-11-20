package com.wuba.car.spacenet.http.bean;


import java.io.Serializable;

public class SystemMsgInfoBean implements Serializable, Comparable<SystemMsgInfoBean> {

    private Type type;
    private String title;
    private String content;
    private long time;
    private int unReadCount;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    @Override
    public int compareTo(SystemMsgInfoBean other) {
        if(other.getTime() > this.getTime()){
            return 1;
        }
        if(other.getTime() < this.getTime()){
            return -1;
        }
        return 0;
    }

    public enum Type{
        CLICK_LIKE_NOTICE,//点赞通知
        INCOME_NOTICE,//收益提醒
        RADIO_STATION,//电台消息通知
        SYSTEM_NOTICE,//获取系统通知(香蕉公园)
        TOPIC_COMMENT_NOTICE,//主题评论通知
        TOPIC_SIGN_UP_NOTICE,//报名通知
        USER_COMMENT_NOTICE;//用户评价通知
    }
}

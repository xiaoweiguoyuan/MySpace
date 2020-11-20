package com.puci.qs.spacenet.http.bean;

import java.io.Serializable;

public class PhotoBean implements Serializable {
    public int id;
    public int user_id;
    public int i_type;//类型 1图片 2视频
    public String img_url;
    public int burn_after_reading;
    public int authentication;
    public int status;
    public long create_time;
    public long update_time;
    public int secret;//加密：1:是 2:否 （默认为3元解相片）
}

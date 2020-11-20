package com.wuba.car.spacenet.http.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfoBean implements Serializable {
    public long user_id;//用户user_id
    public long pid;//上级用户user_id
    public long id;//
    public int status;//
    public long create_time;//
    public long update_time;//
    public String invite_code;//用户邀请码 可能为空
    public String union_id;//
    public int register_type;//
    public String user_name;//用户名
    public String nick_name;//昵称
    public String birthday;//生日　
    public String avatar;//头像url
    public String mobile;//用户手机号 可能为空
    public int gender;//性别 0未设置 1:男 2女　
    public int industry_id;//
    public String wx_number;//微信号
    public int wx_number_show;//是否显示 1:显示 2:隐藏
    public int height;//身高 单位：cm
    public int weight;//体重 单位：kg
    public String constellation;//星座//
    public int be_love_num;//被喜欢的次数
    public int love_num;//我喜欢的
    public int comment_num;//评论数
    public String city_name;//所在城市
    public String profile_desc;//
    public int profile_weight;//
    public int authentication;//个人认证1:是 2:否
    public long vip_star_time;//会员开始时间
    public long vip_end_time;//会员结束时间
    public String last_login_device;//
    public String last_login_ip;//
    public long token_valid_time;//
    public long last_login_time;//
    public String last_login_token;//
    public int is_vip;//是否vip１:是　0:不是　
    public int dating_show_id;//交友节目id
    public String dating_show_name;//交友节目名称
    public int hope_object_id;//希望对象主键id
    public String hope_object_name;//希望对象名称
    public int position_id;//职位信息id
    public String position_name;//职位信息名称
    public ArrayList<PhotoBean> image_list;
    public int is_love;//是否收藏 0:否 1:是
    public int is_lock_album;//是否锁相册 1:是 2:否
    public int is_unlock_album;//是否已经解锁相册 1:是 2:否
    public int unlock_album_price;//解锁相册花费
    public ArrayList<DatingShowBean> dating_show_list;
    public ArrayList<HopeObjectBean> hope_object_list;
}

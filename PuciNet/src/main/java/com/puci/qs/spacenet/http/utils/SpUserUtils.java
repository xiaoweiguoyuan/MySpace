package com.puci.qs.spacenet.http.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.puci.qs.spacenet.http.PullNetApplication;

public class SpUserUtils {

    private SharedPreferences sharedPreferences;
    private static SpUserUtils mInstance;
//    private UserInfoBean userInfo;
//    private UserInfoExtendBean extendUserInfo;
    private String token;
    private String userId;

    private SpUserUtils() {
        sharedPreferences = PullNetApplication.Companion.getInstance().getSharedPreferences("spUser", Context.MODE_PRIVATE);
    }

    public static SpUserUtils getInstance() {
        if (mInstance == null) {
            synchronized (SpUserUtils.class) {
                if (mInstance == null) {
                    mInstance = new SpUserUtils();
                }
            }
        }
        return mInstance;
    }

    public void saveUserPhone(String phone) {
        sharedPreferences.edit().putString("userPhone", phone).commit();
    }

    public String getUserPhone() {
        return sharedPreferences.getString("userPhone", "");
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString("token", token).commit();
    }

    public String getToken() {
        if (!TextUtils.isEmpty(token)) {
            return token;
        }
        token = sharedPreferences.getString("token", "");
        return token;
    }

    public void saveUserId(String userId) {
        sharedPreferences.edit().putString("userId", userId).commit();
    }

    public String getUserId() {
        if (!TextUtils.isEmpty(userId)) {
            return userId;
        }
        userId = sharedPreferences.getString("userId", "");
        return userId;
    }

    public void saveWxId(String wxId) {
        sharedPreferences.edit().putString("wxId", wxId).commit();
    }

    public String getWxId() {
        return sharedPreferences.getString("wxId", "");
    }

    public void saveLoginStatus() {
        sharedPreferences.edit().putInt("loginStatus", 1).commit();
    }

    public int getLoginStatus() {
        return sharedPreferences.getInt("loginStatus", 0);
    }

//    public void saveUserInfo(UserInfoBean userInfo) {
//        if (userInfo == null) {
//            return;
//        }
//        Gson gson = new Gson();
//        String userInfoJson = gson.toJson(userInfo);
//        sharedPreferences.edit().putString("userInfo", userInfoJson).commit();
//    }
//
//    public UserInfoBean getUserInfo() {
//        if (userInfo != null) {
//            return userInfo;
//        }
//        try {
//            Gson gson = new Gson();
//            String json = sharedPreferences.getString("userInfo", "");
//            if (TextUtils.isEmpty(json)) {
//                return null;
//            }
//            userInfo = gson.fromJson(json, UserInfoBean.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return userInfo;
//    }
//    public void saveExtendUserInfo(UserInfoExtendBean userInfo) {
//        if (userInfo == null) {
//            return;
//        }
//        Gson gson = new Gson();
//        String userInfoJson = gson.toJson(userInfo);
//        sharedPreferences.edit().putString("extendUserInfo", userInfoJson).commit();
//    }
//
//    public UserInfoExtendBean getExtendUserInfo() {
//        if (extendUserInfo != null) {
//            return extendUserInfo;
//        }
//        try {
//            Gson gson = new Gson();
//            String json = sharedPreferences.getString("extendUserInfo", "");
//            if (TextUtils.isEmpty(json)) {
//                return null;
//            }
//            extendUserInfo = gson.fromJson(json, UserInfoExtendBean.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return extendUserInfo;
//    }
    public void clear() {
//        userInfo = null;
//        token = null;
//        userId = null;
//        extendUserInfo = null;
        String phone = getUserPhone();
        sharedPreferences.edit().clear().apply();
        if (TextUtils.isEmpty(phone)) {
            saveUserPhone(phone);
        }
    }
}

package com.puci.qs.spacenet.http.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.puci.qs.spacenet.http.PullNetApplication;

public class PreferenceUtils {
    private static final String FILE_NAME = "UserInfoCache";
    private static PreferenceUtils instance;
    private SharedPreferences sharedPreferences;
//    private UserInfoBean userInfo;
    private String token, userId;
    private static Context mContext;

    private PreferenceUtils() {
        if (mContext == null) {
            mContext = PullNetApplication.Companion.getInstance();
//            mContext = ActivityLifecycleManager.getInstance().getCurrentActivity().getApplicationContext();
        }
        sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void initContext(Context context) {
        mContext = context;
    }

    public static synchronized PreferenceUtils getInstance() {
        if (instance == null) {
            instance = new PreferenceUtils();
        }
        return instance;
    }

    public void save(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    public void save(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public void save(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public void save(String key, long value) {
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

}

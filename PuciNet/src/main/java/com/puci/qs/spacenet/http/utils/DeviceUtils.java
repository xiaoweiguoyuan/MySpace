package com.puci.qs.spacenet.http.utils;


import android.app.Activity;
import android.provider.Settings;

import com.puci.qs.basedependencies.utils.ActivityLifecycleManager;

public class DeviceUtils {
    private static String deivceId;

    public static String getDeviceId() {
        if (deivceId == null) {
            Activity activity = ActivityLifecycleManager.getInstance().getCurrentActivity();
            deivceId = Settings.System.getString(activity.getContentResolver(), Settings.System.ANDROID_ID);
        }
        return deivceId;
    }

}

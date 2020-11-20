package com.puci.qs.spacenet.http.interceptor;


import android.app.Application;
import android.os.Build;
import android.text.TextUtils;

import com.puci.qs.spacenet.http.PullNetApplication;
import com.puci.qs.spacenet.http.utils.DeviceUtils;
import com.puci.qs.spacenet.http.utils.PreferenceKey;
import com.puci.qs.spacenet.http.utils.PreferenceUtils;
import com.puci.qs.spacenet.http.utils.SpUserUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 保持cookie一致拦截器
 */
public class CookieRequestInterceptor implements Interceptor {
    private String deivceId;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder().url(originalRequest.url());
        String token = SpUserUtils.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("token", token);
        }
        boolean isWechatLogin = PreferenceUtils.getInstance().getBoolean(PreferenceKey.IS_WECHATLOGIN, false);
        builder.addHeader("objectName", "QSEr");
        builder.addHeader("platform", "android");
        builder.addHeader("loginType", isWechatLogin ? "2" : "3");
        builder.addHeader("appVersion", BuildConfig.VERSION_CODE + "");
        if (isWechatLogin) {
//            builder.addHeader("AppID", BuildConfig.VERSION_CODE + "");
        }
        builder.addHeader("deviceId", DeviceUtils.getDeviceId());
        builder.addHeader("userId",DeviceUtils.getDeviceId());
        builder.addHeader("scene", TextUtils.equals(BuildConfig.BUILD_TYPE, "debug") ? "dev" : "online");
        builder.addHeader("User-Agent", getUserAgent());
        return chain.proceed(builder.build());
    }

    private String getUserAgent() {
        //        httpGet.setHeader( "User-Agent" , String.format( "%s/%s (Linux; Android %s; %s Build/%s)" , MY_APP_NAME, MY_APP_VERSION_NAME, Build.VERSION.RELEASE, Build.MANUFACTURER, Build.ID));
        Application application = PullNetApplication.Companion.getInstance();
        return String.format( "%s/%s (Linux; Android %s; %s Build/%s)" , application.getPackageName(), "1.1.0", Build.VERSION.RELEASE, Build.MANUFACTURER, Build.ID);
    }


}

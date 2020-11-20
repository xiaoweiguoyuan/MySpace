package com.puci.qs.spacenet.http.service;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WechatPayCallBack<T extends ResponseBean> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() == null) {
            onResponseFailure(-1000, "服务器错误");
            Log.e("okhttp", "okhttp_request,failure,response is null");
            return;
        }
        if (response.body().errno != 200) {
            onResponseFailure(response.body().errno, response.body().errmsg);
            return;
        }
        onResponseSuccessful(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        Log.e("okhttp", "okhttp_throw:" + t.toString());
        onResponseFailure(-10000, "服务器错误");
    }

    public abstract void onResponseSuccessful(T response);

    public abstract void onResponseFailure(int code, String msg);
}

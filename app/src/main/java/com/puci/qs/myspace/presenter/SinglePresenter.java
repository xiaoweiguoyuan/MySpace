package com.puci.qs.myspace.presenter;

import android.content.Context;

import com.puci.qs.myspace.base.BasePresenter;
import com.puci.qs.myspace.entity.SingleBean;
import com.puci.qs.myspace.net.HttpEngine;
import com.puci.qs.myspace.view.SingleV;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinglePresenter extends BasePresenter<SingleV> {

    public SinglePresenter(Context context, Object view) {
        super(context, (SingleV) view);
    }

    public void getSingleDataByNet(String keyword,boolean isRefresh) {
        HttpEngine.getInstance().searchSingleByNet(keyword, "episode", new Callback<SingleBean>() {
            @Override
            public void onResponse(Call<SingleBean> call, Response<SingleBean> response) {
                if (response.body()!=null) {
                    view.onResult(response.body(), isRefresh);
                }
            }


            @Override
            public void onFailure(Call<SingleBean> call, Throwable t) {

            }
        });
    }





}

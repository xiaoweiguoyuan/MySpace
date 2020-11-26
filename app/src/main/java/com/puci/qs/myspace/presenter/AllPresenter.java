package com.puci.qs.myspace.presenter;

import android.content.Context;

import com.puci.qs.myspace.base.BasePresenter;
import com.puci.qs.myspace.entity.AllBean;
import com.puci.qs.myspace.net.HttpEngine;
import com.puci.qs.myspace.view.AllV;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPresenter extends BasePresenter<AllV> {

    public AllPresenter(Context context, AllV view) {
        super(context, view);
    }

    public void getAllDataByNet(String keyword,boolean isRefresh) {
        HttpEngine.getInstance().searchByNet(keyword, "all", new Callback<AllBean>() {
            @Override
            public void onResponse(Call<AllBean> call, Response<AllBean> response) {
                if (response.body()!=null) {
                    view.onResult(response.body(), isRefresh);
                }
            }


            @Override
            public void onFailure(Call<AllBean> call, Throwable t) {

            }
        });
    }



}

package com.wuba.car.myspace.presenter;

import android.content.Context;

import com.wuba.car.myspace.base.BasePresenter;
import com.wuba.car.myspace.entity.ProgramBean;
import com.wuba.car.myspace.entity.SingleBean;
import com.wuba.car.myspace.net.HttpEngine;
import com.wuba.car.myspace.view.ProgramV;
import com.wuba.car.myspace.view.SingleV;

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

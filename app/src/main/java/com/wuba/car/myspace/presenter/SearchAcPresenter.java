package com.wuba.car.myspace.presenter;

import android.content.Context;
import android.util.Log;

import com.wuba.car.myspace.base.BasePresenter;
import com.wuba.car.myspace.entity.AllBean;
import com.wuba.car.myspace.net.HttpEngine;
import com.wuba.car.myspace.view.SearchAcV;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAcPresenter extends BasePresenter {
    private SearchAcV view;
    public SearchAcPresenter(Context context, SearchAcV view) {
        super(context, view);
        this.view = view;
    }

    public void getAllDataByNet(String keyword,boolean isRefresh) {
        HttpEngine.getInstance().searchByNet(keyword, "all", new Callback<AllBean>() {
            @Override
            public void onResponse(Call<AllBean> call, Response<AllBean> response) {
                Log.i("zz",call.toString()+response+toString());
                view.onResult(response.body(),isRefresh);
            }


            @Override
            public void onFailure(Call<AllBean> call, Throwable t) {

            }
        });
    }
}

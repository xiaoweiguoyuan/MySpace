package com.puci.qs.myspace.presenter;

import android.content.Context;

import com.puci.qs.myspace.base.BasePresenter;
import com.puci.qs.myspace.entity.ProgramBean;
import com.puci.qs.myspace.net.HttpEngine;
import com.puci.qs.myspace.view.ProgramV;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramPresenter extends BasePresenter<ProgramV> {

    public ProgramPresenter(Context context, Object view) {
        super(context, (ProgramV) view);
    }

    public void getProgramDataByNet(String keyword,boolean isRefresh) {
        HttpEngine.getInstance().searchProgramByNet(keyword, "podcast", new Callback<ProgramBean>() {
            @Override
            public void onResponse(Call<ProgramBean> call, Response<ProgramBean> response) {
                if (response.body()!=null) {
                    view.onResult(response.body(), isRefresh);
                }
            }


            @Override
            public void onFailure(Call<ProgramBean> call, Throwable t) {

            }
        });
    }





}

package com.puci.qs.myspace.base;

import android.content.Context;

import com.puci.qs.myspace.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

public abstract class BasePresenter<T> {
    protected Context mCxt;
    protected static String TAG;
    private LoadingDialog loadingDialog;
    protected T view;
    private boolean visiable;


    public BasePresenter(Context context, T view) {
        mCxt = context;
        this.view = view;
        TAG = this.getClass().getSimpleName();
    }

    protected void register() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }


    protected void unBind() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    void setVisiable(boolean visiable) {
        this.visiable = visiable;
    }

    protected boolean isVisible() {
        return visiable;
    }

    protected void onResume() {

    }

    public void detach() {
        view = null;
        mCxt = null;
    }

    public boolean isAttach() {
        return view != null;
    }

    public void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mCxt);
        }

        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}

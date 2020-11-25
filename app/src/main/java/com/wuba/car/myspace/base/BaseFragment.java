package com.wuba.car.myspace.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wuba.car.qishuier.R;
import com.wuba.car.myspace.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements View.OnClickListener {
    protected String TAG = this.getClass().getSimpleName();
    protected WeakReference<View> mRootView;
    protected Context mContext;
    protected T presenter;
    protected LoadingDialog loadingDialog;
    private Unbinder unbind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            View view = inflater.inflate(inflaterLayout(), container, false);
            unbind = ButterKnife.bind(this, view);
            mRootView = new WeakReference<>(view);
            initViews(view);
        }

        if (mRootView != null && mRootView.get() != null) {
            mRootView.get().setClickable(true); //解决fragment 点击穿透问题
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        initViewData();
        return mRootView.get();
    }


    public void initNoDataView(@DrawableRes int resId, String desc) {
        ((ImageView) mRootView.get().findViewById(R.id.no_data_iv)).setImageResource(resId);
        ((TextView) mRootView.get().findViewById(R.id.no_data_tv)).setText(desc);
    }

    public void isNoDataViewShown(boolean isShown) {
        mRootView.get().findViewById(R.id.no_data_layout).setVisibility(isShown ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbind != null) {
            unbind.unbind();
        }
    }

    public void autoRefresh() {
    }


    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.setVisiable(true);
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.setVisiable(false);
        }
    }

    /**
     * 加载布局
     *
     * @return
     */
    protected abstract int inflaterLayout();

    /**
     * 初始化布局
     *
     * @param view
     */
    protected abstract void initViews(View view);

    protected abstract void initViewData();

    protected void handleResult(int requestCode, int resultCode, @Nullable Intent data){

    }

    protected void jump(Class dest) {
        Intent intent = new Intent(getActivity(), dest);
        startActivity(intent);

    }

    protected void jump(Intent intent) {
        startActivity(intent);
    }

    /**
     * 创建fragment的静态方法，方便传递参数
     *
     * @param args 传递的参数
     * @return
     */
    public static <T extends Fragment> T newInstance(Class clazz, Bundle args) {
        T mFragment = null;
        try {
            mFragment = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mFragment.setArguments(args);
        return mFragment;
    }

}

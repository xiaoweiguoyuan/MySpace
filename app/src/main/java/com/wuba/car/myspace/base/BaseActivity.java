package com.wuba.car.myspace.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.wuba.car.qishuier.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T presenter;
    protected String TAG = getClass().getSimpleName();
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android 6.0 以上的系统
            //如果状态栏背景是白色，改变状态栏文字为黑色
            if (Color.WHITE == getStatusBarColor()) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        //  设置默认虚拟导航背景色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.color_333333));
        }

        setContentView(setContentView());
        ButterKnife.bind(this);
        initView();
        showBack();
        initData();
    }

    protected int getStatusBarColor() {
        return Color.WHITE;
    }

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detach();
            presenter.unBind();
        }
        super.onDestroy();
    }

    /**
     * activity 跳转
     *
     * @param intent
     */
    public void jump(Intent intent, boolean isFinish) {
        startActivity(intent);

        if (isFinish) {
            finish();
        }
    }

    /**
     * activity 跳转
     *
     * @param cls
     */
    public void jump(Class cls, boolean isFinish) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);

        if (isFinish) {
            finish();
        }
    }

    protected void setTitle(String title) {
        TextView titleTv = findViewById(R.id.title_tv);
        if (titleTv != null) {
            titleTv.setText(title);
        }
    }

    protected void setMenuIcon(@DrawableRes int resId) {
        ImageView menuIv = findViewById(R.id.menu_iv);
        if (menuIv != null) {
            menuIv.setImageResource(resId);
            menuIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMenuClick();
                }
            });
        }
    }

    protected void setMenuText(String text) {
        TextView menuTv = findViewById(R.id.menu_tv);
        if (menuTv != null) {
            menuTv.setText(text);
            menuTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMenuClick();
                }
            });
        }
    }


    protected void setSplitActionBar(int colorId) {
        View splitView = findViewById(R.id.split_action_bar);
        if (splitView != null) {
            splitView.setBackgroundColor(ContextCompat.getColor(this, colorId));
        }
    }

    private void showBack() {
        ImageView backIv = findViewById(R.id.back_iv);
        if (backIv != null) {
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBack();
                }
            });
        }
    }

    protected void onBack() {
        finish();
    }


    protected void onMenuClick() {
    }

    protected void jump(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void jump(Intent intent) {
        startActivity(intent);
    }


    public void initNoDataView(@DrawableRes int resId, String desc) {
        ((ImageView) findViewById(R.id.no_data_iv)).setImageResource(resId);
        ((TextView) findViewById(R.id.no_data_tv)).setText(desc);
    }

    public void isNoDataViewShown(boolean isShown) {
        findViewById(R.id.no_data_layout).setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    public void showToast(String msg){
        if(TextUtils.isEmpty(msg)){
            return;
        }
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}

package com.wuba.car.myspace.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuba.car.qishuier.R;


/**
 * @author guolvgang
 * @Description 自定义loading
 */

public class LoadingDialog extends Dialog {

    private Context context;
    private String content;
    private TextView contentText;

    public LoadingDialog(Context context) {
        super(context, R.style.loading_dialog_style);
        this.context = context;
    }

    public LoadingDialog(Context context, String content) {
        this(context);
        this.content = content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout_loading);
        ImageView progressView = findViewById(R.id.progressView);
        contentText = findViewById(R.id.contentText);

        if (TextUtils.isEmpty(content)) {
            contentText.setText(R.string.loading);
        } else {
            contentText.setText(content);
        }
        Glide.with(context).asGif().load(R.drawable.loading).into(progressView);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }

}

package com.mengk.viewmodellivedata.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.mengk.viewmodellivedata.R;


/**
 * Desc 没有遮罩的loading
 * Date 2019/12/14
 * @author mengk
 */
public class NoBgDialogLoading extends Dialog {
    public Context context;
    private TextView tvLoading;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NoBgDialogLoading(@NonNull Context context) {
        super(context, R.style.bf_loading_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_no_bg_loading);
        try {

            setCanceledOnTouchOutside(false);
            Window window = getWindow();
            if (window == null) {return;}
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.CENTER;
            attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
            initView();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tvLoading = findViewById(R.id.tv_loading);
        try {
            tvLoading.setText(!TextUtils.isEmpty(getTitle()) ? getTitle() :
                    "加载中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

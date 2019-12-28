package com.mengk.viewmodellivedata.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.mengk.viewmodellivedata.R;


/**
 * Desc 自定义删除的dialog
 * Date 2019/10/20
 * author mengk
 */
public class DeleteImgDialog extends Dialog {
    public Context context;
    public TextView tvTitle;
    public View viewLineCenter;
    public TextView tvCancel;
    public TextView tvSure;
    public String title;

    public DeleteImgDialog(@NonNull Context context,String title) {
        super(context, R.style.common_dialog);
        this.context = context;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_del);
        try {
            setCanceledOnTouchOutside(false);
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.CENTER;
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(attributes);
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title_publish_del);
        tvCancel = findViewById(R.id.tv_title_publish_cancel);
        tvSure = findViewById(R.id.tv_title_publish_sure);
        viewLineCenter = findViewById(R.id.view_line_center);
        tvTitle.setText(title);
        tvSure.setOnClickListener(v -> {
            if (mSureOrCancelListener != null) {
                mSureOrCancelListener.sure();
            }
        });
        tvCancel.setOnClickListener(v -> {
            if (mSureOrCancelListener != null) {
                mSureOrCancelListener.cancel();
            }
        });
    }

    public interface SureOrCancelListener {
        void cancel();
        void sure();
    }

    private SureOrCancelListener mSureOrCancelListener;

    public void setSureOrCancelListener(SureOrCancelListener mSureOrCancelListener) {
        this.mSureOrCancelListener = mSureOrCancelListener;
    }
}

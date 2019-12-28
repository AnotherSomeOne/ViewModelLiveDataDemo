package com.mengk.viewmodellivedata.view.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.jumpUtl;
import com.mengk.viewmodellivedata.model.viewmodel.SortModel;
import com.mengk.viewmodellivedata.model.viewmodel.VoidModel;
import com.mengk.viewmodellivedata.view.dialog.BottomDialog;
import com.mengk.viewmodellivedata.view.dialog.DeleteImgDialog;
import com.mengk.viewmodellivedata.view.dialog.NoBgDialogLoading;

public class DialogActivity extends AbsLifecycleActivity<VoidModel> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @OnClick({
            R.id.btn1,
            R.id.btn2,
            R.id.btn3
    })
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                showDialog1();
                break;
            case R.id.btn2:
                showDialog2();
                break;
            case R.id.btn3:
                showDialog3();
                break;

        }
    }

    private void showDialog3() {
        NoBgDialogLoading loading = new NoBgDialogLoading(this);
        loading.show();
    }

    private void showDialog2() {
        DeleteImgDialog dialog = new DeleteImgDialog(this,"sure");
        dialog.show();
    }

    private void showDialog1() {
        BottomDialog bottomDialog = new BottomDialog(this);
        bottomDialog.show();
    }

}

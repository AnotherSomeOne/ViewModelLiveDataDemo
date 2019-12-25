package com.mengk.viewmodellivedata.view;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import com.mengk.viewmodellivedata.MainActivity;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.ToastUtil;
import com.mengk.viewmodellivedata.model.bean.DelayTimeBean;
import com.mengk.viewmodellivedata.model.viewmodel.BaseViewModel;
import com.mengk.viewmodellivedata.model.viewmodel.SplashViewModel;

/**
 * 闪屏页
 */
public class SplashActivity extends AbsLifecycleActivity<SplashViewModel> {

    @BindView(R.id.tv_sp)
    TextView textView;
//    SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewModel = new SplashViewModel(this);
//        getLifecycle().addObserver(viewModel);
        initEvent();
        Log.e("===z","SplashActivity onCreate");
        ToastUtil.showToast("toast");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void initEvent() {
        mViewModel.delayTime();
        mViewModel.getDelayToTime().observe(this, delayTimeBean -> {
            if (delayTimeBean != null) {
                int state = delayTimeBean.getState();
                Log.e("===z","接收 = " + delayTimeBean.getState());
                textView.setText(delayTimeBean.getData().toString());
                if (state == 1) {
                    Intent intent = new Intent(SplashActivity.this, BusinessActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}

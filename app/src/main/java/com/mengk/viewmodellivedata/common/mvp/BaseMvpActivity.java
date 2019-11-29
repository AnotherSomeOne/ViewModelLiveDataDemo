package com.mengk.viewmodellivedata.common.mvp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.mengk.viewmodellivedata.common.mvp.baseapp.AppManager;


public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    public P mPresenter;
    public abstract void initPresenter();

    @Override
    protected void onAfterCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        isConfigChange = false;
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        doAfterSetcontentView();
        if (isRegisterEvent()) {
            // 进行事件注册
        }
        AppManager.getInstance().addActivity(this);

        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        // mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initPresenter();

        initView();
        bindEvent();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}

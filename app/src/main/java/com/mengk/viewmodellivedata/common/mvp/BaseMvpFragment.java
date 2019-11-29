package com.mengk.viewmodellivedata.common.mvp;

import android.view.View;


public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected View rootView;
    public P mPresenter;
    public abstract void initPresenter();


    @Override
    public void initViewData() {
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }
        initPresenter();
        initView();
        bindEvent();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }


}

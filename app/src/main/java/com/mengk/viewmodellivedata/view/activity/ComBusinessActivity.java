package com.mengk.viewmodellivedata.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;
import com.mengk.viewmodellivedata.common.util.JumpUtl;
import com.mengk.viewmodellivedata.model.designpatterns.ImageLoader;
import com.mengk.viewmodellivedata.model.designpatterns.NetWorkLoad;
import com.mengk.viewmodellivedata.model.viewmodel.SortModel;
import com.mengk.viewmodellivedata.model.viewmodel.VoidModel;

public abstract class ComBusinessActivity<T extends AbsViewModel> extends AbsLifecycleActivity<T> {

    @BindView(R.id.view_state)
    View viewState;
    @BindView(R.id.fl_content_base)
    FrameLayout flContent;

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        setStateViewHeight(viewState);
        flContent.removeAllViews();
        LayoutInflater.from(this).inflate(getContentLayoutId(), flContent);
    }

    public abstract int getContentLayoutId();

    @Override
    public int getLayoutId() {
        return R.layout.activity_business_com;
    }

}

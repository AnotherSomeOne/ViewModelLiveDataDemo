package com.mengk.viewmodellivedata.common.mvvm.stateview;

import com.mengk.viewmodellivedata.R;
import com.tqzhang.stateview.stateview.BaseStateControl;

public class LoadingState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.loading_view;
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

}

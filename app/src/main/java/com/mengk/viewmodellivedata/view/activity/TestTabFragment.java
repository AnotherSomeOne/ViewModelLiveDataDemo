package com.mengk.viewmodellivedata.view.activity;

import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleFragment;
import com.mengk.viewmodellivedata.model.viewmodel.VoidModel;

public class TestTabFragment extends AbsLifecycleFragment<VoidModel> {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}

package com.mengk.viewmodellivedata.view.activity;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.JumpUtl;
import com.mengk.viewmodellivedata.model.viewmodel.NetModel;

public class NetActivity extends AbsLifecycleActivity<NetModel> {

    @BindView(R.id.view_state)
    View viewState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStateViewHeight(viewState);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        mViewModel.getHomeListData("0");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_net;
    }



}

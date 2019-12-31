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
    public int getLayoutId() {
        return R.layout.activity_net;
    }

    @Nullable
    @OnClick({R.id.btn_sort_by_letter,R.id.btn_tab1})
    void submit(View view) {
        switch (view.getId()) {
            case R.id.btn_sort_by_letter:
                JumpUtl.navigate(this,SortByLetterActivity.class);
                break;
            case R.id.btn_tab1:
                JumpUtl.navigate(this,TabLayout2Activity.class);
                break;
        }
    }



}

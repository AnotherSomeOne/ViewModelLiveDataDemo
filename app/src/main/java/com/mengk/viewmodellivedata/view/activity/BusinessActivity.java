package com.mengk.viewmodellivedata.view.activity;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.JumpUtl;
import com.mengk.viewmodellivedata.model.designpatterns.ImageLoader;
import com.mengk.viewmodellivedata.model.designpatterns.NetWorkLoad;
import com.mengk.viewmodellivedata.model.viewmodel.SortModel;

public class BusinessActivity extends AbsLifecycleActivity<SortModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //测试策略模式
        ImageLoader imageLoader = new ImageLoader();
        NetWorkLoad netWorkLoad = new NetWorkLoad();
        imageLoader.setStrategy(netWorkLoad);
        imageLoader.load();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_business;
    }

    @OnClick({R.id.btn_sort_by_letter,R.id.btn_tab1})
    void submit(View view) {
        switch (view.getId()) {
            case R.id.btn_sort_by_letter:
                JumpUtl.navigate(this,SortByLetterActivity.class);
                break;
            case R.id.btn_tab1:
//                JumpUtl.navigate(this,TabLayoutOneActivity.class);
                JumpUtl.navigate(this,TabLayout2Activity.class);
//                jumpUtl.navigate(this,DialogActivity.class);
                break;
        }
    }



}

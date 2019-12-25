package com.mengk.viewmodellivedata.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.util.jumpUtl;
import com.mengk.viewmodellivedata.model.designpatterns.ImageLoader;
import com.mengk.viewmodellivedata.model.designpatterns.NetWorkLoad;
import com.mengk.viewmodellivedata.model.viewmodel.BaseViewModel;

public class BusinessActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setTitle("业务Activity");
        //测试策略模式
        ImageLoader imageLoader = new ImageLoader();
        NetWorkLoad netWorkLoad = new NetWorkLoad();
        imageLoader.setStrategy(netWorkLoad);
        imageLoader.load();

    }

    @OnClick({R.id.btn_sort_by_letter})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.btn_sort_by_letter:
                jumpUtl.navigate(this,SortByLetterActivity.class);
                break;
        }
    }


    @Override
    public int getLayoutID() {
        return R.layout.activity_business;
    }

    @Override
    public BaseViewModel createViewModel() {
        return null;
    }


}

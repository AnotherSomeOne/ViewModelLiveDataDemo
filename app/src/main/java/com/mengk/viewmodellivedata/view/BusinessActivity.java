package com.mengk.viewmodellivedata.view;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.model.designpatterns.ImageLoader;
import com.mengk.viewmodellivedata.model.designpatterns.NetWorkLoad;

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

    @Override
    public int getLayoutID() {
        return R.layout.activity_business;
    }
}

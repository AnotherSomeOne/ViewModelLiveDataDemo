package com.mengk.viewmodellivedata.view;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import com.mengk.viewmodellivedata.R;

public class BusinessActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setTitle("业务Activity");
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_business;
    }
}

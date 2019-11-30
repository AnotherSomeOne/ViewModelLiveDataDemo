package com.mengk.viewmodellivedata;

import android.app.Application;
import com.mengk.viewmodellivedata.common.util.ToastUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
    }
}

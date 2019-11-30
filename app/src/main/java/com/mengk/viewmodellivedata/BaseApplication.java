package com.mengk.viewmodellivedata;

import android.app.Application;
import com.mengk.viewmodellivedata.common.util.ToastUtil;

public class BaseApplication extends Application {

    private static BaseApplication mApplication = null;
    public static BaseApplication getApplication() {
        return mApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ToastUtil.init(this);
    }
}

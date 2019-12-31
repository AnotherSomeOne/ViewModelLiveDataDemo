package com.mengk.viewmodellivedata;

import android.app.Application;
import com.mengk.viewmodellivedata.common.config.URL;
import com.mengk.viewmodellivedata.common.http.HttpHelper;
import com.mengk.viewmodellivedata.common.mvvm.stateview.ErrorState;
import com.mengk.viewmodellivedata.common.mvvm.stateview.LoadingState;
import com.mengk.viewmodellivedata.common.util.ToastUtil;
import com.tqzhang.stateview.core.LoadState;

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
        new HttpHelper.Builder(this)
                .initOkHttp()
                .createRetrofit(URL.BASE_URL)
                .build();

    }

    public static BaseApplication instance() {
        return mApplication;
    }
}

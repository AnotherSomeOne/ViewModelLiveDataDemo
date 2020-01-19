package com.mengk.viewmodellivedata.model.viewmodel;

import android.util.Log;
import com.mengk.viewmodellivedata.common.config.Constants;
import com.mengk.viewmodellivedata.common.mvvm.base.BaseRepository;
import com.mengk.viewmodellivedata.common.mvvm.stateview.StateConstants;
import com.mengk.viewmodellivedata.common.util.StringUtil;
import com.mengk.viewmodellivedata.model.bean.DelayTimeBean;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class SplashRepository extends BaseRepository {

    public static String EVENT_KEY_WORK_LIST = null;

    public static String EVENT_KEY_WORK_MORE = null;

    public SplashRepository() {
        if (EVENT_KEY_WORK_LIST==null) {
            EVENT_KEY_WORK_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_WORK_LIST==null) {
            EVENT_KEY_WORK_MORE = StringUtil.getEventKey();
        }
    }

    public void delayTime() {
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    Log.e("===z","aLong = " + aLong);
                    new DelayTimeBean(0,aLong + 1);
                    postState(StateConstants.SUCCESS_STATE);
                    if (aLong >= 2) {
                    }

                });
        addDisposable(subscribe);
    }

}

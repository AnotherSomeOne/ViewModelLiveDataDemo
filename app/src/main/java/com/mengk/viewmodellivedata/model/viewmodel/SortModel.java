package com.mengk.viewmodellivedata.model.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;
import com.mengk.viewmodellivedata.model.bean.DelayTimeBean;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description 字母排序viewModel
 */
public class SortModel extends AbsViewModel implements LifecycleObserver {
    //用于存放Disposable的容器
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<DelayTimeBean> delayToTime = new MutableLiveData<>();

    public SortModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<DelayTimeBean> getDelayToTime() {
        return delayToTime;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.e("===z","SplashViewModel onCreate");
        delayTime();
    }


    public void delayTime() {
        Log.e("===z","SplashViewModel delayTime");
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    Log.e("===z","aLong = " + aLong);
                    delayToTime.postValue(new DelayTimeBean(0,aLong + 1));
                    if (aLong >= 2) {
                        dispose();
                        delayToTime.postValue(new DelayTimeBean(1,aLong + 1));
                    }
                });
        compositeDisposable.add(subscribe);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        dispose();
    }

    private void dispose() {
        compositeDisposable.dispose();
    }


}

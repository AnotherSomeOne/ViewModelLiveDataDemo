package com.mengk.viewmodellivedata.model.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.*;
import com.mengk.viewmodellivedata.model.bean.DelayTimeBean;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description 闪屏页viewModel
 */
public class SplashViewModel extends BaseViewModel implements LifecycleObserver {
    //用于存放Disposable的容器
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;

    private MutableLiveData<DelayTimeBean> delayToTime = new MutableLiveData<>();

    public MutableLiveData<DelayTimeBean> getDelayToTime() {
        return delayToTime;
    }

    public SplashViewModel() {}

    public SplashViewModel(Context context) {
        this.context = context;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.e("===z","SplashViewModel onCreate");
        delayTime();
    }


    private void delayTime() {
        Log.e("===z","SplashViewModel delayTime");
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Disposable subscribe = Observable.interval(1, 1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    Log.e("===z","aLong = " + aLong);
                    delayToTime.postValue(new DelayTimeBean(0,aLong));
                    if (aLong == 3) {
                        dispose();
                        delayToTime.postValue(new DelayTimeBean(1,aLong));
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

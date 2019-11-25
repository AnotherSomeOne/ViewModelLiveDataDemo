package com.mengk.viewmodellivedata.model.viewmodel;

import androidx.lifecycle.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description
 */
public class BaseViewModel extends ViewModel implements LifecycleObserver {
    public BaseViewModel() {}

    public MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Object> getDelayTime() {
        return mutableLiveData;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {

    }
}

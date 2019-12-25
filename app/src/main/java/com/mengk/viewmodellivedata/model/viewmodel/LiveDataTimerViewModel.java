package com.mengk.viewmodellivedata.model.viewmodel;

import android.content.Context;
import android.os.SystemClock;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mengk.viewmodellivedata.common.util.JsonDataUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mengk
 * @date {2019/9/5}
 * @description
 */
public class LiveDataTimerViewModel extends ViewModel {

    private static final int ONE_SECOND = 1000;

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private long mInitialTime;

    /**
     * 在初始化时启动一个定时任务，每隔一秒通过postValue方法刷新一下数据。
     */
    public LiveDataTimerViewModel() {
        mInitialTime = SystemClock.elapsedRealtime();

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                mElapsedTime.postValue(newValue);
            }
        },ONE_SECOND,ONE_SECOND);

    }

    public MutableLiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }


}

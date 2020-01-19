package com.mengk.viewmodellivedata.model.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;
import com.mengk.viewmodellivedata.model.source.NetRepository;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description viewModel
 */
public class NetModel extends AbsViewModel<NetRepository> {

    protected MutableLiveData<Boolean> mLiveData1;
    protected MutableLiveData<String> mLiveData2;

    public NetModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getLiveData1() {
        if (mLiveData1 == null) {
            mLiveData1 = new MutableLiveData<>();
        }
        return mLiveData1;
    }

    public LiveData<String> getLiveData2() {
        if (mLiveData2 == null) {
            mLiveData2 = new MutableLiveData<>();
        }
        return mLiveData2;
    }

    public void getHomeListData(String id) {
        mRepository.getData1().subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                mLiveData1.setValue(aBoolean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


        mRepository.getData2().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mLiveData2.setValue(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}

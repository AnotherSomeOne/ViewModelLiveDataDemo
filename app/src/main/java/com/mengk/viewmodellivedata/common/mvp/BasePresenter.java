package com.mengk.viewmodellivedata.common.mvp;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends LifecycleOwner, M extends BaseModel> {

    public Context mContext;
    public M mModel;
    public V mView;
    protected CompositeDisposable mDisposables = new CompositeDisposable();

    public void setVM(V v) {
        this.mView = v;
        this. mModel = TUtil.getT(this, 1);
        this.onStart();
    }

    public void onStart() {
    }

    public void add(Disposable disposable) {
        mDisposables.add(disposable);
    }

    public void onDestroy() {
        mDisposables.clear();
    }


}

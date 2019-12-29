package com.mengk.viewmodellivedata.common.mvvm.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.mengk.viewmodellivedata.common.mvvm.event.LiveBus;
import com.mengk.viewmodellivedata.common.mvvm.stateview.ErrorState;
import com.mengk.viewmodellivedata.common.mvvm.stateview.LoadingState;
import com.mengk.viewmodellivedata.common.mvvm.stateview.StateConstants;
import com.mengk.viewmodellivedata.common.util.TUtil;
import com.tqzhang.stateview.stateview.BaseStateControl;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsLifecycleFragment<T extends AbsViewModel> extends BaseFragment {

    protected T mViewModel;


    @Override
    public void initView(Bundle state) {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        if (null != mViewModel) {
            dataObserver();
        }
    }

    /**
     * create ViewModelProviders
     *
     * @return ViewModel
     */
    protected <T extends ViewModel> T VMProviders(BaseFragment
                                                          fragment, @NonNull Class<T> modelClass) {
        return ViewModelProviders.of(fragment).get(modelClass);

    }

    protected void dataObserver() {

    }


    @Override
    protected void onStateRefresh() {

    }

    /**
     * 获取网络数据
     */
    protected void getRemoteData() {

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}

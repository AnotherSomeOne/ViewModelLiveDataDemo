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

    private List<Object> eventKeys = new ArrayList<>();

    @Override
    public void initView(Bundle state) {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        if (null != mViewModel) {
            dataObserver();
            mViewModel.mRepository.loadState.observe(this, observer);
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

    protected <T> MutableLiveData<T> registerSubscriber(Object eventKey, Class<T> tClass) {

        return registerSubscriber(eventKey, null, tClass);
    }

    protected <T> MutableLiveData<T> registerSubscriber(Object eventKey, String tag, Class<T> tClass) {
        String event;
        if (TextUtils.isEmpty(tag)) {
            event = (String) eventKey;
        } else {
            event = eventKey + tag;
        }
        eventKeys.add(event);
        return LiveBus.getDefault().subscribe(eventKey, tag, tClass);
    }


    @Override
    protected void onStateRefresh() {
        showLoading();
    }


    /**
     * 获取网络数据
     */
    protected void getRemoteData() {

    }

    protected void showError(Class<? extends BaseStateControl> stateView, Object tag) {
        loadManager.showStateView(stateView, tag);
    }

    protected void showError(Class<? extends BaseStateControl> stateView) {
        showError(stateView, null);
    }

    protected void showSuccess() {
        loadManager.showSuccess();
    }

    protected void showLoading() {
        loadManager.showStateView(LoadingState.class);
    }


    /**
     * 状态页面监听
     */
    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                if (StateConstants.ERROR_STATE.equals(state)) {
                    showError(ErrorState.class, "2");
                } else if (StateConstants.NET_WORK_STATE.equals(state)) {
                    showError(ErrorState.class, "1");
                } else if (StateConstants.LOADING_STATE.equals(state)) {
                    showLoading();
                } else if (StateConstants.SUCCESS_STATE.equals(state)) {
                    showSuccess();
                } else if (StateConstants.NOT_DATA_STATE.equals(state)) {
                    showError(ErrorState.class, "0");
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearEvent();
    }

    private void clearEvent() {
        if (eventKeys != null && eventKeys.size() > 0) {
            for (int i = 0; i < eventKeys.size(); i++) {
                LiveBus.getDefault().clear(eventKeys.get(i));
            }
        }
    }
}

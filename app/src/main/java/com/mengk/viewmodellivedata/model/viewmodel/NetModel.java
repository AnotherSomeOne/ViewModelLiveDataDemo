package com.mengk.viewmodellivedata.model.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;
import com.mengk.viewmodellivedata.model.source.NetRepository;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description viewModel
 */
public class NetModel extends AbsViewModel<NetRepository> {

    public NetModel(@NonNull Application application) {
        super(application);
    }

    public void getHomeListData(String id) {
        mRepository.getHomeData(id);
    }


}

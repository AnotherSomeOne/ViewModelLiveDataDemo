package com.mengk.viewmodellivedata.model.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description viewModel
 */
public class NetModel extends AbsViewModel implements LifecycleObserver {

    public NetModel(@NonNull Application application) {
        super(application);
    }


}

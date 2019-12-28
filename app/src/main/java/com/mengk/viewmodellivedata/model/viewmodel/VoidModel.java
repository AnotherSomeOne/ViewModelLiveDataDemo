package com.mengk.viewmodellivedata.model.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsViewModel;
import com.mengk.viewmodellivedata.model.bean.IndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexListLableLetterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mengk
 * @date {2019/11/25}
 * @description viewModel
 */
public class VoidModel extends AbsViewModel implements LifecycleObserver {

    public VoidModel(@NonNull Application application) {
        super(application);
    }


}

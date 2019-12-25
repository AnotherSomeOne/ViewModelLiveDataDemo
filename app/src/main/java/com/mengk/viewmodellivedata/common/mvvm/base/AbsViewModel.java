package com.mengk.viewmodellivedata.common.mvvm.base;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.mengk.viewmodellivedata.common.util.TUtil;

public class AbsViewModel<T extends AbsRepository> extends AndroidViewModel {


    public T mRepository;

    public AbsViewModel(@NonNull Application application) {
        super(application);
        mRepository = TUtil.getNewInstance(this, 0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.unDisposable();
        }
    }

}

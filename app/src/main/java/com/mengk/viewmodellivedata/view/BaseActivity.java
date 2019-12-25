package com.mengk.viewmodellivedata.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mengk.viewmodellivedata.common.util.MFactory;
import com.mengk.viewmodellivedata.common.util.MUtil;
import com.mengk.viewmodellivedata.model.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressLint("Registered")
public abstract class BaseActivity<M extends BaseViewModel> extends AppCompatActivity {

    private Unbinder bind;
    public BaseViewModel viewModel;
    public M mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        bind = ButterKnife.bind(this);
        mViewModel = createViewModel();
        viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);

    }

    public abstract int getLayoutID();

    public M createViewModel() {
        //这里获得到的是类的泛型的类型
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        if (type != null) {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            Class<M> tClass = (Class<M>) actualTypeArguments[0];
            mViewModel = MFactory.createViewModel(tClass);
        }
        return mViewModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}

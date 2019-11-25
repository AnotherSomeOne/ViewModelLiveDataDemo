package com.mengk.viewmodellivedata.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.mengk.viewmodellivedata.model.viewmodel.BaseViewModel;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;
    public BaseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        bind = ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
    }

    public abstract int getLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}

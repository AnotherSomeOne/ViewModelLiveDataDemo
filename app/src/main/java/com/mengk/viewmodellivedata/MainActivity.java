package com.mengk.viewmodellivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.mengk.viewmodellivedata.model.viewmodel.LiveDataTimerViewModel;

public class MainActivity extends AppCompatActivity {

    private LiveDataTimerViewModel mModel;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);

        subscribe();
    }


    private void subscribe() {
        mModel.getElapsedTime().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                //Update UI
                mTextView.setText("time = " + aLong);
            }
        });
    }


    private void initViews() {
        mTextView = findViewById(R.id.text_view);
    }
}

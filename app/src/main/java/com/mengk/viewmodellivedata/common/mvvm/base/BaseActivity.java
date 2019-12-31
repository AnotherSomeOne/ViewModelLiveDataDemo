package com.mengk.viewmodellivedata.common.mvvm.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gyf.immersionbar.ImmersionBar;
import com.mengk.viewmodellivedata.R;
import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;


public abstract class BaseActivity extends AppCompatActivity {

    protected LoadManager loadManager;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //状态栏
        initStatusBar();
        //设置布局内容
        setContentView(getLayoutId());
        doSomeThingAfterSetContentView();
        bind = ButterKnife.bind(this);
//        loadManager = new LoadManager.Builder()
//                .setViewParams(this)
//                .setListener(new BaseStateControl.OnRefreshListener() {
//                    @Override
//                    public void onRefresh(View v) {
//                        onStateRefresh();
//                    }
//                })
//                .build();
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();

    }

    protected void doSomeThingAfterSetContentView() {
        if (hasImmersionBar()) {
            initImmersionBar();
        }
    }

    protected boolean hasImmersionBar() {
        return true;
    }


    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        // 设置共同沉浸式样式
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(getStatusBarColor())
                .navigationBarColor(getNavigationBarColor())
                .init();
    }

    /**
     * 获取StatusBar颜色，默认白色
     *
     * @return
     */
    protected @ColorRes
    int getStatusBarColor() {
        return R.color.white;
    }

    /**
     * 获取NavigationBar颜色，默认白色
     *
     * @return
     */
    protected @ColorRes
    int getNavigationBarColor() {
        return R.color.white;
    }

    public int getStatusHeight() {
        return ImmersionBar.getStatusBarHeight(this);
    }

    protected void initStatusBar() {

    }


    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化views
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化toolbar
     */
    protected void initToolBar() {
        //doSomething
    }

    protected void onStateRefresh() {
    }

    /**
     * 显示进度条
     */
    public void showProgressBar() {
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressBar() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}


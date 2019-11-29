package com.mengk.viewmodellivedata.common.mvp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.mengk.viewmodellivedata.common.mvp.baseapp.AppManager;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    protected boolean isConfigChange = false;
    private boolean initScore = true;

    protected boolean isRegisterEvent() {
        return false;
    }

    protected boolean initScore() {
        return initScore;
    }

    public abstract int getLayoutId();

    /**
     * 初始化子View
     */
    protected abstract void initView();

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 点击事件处理
     *
     * @param view
     */
    protected abstract void processClick(View view);

    protected void onBeforeCreate(@Nullable Bundle savedInstanceState) {
        // 去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void onAfterCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        isConfigChange = false;
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        doAfterSetcontentView();
        if (isRegisterEvent()) {
            // 进行事件注册
        }
        AppManager.getInstance().addActivity(this);
        initViewData();
    }


    /**
     * 添加点击事件
     *
     * @param view
     */
    protected void bindClick(View view) {
        view.setOnClickListener(this);
    }

    /**
     * 添加点击事件
     *
     * @param viewId
     */
    protected void bindClick(int viewId) {
        F(viewId).setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        getIntentData();
        onAfterCreate(savedInstanceState);
    }

    protected void getIntentData() {

    }

    /**
     * 初始化view和数据
     */
    public void initViewData() {
        initView();
        bindEvent();
        initData();
    }


    /**
     * 设置主题
     */
    protected void initTheme() {
    }


    /**
     * 设置layout前配置
     */
    protected void doBeforeSetcontentView() {
        //设置昼夜主题
        initTheme();
    }

    protected void doAfterSetcontentView() {
    }

    /**
     * 获取当前view的LayoutInflater实例
     *
     * @return
     */
    protected LayoutInflater getLayouInflater() {
        LayoutInflater _LayoutInflater = LayoutInflater.from(this);
        return _LayoutInflater;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    /**
     * 弹出toast 显示时长short
     *
     * @param pMsg
     */
    public void showToastMsgShort(String pMsg) {
//        ToastUtils.showToast(pMsg);
    }

    /**
     * 弹出toase 显示时长long
     *
     * @param pMsg
     */
    public void showToastMsgLong(String pMsg) {
//        ToastUtils.showLong(pMsg);
    }

    /**
     * 根据传入的类(class)打开指定的activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
    }

    protected void openActivityByIntent(Intent pIntent) {
        startActivity(pIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 必须清除保证不会内存泄漏
        if (isRegisterEvent()) {
            // BusManager.getBus().unregister(this);
        }
        if (!isConfigChange) {
            AppManager.getInstance().removeActivity(this);
        }
    }

    @Override
    public void onClick(View view) {
        processClick(view);
    }

    protected <E extends View> E F(@IdRes int viewId) {
        return (E) super.findViewById(viewId);
    }

    protected <E extends View> E F(@NonNull View view, @IdRes int viewId) {
        return (E) view.findViewById(viewId);
    }

    protected <E extends View> void C(@NonNull E view) {
        view.setOnClickListener(this);
    }

    private AlertDialog mLoadingDialog = null;

//    public void showDialogLoading() {
//        showDialogLoading(getString(R.string.app_loading));
//    }

    /**
     * @param msg
     */
    /*public void showDialogLoading(String msg) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing() == true) {
            return;
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = new MaterialLoadingDialog.Builder(this).show(msg);
        }
    }*/

    /**
     *
     */
    public void hideDialogLoading() {
        if (mLoadingDialog == null) {
            return;
        }
        if (mLoadingDialog.isShowing() == true) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    /**
     * @param msg
     */
    public void showErrorTip(String msg) {
        showToastMsgShort(msg);
    }


    /*public PlaceholderView getPlaceholderView() {
        return null;
    }

    *//**
     * 展示加载中的占位图
     *//*
    public void showPageLoading() {
        if (getPlaceholderView() == null) {
            return;
        }
        getPlaceholderView().showLoading();
    }

    public void hidePageLoading() {
        if (getPlaceholderView() == null) {
            return;
        }
        if (getPlaceholderView().isLoading() == true) {
            getPlaceholderView().hideLoading();
        }
    }

    *//**
     * 展示空数据的占位图
     *//*
    public void showPageEmpty(String msg) {
        if (getPlaceholderView() == null) {
            return;
        }
        hidePageLoading();
        getPlaceholderView().showEmpty(msg);
    }

    *//**
     * 展示空数据的占位图
     *//*
    public void showPageContent() {
        if (getPlaceholderView() == null) {
            return;
        }
        hidePageLoading();
        getPlaceholderView().showContent();
    }

    *//**
     * 展示加载错误的占位图
     *//*
    public void showPageError(String msg) {
        if (getPlaceholderView() == null) {
            return;
        }
        hidePageLoading();
        getPlaceholderView().showError(msg);
    }

    *//**
     * 设置页面加载错误重连的监听
     *//*
    public void setPageErrorRetryListener(View.OnClickListener listener) {
        if (getPlaceholderView() == null) {
            return;
        }
        getPlaceholderView().setPageErrorRetryListener(listener);
    }*/

    /**
     * 重新数据
     */
    public void retryLoad() {
    }


    public void setVisibility(View view, boolean isVisible) {
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    public void setVisibility(View view, int visible) {
        if (view != null) {
            view.setVisibility(visible);
        }
    }

    public void setText(TextView textView, @StringRes int resId) {
        if (textView != null) {
            textView.setText(resId);
        }
    }

    public void setText(TextView textView, String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }

    protected boolean isTouchHideSoftInput() {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isTouchHideSoftInput()) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                // 是否需要隐藏软键盘
                /*if (SoftInputUtils.INSTANCE.isHideSoftInput(ev)) {
                    SoftInputUtils.INSTANCE.invokeOnTouchOutsideListener(this);
                }*/
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * determine Activity is Port.
     */
    boolean isPort() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public boolean isFullScreen() {
        return !isPortScreen();
    }

    boolean isPortScreen() {
        return isPort();
    }

    public void setFullScreen(boolean isFullScreen) {
        if (isFullScreen()) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public int getStatusHeight() {
        return ImmersionBar.getStatusBarHeight(this);
    }

    public boolean hasNotchHeight() {
        return ImmersionBar.hasNotchScreen(this);
    }

    public int getNotchHeight() {
        return ImmersionBar.getNotchHeight(this);
    }
}

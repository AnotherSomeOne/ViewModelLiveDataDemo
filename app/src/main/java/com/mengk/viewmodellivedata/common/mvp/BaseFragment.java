package com.mengk.viewmodellivedata.common.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected Context mContext;
    protected Resources mResources;
    protected LayoutInflater mInflater;
    protected View mConvertView;
    private boolean mIsRegisterEvent = false;
    private boolean active = false;

    // view 是否创建
    private boolean isViewCreated = false;
    // view 是否可见
    private boolean isViewVisable = false;

    protected boolean isActive() {
        return active;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
        this.mResources = mContext.getResources();
        this.mInflater = LayoutInflater.from(mContext);
        if (mIsRegisterEvent) {
            // BusManager.getBus().register(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        mConvertView = inflater.inflate(getLayoutId(), container, false);
        active = true;
        return mConvertView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        // 如果启用了懒加载就进行懒加载，否则就进行预加载
        if (enableLazyData()) {
            lazyLoad();
        } else {
            initViewData();
        }
    }

    /**
     * 初始化view和数据
     */
    public void initViewData() {
        active = true;
        initView();
        bindEvent();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        active = false;
        if (mIsRegisterEvent) {
            // BusManager.getBus().unregister(this);
        }
    }

    @Override
    public void onClick(View view) {
        processClick(view);
    }

    protected <E extends View> E findView(@IdRes int viewId) {
        if (mConvertView == null) {
            return null;
        }
        return (E) mConvertView.findViewById(viewId);
    }

    protected <E extends View> E findView(@NonNull View view, @IdRes int viewId) {
        return (E) view.findViewById(viewId);
    }

    protected <E extends View> void setOnClickListener(@NonNull E view) {
        view.setOnClickListener(this);
    }

    public boolean isRegisterEvent() {
        return mIsRegisterEvent;
    }

    public BaseFragment setRegisterEvent(boolean mIsRegisterEvent) {
        this.mIsRegisterEvent = mIsRegisterEvent;
        return this;
    }

    /**
     * 布局的LayoutID
     *
     * @return
     */
    protected abstract int getLayoutId();

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


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 弹出toast 显示时长short
     *
     * @param pMsg
     */
    protected void showToastMsgShort(String pMsg) {
//        ToastUtils.showToast(pMsg);
    }

    /**
     * 弹出toase 显示时长long
     *
     * @param pMsg
     */
    protected void showToastMsgLong(String pMsg) {
//        ToastUtils.showLong(pMsg);
    }


    /**
     * 默认不启用懒加载
     *
     * @return
     */
    public boolean enableLazyData() {
        return false;
    }

    private void lazyLoad() {
        //这里进行双重标记判断,必须确保onCreateView加载完毕且页面可见,才加载数据

        if (isViewCreated && isViewVisable) {
            initViewData();
            // 数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isViewVisable = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isViewVisable = isVisibleToUser;
        //如果启用了懒加载就进行懒加载，
        if (enableLazyData() && isViewVisable) {
            lazyLoad();
        }
    }

    /*public PlaceholderView getPlaceholderView() {
        return null;
    }

    public void showPageLoading() {
        if (getPlaceholderView() != null) {
            getPlaceholderView().showLoading();
        }
    }


    public void hidePageLoading() {
        if (getPlaceholderView() != null) {
            getPlaceholderView().hideLoading();
        }
    }

    public void showPageEmpty(String msg) {
        if (getPlaceholderView() != null) {
            getPlaceholderView().showEmpty(msg);
        }
    }

    public void showPageError(String msg) {
        if (getPlaceholderView() != null) {
            getPlaceholderView().showError(msg);
        }
    }

    public void showPageContent() {
        if (getPlaceholderView() != null) {
            getPlaceholderView().showContent();
        }
    }*/

    public void setVisible(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public void setGone(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

}

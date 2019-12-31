package com.mengk.viewmodellivedata.common.http.rx;


import com.google.gson.JsonParseException;

import com.mengk.viewmodellivedata.common.http.ServerException;
import com.mengk.viewmodellivedata.common.util.NetworkUtils;
import com.mengk.viewmodellivedata.common.util.ToastUtil;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {


    public RxSubscriber() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
        if (!NetworkUtils.isNetworkAvailable()) {
            onNoNetWork();
            cancel();
            ToastUtil.showToast("网络状态异常");
            return;
        }
    }


    @Override
    public void onComplete() {

    }

    protected void showLoading() {

    }

    protected void showProgress() {

    }

    protected void onNoNetWork() {

    }

    @Override
    public void onError(Throwable e) {
        String message;
        int code = -1;
        if (e instanceof UnknownHostException) {
            message = "没有网络";
        } else if (e instanceof HttpException) {
            message = "网络错误";
        } else if (e instanceof SocketTimeoutException) {
            message = "网络连接超时";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            message = "解析错误";
        } else if (e instanceof ConnectException) {
            message = "连接失败";
        } else if (e instanceof ServerException) {
            message = ((ServerException) e).message;
            code = ((ServerException) e).code;
        } else {
            message = "未知错误";
        }
        ToastUtil.showToast(message);
        onFailure(message, code);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    /**
     * success
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * failure
     *
     * @param msg
     */
    public abstract void onFailure(String msg, int code);
}

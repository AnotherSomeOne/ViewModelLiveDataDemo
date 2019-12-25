package com.mengk.viewmodellivedata.common.mvvm.base;


import com.mengk.viewmodellivedata.common.http.ApiService;
import com.mengk.viewmodellivedata.common.http.HttpHelper;
import com.mengk.viewmodellivedata.common.mvvm.event.LiveBus;

public class BaseRepository extends AbsRepository {

    protected ApiService apiService;


    public BaseRepository() {
        if (null == apiService) {
            apiService = HttpHelper.getInstance().create(ApiService.class);
        }
    }


    protected void postData(Object eventKey, Object t) {
        postData(eventKey, null, t);
    }


    protected void showPageState(Object eventKey, String state) {
        postData(eventKey, state);
    }

    protected void showPageState(Object eventKey, String tag, String state) {
        postData(eventKey, tag, state);
    }

    protected void postData(Object eventKey, String tag, Object t) {
        LiveBus.getDefault().postEvent(eventKey, tag, t);
    }

}

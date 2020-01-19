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


}

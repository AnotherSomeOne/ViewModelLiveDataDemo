package com.mengk.viewmodellivedata.model.source;

import android.util.Log;
import com.mengk.viewmodellivedata.common.http.rx.RxSchedulers;
import com.mengk.viewmodellivedata.common.http.rx.RxSubscriber;
import com.mengk.viewmodellivedata.common.mvvm.base.BaseRepository;
import com.mengk.viewmodellivedata.model.bean.HomeListVo;
import com.mengk.viewmodellivedata.model.bean.WorksListVo;

public class NetRepository extends BaseRepository {
    public NetRepository() {}

    public void getHomeData(String id) {
        apiService.getHomeData(id);
        addDisposable(apiService.getHomeData(id)
        .compose(RxSchedulers.io_main())
        .subscribeWith(new RxSubscriber<HomeListVo>() {
            @Override
            protected void onNoNetWork() {
                super.onNoNetWork();
            }

            @Override
            public void onSuccess(HomeListVo worksListVo) {
                Log.e("====z","首页数据 = " + worksListVo.toString());
            }

            @Override
            public void onFailure(String msg, int code) {

            }
        }));
    }


}

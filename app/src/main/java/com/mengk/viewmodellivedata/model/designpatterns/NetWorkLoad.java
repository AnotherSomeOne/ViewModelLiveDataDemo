package com.mengk.viewmodellivedata.model.designpatterns;

import com.mengk.viewmodellivedata.common.util.ToastUtil;

/**
 * Desc
 * Date 2019/12/8
 * author mengk
 */
public class NetWorkLoad implements IStrategy {
    @Override
    public void load() {
        //
        ToastUtil.showToast("NetWorkLoad");
    }
}

package com.mengk.viewmodellivedata.common.util;

import com.mengk.viewmodellivedata.model.viewmodel.BaseViewModel;

public class MFactory {
    public static <T extends BaseViewModel> T createViewModel(Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}

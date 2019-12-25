package com.mengk.viewmodellivedata.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Desc
 * Date 2019/11/7
 * author mengk
 */


public class OutSideIndexListLableLetterBean implements Serializable {
    private String title;
    private List<IndexLableLetterBean> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IndexLableLetterBean> getList() {
        return list;
    }

    public void setList(List<IndexLableLetterBean> list) {
        this.list = list;
    }
}

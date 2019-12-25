package com.mengk.viewmodellivedata.model.bean;

import java.io.Serializable;

/**
 * Desc
 * Date 2019/11/15
 * author mengk
 */
public class IndexLableLetterBean implements Serializable {
    private int id;
    private int type;
    private String referId;
    private String lable;
    private String picUrl;
    private String headLetter;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getHeadLetter() {
        return headLetter;
    }

    public void setHeadLetter(String headLetter) {
        this.headLetter = headLetter;
    }
}

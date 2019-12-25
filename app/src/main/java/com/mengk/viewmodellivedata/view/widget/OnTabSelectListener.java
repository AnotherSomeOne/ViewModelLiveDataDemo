package com.mengk.viewmodellivedata.view.widget;

/**
 * TabLayout的监听
 */
public interface OnTabSelectListener {

    /**
     * Tab选中
     */
    void onTabSelect(int position);

    /**
     * Tab重新选中
     */
    void onTabReselect(int position);
}
package com.mengk.viewmodellivedata.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.google.gson.Gson;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.common.util.JsonDataUtil;
import com.mengk.viewmodellivedata.model.adapter.TagSortByIndexOutSideLabelLetterAdapter;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexLableLetterBean;
import com.mengk.viewmodellivedata.model.bean.OutSideIndexListLableLetterBean;
import com.mengk.viewmodellivedata.model.viewmodel.SortModel;
import com.mengk.viewmodellivedata.view.widget.MTabLayout;
import com.mengk.viewmodellivedata.view.widget.SideIndexBar;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutOneActivity extends AbsLifecycleActivity<SortModel> {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.xtablayout_info)
    MTabLayout xTabLayout;
    @BindView(R.id.viewPager_info)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setTitle("TAbLayout1");

        xTabLayout.setGradientIndicatorDrawable(R.drawable.indicator_info_home);

    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_tab1;
    }

    public void getTestJson(Context context) {
        String json = JsonDataUtil.getJson(context, "tab_json.json");


    }

}

package com.mengk.viewmodellivedata.view.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.mvvm.base.AbsLifecycleActivity;
import com.mengk.viewmodellivedata.model.adapter.TabFragmentPagerAdapter;
import com.mengk.viewmodellivedata.model.viewmodel.VoidModel;
import com.mengk.viewmodellivedata.view.widget.XTabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayout2Activity extends AbsLifecycleActivity<VoidModel> {

    @BindView(R.id.xtablayout_info)
    XTabLayout xTabLayout;
    @BindView(R.id.viewPager_info)
    ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTestJson(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab2;
    }

    public void getTestJson(Context context) {
        fragments.add(new TestTabFragment());
        fragments.add(new TestTabFragment());
        titles.add("customLable");
        titles.add("customLable");
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        xTabLayout.setViewPager(viewPager);
        xTabLayout.setGradientIndicatorDrawable();
    }

}

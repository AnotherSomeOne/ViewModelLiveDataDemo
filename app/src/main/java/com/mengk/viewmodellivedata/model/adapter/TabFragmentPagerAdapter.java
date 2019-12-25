package com.mengk.viewmodellivedata.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> tiles;

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> tiles) {
        super(fm);
        this.mFragments = mFragments;
        this.tiles = tiles;
    }

    @Override
    public Fragment getItem(int position) {//必须实现
        return mFragments.get(position);
    }

    @Override
    public int getCount() {//必须实现
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//选择性实现
        return tiles.get(position);
    }
}

package com.example.pope.cream.page.home.adapter;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * 首页Fragment切换的ViewPager的适配器
 *
 * @author popeg
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> pageList;

    public HomeViewPagerAdapter(android.support.v4.app.FragmentManager fm, Context context, List<Fragment> pageList) {
        super(fm);
        this.context = context;
        this.pageList = pageList;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList != null ? pageList.size() : 0;
    }
}

package com.kun.latesttechnologystudy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

 public class VgAdapter extends FragmentPagerAdapter{
     private List<Fragment> fragmentList ;
    public VgAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        fragmentList=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        return fragment;
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

package com.kun.latesttechnologystudy.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

public class ViewPagerPageAdapter extends PagerAdapter {
    private List<Fragment> fragmentList;
    private List<View> mVIEWList;

    /*public ViewPagerPageAdapter(List<Fragment> fragments) {
        fragmentList = fragments;
    }*/

    public ViewPagerPageAdapter(Context context) {
        TextView textView = new TextView(context);
        textView.setText("这是textview");
        mVIEWList = new ArrayList<>();
        mVIEWList.add(textView);
        mVIEWList.add(textView);
        mVIEWList.add(textView);
        mVIEWList.add(textView);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView( mVIEWList.get(position));
        return  mVIEWList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView( mVIEWList.get(position));
    }

    @Override
    public int getCount() {
        return mVIEWList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}

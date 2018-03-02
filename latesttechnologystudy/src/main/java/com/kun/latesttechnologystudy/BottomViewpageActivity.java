package com.kun.latesttechnologystudy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kun.latesttechnologystudy.adapter.VgAdapter;
import com.kun.latesttechnologystudy.fragment.Own1Fragment;
import com.kun.latesttechnologystudy.fragment.Own2Fragment;
import com.kun.latesttechnologystudy.fragment.Own3Fragment;
import com.kun.latesttechnologystudy.fragment.Own4Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class BottomViewpageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private Context mContext;
    private BottomNavigationView bottom_main5;
    private ViewPager mVg_bvg;
    private List list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomviewpage);
        mContext = BottomViewpageActivity.this;
        initView();
        initEvent();
    }

    private void initView() {
        list = new ArrayList();

        bottom_main5 = (BottomNavigationView) findViewById(R.id.bottom_main5);

        mVg_bvg = (ViewPager) findViewById(R.id.vg_bvg);
        Own1Fragment own1Fragment = new Own1Fragment(mContext);
        Own2Fragment own2Fragment = new Own2Fragment(mContext);
        Own3Fragment own3Fragment = new Own3Fragment(mContext);
        Own4Fragment own4Fragment = new Own4Fragment(mContext);
        list.add(own1Fragment);
        list.add(own2Fragment);
        list.add(own3Fragment);
        list.add(own4Fragment);

    }

    private void initEvent() {

        //底部导航的平分
        BottomNavigationViewHelper.disableShiftMode(bottom_main5);

        // 设置监听
        bottom_main5.setOnNavigationItemSelectedListener(this);

        //设置adapter
        VgAdapter mVgAdapter = new VgAdapter(getSupportFragmentManager(), list);
        mVg_bvg.setAdapter(mVgAdapter);


        // 设置Pageradapter



        // 设置viewpage的滑动的监听
        mVg_bvg.addOnPageChangeListener(this);
    }

    private int mCurrentPostion = 0;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorites5:
                // 点击的时候切换的 就是我们的FrameLayout
                if (mCurrentPostion != 0) {
                    mCurrentPostion = 0;
                    mVg_bvg.setCurrentItem(mCurrentPostion);
                }
                return true;
            //break;
            case R.id.action_launcher5:
                if (mCurrentPostion != 1) {
                    mCurrentPostion = 1;
                    mVg_bvg.setCurrentItem(mCurrentPostion);
                }
                return true;
            // break;
            case R.id.action_music5:
                if (mCurrentPostion != 2) {
                    mCurrentPostion = 2;
                    mVg_bvg.setCurrentItem(mCurrentPostion);
                }
                return true;
            // break;
            case R.id.action_sport5:
                if (mCurrentPostion != 3) {
                    mCurrentPostion = 3;
                    mVg_bvg.setCurrentItem(mCurrentPostion);
                }
                return true;
            //  break;
        }
        return false;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑动

    }

    @Override
    public void onPageSelected(int position) {
        //  怎么设置底部对应的iteam  去bottomNaVIAGTUONvIEW
        /*final Menu menu = bottom_main5.getMenu();
        MenuItem item = menu.findItem(position);
        item.setChecked(true);*/
        bottom_main5.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //状态变化

    }
}

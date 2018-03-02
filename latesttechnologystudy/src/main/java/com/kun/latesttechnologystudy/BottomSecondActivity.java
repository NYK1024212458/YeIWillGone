package com.kun.latesttechnologystudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.kun.latesttechnologystudy.fragment.Own1Fragment;

/**
 * Created by Administrator on 2018/3/1.
 */

public class BottomSecondActivity extends AppCompatActivity {

    private BottomNavigationView bottom_frgament;
    private FrameLayout fl_main_contain;


    private Context mContext;
    private Own1Fragment own1Fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomsecond);
        mContext = BottomSecondActivity.this;


        initView();

        initEvent();

    }


    /**
     * 控件的初始化
     */
    private void initView() {
        bottom_frgament = (BottomNavigationView) findViewById(R.id.bottom_frgament);
        BottomNavigationViewHelper.disableShiftMode(bottom_frgament);

        fl_main_contain = (FrameLayout) findViewById(R.id.fl_main_contain);


        // 创建fragment
        own1Fragment = new Own1Fragment(mContext);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_contain, own1Fragment).commit();
    }

    /**
     * 事物的处理
     */
    private void initEvent() {

        bottom_frgament.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_favorites4:
                        // 点击的时候切换的 就是我们的FrameLayout
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_contain, own1Fragment).commit();
                        return true;
                    // break;
                    case R.id.action_launcher4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_contain, own1Fragment).commit();
                        return true;
                    // break;
                    case R.id.action_music4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_contain, own1Fragment).commit();
                        return true;
                    // break;
                    case R.id.action_sport4:
                        Intent intent = new Intent(mContext,BottomViewpageActivity.class);
                        startActivity(intent);
                        return true;
                    //  break;
                }
                return false;
            }
        });
    }
}

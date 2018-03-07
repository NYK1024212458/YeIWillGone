package com.kun.latesttechnologystudy.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.ImmersionFragment;
import com.kun.latesttechnologystudy.R;

/**
 * Created by Administrator on 2018/3/1.
 */

@SuppressLint("ValidFragment")
public class Own3Fragment extends ImmersionFragment   {
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(mContext);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setText("三三三三三");
        return textView;
    }

    @SuppressLint("ValidFragment")
    public Own3Fragment(Context context) {
        mContext = context;
    }

    @Override
    protected void immersionInit() {
       /* ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .navigationBarColor(R.color.ceshi)
                .init();*/
    }
}

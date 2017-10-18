package com.kunkun.forlove.sanyidademo.application;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MyApplication extends Application {
    private Context mContext;

    @Override
    public void onCreate() {
        mContext = MyApplication.this;
        // 将“12345678”替换成您应用对应的APPID，申请地址：http://aiui.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(mContext, SpeechConstant.APPID + "=58ed7c97");

    }


}

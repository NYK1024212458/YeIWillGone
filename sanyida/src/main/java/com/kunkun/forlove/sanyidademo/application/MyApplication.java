package com.kunkun.forlove.sanyidademo.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.kunkun.forlove.sanyidademo.MainActivity;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MyApplication extends Application {
    private Context mContext;
    private  String TAG = MainActivity.class.getSimpleName().toString();

    @Override
    public void onCreate() {
        mContext = MyApplication.this;
        // 将“12345678”替换成您应用对应的APPID，申请地址：http://aiui.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(mContext, SpeechConstant.APPID + "=59e94e7b");
        Log.d(TAG, "onCreate: 测试的是不是在MyApplication完成了初始化的操作");

    }


}

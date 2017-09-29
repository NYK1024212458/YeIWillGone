package com.kunkun.forlove.formyself;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.kunkun.forlove.formyself.utils.SPUtils;
import com.kunkun.forlove.formyself.utils.T;
import com.kunkun.forlove.formyself.view.KunVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kunkun.forlove.formyself.R.id.et_password;

public class MyLifeActivity extends AppCompatActivity {

    public Context mContext;
    private static final String TAG = MyLifeActivity.class.getSimpleName();

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_fogetpwd)
    Button btnFogetpwd;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    @BindView(R.id.ll_shezhi)
    RelativeLayout llShezhi;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.activity_my_life)
    FrameLayout activityMyLife;


    private KunVideoView kuncd_main_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_life);
        ButterKnife.bind(this);

        mContext = MyLifeActivity.this;

        initView();
    }

    private void initView() {

        kuncd_main_show = (KunVideoView) findViewById(R.id.kuncd_main_show);

        //设置视频的播放地址
        kuncd_main_show.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        //开始播放

        kuncd_main_show.start();


        //设置监听,播放结束后再次开始播放

        kuncd_main_show.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                kuncd_main_show.start();
            }
        });

        // 保存一个用户名和密码
        SPUtils.put(mContext,"username","bigtrouble");
        SPUtils.put(mContext,"userpwd","marrywithkun");

    }

    @Override
    protected void onRestart() {
        //重启
        initView();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        //暂停
        kuncd_main_show.stopPlayback();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    /*
    下面的三个方法是butterknife注入的点击事件
     */

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        T.showShort(mContext,"marry with me");
        String username = etUsername.getText().toString().trim();
        String etpwd = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)&& TextUtils.isEmpty(etpwd)){
            T.showShort(mContext,"不能输入空值");
            return;
        }else {
           startActivity( new Intent(mContext,PictureActivity.class));
        }



        //


    }

    @OnClick(R.id.btn_fogetpwd)
    public void onBtnFogetpwdClicked() {
    }

    @OnClick(R.id.btn_regist)
    public void onBtnRegistClicked() {
    }
}

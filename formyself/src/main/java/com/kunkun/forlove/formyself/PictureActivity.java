package com.kunkun.forlove.formyself;

import android.Manifest;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.joker.api.Permissions4M;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.kun.formyself.videobackgroundpackage
 * <p>
 * Created by ${kun}
 * 2017/9/28
 */
public class PictureActivity extends AppCompatActivity {
    private static final int AUDIO_CODE = 6;
    @BindView(R.id.iv_showlove)
    ImageView ivShowlove;

    private Context mContext;

    // 本业展示我们获取的图片设置迅雷倒计时


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        mContext = PictureActivity.this;
       // checkPermisssion();

        // glide获取图片并加载
            setImage();


    }

    private void setImage() {



    }

    private void checkPermisssion() {
        Permissions4M.get(PictureActivity.this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                // .requestUnderM(false)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.INTERNET)
                // 权限码
               // .requestCodes(AUDIO_CODE)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行

                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                //.requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        Permissions4M.onRequestPermissionsResult(PictureActivity.this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

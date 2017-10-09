package com.kunkun.forlove.formyself;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
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
    private String ImageUrl = "http://116.196.91.100/wordpress/wp-content/uploads/2017/09/微信图片_20170929111751.jpg";
    // 本业展示我们获取的图片设置迅雷倒计时

    private String BigImageUrl = "http://kunkun5love.com/wordpress/wp-content/uploads/2017/09/321171.jpg";

    // 设置的是gif的图片地址
    private String LoadGif = "http://kunkun5love.com/wordpress/wp-content/uploads/2017/10/loadgif.gif";


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

      /*  Glide.with(PictureActivity.this)
                .load(ImageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.err)
                .fallback(R.mipmap.fallback)
                .fitCenter()
                .into(ivShowlove);*/

        // 设置我们需要的自定义的动画:

        ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                view.setAlpha(0f);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                objectAnimator.setDuration(2500);
                objectAnimator.start();
            }
        };

        //   .crossFade(600) // 设置在加载动画的时候动画的而设置,有多个方法的重载.
        // 默认是开启的,实质是对动画的一个加载.  我们在此的设置设置的是动画的时长是600ms
        // .dontAnimate()  // 这都是来禁止使用动画的方法
        //.dontTransform() // 禁止使用动画的方法
        RequestListener<? super String, GlideDrawable> loadListern = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        };
        Glide.with(mContext)
                .load(LoadGif)    // 加载的地址
                .listener(loadListern)
                // 判断加载的是不是gif图片        .asGif()
                //.asBitmap()  // 这个方法就是在加载gif图片的时候
                .placeholder(R.mipmap.ic_launcher)  //  正在加载的时候展示的图片
                .error(R.mipmap.err)  // 加载是被的时候展示的图片
                .fallback(R.mipmap.fallback) // 设置的是在loadURL为空的时候展示的图片
                .centerCrop() // 设置图片的伸缩
                .skipMemoryCache(true)// 设置是否跳过内存缓冲  默认是false 是不跳过内存缓冲的
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 设置的硬盘缓冲的设置  此时是禁止使用硬盘缓冲的!
                .priority(Priority.HIGH) // 设置glide的请求的优先级,不会影响最后的显示的顺序
                .animate(animator)

                .into(ivShowlove);


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

package com.kunkun.forlove.formyself;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.joker.api.Permissions4M;
import com.kunkun.forlove.formyself.utils.L;
import com.kunkun.forlove.formyself.utils.LogUtils;
import com.kunkun.forlove.formyself.utils.T;
import com.kunkun.forlove.formyself.view.CustomTimerView;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.kun.formyself.videobackgroundpackage
 * <p>  实现沉浸式的高级用法
 * Created by ${kun}
 * 2017/9/28
 */
public class PictureActivity extends AppCompatActivity {

    private int START_COUNTER_TIMER = 110;

    private Handler mMhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == START_COUNTER_TIMER) {
                //开始倒计时的操作迅雷
                statrCountTimerView();
            }


        }


    };

    /**
     * start counttimer view
     */
    private void statrCountTimerView() {
        customTimerView.start();
        //设置监听

        CustomTimerView.CountDownTimerListener countDownTimerListener = new CustomTimerView.CountDownTimerListener() {
            @Override
            public void onStartCount() {

                T.show(mContext, "可以直接点击图标跳过", Toast.LENGTH_SHORT);

            }

            @Override
            public void onFinishCount() {
                // 进入到主页面    finish

                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();

            }
        };
        customTimerView.setCountDownTimerListener(countDownTimerListener);

        // 设置点击事件
        customTimerView.setcountTimerClickListener(new CustomTimerView.countTimerClickListener() {
            @Override
            public void onCountTimerClick() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private static final int AUDIO_CODE = 6;
    @BindView(R.id.iv_showlove)
    ImageView ivShowlove;

    @BindView(R.id.cv_customtim_view)
    CustomTimerView customTimerView;

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

        //沉浸式的高级用法
        initImmersionBar();


        ButterKnife.bind(this);

        mContext = PictureActivity.this;
        // checkPermisssion();

        // glide获取图片并加载
        setImage();


    }

    private void initImmersionBar() {
        ImmersionBar.with(this).init();

      /*  ImmersionBar mImmersionBar = ImmersionBar
                .with(this);


        mImmersionBar.barColor(R.color.colorPrimary)
                .init();
*/

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
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                           boolean isFromMemoryCache, boolean isFirstResource) {

                //  我们看看日志的输出\

                L.d("ceshideshuchu", "测试我们看看是不是图片的加载已经是不是完成了!");

                //加载完毕我们可以倒计时了!
                mMhandle.sendEmptyMessage(START_COUNTER_TIMER);

                return false;
            }
        };

        Transformation transformation = new Transformation();
        transformation.setAlpha(1);

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
                //  .transform()
                //  .bitmapTransform()
                .into(ivShowlove); //  参数是imageview
        //  .into(10,10);      // 参数是 两个int类型的数据  FutureTarget<TranscodeType>
        // .into()  ;  //  源码如此 public <Y extends Target<TranscodeType>> Y into(Y target) {  }


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

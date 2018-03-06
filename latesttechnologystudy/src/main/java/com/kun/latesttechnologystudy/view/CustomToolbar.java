package com.kun.latesttechnologystudy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kun.latesttechnologystudy.R;


/**
 * com.kunstudy.toolbardemo.view
 * <p>
 * Created by ${kun}
 * 2017/6/14
 */

public class CustomToolbar extends Toolbar {
    //添加布局必不可少的工具
    private LayoutInflater mInflater;

    //搜索框
    private EditText mEditSearchView;
    //标题
    private TextView mTextTitle;
    //右边按钮
    private ImageButton mRightButton;
    //左边按钮
    private ImageButton mLeftButton;

    private View mView;

    public CustomToolbar(Context context) {
        this(context, null);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //  开始初始化
        initView();
        // 获取属性值
        //Set the content insets for this toolbar relative to layout direction(方向).
        setContentInsetsRelative(10, 10);

        if (attrs != null) {
            //读写自定义的属性，如果不会自己写的时候，就看看官方文档是怎么写的哈
            /**
             * 下面是摘自官方文档
             * final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
             R.styleable.Toolbar, defStyleAttr, 0);

             mTitleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
             mSubtitleTextAppearance = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
             mGravity = a.getInteger(R.styleable.Toolbar_android_gravity, mGravity);
             mButtonGravity = Gravity.TOP;
             mTitleMarginStart = mTitleMarginEnd = mTitleMarginTop = mTitleMarginBottom =
             a.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, 0);

             final int marginStart = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
             if (marginStart >= 0) {
             mTitleMarginStart = marginStart;
             }

             *
             */
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.LetToolBar, defStyleAttr, 0);

            final Drawable leftIcon = a.getDrawable(R.styleable.LetToolBar_leftButtonIcon);
            if (leftIcon != null) {
                setLeftButtonIcon(leftIcon);
            }

            final Drawable rightIcon = a.getDrawable(R.styleable.LetToolBar_rightButtonIcon);
            if (rightIcon != null) {
                setRightButtonIcon(rightIcon);
            }


            boolean isShowSearchView = a.getBoolean(R.styleable.LetToolBar_isShowSearchView, false);

            //如果要显示searchView的时候
            if (isShowSearchView) {
                showSearchView();
                hideTitleView();
            }
            //资源的回收
            a.recycle();
        }
    }

    private void initView() {
        if (mView == null) {
            //初始化
            mInflater = LayoutInflater.from(getContext());
            //添加布局文件
            mView = mInflater.inflate(R.layout.custom_toolbar, null);

            //绑定控件
            //绑定控件
            mEditSearchView = (EditText) mView.findViewById(R.id.toolbar_searchview);
            mTextTitle = (TextView) mView.findViewById(R.id.toolbar_title);
            mLeftButton = (ImageButton) mView.findViewById(R.id.toolbar_leftButton);
            mRightButton = (ImageButton) mView.findViewById(R.id.toolbar_rightButton);


            //然后使用LayoutParams把控件添加到子view中
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);

        }
    }

    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if (mTextTitle != null) {
            mTextTitle.setText(title);
            showTitleView();
        }
    }

    //隐藏标题
    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);
    }

    //显示标题
    public void showTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(VISIBLE);
    }

    //显示搜索框
    public void showSearchView() {
        if (mEditSearchView != null)
            mEditSearchView.setVisibility(VISIBLE);
    }

    //隐藏搜索框
    public void hideSearchView() {
        if (mEditSearchView != null) {
            mEditSearchView.setVisibility(GONE);
        }
    }

    //给右侧按钮设置图片，也可以在布局文件中直接引入
    // 如：app:leftButtonIcon="@drawable/icon_back_32px"
    public void setRightButtonIcon(Drawable icon) {
        if (mRightButton != null) {
            mRightButton.setImageDrawable(icon);
            mRightButton.setVisibility(VISIBLE);
        }
    }

    //给左侧按钮设置图片，也可以在布局文件中直接引入
    private void setLeftButtonIcon(Drawable icon) {
        if (mLeftButton != null) {
            mLeftButton.setImageDrawable(icon);
            mLeftButton.setVisibility(VISIBLE);
        }
    }

    //设置右侧按钮监听事件
    public void setRightButtonOnClickLinster(OnClickListener linster) {
        mRightButton.setOnClickListener(linster);
    }

    //设置左侧按钮监听事件
    public void setLeftButtonOnClickLinster(OnClickListener linster) {
        mLeftButton.setOnClickListener(linster);
    }


    private boolean toolbarShow = false;

    //  动态的显示和展示toolbar
    public void isShowToolbar(boolean show) {

        //  非空的判断
        if (mView == null) {
            return;
        }

        toolbarShow = show;
        if (show) {
            mView.animate()
                    .translationY(0)
                    .alpha(1)
                    .setDuration(1000)
                    .setInterpolator(new DecelerateInterpolator());
            setVisibility(View.VISIBLE);
        } else {
            mView.animate()
                    .translationY(-mView.getBottom())
                    .alpha(0)
                    .setDuration(1000)
                    .setInterpolator(new DecelerateInterpolator());
            setVisibility(View.INVISIBLE);
        }


    }
}

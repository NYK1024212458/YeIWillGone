package com.kunkun.forlove.formyself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.kunkun.forlove.formyself.R;


/**
 * SwipeFling底部区域
 *
 * @zc
 */
public class SwipeFlingBottomLayout extends LinearLayout implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comeback:
                onComeBackClick();
                break;

            case R.id.superlike:
                onSuperLikeClick();
                break;

            case R.id.like:
                onLikeClick();
                break;

            case R.id.unlike:
                onUnLikeClick();
                break;

        }

    }
    public void onComeBackClick() {
        if (mListener != null) {
            mListener.onComeBackClick();
        }
    }
    public void onSuperLikeClick() {
        if (mListener != null) {
            mListener.onSuperLikeClick();
        }
    }

    public void onLikeClick() {
        if (mListener != null) {
            mListener.onLikeClick();
        }
    }


    public void onUnLikeClick() {
        if (mListener != null) {
            mListener.onUnLikeClick();
        }
    }
    public final static int ANIMATION_DURATION = 50;  // animation_duraction  50


    private BottomSwitchView mComeBackView;


    private BottomSwitchView mSuperLikeView;


    private BottomSwitchView mLikeView;


    private BottomSwitchView mUnlikeView;


    private OnBottomItemClickListener mListener;

    public SwipeFlingBottomLayout(Context context) {
        super(context);
    }

    public SwipeFlingBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {  // 绑定布局
        super.onFinishInflate();

        initView();
        //mComeBackView.setEnabled(false);
    }

    private void initView() {
        findViewById(R.id.like).setOnClickListener(this);
        findViewById(R.id.unlike).setOnClickListener(this);
        findViewById(R.id.superlike).setOnClickListener(this);
        findViewById(R.id.comeback).setOnClickListener(this);
    }

    public void show(int delay) {
        show(mLikeView, delay);
        show(mUnlikeView, delay);
        show(mComeBackView, delay);
        show(mSuperLikeView, delay);
    }

    public void hide() {
        int delay = 0;
        hide(mLikeView, delay);
        hide(mUnlikeView, delay);
        hide(mComeBackView, delay);
        hide(mSuperLikeView, delay);
    }

    private void show(View view, int delay) {
        view.animate()
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(delay)
                .alpha(1);
    }

    private void hide(View view, int delay) {
        view.animate()
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(delay)
                .alpha(0);
    }


    public BottomSwitchView getLikeView() {
        return mLikeView;
    }

    public BottomSwitchView getUnLikeView() {
        return mUnlikeView;
    }

    public BottomSwitchView getSuperLikeView() {
        return mSuperLikeView;
    }

    public void setEnableComeback(boolean isEnable) {
        mComeBackView.setEnabled(isEnable);
    }

    public void setEnableSuperLike(boolean isEnable) {
        mSuperLikeView.setEnabled(isEnable);
    }

    public boolean isEnableComeback() {
        return mComeBackView.isEnabled();
    }

    public boolean isEnableSuperLike() {
        return mSuperLikeView.isEnabled();
    }

    public void setComebackClickable(boolean clickable) {
        mComeBackView.setClickable(clickable);
    }

    public void setOnBottomItemClickListener(OnBottomItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnBottomItemClickListener {
        public void onComeBackClick();

        public void onSuperLikeClick();

        public void onLikeClick();

        public void onUnLikeClick();
    }

}

package com.kunkun.forlove.formyself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * com.kunstudy.videoforlogin.view
 * <p>
 * Created by ${kun}
 * 2017/6/27
 */

public class KunVideoView extends VideoView {
    public KunVideoView(Context context) {
        super(context);
        initView();
    }

    public KunVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public KunVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

/*
        //我们重新计算高度
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);*/

        int defaultSize = getDefaultSize(0, widthMeasureSpec);

        int defaultSize1 = getDefaultSize(0, heightMeasureSpec);

        setMeasuredDimension(defaultSize,defaultSize1);


    }
}

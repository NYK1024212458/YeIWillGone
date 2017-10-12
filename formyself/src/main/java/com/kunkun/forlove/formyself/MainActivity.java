package com.kunkun.forlove.formyself;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.kunkun.forlove.formyself.adapter.UserAdapter;
import com.kunkun.forlove.formyself.utils.T;
import com.kunkun.forlove.formyself.utils.tantan.BaseModel;
import com.kunkun.forlove.formyself.utils.tantan.CardEntity;
import com.kunkun.forlove.formyself.utils.tantan.RetrofitHelper;
import com.kunkun.forlove.formyself.view.BottomSwitchView;
import com.kunkun.forlove.formyself.view.SwipeFlingBottomLayout;
import com.zc.swiple.SwipeFlingView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/10/11.
 */
public class MainActivity extends AppCompatActivity implements SwipeFlingView.onSwipeListener,
        SwipeFlingView.OnItemClickListener, SwipeFlingBottomLayout.OnBottomItemClickListener {


    private SwipeFlingBottomLayout mSwipeFlingBottomLayout;

    private SwipeFlingView mSwipeFlingView;


    private Context mContext;


    private UserAdapter mAdapter;

    private boolean mIsRequestGirlList;
    private ArrayList<CardEntity> mGrilList = new ArrayList<>();  // 存放的是包含图片url的集合
    private int mPageIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        //  开始设置

        initdata();

        //初始化view
        initview();


    }

    private void initview() {

        // 沉浸式的实现
        ImmersionBar.with(this).init();


        mSwipeFlingView = (SwipeFlingView) findViewById(R.id.frame);

        mAdapter = new UserAdapter(mContext, mGrilList);
        mSwipeFlingView.setAdapter(mAdapter);

        mSwipeFlingView.setFlingListener(this);
        mSwipeFlingView.setOnItemClickListener(this);

        // 获取底部的按钮
        mSwipeFlingBottomLayout = (SwipeFlingBottomLayout) findViewById(R.id.swipe_fling_bottom);

        //  直接设置点击事件

        mSwipeFlingBottomLayout.setOnBottomItemClickListener(this);


    }


    private void initdata() {

        // 获取数据
        requestGirlList();
    }

    private void requestGirlList() {
        if (mIsRequestGirlList) {
            return;
        }
        mIsRequestGirlList = true;
        Call<BaseModel<ArrayList<CardEntity>>> call = RetrofitHelper.api().getGirlList(mPageIndex);
        RetrofitHelper.call(call, new RetrofitHelper.ApiCallback<ArrayList<CardEntity>>() {
            @Override
            public void onLoadSucceed(ArrayList<CardEntity> result) {
                updateListView(result);
                ++mPageIndex;
                mIsRequestGirlList = false;
            }

            @Override
            public void onLoadFail(int statusCode) {
                mIsRequestGirlList = false;
                Toast.makeText(mContext, "API服务器请求失败,使用默认测试数据填充", Toast.LENGTH_LONG).show();
                // addTestData();
            }

            @Override
            public void onForbidden() {
                mIsRequestGirlList = false;
            }
        });
    }

    /*private void addTestData() {
        updateListView(TestData.getApiData(mContext));
    }
*/
    private void updateListView(ArrayList<CardEntity> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        mGrilList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }


    //  下面都是继承的监听的方法

    @Override
    public void onStartDragCard() {

    }

    @Override
    public boolean canLeftCardExit() {
        return true;
    }

    @Override
    public boolean canRightCardExit() {
        return true;
    }

    @Override
    public void onPreCardExit() {

    }

    @Override
    public void onLeftCardExit(View view, Object o, boolean b) {

    }

    @Override
    public void onRightCardExit(View view, Object o, boolean b) {

    }

    @Override
    public void onSuperLike(View view, Object o, boolean b) {

    }

    @Override
    public void onTopCardViewFinish() {

    }

    @Override
    public void onAdapterAboutToEmpty(int i) {
        requestGirlList();

    }

    @Override
    public void onAdapterEmpty() {

    }

    @Override
    public void onScroll(View view, float v) {

    }

    @Override
    public void onEndDragCard() {

    }


    @Override
    public void onItemClicked(int i, Object o) {

    }


    //  下面的方法是实现的是 SwipeFlingBottomLayout
    @Override
    public void onComeBackClick() {
        //参数决定动画开始位置是从左边还是右边出现
        mSwipeFlingView.selectComeBackCard(true);

        Toast.makeText(mContext, "点击的是comback按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuperLikeClick() {
        if (mSwipeFlingView.isAnimationRunning()) {
            return;
        }
        mSwipeFlingView.selectSuperLike(true);

        Toast.makeText(mContext, "点击的是onSuperLike按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLikeClick() {
        if (mSwipeFlingView.isAnimationRunning()) {
            return;
        }
        mSwipeFlingView.selectRight(true);
        Toast.makeText(mContext, "点击的是onlick按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnLikeClick() {
        if (mSwipeFlingView.isAnimationRunning()) {
            return;
        }
        mSwipeFlingView.selectLeft(true);
        Toast.makeText(mContext, "点击的是unlike按钮", Toast.LENGTH_SHORT).show();
    }

}

package com.kunkun.forlove.formyself.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.kunkun.forlove.formyself.R;
import com.kunkun.forlove.formyself.utils.tantan.CardEntity;
import com.kunkun.forlove.formyself.utils.tantan.ImageLoaderHandler;
import com.kunkun.forlove.formyself.view.CardImageView;
import com.kunkun.forlove.formyself.view.CardLayout;
import com.kunkun.forlove.formyself.view.SwipeIndicatorView;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * card适配器
 *
 * @zc
 */
public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<CardEntity> mList;

    public UserAdapter(Context context, ArrayList<CardEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.swipe_fling_item, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        CardEntity cardEntity = ((CardEntity) getItem(position)); // 获取实体

        holder.likeIndicator.reset();
        holder.unLikeIndicator.reset();

        holder.nameView.setText(cardEntity.who);
        holder.addressView.setText(cardEntity.desc);

        holder.img.reset();
        holder.img.setUser(cardEntity);

        // 这才是真真的加载图片的工具类!
        ImageLoaderHandler.get().loadCardImage((Activity) mContext, holder.img, null, cardEntity.url, false);

        return convertView;
    }

    static class ViewHolder {
        CardLayout cardLayout;
        CardImageView img;
        TextView nameView;
        TextView addressView;
        SwipeIndicatorView likeIndicator;
        SwipeIndicatorView unLikeIndicator;
        TextView mFriendCountTv;
        TextView mInterestCountTv;
        ViewGroup mBottomLayout;

        ViewHolder(View rootView) {
            cardLayout = (CardLayout) rootView;  //实质就是一个framelayout
            img = ButterKnife.findById(rootView, R.id.item_img);   //  cardimageview

            nameView = ButterKnife.findById(rootView, R.id.item_name);
            addressView = ButterKnife.findById(rootView, R.id.item_address);

            likeIndicator = ButterKnife.findById(rootView, R.id.item_swipe_like_indicator);
            unLikeIndicator = ButterKnife.findById(rootView, R.id.item_swipe_unlike_indicator);

            mFriendCountTv = ButterKnife.findById(rootView, R.id.item_friend_count);
            mInterestCountTv = ButterKnife.findById(rootView, R.id.item_interest_count);

            mBottomLayout = ButterKnife.findById(rootView, R.id.item_bottom_layout);
        }

        @Override
        public String toString() {
            return "[Card:" + nameView.getText() + "]";
        }
    }

}

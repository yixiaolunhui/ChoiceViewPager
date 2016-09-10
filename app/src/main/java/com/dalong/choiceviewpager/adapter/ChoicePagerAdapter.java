package com.dalong.choiceviewpager.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.choiceviewpager.R;
import com.dalong.choiceviewpager.entity.Card;
import com.dalong.viewpagerlib.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * viewpager 适配器
 * Created by zhouweilong on 16/9/10.
 */

public class ChoicePagerAdapter extends PagerAdapter  implements ChoiceInterface {

    //数据list
    public List<Card> mCards;
    //view集合
    public List<CardView> mCardViews;
    //上下文
    public Context mContext;
    //cardview基本高度
    private float mBaseElevation;

    public ChoicePagerAdapter(List<Card> mCards, Context mContext) {
        this.mCards = mCards;
        this.mContext = mContext;
        mCardViews = new ArrayList<>();
        for (int i = 0; i < mCards.size(); i++) {
            mCardViews.add(null);
        }
    }


    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public CardView getCardView(int position) {
        return mCardViews.get(position);
    }

    @Override
    public float getBaseCardElevation() {
        return mBaseElevation;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.view_cardview_item, container, false);
        container.addView(view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        CircleImageView mIcon = (CircleImageView) view.findViewById(R.id.card_icon);
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) mOnItemClickListener.onClick(position);
            }
        });
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mCardViews.set(position, cardView);
        final Card card = mCards.get(position);
        mIcon.setImageResource(card.getImg());
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mCardViews.set(position, null);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}

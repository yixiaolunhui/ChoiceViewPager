package com.dalong.choiceviewpager;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.dalong.choiceviewpager.adapter.ChoiceInterface;
import com.dalong.viewpagerlib.view.ViewPager;

/**
 * Created by zhouweilong on 16/9/10.
 */
public class ChoiceTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private  float mScale = 0.4f;//缩放比例  这里缩放是中间比两边大0.2  也就是说两边是你设定的值  中间缩放到了1.2
    private  float mAlpha = 1f;//左右透明度
    private ViewPager mViewPager;
    private TextView mName;
    private ChoiceInterface mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled;
    private boolean mAlphaEnabled;

    public ChoiceTransformer(ViewPager viewPager, ChoiceInterface adapter, TextView name) {
        mViewPager = viewPager;
        mAdapter = adapter;
        mName = name;
        viewPager.setOnPageChangeListener(this);
    }

    /**
     * 设置缩放
     * @param mScale
     * @param isScale
     */
    public void setScale(float mScale,boolean isScale){
        this.mScale=mScale;
        setCanScale(isScale);
    }

    /**
     * 设置透明度
     * @param mAlpha
     * @param isAlpha
     */
    public void setAlpha(float mAlpha,boolean isAlpha){
        this.mAlpha=mAlpha;
        setCanAlpha(isAlpha);
    }


    /**
     * 设置是否缩放
     * @param isCanScale
     */
    public void setCanScale(boolean isCanScale) {
        if (!isCanScale) {
            // 不设置缩放的时候就把中间的选择的设置为1
            CardView currentCard = mAdapter.getCardView(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        }else{
            // 设置缩放就设置当前选择的缩放为1+缩放值
            CardView currentCard = mAdapter.getCardView(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1+mScale);
                currentCard.animate().scaleX(1+mScale);
            }
        }

        mScalingEnabled = isCanScale;
    }

    /**
     * 设置是否透明度
     * @param isCanAlpha
     */
    public void setCanAlpha(boolean isCanAlpha) {
        if (!isCanAlpha) {
            // 不设置透明度的时候吧所有的view都设置为1 不透明
            for(int i=0;i<mAdapter.getCount();i++){
                CardView cardView=mAdapter.getCardView(i);
                if (cardView != null) {
                    cardView.setAlpha(1f);
                }
            }

        }else {
            // 当设置了透明度 把中间的选择的view设置为1,其他的设置指定的透明度
            for(int i=0;i<mAdapter.getCount();i++){
                CardView cardView=mAdapter.getCardView(i);
                if(i==mViewPager.getCurrentItem()){
                    if (cardView != null) {
                        cardView.setAlpha(1f);
                    }
                }else{
                    if (cardView != null) {
                        cardView.setAlpha(mAlpha);
                    }
                }
            }
        }
        mAlphaEnabled = isCanAlpha;
    }


    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseCardElevation();
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        CardView currentCard = mAdapter.getCardView(realCurrentPosition);
        if (currentCard != null) {
            //当设置可以缩放 按指定缩放设置
            if (mScalingEnabled) {
                currentCard.setScaleX(1 + mScale * (1 - realOffset));
                currentCard.setScaleY(1 + mScale * (1 - realOffset));
            }
            //当设置可以设置透明度的时候设置透明度
            if(mAlphaEnabled){
                currentCard.setAlpha(1-realOffset+mAlpha);
            }
            currentCard.setCardElevation((baseElevation + baseElevation
                    * (ChoiceInterface.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextCard = mAdapter.getCardView(nextPosition);

        if (nextCard != null) {
            //当设置可以缩放 按指定缩放设置
            if (mScalingEnabled) {
                nextCard.setScaleX(1 + mScale * (realOffset));
                nextCard.setScaleY(1 + mScale * (realOffset));
            }
            //当设置可以设置透明度的时候设置透明度
            if(mAlphaEnabled){
                nextCard.setAlpha(realOffset+mAlpha);
            }
            nextCard.setCardElevation((baseElevation + baseElevation
                    * (ChoiceInterface.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }
        //记录最后的
        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        mName.setText(""+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

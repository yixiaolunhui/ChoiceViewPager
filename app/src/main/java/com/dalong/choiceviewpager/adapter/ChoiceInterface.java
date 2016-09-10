package com.dalong.choiceviewpager.adapter;

import android.support.v7.widget.CardView;

/**
 *
 * Created by zhouweilong on 16/9/10.
 */

public interface ChoiceInterface {
    /**
     * 选择的数量
     * @return
     */
    int  getCount();

    /**
     * 根据position 获取对应的view  这里使用cardView父布局 也可以换成别的
     * @param position
     * @return
     */
    CardView getCardView(int position);

    /**
     * 获取对应view(cardView)的默认高度 这里使用cardview自带的方法命名便于理解
     * @return
     */
    float getBaseCardElevation();


    /**
     * cardview的最大高的因数  setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
     */
    int MAX_ELEVATION_FACTOR = 5;
}

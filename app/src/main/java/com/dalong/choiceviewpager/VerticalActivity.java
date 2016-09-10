package com.dalong.choiceviewpager;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dalong.choiceviewpager.adapter.ChoicePagerAdapter;
import com.dalong.choiceviewpager.entity.Card;
import com.dalong.viewpagerlib.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 竖直的选择viewpager
 * Created by zhouweilong on 16/9/10.
 */
public class VerticalActivity extends AppCompatActivity {
    private List<Card> mlist=new ArrayList<>();
    private int[] mImgs ={R.mipmap.meinv1,R.mipmap.meinv2,R.mipmap.meinv3,R.mipmap.meinv4,R.mipmap.meinv5};

    private ViewPager viewpager1,viewpager2,viewpager3;
    private TextView name1,name2,name3;
    private ChoicePagerAdapter mCardAdapter,mCardAdapter2,mCardAdapter3;
    private ChoiceTransformer mCardTransformer,mCardTransformer2,mCardTransformer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        mCardAdapter.setOnItemClickListener(new ChoicePagerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                viewpager1.setCurrentItem(position);
            }
        });
        mCardAdapter2.setOnItemClickListener(new ChoicePagerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                viewpager2.setCurrentItem(position);
            }
        });
        mCardAdapter3.setOnItemClickListener(new ChoicePagerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                viewpager3.setCurrentItem(position);
            }
        });


    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i=0;i<mImgs.length;i++){
            Card item=new Card();
            item.setImg(mImgs[i]);
            item.setName(i+"km");
            mlist.add(item);
        }

    }
    private void initView() {
        viewpager1=(ViewPager)findViewById(R.id.viewpager1);
        viewpager2=(ViewPager)findViewById(R.id.viewpager2);
        viewpager3=(ViewPager)findViewById(R.id.viewpager3);
        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);
        name3=(TextView)findViewById(R.id.name3);


        //1
        mCardAdapter = new ChoicePagerAdapter(mlist,this);
        mCardTransformer = new ChoiceTransformer(viewpager1, mCardAdapter,name1);
        viewpager1.setAdapter(mCardAdapter);
        viewpager1.setPageTransformer(false, mCardTransformer);
        viewpager1.setOffscreenPageLimit(4);

        //2
        mCardAdapter2 = new ChoicePagerAdapter(mlist,this);
        mCardTransformer2 = new ChoiceTransformer(viewpager2, mCardAdapter2,name2);
        viewpager2.setAdapter(mCardAdapter2);
        viewpager2.setPageTransformer(false, mCardTransformer2);
        viewpager2.setOffscreenPageLimit(4);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCardTransformer2.setScale(0.2f,true);
            }
        },1000);

        //2
        mCardAdapter3 = new ChoicePagerAdapter(mlist,this);
        mCardTransformer3 = new ChoiceTransformer(viewpager3, mCardAdapter3,name3);
        viewpager3.setAdapter(mCardAdapter3);
        viewpager3.setPageTransformer(false, mCardTransformer3);
        viewpager3.setOffscreenPageLimit(4);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCardTransformer3.setAlpha(0.4f,true);
            }
        },1000);

    }
    Handler mHandler=new Handler();
}

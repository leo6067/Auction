package com.leo.auction.ui.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.leo.auction.R;
import com.leo.auction.common.adapter.FriendsPagerAdapter;
import com.leo.auction.common.other.RotationPageTransformer;


public class ViewPagerActivity extends Activity {

    private FriendsPagerAdapter mPagerAdapter;



    int[] drawIds = {R.drawable.logo,R.drawable.qq_a};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);

//        Bundle bundle = getIntent().getExtras();
//        final String[] strs = bundle.getStringArray("str");
//        Globals.log("log xwj strs "+strs[0]);




        mPagerAdapter = new FriendsPagerAdapter(drawIds,this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true,new RotationPageTransformer());
        mViewPager.setOffscreenPageLimit(2);//设置预加载的数量，这里设置了2,会预加载中心item左边两个Item和右边两个Item
        mViewPager.setPageMargin(10);//设置两个Page之间的距离

    }
}

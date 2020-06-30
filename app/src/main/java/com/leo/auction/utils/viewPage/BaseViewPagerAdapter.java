package com.leo.auction.utils.viewPage;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.base
 * Created by 彭俊鸿 on 2018/5/23.
 * e-mail : 1031028399@qq.com
 * 复用的adapter
 */

public abstract class BaseViewPagerAdapter extends FragmentStatePagerAdapter {

    private int fragmentsSize;

    public BaseViewPagerAdapter(FragmentManager fm, int fragmentsSize) {
        super(fm);
        this.fragmentsSize = fragmentsSize;
    }

    @Override
    public int getCount() {
        return fragmentsSize;
    }

    @Override
    public abstract Fragment getItem(int arg0);

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        try{
            super.finishUpdate(container);
        } catch (NullPointerException nullPointerException){
            System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
        }
    }
}

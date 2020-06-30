package com.leo.auction.utils.viewPage;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/5/23
 * 描    述：ViewPager适配器基类(防止内存泄漏)
 * ================================================
 */
public abstract class BaseViewPagerAndTabsAdapter extends FragmentStatePagerAdapter {

    private List<String> data;

    public BaseViewPagerAndTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<String> data){
        if(data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    @Override
    public abstract Fragment getItem(int position);

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        try{
            super.finishUpdate(container);
        } catch (NullPointerException nullPointerException){
            System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
        }
    }
}

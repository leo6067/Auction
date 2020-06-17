package com.aten.compiler.widget.tabLayout.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentChangeManager {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;
    /** Fragment切换数组 */
    private ArrayList<Fragment> mFragments;
    /** 当前选中的Tab */
    private int mCurrentTab=-1;

    public FragmentChangeManager(FragmentManager fm, int containerViewId, ArrayList<Fragment> fragments) {
        this.mFragmentManager = fm;
        this.mContainerViewId = containerViewId;
        this.mFragments = fragments;
        initFragments();
    }

    /** 初始化fragments */
    private void initFragments() {
        for (Fragment fragment : mFragments) {
            mFragmentManager.beginTransaction().add(mContainerViewId, fragment).hide(fragment).commit();
        }

        setFragments(0);
    }

    /** 界面切换控制 */
    public void setFragments(int index) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mCurrentTab!=-1){
            Fragment fragment1 = mFragments.get(mCurrentTab);
            ft.hide(fragment1);
        }

        Fragment fragment2 = mFragments.get(index);
        ft.show(fragment2);
        ft.commit();
//        for (int i = 0; i < mFragments.size(); i++) {
//            FragmentTransaction ft = mFragmentManager.beginTransaction();
//            Fragment fragment = mFragments.get(i);
//            if (i == index) {
//                ft.show(fragment);
//            } else {
//                ft.hide(fragment);
//            }
//            ft.commit();
//        }
        mCurrentTab = index;
    }

    public int getCurrentTab() {
        return mCurrentTab;
    }

    public Fragment getCurrentFragment() {
        return mFragments.get(mCurrentTab);
    }
}
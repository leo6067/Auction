package com.aten.compiler.widget.costomBottomTab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aten.compiler.R;
import com.aten.compiler.widget.glide.GlideUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/5/18
 * 描    述：首页底部tab封装 (可以加载网络图片)
 * Android官方底部Tab栏设计规范
 *
 * 1、推荐底部可以放置3到5个。
 * 2、推荐选中的图标或者文字为APP的主色调，如果Tab栏本身就是彩色，推荐黑色和白色作为图标或者文字
 * 3、选中的Tab同时显示icon和text。
 * 如果只有三个Tab，无论选中未选中，一直显示icon和文字。
 * 如果有四到五个Tab，选中的Tab显示文字和icon，未选中的Tab只显示icon
 * 4、文字要求言简意赅
 * 5、bars的高度推荐为56dp，icon的尺寸为24*24，这种Google一般推荐使用8的倍数。选中tab的字体大小为14sp,未选中为12sp
 *
 * ================================================
 */

public class CustomBottomTabLayout extends LinearLayout {
    //上下文
    private Context mContext;
    //底部tab数据
    private List<BottomTabModel> mBottomTabs = new ArrayList<>();
    //底部所有itemViews
    private List<View> mBottomTabViews = new ArrayList<>();
    //当前选择的tab index
    private int currentTab;
    //tab切换回调接口
    private OnTabChangeListener mOnTabChangeListener;

    public CustomBottomTabLayout(Context context) {
        this(context, null);
    }

    public CustomBottomTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBottomTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

    //设置tab数据
    public void setBottomTabData(List<BottomTabModel> mBottomTabs) {
        if (mBottomTabs == null || mBottomTabs.size() == 0) {
            return;
        }
        this.mBottomTabViews.clear();
        //清除所有的子view
        removeAllViews();
        this.mBottomTabs = mBottomTabs;
        initTabViews();
    }

    //初始化tabviews
    public void initTabViews() {
        View tabView;
        for (int index = 0; index < mBottomTabs.size(); index++) {
            tabView = LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_tab, null);
            tabView.setTag(index);
            addTabView(index, tabView);
            mBottomTabViews.add(tabView);
        }
        setCurrentTab(0);
    }

    //添加tabview
    private void addTabView(final int index, View tabView) {
        TextView mTabName = (TextView) tabView.findViewById(R.id.tab_item_name);
        ImageView mTabIcon = (ImageView) tabView.findViewById(R.id.tab_item_icon);

        int tabUnSelectIcon = mBottomTabs.get(index).getTabUnSelectIcon();
        String tabUnSelectUrl = mBottomTabs.get(index).getTabUnSelectUrl();
        String tabSelectUrl = mBottomTabs.get(index).getTabSelectUrl();

        //不显示该图片以及文字
        if ("empty".equals(mBottomTabs.get(index).getTabName())) {
            mTabName.setText("");
        } else {
            mTabName.setText(mBottomTabs.get(index).getTabName());
        }

        //如果网络图片中选中或者不选中的图片其中之一为空都使用本地图片
        if ("empty".equals(mBottomTabs.get(index).getTabName())) {
            mTabIcon.setImageResource(0);
        } else if (TextUtils.isEmpty(tabUnSelectUrl) || TextUtils.isEmpty(tabSelectUrl)) {
            mTabIcon.setImageResource(tabUnSelectIcon);
        } else {
            GlideUtils.loadImg(tabUnSelectUrl,mTabIcon,tabUnSelectIcon,tabUnSelectIcon,DiskCacheStrategy.ALL,null);
        }

        //设置tab点击事件 其中多加了一个重复点击的回调 为了有的需求是点击多次也要刷新的奇葩需求
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (!"empty".equals(mBottomTabs.get(index).getTabName())) {
                    if (currentTab != position) {
                        if (mOnTabChangeListener != null) {
                            boolean tabCanSelect = mOnTabChangeListener.onTabSelect(position);
                            if (tabCanSelect){
                                setCurrentTab(position);
                            }
                        }
                    } else {
                        if (mOnTabChangeListener != null) {
                            mOnTabChangeListener.onTabSelected(position);
                        }
                    }
                }
            }
        });
        LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        addView(tabView, index, params);
    }

    //设置选中
    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
        updateTabState(currentTab);
    }

    //更新选中的和未选中的样式
    private void updateTabState(int currentTab) {
        for (int index = 0; index < mBottomTabs.size(); index++) {
            View tabView = getChildAt(index);
            final boolean isSelect = index == currentTab;
            BottomTabModel mBottomTab = mBottomTabs.get(index);
            TextView mTabName = (TextView) tabView.findViewById(R.id.tab_item_name);
            ImageView mTabIcon = (ImageView) tabView.findViewById(R.id.tab_item_icon);

            //根据判别是否是选择的哪一个来设置对应的颜色值
            mTabName.setTextColor(isSelect ? getResources().getColor(mBottomTab.getTabNameSelectColor())
                    : getResources().getColor(mBottomTab.getTabNameUnSelectColor()));

            //判别网络图片url是不是为空要是为空的话就是使用本地的图片如果不为空就用网络图片  默认加载的图片为对应的本地图片 避免加载空白问题
            if (TextUtils.isEmpty(mBottomTab.getTabSelectUrl()) || TextUtils.isEmpty(mBottomTab.getTabUnSelectUrl())) {
                mTabIcon.setImageResource(isSelect ? mBottomTab.getTabSelectIcon() : mBottomTab.getTabUnSelectIcon());
            } else {
                GlideUtils.loadImg(isSelect ? mBottomTab.getTabSelectUrl() : mBottomTab.getTabUnSelectUrl(),mTabIcon,
                        isSelect ? mBottomTab.getTabSelectIcon() : mBottomTab.getTabUnSelectIcon(),
                        isSelect ? mBottomTab.getTabSelectIcon() : mBottomTab.getTabUnSelectIcon());
            }
        }
    }

    //设置底部小红点的数量
    public void setBottomTabNum(int index, int num) {
        for (int i = 0; i < mBottomTabViews.size(); i++) {
            TextView mTabNum = (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            if (index == i && num > 0) {
                mTabNum.setVisibility(VISIBLE);
                mTabNum.setText(num >= 100 ? "99+" : String.valueOf(num));
            }
        }
    }

    //清除所有的小红点的数量
    public void clearAllBottomTabNum() {
        for (int i = 0; i < mBottomTabViews.size(); i++) {
            TextView mTabNum = (TextView) mBottomTabViews.get(i).findViewById(R.id.tab_item_num);
            mTabNum.setText("");
            mTabNum.setVisibility(GONE);
        }
    }

    //设置回调实现
    public void setOnTabChangeListener(OnTabChangeListener mOnTabChangeListener) {
        this.mOnTabChangeListener = mOnTabChangeListener;
    }

    public void setUpWithViewPager(final ViewPager viewPager) {
        this.setUpWithViewPager(viewPager,null);
    }


    //设置tab和viewpager的关联关系
    public void setUpWithViewPager(final ViewPager viewPager, final onUpWithViewPagerListener onUpWithViewPagerListener) {
        setOnTabChangeListener(new OnTabChangeListener() {
            @Override
            public boolean onTabSelect(int position) {
                if (onUpWithViewPagerListener==null){
                    viewPager.setCurrentItem(position);//设置当前显示标签页为第一页
                    return true;
                }else {
                    return onUpWithViewPagerListener.onTabSelect(position);
                }
            }

            @Override
            public void onTabSelected(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (onUpWithViewPagerListener==null){
                    setCurrentTab(position);
                }else {
                    onUpWithViewPagerListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
    //设置tab和viewpager的关联回调关系
    public interface onUpWithViewPagerListener{
        boolean onTabSelect(int position);
        void onPageSelected(int position);
    }

}

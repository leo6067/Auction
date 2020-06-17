package com.aten.compiler.widget.costomBottomTab;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/5/18
 * 描    述：首页底部tab选择回调类
 * ================================================
 */

public interface OnTabChangeListener {
    //tab选择回调
    boolean onTabSelect(int position);
    //tab重复选择回调
    void onTabSelected(int position);
}

package com.aten.compiler.widget.costomBottomTab;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/5/18
 * 描    述：首页底部tab对象
 * ================================================
 */

public class BottomTabModel {

    //tab名字
    public String tabName;
    //tab没有选择字体颜色
    public int tabNameUnSelectColor;
    //tab选择后的字体颜色
    public int tabNameSelectColor;
    //tab没有选择的本地icon
    public int tabUnSelectIcon;
    //tab选择后的本地icon
    public int tabSelectIcon;
    //tab没有选择的网络图片url
    public String tabUnSelectUrl;
    //tab选择的网络图片url
    public String tabSelectUrl;

    public BottomTabModel() {
    }

    public BottomTabModel(String tabName, int tabNameUnSelectColor, int tabNameSelectColor, int tabUnSelectIcon, int tabSelectIcon, String tabUnSelectUrl, String tabSelectUrl) {
        this.tabName = tabName;
        this.tabNameUnSelectColor = tabNameUnSelectColor;
        this.tabNameSelectColor = tabNameSelectColor;
        this.tabUnSelectIcon = tabUnSelectIcon;
        this.tabSelectIcon = tabSelectIcon;
        this.tabUnSelectUrl = tabUnSelectUrl;
        this.tabSelectUrl = tabSelectUrl;
    }


    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getTabNameUnSelectColor() {
        return tabNameUnSelectColor;
    }

    public void setTabNameUnSelectColor(int tabNameUnSelectColor) {
        this.tabNameUnSelectColor = tabNameUnSelectColor;
    }

    public int getTabNameSelectColor() {
        return tabNameSelectColor;
    }

    public void setTabNameSelectColor(int tabNameSelectColor) {
        this.tabNameSelectColor = tabNameSelectColor;
    }

    public int getTabUnSelectIcon() {
        return tabUnSelectIcon;
    }

    public void setTabUnSelectIcon(int tabUnSelectIcon) {
        this.tabUnSelectIcon = tabUnSelectIcon;
    }

    public int getTabSelectIcon() {
        return tabSelectIcon;
    }

    public void setTabSelectIcon(int tabSelectIcon) {
        this.tabSelectIcon = tabSelectIcon;
    }

    public String getTabUnSelectUrl() {
        return tabUnSelectUrl;
    }

    public void setTabUnSelectUrl(String tabUnSelectUrl) {
        this.tabUnSelectUrl = tabUnSelectUrl;
    }

    public String getTabSelectUrl() {
        return tabSelectUrl;
    }

    public void setTabSelectUrl(String tabSelectUrl) {
        this.tabSelectUrl = tabSelectUrl;
    }
}

package com.aten.compiler.widget.banner.animation;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * ================================================
 * 项目名称：BannerLayout
 * 包   名：com.bannerlayout.animation
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/1/30
 * 描   述：仿魅族一屏多图的效果
 * ================================================
 */
public class MeizuBannerTransformer extends BannerTransformer{
    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1){
            page.setScaleY(0.8f);
        }else if (position <= 1){
            page.setScaleY(Math.max(0.8f, 1 - Math.abs(position)));
        }else {
            page.setScaleY(0.8f);
        }
    }
}

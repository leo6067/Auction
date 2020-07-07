package com.leo.auction.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aten.compiler.utils.SizeUtils;
import com.gyf.immersionbar.ImmersionBar;

import java.lang.reflect.Method;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.dgonline_android.utils
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/1/4
 * 描   述：
 * ================================================
 */
public class SafePwdBoardUtils {
    private int scrolldis = 0;	//输入框在键盘被弹出时，要被推上去的距离
    public static int screenh=-1;
    public static int screenh_nonavbar=-1;	//不包含导航栏的高度
    public static int real_scontenth =-1;	//实际内容高度，  计算公式:屏幕高度-导航栏高度-电量栏高度
    public static float density = 1.0f;
    public static int densityDpi = 160;
    private View mDecorView;
    private View mContentView;
    private View view;

    //初始化屏幕的一些基本信息
    public void initScreenParams(final Activity activity, View view){
        this.view=view;
        view.post(new Runnable() {
            @Override
            public void run() {
                mDecorView =activity.getWindow().getDecorView();
                mContentView =activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);

                DisplayMetrics dMetrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();
                display.getMetrics(dMetrics);

                screenh = dMetrics.heightPixels;
                density = dMetrics.density;
                densityDpi = dMetrics.densityDpi;
                screenh_nonavbar = screenh;
                int ver = Build.VERSION.SDK_INT;
                // 新版本的android 系统有导航栏，造成无法正确获取高度
                if (ver == 13){
                    try {
                        Method mt = display.getClass().getMethod("getRealHeight");
                        screenh_nonavbar = (Integer)mt.invoke(display);
                    }catch (Exception e){
                    }
                } else if (ver > 13){
                    try{
                        Method mt = display.getClass().getMethod("getRawHeight");
                        screenh_nonavbar = (Integer)mt.invoke(display);
                    }catch (Exception e){
                    }
                }

                real_scontenth = screenh_nonavbar- ImmersionBar.getStatusBarHeight(activity);
            }
        });
    }

    //键盘弹出的时候 布局需要上移的高度
    public void boardShow(){
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] pos=new int[2];
                view.getLocationOnScreen(pos);
                float height= SizeUtils.dp2px(236);

                Rect outRect = new Rect();
                mDecorView.getWindowVisibleDisplayFrame(outRect);

                int screen=real_scontenth;
                scrolldis=(int)((pos[1]+view.getMeasuredHeight()-outRect.top)
                        -(screen-height)
                        + SizeUtils.dp2px(20));

                if(scrolldis>0){
                    mContentView.scrollBy(0, scrolldis);
                }
            }
        },100);
    }
    //键盘关闭的时候 布局需要下移的高度
    public void boardDiss(){
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(scrolldis>0){
                    int temp=scrolldis;
                    scrolldis=0;
                    if(null != mContentView){
                        mContentView.scrollBy(0, -temp);
                    }
                }
            }
        },100);
    }

}

package com.aten.compiler.base;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.arialyy.aria.core.Aria;
import com.aten.compiler.R;

import com.aten.compiler.utils.KeyboardUtils;
import com.aten.compiler.utils.NetworkUtils;

import com.aten.compiler.widget.loadingView.KProgressHUD;
import com.aten.compiler.widget.title.OnTitleBarListener;
import com.aten.compiler.widget.title.TitleBar;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Method;
import java.util.HashMap;

import butterknife.ButterKnife;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/5/13
 * 描    述：activity基类
 * ================================================
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG = this.getClass().getSimpleName();
    public KProgressHUD hud;//数据加载框空间
    protected TitleBar mTitleBar;
    protected ImmersionBar mImmersionBar;


    public HashMap<String, String> mHashMap = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断app状态
//        if (this instanceof WelcomeActivity) {
//            AppStatusManager.getInstance().setAppStatus(AppStatusManager.STATUS_NORMAL);
//        } else if (AppStatusManager.getInstance().getAppStatus() == AppStatusManager.STATUS_RECYCLE) {
//            // 非正常启动流程，直接重新初始化应用界面
////            showShortToast("app在后台已被系统回收，即将重启app。。。");
//            Intent intent = new Intent(BaseActivity.this, WelcomeActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            AppManager_Acivity.getInstance().finishAllActivity();
//            return;
//        }

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O || !isTranslucentOrFloating()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设定为竖屏
        }
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);//禁用系统截屏


        setContentViewLayout();
        ButterKnife.bind(this);
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("请开启网络!");
        }

        if (hasNeedTitle()) {
            initTitle();
        }
        initView();
        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        initData();
        initEvent();

        ActivityManager.addActivity(this);
        //友盟推送
        PushAgent.getInstance(this).onAppStart();
        //下载框架
        Aria.download(this).register();
    }

    //判断是否是透明
    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    //是否可以使用沉浸式
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)

                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
//                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//                .transparentStatusBar()
                .statusBarDarkFont(true)
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    //是否需要标题 默认为true
    public boolean hasNeedTitle() {
        return true;
    }

    //初始化头部title
    public void initTitle() {
        mTitleBar = (TitleBar) findViewById(com.aten.compiler.R.id.title_bar);
        if (mTitleBar == null) {
            return;
        }
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                leftTitleViewClick();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                rightTitleViewClick();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        ActivityManager.setCurrentActivity(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }


    @Override
    protected void onDestroy() {
        hideWaitDialog();
        setContentView(R.layout.layout_view_null);
        super.onDestroy();
        ActivityManager.removeActivity(this);
        //可以取消同一个tag的
        OkHttpUtils.getInstance().cancelTag(TAG);//取消以Activity.this作为tag的请求
    }

    //设置title的数据
    public void setTitle(String title) {
        if (mTitleBar != null) {
            mTitleBar.setTitle(title);
            mTitleBar.getTitleView().setTextColor(Color.parseColor("#000000"));
        }
    }

    //title左侧点击事件
    public void leftTitleViewClick() {
        onBackPressed();
        KeyboardUtils.hideSoftInput(mTitleBar);
    }

    //title右侧点击事件
    public void rightTitleViewClick() {
        ToastUtils.showShort("title右侧点击事件");
    }

    //title右侧控件显示
    public void showRightView() {
        if (mTitleBar != null) {
            mTitleBar.getRightView().setVisibility(View.VISIBLE);
        } else {
            ToastUtils.showShort("控件还未初始化!");
        }
    }

    //title右侧控件隐藏
    public void hideRightView() {
        if (mTitleBar != null) {
            mTitleBar.getRightView().setVisibility(View.GONE);
        } else {
            ToastUtils.showShort("控件还未初始化!");
        }
    }

    //设置title右侧控件文字
    public void setRightTitleView(String text) {
        if (mTitleBar != null) {
            mTitleBar.setRightTitle(text);
        }
    }

    //设置title右侧控件文字
    public void setRightIcon(Drawable drawable) {
        if (mTitleBar != null) {
            mTitleBar.setRightIcon(drawable);
        }
    }

    //显示数据加载框
    public void showWaitDialog() {
        showWaitDialog(null);
    }

    public KProgressHUD getHud() {
        return hud;
    }

    //显示数据加载框
    public void showWaitDialog(String tip) {
        if (isFinishing()) {
            return;
        }

        if (hud == null) {
            hud = KProgressHUD.create(BaseActivity.this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        }

        if (!TextUtils.isEmpty(tip)) {
            hud.setLabel(tip);
        }
        hud.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hud != null && hud.isShowing()) {
                    hud.dismiss();
                    hud = null;
                }
            }
        },6000);

    }

    //判断数据加载框是否显示
    public boolean isWaitShow() {
        return hud.isShowing();
    }

    //隐藏数据加载框
    public void hideWaitDialog() {
        if (hud != null && hud.isShowing()) {
            hud.dismiss();
            hud = null;
        }
    }


    //在界面初始化之前需要做的一些判断 可以重写改方法
    public boolean FirstOnCreate() {
        return false;
    }

    //获取布局的layout
    public abstract void setContentViewLayout();

    //初始化布局
    public void initView() {
    }

    //初始化数据
    public void initData() {
    }

    //初始化监听事件
    public void initEvent() {
    }

    @Override
    public void onClick(View v) {
    }


    public void showShortToast(String string) {
        ToastUtils.showShort(string);
    }

    protected void closeSoftInput() {
        KeyboardUtils.hideSoftInput(this);
    }


    //关闭页面
    public void goFinish() {
        onBackPressed();
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}

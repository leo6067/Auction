package com.leo.auction.ui.main;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.blankj.utilcode.util.ScreenUtils;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.common.widget.slidingMenu.SlidingMenu;


public class SlidingMainActivity extends AppCompatActivity {

    private double exitTime;

    private SlidingMenu slidingMenu;


    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_main);
        ScreenUtils.setPortrait(this);
        ActivityManager.addActivity(this);
//        ActivityManager.slidingMainActivity = this;


        //加载侧滑菜单界面
        initSlidingMenu();
    }


    private void initSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.RIGHT);//左/右侧滑出
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸监听
        slidingMenu.setBehindOffset(150);
        //设置滑动时菜单的是否淡入淡出
        slidingMenu.setFadeEnabled(false);
        //设置淡入淡出的比例
        slidingMenu.setFadeDegree(0.5f);
        //设置滑动时拖拽效果:即slidingmenu的遮盖滑出效果
        slidingMenu.setBehindScrollScale(0);
        slidingMenu.setMenu(R.layout.include_nav_header_main);//侧滑布局
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);//添加到activity
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

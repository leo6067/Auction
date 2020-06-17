package com.leo.auction.tool.polling;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import com.leo.auction.utils.Globals;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Leo on 2018/7/9.
 */

public class OnlyService extends Service {

    private Timer mTimer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                bannerHttp();
            }
        };
        mTimer = new Timer();
        mTimer.schedule(task, 0, 10000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        Globals.log("log xwj service onDestroy");
    }

    void bannerHttp() {

//        HashMap<String, Object> hashMap = new HashMap<>();
//
//
//        if (BaseSharePerence.getInstance().getLoginInfo() !=null && BaseSharePerence.getInstance().getLoginInfo().toString().length() > 0) {
//            hashMap.put("cityid", BaseSharePerence.getInstance().getLoginInfo().getRegion());
//            hashMap.put("username", BaseSharePerence.getInstance().getLoginInfo().getUsername());
//
//
//                @Override
//                public void httpError(Call call, Exception e) {
//                }
//
//                @Override
//                public void httpResponse(String resultData) {
//
//                    ResultJson homeImgJson = JSONObject.parseObject(resultData, ResultJson.class);
//                    if (homeImgJson.getState() == 100) {
//                        disconnect();
//                    }
//                }
//            });
//        }


    }


    void disconnect() {

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        try {
            //屏幕是否亮屏：true 亮
            boolean screenOn = powerManager.isScreenOn();
            if (!screenOn) {
                PowerManager.WakeLock mWakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "SimpleTimer");
                //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                mWakeLock.acquire(500);//点亮
                mWakeLock.release();
            }

            //屏幕是否解锁： 锁着的时候返回true
            KeyguardManager mKeyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
            boolean flag = mKeyguardManager.inKeyguardRestrictedInputMode();
            if (flag) {
                //键盘锁管理器对象
                KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
                // 这里参数”kale”作为调试时LogCat中的Tag
                KeyguardManager.KeyguardLock kl = km.newKeyguardLock("kale");
                if (km.inKeyguardRestrictedInputMode()) {
                    // 解锁键盘
                    kl.disableKeyguard();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {

//            boolean topLogin = ActivityUtils.topActivity(this, LoginActivity.class.getName());
//            boolean topRegis = ActivityUtils.topActivity(this, RegisterActivity.class.getName());
//            boolean topMater = ActivityUtils.topActivity(this, MaterialActivity.class.getName());

//            boolean topNavigate = ActivityUtils.topActivity(this, NavigateActivity.class.getName());
//            if (topNavigate) {
//                ActivityManager.mNavigateActivity.onlineHttp();
//            } else {
//                Intent intent = new Intent(this, NavigateActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                BaseAppContext.getInstance().startActivity(intent);
//            }
        }
    }

}

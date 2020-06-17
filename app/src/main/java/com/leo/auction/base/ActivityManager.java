package com.leo.auction.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.utils.Globals;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Leo on 2018/2/6.
 */

public class ActivityManager {

    public static Activity mainActivity;



    public static void JumpActivity(Context a, Class b, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(a, b);
        a.startActivity(intent);
    }

    //
    public static void JumpActivity(Activity a, Class b, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(a, b);
        a.startActivity(intent);
    }

    public static void JumpActivity(Activity a, Class b) {
        Intent intent = new Intent();
        intent.setClass(a, b);
        a.startActivity(intent);
    }


    public static void JumpActivity(Context a, Class b) {
        Intent intent = new Intent();
        intent.setClass(a, b);
        a.startActivity(intent);
    }


    public static void JumpActivity(Activity a, Class b, Bundle bundle, int result) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(a, b);
        a.startActivityForResult(intent, result);
    }

    public static void JumpToTaobao(Context context, String strUri) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        boolean bFindTaobao = false;
        for (int i = 0; i < pinfo.size(); i++) {
            // 循环判断是否存在指定包名
            if (pinfo.get(i).packageName.equalsIgnoreCase("com.taobao.taobao")) {
                bFindTaobao = true;
                Globals.log("find taobao");
                break;
            }
        }
        if (bFindTaobao == false) return;
        Intent intent = new Intent();
        intent.setAction("Android.intent.action.VIEW");
        Uri uri = Uri.parse(strUri); // 商品地址
        intent.setData(uri);
        intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
        context.startActivity(intent);
    }


    public static void JumpQQ(Context context) {
        try {
//     第一种方式：是可以的跳转到qq主页面，不能跳转到qq聊天界面
            Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort(context, "请检查是否安装QQ");
        }
    }

    public static void JumpQQ(Context context, String qqNum) {
        try {
            //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum;//uin是发送过去的qq号码
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort(context, "请检查是否安装QQ");
        }
    }


    public static void JumpDY(Context context, String activityUrl) {
        String url = "snssdk1128://aweme/detail/" + activityUrl;
//        String url = "snssdk1128://user/profile/95627491133?refer=web&gd_label=click_wap_profile_bottom&type=need_follow&needlaunchlog=1";
//        String webStr = "https://www.iesdouyin.com/share/video/6657758842652331272/?region=CN&mid=6657423986114480910&u_code=f6mf8c3d&titleType=title&timestamp=1553154015&utm_campaign=client_share&app=aweme&utm_medium=ios&tt_from=copy&utm_source=copy&iid=66213395255";
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void JumpBrowser(Context context, String activityUrl) {
        Uri uri = Uri.parse(activityUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }


    public static void showActivity(Activity aty, Class clazz) {
        Intent i = new Intent(aty, clazz);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    public static void showActivity(Activity aty, Class clazz, String key, Serializable serialize) {
        Intent i = new Intent(aty, clazz);
        i.putExtra(key, serialize);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    public static void showActivity(Activity aty, Class clazz, Bundle bundle) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    public static void skipActivity(Activity aty, Class clazz) {
        Intent i = new Intent(aty, clazz);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        aty.finish();
    }

    public static void skipActivity(Activity aty, Class clazz, Bundle bundle) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        aty.finish();
    }

    public static void closeSelf(Activity aty) {
        aty.finish();
    }

    public static void showActivityForResult(Activity aty, int requestCode, Class clazz) {
        Intent i = new Intent(aty, clazz);
        aty.startActivityForResult(i, requestCode);
    }

    public static void showActivity(Activity aty, int flags, Class clazz) {
        Intent i = new Intent(aty, clazz);
        i.setFlags(flags);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    public static void skipActivity(Activity aty, int flags, Class clazz) {
        Intent i = new Intent(aty, clazz);
        i.setFlags(flags);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        aty.finish();
    }

    public static void skipActivity(Activity aty, Bundle bundle, int flags, Class clazz) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        i.setFlags(flags);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        aty.finish();
    }

    public static void showActivity(Activity aty, int flags, Bundle bundle, Class clazz) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        i.setFlags(flags);
        aty.startActivity(i);
        aty.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    public static void showActivity(Context aty, int flags, Bundle bundle, Class clazz) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        i.setFlags(flags);
        aty.startActivity(i);

    }

    public static void showActivity(Context aty, int flags, Class clazz) {
        Intent i = new Intent(aty, clazz);
        i.setFlags(flags);
        aty.startActivity(i);
    }

    public static void showActivityAnima(Activity aty, Class clazz, Bundle bundle, int inAnima, int outAnima) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        aty.startActivity(i);
        aty.overridePendingTransition(inAnima, outAnima);
    }

    public static void showActivityAnima(Activity aty, Class clazz, int flags, int inAnima, int outAnima) {
        Intent i = new Intent(aty, clazz);
        i.setFlags(flags);
        aty.startActivity(i);
        aty.overridePendingTransition(inAnima, outAnima);
    }

    public static void skipActivityAnima(Activity aty, Class clazz, Bundle bundle, int inAnima, int outAnima) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        aty.startActivity(i);
        aty.overridePendingTransition(inAnima, outAnima);
        aty.finish();
    }

    public static void showActivityAnima(Activity aty, Class clazz, int inAnima, int outAnima) {
        Intent i = new Intent(aty, clazz);
        aty.startActivity(i);
        aty.overridePendingTransition(inAnima, outAnima);
    }

    public static void showActivityAnima(Activity aty, Class clazz, int flags, Bundle bundle, int inAnima, int outAnima) {
        Intent i = new Intent(aty, clazz);
        i.putExtras(bundle);
        i.setFlags(flags);
        aty.startActivity(i);
        aty.overridePendingTransition(inAnima, outAnima);
    }

    public static void showActivity(Context aty, Intent intent) {
        aty.startActivity(intent);
    }


    /**
     * activity栈的最顶 activity \
     *
     * @return ActivityUtils.topActivity(this, LoginActivity.class.getName ());
     */
    public static boolean topActivity(Context mContext, String activityName) {
        android.app.ActivityManager activityManager = (android.app.ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        String packagename = "";
        if (tasksInfo == null || tasksInfo.size() == 0) {
            return false;
        } else {
            packagename = tasksInfo.get(0).topActivity.getClassName();
            if (activityName.equals(packagename)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 应用信息界面 ---权限设置
     *
     * @return ActivityUtils.topActivity(this, LoginActivity.class.getName ());
     */

    public static void appInfo(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }



    private static List<Activity> activities = new ArrayList<Activity>();
    private static Activity mCurrentActivity;
    private static Fragment mCurrentFragment;

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // 遍历所有Activity并finish
    public static void exitAPP() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        System.exit(0);
    }



    public static void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public static void setCurrentFragment(Fragment fragment) {
        mCurrentFragment = fragment;
    }

    public static Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

}

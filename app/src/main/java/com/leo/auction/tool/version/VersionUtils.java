package com.leo.auction.tool.version;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.base.BaseAppContext;
import com.leo.auction.base.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * 作者：哇牛Aaron
 * 作者简书文章地址: http://www.jianshu.com/users/07a8b5386866/latest_articles
 * 时间: 2016/11/18
 * 功能描述: 版本比较工具
 */
public class VersionUtils {


    private static NotificationManager mNotificationManager;
    private static NotificationCompat.Builder mBuilder;

    /**
     * 联网下载最新版本apk
     * https://f868fd3d93dea615552b722a183e6472.dd.cdntips.com/imtt.dd.qq.com/16891/CFE64F8EF1E7C413EA5B41F3BFD150F6.apk?mkey=5ccfc5f76e5777f6&f=24c3&fsname=com.syezon.wifi_3.8.0_269.apk
     */
    public static void okHttpDownLoadApk(String url) {

        initNotification();

        String path = Environment.getExternalStorageDirectory() + "/Download/";
        OkHttpUtils.get().url("https://f868fd3d93dea615552b722a183e6472.dd.cdntips.com/imtt.dd.qq.com/16891/CFE64F8EF1E7C413EA5B41F3BFD150F6.apk?mkey=5ccfc5f76e5777f6&f=24c3&fsname=com.syezon.wifi_3.8.0_269.apk").build()
                .execute(new FileCallBack(path, Constants.Nouns.APK_NAME) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络故障，下载失败");

                    }

                    @Override
                    public void onResponse(File response, int id) {
                    }

                    @Override
                    public void inProgress(final float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        //为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
//                        if (downProgress == 0 || (progress * 100 > 1 && progress * 100 - downProgress > 10)) {
//                            downProgress = (int) (100 * progress);
//                            mHandler.sendEmptyMessage(DOWNLOAD);
//                        }

                        mBuilder.setProgress(100, (int) (progress * 100), false);
                        mBuilder.setContentText("下载进度:" + (int) (progress * 100) + "%");
                        Notification notification = mBuilder.build();
                        mNotificationManager.notify(1, notification);
//
                        if (progress * 100 == 100) {
//                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            installApk();
                            mNotificationManager.cancelAll();
                        }


                    }
                });
    }


    //初始化通知
    public static void initNotification() {
        mNotificationManager = (NotificationManager) BaseAppContext.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(BaseAppContext.getInstance());
        mBuilder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.drawable.logo)
//                .setLargeIcon(BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.mipmap.ic_launcher_round)) //设置通知的大图标
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        Notification notification = mBuilder.build();//构建通知对象

    }


    /**
     * 安装apk
     */
    public static void installApk() {

        //Environment.getExternalStorageDirectory() 保存的路径
        Intent intent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Globals.log("log xwj installApk1");
            File fileLocation = new File(Environment.getExternalStorageDirectory() + "/Download/" + Constants.Nouns.APK_NAME);
            Uri apkUri = FileProvider.getUriForFile(BaseAppContext.getInstance(), BaseAppContext.getInstance().getPackageName() + ".tool.version.VersionFileProvider", fileLocation);//在AndroidManifest中的android:authorities值
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
//            Globals.log("log xwj installApk2");
            File fileLocation = new File(Environment.getExternalStorageDirectory() + "/Download/", Constants.Nouns.APK_NAME);
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.parse(Uri.fromFile(fileLocation) + "");
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        BaseAppContext.getInstance().startActivity(intent);
    }


    /**
     * 比较版本大小
     * version1 < version2 则 -1
     * version1 > version2 则 1
     * == 则0
     *
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }
            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }


    //获取App版本名 没用到
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //获取App版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }


}

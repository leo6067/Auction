package com.leo.auction.base;

import android.util.Log;

import com.aten.compiler.base.BaseApplication;
import com.aten.compiler.utils.LogUtils;
import com.blankj.utilcode.util.Utils;

import com.leo.auction.utils.shared.UMengUtils;
import com.simple.spiderman.CrashModel;
import com.simple.spiderman.SpiderMan;

import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhouyou.http.EasyHttp;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;


import org.litepal.LitePal;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * 不能用Application缓存数据！
 * 因为Application会因为进入background后内存不足被系统干掉，
 * 进入后系统会重现创建一个Application类，而导致缓存在Application类里的数据全部初始化而丢失。
 **/


public class AppApplication extends BaseApplication {

    private static AppApplication instance;
    //    public static IWXAPI api;
    public static String APP_ID;

    public AppApplication() {

    }

    public static AppApplication getInstance() {
        if (null == instance) {
            instance = new AppApplication();
        }
        return instance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Constants.init(true);//是否正式版
        BaseAppContext.init(this);    //获得app全局上下文
        ZXingLibrary.initDisplayOpinion(this); //初始化二维码
        Utils.init(this); //blankj:utilcode 工具类初始化
        initLog();
//        initOKHttp();
//        initUm();
        initUMeng();
        initRetrofit();
        initLitePal();
//        initX5WebView();
    }



    //初始化数据库
    private void initLitePal() {
        LitePal.initialize(this);
        LitePal.aesKey(Constants.Nouns.AES_KEY);
    }



    //配置友盟的基本信息
    private void initUMeng() {
        //初始化配置信息
        UMengUtils.getInstance().init(this,"5ee97d17978eea083057276b")
                .setQQ(Constants.Nouns.QQAPPID,Constants.Nouns.QQAPPKEY)
                .setWeixin(Constants.Nouns.WEIXINAPPID,Constants.Nouns.WEIXINAPPKEY);
    }

    /**
     * OKHttpUtils 网络配置
     */
    private void initOKHttp() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//内存存储cookie
                .connectTimeout(8000L, TimeUnit.MILLISECONDS)
                .readTimeout(8000L, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {//允许访问https网站,并忽略证书
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .proxy(Proxy.NO_PROXY);

        OkHttpUtils.initClient(okHttpClientBuilder.build());
    }


    private void initLog() {
        SpiderMan.getInstance()
                .init(this)
                //设置是否捕获异常，不弹出崩溃框
                .setEnable(!Constants.logGone)
                //设置是否显示崩溃信息展示页面
                .showCrashMessage(true)
                //是否回调异常信息，友盟等第三方崩溃信息收集平台会用到,
                .setOnCrashListener(new SpiderMan.OnCrashListener() {
                    @Override
                    public void onCrash(Thread t, Throwable ex, CrashModel model) {
                        //CrashModel 崩溃信息记录，包含设备信息
                    }
                });
    }


    private void initRetrofit() {
        String baseUrl = Constants.Api.domain;
        EasyHttp.init(this);//默认初始化,必须调用
        EasyHttp.getInstance()
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setOkproxy(Proxy.NO_PROXY) //忽略代理
                .setBaseUrl(baseUrl);
    }


    private void initUm() {

//        //当用户使用自有账号登录时，可以这样统计：
//        MobclickAgent.onProfileSignIn("userID");
//        //当用户使用第三方账号（如新浪微博）登录时，可以这样统计：
//        MobclickAgent.onProfileSignIn("WB", "userID");
//
//        //登出
//        MobclickAgent.onProfileSignOff();


        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
        UMConfigure.setLogEnabled(false);    //是否显示友盟日志

        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i("TAG", "注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("TAG", "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }



    //初始化webview
    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }


}
package com.leo.auction.ui.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.easyPay.EasyPay;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.aten.compiler.utils.permission.PermissionHelper;
import com.blankj.utilcode.util.AppUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.LoginWxActivity;
import com.leo.auction.ui.login.StartActivity;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.SharedActvity;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.home.model.PicGridNineModel;
import com.leo.auction.ui.main.home.model.ShopModel;
import com.leo.auction.ui.main.home.model.WebShopModel;
import com.leo.auction.ui.main.mine.activity.CommodityEditActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.ui.order.model.WebGoodDetailModel;
import com.leo.auction.ui.version.VersionDialog;
import com.leo.auction.ui.version.VersionDownDialog;
import com.leo.auction.ui.version.VersionModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AgentWebAppActivity extends AppCompatActivity {
    private double exitTime;

    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;



    //backPager 0:代表返回上一页 1：代表回到首页
    public static void newIntance(Context context, int backPager) {
//        CookieSyncManager.createInstance(context);
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.removeAllCookie();
//        CookieSyncManager.getInstance().sync();
        BaseSharePerence.getInstance().setLoginStatus(false);
        Intent intent = new Intent(context, AgentWebAppActivity.class);
        intent.putExtra("backPager", backPager);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    protected void RedImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar mImmersionBar = ImmersionBar.with(this)

                .statusBarColor(R.color.home_title_bg)
                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    protected void GrayImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar mImmersionBar = ImmersionBar.with(this)

                .statusBarColor(R.color.mine_text)
                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    protected void WhiteImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar mImmersionBar = ImmersionBar.with(this)

                .statusBarColor(R.color.white)
                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web_app);
        mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        buildAgentWeb();
        httpVerison();
        initData();
        RedImmersionBar();  //初始化，首页

        rePremissions();
    }



    void rePremissions() {
//        httpCommon();
//        ActivityManager.JumpActivity(StartActivity.this, MainActivity.class, null);
        PermissionHelper permissionHelper = new PermissionHelper();
        permissionHelper.requestPermission(AgentWebAppActivity.this, new PermissionHelper.onPermissionListener() {
            @Override
            public void onSuccess() {
//                backLogin();
            }
            @Override
            public void onFail() {
//                backLogin();
            }
//        }, Permission.READ_PHONE_STATE, Permission.WRITE_EXTERNAL_STORAGE,Permission.RECORD_AUDIO);
        },  com.yanzhenjie.permission.Permission.WRITE_EXTERNAL_STORAGE );  //注释掉打电话
//        com.yanzhenjie.permission.Permission.CAMERA,
    }


    private void initData() {

    }


    protected void buildAgentWeb() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
//                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)

                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl());
        if (mAgentWeb != null) {
            //注入对象
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(AgentWebAppActivity.this));

        }


    }


    private com.just.agentweb.WebViewClient mWebViewClient = new WebViewClient() {


        private HashMap<String, Long> timer = new HashMap<>();

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            Globals.log( "shouldOverrideUrlLoading" + view.getTitle() + view.getUrl());
            return super.shouldOverrideUrlLoading(view, request);
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
//            Globals.log("Info" + "BaseWebActivity onPageStarted" + view.getTitle() + url);

            Globals.log(" mWebViewClient BaseWebActivity onPageStarted" + view.getTitle() + view.getUrl());
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (timer.get(url) != null) {
                long overTime = System.currentTimeMillis();
                Long startTime = timer.get(url);
            }
//            Globals.log(" 01 Info  " + "BaseWebActivity onPageStarted" + view.getTitle() + url);

        }

    };
    private com.just.agentweb.WebChromeClient mWebChromeClient = new WebChromeClient() {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Bundle bundle = new Bundle();

            Globals.log(" mWebChromeClient BaseWebActivity onPageStarted" + view.getTitle() + view.getUrl());
            if (newProgress == 100) {
                if (view.getUrl().equals(Constants.WEB_BASE_URL + "auction-web/pages/sub/product/save")) {  //发布拍品
                    ActivityManager.JumpActivity(AgentWebAppActivity.this, CommodityReleaseActivity.class);
                    mAgentWeb.back();
                } else if (view.getUrl().contains(Constants.WEB_BASE_URL + "auction-web/pages/sub/product/saveOrUpdate?productId=")) { //拍品编辑
                    //截取之后的字符
                    String productId = view.getUrl().substring(view.getUrl().indexOf("?productId=") + 11);
                    bundle.clear();
                    bundle.putString("value", productId);
                    ActivityManager.JumpActivity(AgentWebAppActivity.this, CommodityEditActivity.class, bundle);
                    mAgentWeb.back();
                } else if (view.getUrl().contains(Constants.WEB_BASE_URL + "auction-web/pages/personal/personal")) { //我的
                    RedImmersionBar();
                } else if (view.getUrl().equals(Constants.WEB_BASE_URL + "auction-web/?iscdandroid=1")) { //首页
                    RedImmersionBar();
                } else if (view.getUrl().equals(Constants.WEB_BASE_URL + "auction-web/")) { //首页
                    RedImmersionBar();
                } else if (view.getUrl().equals(Constants.WEB_BASE_URL + "auction-web/pages/follow/follow")) { //关注
                    RedImmersionBar();
                } else if (view.getUrl().contains(Constants.WEB_BASE_URL + "auction-web/pages/sub/mercahnt/index?shopUri=")) { //店铺首页
                    RedImmersionBar();
                } else if (view.getUrl().contains(Constants.WEB_BASE_URL + "auction-web/pages/sub/product/productDetail?productInstanceCode=")) { //拍品详情
                    GrayImmersionBar();
                } else if (view.getUrl().contains(Constants.WEB_BASE_URL + "auction-web/pages/sub/bysubsidy/index")) { //百亿补贴
                    GrayImmersionBar();
                } else {
                    WhiteImmersionBar();
                }
            }

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            if (mTitleTextView != null) {
//                mTitleTextView.setText(title);
//            }

        }
    };

    public String getUrl() {
        return Constants.WEB_APP_URL;
    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Globals.log("Info" + "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (mMineFragment != null && mMineFragment.mAgentWeb != null && mMineFragment.mAgentWeb.getWebCreator().getWebView().canGoBack()) {
//                mMineFragment.mAgentWeb.getWebCreator().getWebView().goBack();
//                return true;
//            }
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showShort("锤定：再次点击退出");
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //版本更新
    public void httpVerison() {
        VersionModel.httpGetVersion(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                VersionModel returnData = JSONObject.parseObject(resultData, VersionModel.class);
                HashMap<String, String> mHashMap = new HashMap<>();
                mHashMap.put("isForce", returnData.getData().isForce() + "");
                mHashMap.put("downUrl", returnData.getData().getDownload());
                if (Integer.parseInt(returnData.getData().getVersion()) == AppUtils.getAppVersionCode()) {
                    return;
                }


                VersionDialog versionDialog = new VersionDialog(AgentWebAppActivity.this, mHashMap, new VersionDialog.VersionInter() {
                    @Override
                    public void versionOK() {
//                        VersionDownDialog downDialog = new VersionDownDialog(MainActivity.this,"https://imtt.dd.qq.com/16891/apk/C831AEEA8BCC274A9EBA11DB22BBC375.apk");
                        VersionDownDialog downDialog = new VersionDownDialog(AgentWebAppActivity.this, returnData.getData().getDownload());
                        downDialog.show();
                        downDialog.setCanceledOnTouchOutside(false);
                    }

                    @Override
                    public void versionCancel() {
                    }
                });
                versionDialog.show();
                versionDialog.setCanceledOnTouchOutside(false);
            }
        });
    }


    //微信登录
    private void wxLogin() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("unionId", map.get("unionid"));
                hashMap.put("openId", map.get("openid"));
                hashMap.put("nickname", map.get("name"));
                hashMap.put("headImg", map.get("iconurl"));
                HttpRequest.httpPostStringWeb(Constants.Api.HOMEPAGE_USER_WX_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        Globals.log("xxxxxx httpResponse UMShareAPI" + resultData);
                        LoginModel loginModel = JSONObject.parseObject(resultData, LoginModel.class);
                        BaseSharePerence.getInstance().setLoginStatus(true);
                        BaseSharePerence.getInstance().setLoginJson(resultData);

                        String encode = resultData;
//                        backLoginWeb(loginModel.getData().getUser().getNestedToken(),resultData);
                        mAgentWeb.getJsAccessEntrace().quickCallJs("appLoginSuccess", "" + encode + "");
                        backLogin(loginModel.getData().getUser().getNestedToken());
                    }
                });
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                if (!UMShareAPI.get(AgentWebAppActivity.this).isInstall(AgentWebAppActivity.this, SHARE_MEDIA.WEIXIN)) {
                    ToastUtils.showShort("请安装微信");
                } else {
                    ToastUtils.showShort("授权失败");
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                ToastUtils.showShort("取消授权");
            }
        });
    }


    //微信支付
    private void wxPay(String resultData) {
        PayModel returnData = JSONObject.parseObject(resultData, PayModel.class);
        PayModel.DataBean.WxBean data = returnData.getData().getWx();
        //实例化微信支付策略
        WXPay wxPay = WXPay.getInstance();


        JSONObject jsonObject = JSONObject.parseObject(resultData);
        String dataStr = jsonObject.getString("data");
        JSONObject dataJson = JSONObject.parseObject(dataStr);
        String wxStr = dataJson.getString("wx");
        JSONObject wxJson = JSONObject.parseObject(wxStr);
        String packageStr = wxJson.getString("package");


        //构造微信订单实体。一般都是由服务端直接返回。
        WXPayBean wxPayBean = new WXPayBean(Constants.Nouns.WEIXINAPPID, data.getMchId(), data.getPrepayId(), packageStr,
                data.getNonceStr(), data.getTimeStamp(), data.getPaySign());

        //策略场景类调起支付方法开始支付，以及接收回调。
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(wxPay, AgentWebAppActivity.this, wxPayBean, new IPayCallback() {
            @Override
            public void success() {
                Globals.log("xxxxx wxPay success");
                mAgentWeb.getJsAccessEntrace().quickCallJs("appWxPaySuccess");
            }

            @Override
            public void failed(int code, String message) {
                Globals.log("xxxxx wxPay code" + code + message);
                ToastUtils.showShort("支付失败");
            }

            @Override
            public void cancel() {
                Globals.log("xxxxx wxPay cancel");
                ToastUtils.showShort("支付取消");
            }
        });
    }


    //web静默登录

    private void backLoginWeb(String nesteToken, String encode) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", nesteToken);
        HttpRequest.httpPostString(Constants.Api.HOMEPAGE_USER_DEFAULT_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
//                    BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
//                    httpuser
                mAgentWeb.getJsAccessEntrace().quickCallJs("appLoginSuccess", "" + encode + "");
                Globals.log("xxxxxx backLogin" + resultData);
                UserModel.httpUpdateUser(AgentWebAppActivity.this);
            }
        });


    }


    //登录---手机
    private void backLogin(String nesteToken) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", nesteToken);
        HttpRequest.httpPostString(Constants.Api.HOMEPAGE_USER_DEFAULT_LOGIN_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
//                    BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
//                    httpuser

                Globals.log("xxxxxx backLogin" + resultData);
                UserModel.httpUpdateUser(AgentWebAppActivity.this);

            }
        });

    }

    //手机登录
    private void phoneLogin(String resultData) {
        BaseSharePerence.getInstance().setLoginStatus(true);
//                    UserModel.httpUpdateUser(AgentWebAppActivity.this);
        Globals.log("xxxxxx loginPhone" + resultData);
        LoginModel loginModel = JSONObject.parseObject(resultData, LoginModel.class);
        backLogin(loginModel.getData().getUser().getNestedToken());
    }


    //店铺分享
    private void shareShop(String resultData) {

        WebShopModel dataBean = JSONObject.parseObject(resultData, WebShopModel.class);
        ArrayList<String> imgStr = new ArrayList<>();
        imgStr.add(dataBean.getHeadImg());
        String type = "2";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情
        UserModel.DataBean mUserJson = BaseSharePerence.getInstance().getUserJson();
        ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareShopTitlelList();
        Random ra = new Random();
        int anInt = ra.nextInt(shareShopTitlelList.size());
        String shareTitle = "【锤定】" + dataBean.getNickname() + shareShopTitlelList.get(anInt);
        String path = Constants.WebApi.SHARE_SHOP_URL + dataBean.getShopUri()
                + "&tpm_shareAgentId=" + mUserJson.getUserId();
        SharedModel sharedModel = new SharedModel(shareTitle, shareTitle, dataBean.getHeadImg(),
                "0.00", dataBean.getHeadImg(), type, path, dataBean.getShopUri(), mUserJson.getUserId(),
                Constants.Action.ACTION_ACTION);
        SharedActvity.newIntance(AgentWebAppActivity.this, sharedModel, imgStr, shareTitle, "");


    }


    //商品详情分享
    private void shareGood(String resultData) {
        Globals.log("xxxxxxx auctionDetailShare" + resultData);
        String shareName = "";
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        WebGoodDetailModel detailModelData = JSONObject.parseObject(resultData, WebGoodDetailModel.class);
        String path = Constants.WebApi.SHARE_PRODUCT_URL + detailModelData.getProductInstanceCode()
                + "&shareAgentId=" + userJson.getNestedToken();
        String type = "3";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情
        ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareTitlelList();

        Random ra = new Random();
        int anInt = ra.nextInt(shareShopTitlelList.size());
        if (detailModelData.getProductUser().getUserId().equals(userJson.getUserId())) {
            shareName = "【" + detailModelData.getProductUser().getNickname() + "】" + shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";
        } else {
            shareName = shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";
        }

        String sharedText = detailModelData.getContent();
        SharedModel sharedModel = new SharedModel(shareName, sharedText, detailModelData.getImages() == null ? "" : detailModelData.getImages().get(0),
                detailModelData.getCurrentPrice() + "", detailModelData.getProductUser().getHeadImg(), type, path, detailModelData.getProductInstanceId() + "", userJson.getUserId(),
                Constants.Action.ACTION_ACTION);
        ArrayList<String> nineImgList = new ArrayList<>();

        //1判断是否有视频
        if (detailModelData.getImages().size() >= 9) {
            for (String s : detailModelData.getImages().subList(0, 8)) {
                nineImgList.add(s);
            }
        } else {
            nineImgList = detailModelData.getImages();
        }

        /**
         *
         * 接口获取二维码失败，导致分享失败
         * **/
        Globals.log("xxxxxxx auctionDetailShare nineImgList" + nineImgList.toString());
        SharedActvity.newIntance(AgentWebAppActivity.this, sharedModel, nineImgList, sharedText + path, "");
    }


    /**
     * 超级仓库分享
     */
    private void shareHouse(String resultData) {
        Globals.log("xxxxxxx auctionDetailShare" + resultData);
        String shareName = "";
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        WebGoodDetailModel detailModelData = JSONObject.parseObject(resultData, WebGoodDetailModel.class);
        String path = Constants.WebApi.SHARE_PRODUCT_URL + detailModelData.getProductInstanceCode()
                + "&shareAgentId=" + userJson.getNestedToken();
        String type = "3";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情
        ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareTitlelList();

        Random ra = new Random();
        int anInt = ra.nextInt(shareShopTitlelList.size());
        if (detailModelData.getProductUser().getUserId().equals(userJson.getUserId())) {
            shareName = "【" + detailModelData.getProductUser().getNickname() + "】" + shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";
        } else {
            shareName = shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";
        }

        String sharedText = detailModelData.getContent();
        SharedModel sharedModel = new SharedModel(shareName, sharedText, detailModelData.getImages() == null ? "" : detailModelData.getImages().get(0),
                detailModelData.getCurrentPrice() + "", detailModelData.getProductUser().getHeadImg(), type, path, detailModelData.getProductInstanceId() + "", userJson.getUserId(),
                Constants.Action.ACTION_ACTION);
        ArrayList<String> nineImgList = new ArrayList<>();

        //1判断是否有视频
        if (detailModelData.getImages().size() >= 9) {
            for (String s : detailModelData.getImages().subList(0, 8)) {
                nineImgList.add(s);
            }
        } else {
            nineImgList = detailModelData.getImages();
        }

        /**
         *
         * 接口获取二维码失败，导致分享失败
         * **/
        Globals.log("xxxxxxx auctionDetailShare nineImgList" + nineImgList.toString());
        SharedActvity.newIntance(AgentWebAppActivity.this, sharedModel, nineImgList, sharedText + path, "");
    }


    /**
     * js交互
     */

    public class AndroidInterface {

        private Handler deliver = new Handler(Looper.getMainLooper());
        private Context context;

        public AndroidInterface(Context context) {
            this.context = context;
        }


        @JavascriptInterface
        public void closeAction() {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
        }


        @JavascriptInterface
        public void LogPrinting(String data) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("invitationAction  LogPrinting----web     " + data);
//                JSShareJson jsShareJson = JSONObject.parseObject(data, JSShareJson.class);
//                SharedModel sharedModel = new SharedModel(jsShareJson.getTitle(), jsShareJson.getDesc(), jsShareJson.getImg(), jsShareJson.getUrl());
//                SharedActvity.newIntance(context, sharedModel);
                }
            });
        }


        //微信登录
        @JavascriptInterface
        public void loginWX() {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    wxLogin();
                }
            });
        }


        //手机号登录
        @JavascriptInterface
        public void loginPhone(String userInfoStr) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    phoneLogin(userInfoStr);
                }
            });
        }


        //微信支付
        @JavascriptInterface
        public void detailWxPay(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    wxPay(resultData);
                }
            });
        }


        //百亿邀请签到 ---分享
        @JavascriptInterface
        public void invitationAction(String data) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("invitationAction" + data);
                    JSShareJson jsShareJson = JSONObject.parseObject(data, JSShareJson.class);
                    SharedModel sharedModel = new SharedModel(jsShareJson.getTitle(), jsShareJson.getDesc(), jsShareJson.getImg(), jsShareJson.getUrl(), "2", "");
                    SharedActvity.newIntance(context, sharedModel);
                }
            });
        }


        //商品详情分享
        @JavascriptInterface
        public void auctionDetailShare(String resultData,String code) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    shareGood(resultData);
                }
            });
        }


        /*
         * 店铺分享
         * */
        @JavascriptInterface
        public void shopShare(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    shareShop(resultData);
                }
            });
        }


        /*
         * 超级仓库分享
         * */
        @JavascriptInterface
        public void houseShare(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    shareHouse(resultData);
                }
            });
        }

    }

}



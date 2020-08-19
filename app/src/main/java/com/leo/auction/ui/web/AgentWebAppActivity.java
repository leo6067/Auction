package com.leo.auction.ui.web;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.FileUtils;
import com.aten.compiler.utils.ImageUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.easyPay.EasyPay;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.aten.compiler.utils.permission.PermissionHelper;
import com.aten.compiler.widget.glide.GlideApp;
import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.main.SharedActvity;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.home.model.WebShopModel;
import com.leo.auction.ui.main.mine.activity.CommodityEditActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.model.HouseOrderCodeModel;
import com.leo.auction.ui.order.model.HouseOrderModel;
import com.leo.auction.ui.order.model.HouseShareModel;
import com.leo.auction.ui.order.model.WebGoodDetailModel;
import com.leo.auction.ui.version.VersionDialog;
import com.leo.auction.ui.version.VersionDownDialog;
import com.leo.auction.ui.version.VersionModel;
import com.leo.auction.utils.GlideUtils;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;
import com.sch.share.Options;
import com.sch.share.WXShareMultiImageHelper;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yanzhenjie.permission.Permission;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AgentWebAppActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private double exitTime;

    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;

    private ImageView mBackImageView;
    private View mLineView;
    private ImageView mFinishImageView;
    private TextView mTitleTextView;


    private int barType = 0; // 0 红色 1 灰色  2 白色
    private int barLogin = 0;


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

                .statusBarColor(R.color.gray_title)
//                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    protected void WhiteImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(R.color.white)
//                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web_app);
        ButterKnife.bind(this);
        mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        buildAgentWeb();
        httpVerison();
        initView();
        RedImmersionBar();  //初始化，首页
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
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
        }, Permission.WRITE_EXTERNAL_STORAGE);  //注释掉打电话
//        com.yanzhenjie.permission.Permission.CAMERA,
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

    protected void initView() {

        mBackImageView = (ImageView) findViewById(R.id.iv_back);
        mLineView = findViewById(R.id.view_line);
        mFinishImageView = (ImageView) findViewById(R.id.iv_finish);
        mTitleTextView = (TextView) findViewById(R.id.toolbar_title);
        mBackImageView.setOnClickListener(mOnClickListener);
        mFinishImageView.setOnClickListener(mOnClickListener);

        mFinishImageView.setVisibility(View.GONE);

//        pageNavigator(View.GONE);

    }


    private WebViewClient mWebViewClient = new WebViewClient() {


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

//            Globals.log(" mWebViewClient BaseWebActivity onPageStarted" + view.getTitle() + view.getUrl());
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
    private WebChromeClient mWebChromeClient = new WebChromeClient() {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            if (mTitleTextView != null) {
//                mTitleTextView.setText(title);
//            }
            Bundle bundle = new Bundle();
            mToolbar.setVisibility(View.VISIBLE);
            mTitleTextView.setText(view.getTitle());

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
            } else if (view.getUrl().equals(Constants.WEB_BASE_URL + "auction-web/?iscdandroid=1")) { //首页
                if (barLogin == 1) {
                    barLogin = 0;
                    WhiteImmersionBar();
                    barType = 0;
                    return;
                }

            } else if (view.getUrl().equals(Constants.WEB_BASE_URL + "auction-web/")) { //首页
                if (barLogin == 1) {
                    barLogin = 0;
                    WhiteImmersionBar();
                    barType = 0;
                }

            } else if (view.getUrl().contains("pages/sub/seach")) {
                mToolbar.setVisibility(View.GONE);
            }

            switch (title) {
                case "首页":
                case "关注":
                case "我的":
                case "店铺首页":
                    RedImmersionBar();
                    barType = 0;
                    break;
                case "拍品详情":
                case "TOP百亿补贴":
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.gray_title));
                    GrayImmersionBar();
                    barType = 1;
                    break;
                default:
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.white));
                    WhiteImmersionBar();
                    barType = 2;
            }


            switch (title) {
                case "首页":
                case "关注":
                case "消息":
                case "分类":
                case "我的":
                case "店铺首页":
                case "搜索":
                case "锤定":
                    mToolbar.setVisibility(View.GONE);
                    break;
                default:
                    mToolbar.setVisibility(View.VISIBLE);
            }

            if (view.getUrl().contains("pages/sub/seach")) {
                mToolbar.setVisibility(View.GONE);
                WhiteImmersionBar();
                barType = 2;
            }


            Globals.log(" mWebChromeClient BaseWebActivity onReceivedTitle" + view.getTitle() + view.getUrl());
        }
    };

    public String getUrl() {
        return Constants.WEB_APP_URL;
//        return "http://192.168.0.205:8080/";
    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        Globals.log("xxxxx onResume");
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


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back:
                    // true表示AgentWeb处理了该事件
                    mAgentWeb.back();
                    break;
                case R.id.iv_finish:
//                    AgentWebFragment.this.getActivity().finish();
                    break;
                default:
                    break;

            }
        }
    };


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
                System.exit(0);
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

        String shopName = "【锤定】" + dataBean.getNickname();
        String goodName = "";

        String shareContent = shareShopTitlelList.get(anInt);
        String path = Constants.WebApi.SHARE_SHOP_URL + dataBean.getShopUri()
                + "&tpm_shareAgentId=" + mUserJson.getUserId();


        SharedModel sharedModel = new SharedModel(shopName, goodName, shopName, shareContent, dataBean.getHeadImg(),
                "0.00", dataBean.getHeadImg(), type, path, dataBean.getShopUri(), mUserJson.getUserId(),
                "2");
        SharedActvity.newIntance(AgentWebAppActivity.this, sharedModel, imgStr, shareContent, "");


    }


    //商品详情分享
    private void shareGood(String resultData) {
        String shopName = "";
        String goodName = "";
        String shareTitle = "";
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        WebGoodDetailModel detailModelData = JSONObject.parseObject(resultData, WebGoodDetailModel.class);
        String path = Constants.WebApi.SHARE_PRODUCT_URL + detailModelData.getProductInstanceCode()
                + "&shareAgentId=" + userJson.getUserId();
        String type = "3";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情
        ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareTitlelList();

        Random ra = new Random();
        int anInt = ra.nextInt(shareShopTitlelList.size());
        if (detailModelData.getProductUser().getUserId().equals(userJson.getUserId())) {
            shareTitle = "【" + detailModelData.getProductUser().getNickname() + "】" + shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";
        } else {
            shareTitle = shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";
        }
        shopName = detailModelData.getProductUser().getNickname();
        goodName = detailModelData.getTitle();


        String sharedText = detailModelData.getContent();


        String shareContent = "【品名】  " + detailModelData.getTitle() + "\n";

//        shareContent += "【" + "大类" + "】  " + detailModelData.getParentCategoryName() + "\n";
//        shareContent += "【" + "小类" + "】  " + detailModelData.getCategoryName() + "\n";

        List<WebGoodDetailModel.AttributesBean> attributes = detailModelData.getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).getTitle().equals("类型") && attributes.get(i).getValue().equals("其他")) {
                shareContent += "";
            } else if(attributes.get(i).getValue().equals("")){
                shareContent += "";
            }else {
                shareContent += "【" + attributes.get(i).getTitle() + "】  " + attributes.get(i).getValue() + "\n";
            }
        }
        shareContent += "【" + "描述" + "】  " + detailModelData.getContent() + "\n";


        SharedModel sharedModel = new SharedModel(shopName, goodName, shareTitle, sharedText, detailModelData.getImages().get(0),
                detailModelData.getCurrentPrice() + "", detailModelData.getProductUser().getHeadImg(), type, path, detailModelData.getProductInstanceId() + "", userJson.getUserId(),
                "0");
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
        SharedActvity.newIntance(AgentWebAppActivity.this, sharedModel, nineImgList, shareContent + path, "");
    }


    /**
     * 百亿补贴分享
     */
    private void shareBY(String resultData) {
        JSShareJson jsShareJson = JSONObject.parseObject(resultData, JSShareJson.class);
        SharedModel sharedModel = new SharedModel(jsShareJson.getTitle(), jsShareJson.getTitle(),jsShareJson.getDesc(), jsShareJson.getImg(), jsShareJson.getUrl(), "2", "");
        SharedActvity.newIntance(AgentWebAppActivity.this, sharedModel);
    }

    /**
     * 超级仓库分享
     */
    private void shareHouse(String resultData) {
        Globals.log("xxxxxxx auctionDetailShare" + resultData);

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        HouseShareModel detailModelData = JSONObject.parseObject(resultData, HouseShareModel.class);
//        String path = Constants.WebApi.SHARE_PRODUCT_URL + detailModelData.getProductInstanceCode()
//                + "&shareAgentId=" + userJson.getNestedToken();
//        String type = "3";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情

        String shareContent = "【品名】  " + detailModelData.getTitle() + "\n";

//        shareContent += "【" + "大类" + "】  " + detailModelData.getParentCategoryName() + "\n";
//        shareContent += "【" + "小类" + "】  " + detailModelData.getCategoryName() + "\n";

        List<HouseShareModel.AttributesBean> attributes = detailModelData.getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
//            shareContent += "【" + attributes.get(i).getTitle() + "】  " + attributes.get(i).getValue() + "\n";
            if (attributes.get(i).getTitle().equals("类型") && attributes.get(i).getValue().equals("其他")) {
                shareContent += "";
            } else if(attributes.get(i).getValue().equals("")){
                shareContent += "";
            }else {
                shareContent += "【" + attributes.get(i).getTitle() + "】  " + attributes.get(i).getValue() + "\n";
            }
        }
        shareContent += "【" + "描述" + "】  " + detailModelData.getContent() + "\n";
        shareMuiltImgToFriendCircle(shareContent, detailModelData.getImages());
    }


    //分享多张图片到朋友圈
    private void shareMuiltImgToFriendCircle(String shareContent, List<String> nineImgs) {
        final TreeMap<String, Bitmap> picBitmaps = new TreeMap<>();
        for (int i = 0; i < nineImgs.size(); i++) {
            String picdata = nineImgs.get(i);
            if (picdata.contains("?")) {
                picdata += "&x-oss-process=image/resize,s_640";
            } else {
                picdata += "?x-oss-process=image/resize,s_640";
            }
            final int finalI = i;

            GlideApp.with(AgentWebAppActivity.this).asBitmap().load(picdata).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    picBitmaps.put(String.valueOf(finalI), resource);
                    if (picBitmaps.size() == nineImgs.size()) {
                        ArrayList<Bitmap> picDatas = new ArrayList<>();
                        for (String key : picBitmaps.keySet()) {
                            picDatas.add(picBitmaps.get(key));
                        }
                        picBitmaps.clear();

                        Options options = new Options();
                        options.setText(shareContent);
                        options.setAutoFill(true);
                        options.setAutoPost(false);
                        options.setNeedShowLoading(true);
                        options.setOnPrepareOpenWXListener(new Options.OnPrepareOpenWXListener() {
                            @Override
                            public void onPrepareOpenWX() {
                                options.setNeedShowLoading(false);
                            }
                        });
                        WXShareMultiImageHelper.shareToTimeline(AgentWebAppActivity.this, (Bitmap[]) picDatas.toArray(new Bitmap[picDatas.size()]), options);
                    }
                }
            });
        }
    }



    /*
     * 一键下单 --跳转小程序
     * */

    private void houseCreateOrder(String resultData) {

        String appId = Constants.Nouns.WEIXINAPPID; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(AgentWebAppActivity.this, appId);

        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = Constants.Nouns.WEIXINA_SMALL; // 填小程序原始id
        req.path = "pages/products/productdetail/productdetail?id=" + resultData;                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
//        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// 可选打开 开发版，体验版和正式版
        Globals.log("xxxxxxxx" + req.path);
        api.sendReq(req);
    }


    /*
     * 一键代发 --跳转小程序
     * */

    private void houseOrderSend(String resultData) {

        HouseOrderCodeModel.ProductParent productParent = new HouseOrderCodeModel.ProductParent();
        ArrayList<HouseOrderCodeModel.Product> productParents = new ArrayList<>();

        HouseOrderModel houseOrderCodeModel = JSONObject.parseObject(resultData, HouseOrderModel.class);


        HouseOrderCodeModel.Product productModel = new HouseOrderCodeModel.Product();
        HouseOrderCodeModel.Product.MerchantInfoBean merchantInfoBean = new HouseOrderCodeModel.Product.MerchantInfoBean();
        merchantInfoBean.setHeadimg(houseOrderCodeModel.getGoods().getSupplier().getHeadImg());
        merchantInfoBean.setNickname(houseOrderCodeModel.getGoods().getSupplier().getNickname());
        merchantInfoBean.setShopUri(houseOrderCodeModel.getGoods().getSupplier().getShopUri());
        merchantInfoBean.setSupplierId(houseOrderCodeModel.getGoods().getSupplier().getSupplierId());
        productModel.setMerchantInfo(merchantInfoBean);


        ArrayList<HouseOrderCodeModel.Product.ListBean> listBeans = new ArrayList<>();
        HouseOrderCodeModel.Product.ListBean listBean = new HouseOrderCodeModel.Product.ListBean();
        listBean.setGoodsId(houseOrderCodeModel.getGoods().getGoodsId());
        if (houseOrderCodeModel.getGoods().getImages().get(0).contains("?")) {
            listBean.setIsyu(true);
        } else {
            listBean.setIsyu(false);
        }

        listBean.setImg(houseOrderCodeModel.getGoods().getImages().get(0));
        listBean.setPaynumber("1");
        listBean.setTitle(houseOrderCodeModel.getGoods().getTitle());
        listBean.setTotalPrice(houseOrderCodeModel.getGoods().getRealAgentPrice());
        listBean.setRealAgentPrice(houseOrderCodeModel.getGoods().getRealAgentPrice());
        listBean.setRealPrice(houseOrderCodeModel.getGoods().getPrice());
        listBean.setPrice(houseOrderCodeModel.getGoods().getRealAgentPrice());
        listBean.setStock(houseOrderCodeModel.getGoods().getStock());
        listBeans.add(listBean);
        productModel.setList(listBeans);
        productParents.add(productModel);
        productParent.setList(productParents);


        HouseOrderCodeModel.Addressshouhuo addressVoBean = new HouseOrderCodeModel.Addressshouhuo();
        addressVoBean.setAddr1Name(houseOrderCodeModel.getAddressVo().getAddr1Name());
        addressVoBean.setAddr2Name(houseOrderCodeModel.getAddressVo().getAddr2Name());
        addressVoBean.setAddr3Name(houseOrderCodeModel.getAddressVo().getAddr3Name());
        addressVoBean.setAddress(houseOrderCodeModel.getAddressVo().getAddress());
        addressVoBean.setCode(houseOrderCodeModel.getAddressVo().getCode());
        addressVoBean.setLinkman(houseOrderCodeModel.getAddressVo().getLinkman());
        addressVoBean.setPhone(houseOrderCodeModel.getAddressVo().getPhone());

        HouseOrderCodeModel.ZhidInfo zhidInfo = new HouseOrderCodeModel.ZhidInfo();
        zhidInfo.setName(houseOrderCodeModel.getSender());
        zhidInfo.setPhone(houseOrderCodeModel.getSendPhone());


        String productModelStr = JSON.toJSONString(productParent.getList());
        String addressVoBeanStr = JSON.toJSONString(addressVoBean);
        String zhidInfoStr = JSON.toJSONString(zhidInfo);


        Globals.log("xxxxx productModelStr" + productModelStr);

        String productStr = "";
        String addressStr = "";
        String zhiStr = "";

        try {
            productStr = URLEncoder.encode(productModelStr, "UTF-8");
            addressStr = URLEncoder.encode(addressVoBeanStr, "UTF-8");
            zhiStr = URLEncoder.encode(zhidInfoStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String appId = Constants.Nouns.WEIXINAPPID; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(AgentWebAppActivity.this, appId);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = Constants.Nouns.WEIXINA_SMALL; // 填小程序原始id
        req.path = "pages/orders/closeorderpage/closeorderpage?product=" + productStr
                + "&addressshouhuo=" + addressStr + "&zhidInfo=" + zhiStr + "&orderToken=" + houseOrderCodeModel.getOrderToken();                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
//        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// 可选打开 开发版，体验版和正式版
        Globals.log("xxxxxxxx" + req.path);
        api.sendReq(req);
    }


    /*
     * 个人保存下载二维码
     * */
    private void downBitmap(String url) {

        Bitmap bitmap = GlideUtils.returnBitMap(url);

        FileUtils.fileDirExis(BaseGlobal.getQrCodeDir());
        String imagPath = BaseGlobal.getQrCodeDir() + System.currentTimeMillis() + ".jpg";
        ImageUtils.save(bitmap, imagPath, Bitmap.CompressFormat.JPEG, true);
        // 最后通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagPath)));


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShort("保存成功");
            }
        });

    }

    @OnClick(R.id.toolbar)
    public void onViewClicked() {
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
        public void invitationAction(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("invitationAction" + resultData);

                    shareBY(resultData);
                }
            });
        }


        //商品详情分享
        @JavascriptInterface
        public void auctionDetailShare(String resultData, String code) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("xxxxx商品详情分享" + resultData + code);

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
         * 超级仓库分享---一键转发
         * */
        @JavascriptInterface
        public void houseShare(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("xxxxx超级仓库分享" + resultData);
                    shareHouse(resultData);
                }
            });
        }


        /*
         * 一键下单
         * */
        @JavascriptInterface
        public void houseOrder(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("xxxxx一键下单" + resultData);
                    houseCreateOrder(resultData);
                }
            });
        }


        /*
         * 一键代发
         * */
        @JavascriptInterface
        public void insteadOrderSend(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("xxxxxx一键代发" + resultData);
                    houseOrderSend(resultData);
                }
            });
        }


        /*
         * 保存二维码--推荐商家粉丝
         * */
        @JavascriptInterface
        public void saveCode(String resultData) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    Globals.log("xxxxxx保存二维码--推荐商家粉丝" + resultData);
                    String imgString = resultData.substring(resultData.indexOf(",") + 1);
                    Bitmap bitmap = stringtoBitmap(imgString);
                    FileUtils.fileDirExis(BaseGlobal.getQrCodeDir());
                    String imagPath = BaseGlobal.getQrCodeDir() + System.currentTimeMillis() + ".jpg";
                    ImageUtils.save(bitmap, imagPath, Bitmap.CompressFormat.JPEG, true);
                    // 最后通知图库更新
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagPath)));
                    ToastUtils.showShort("保存成功");

                }
            });
        }


        public Bitmap stringtoBitmap(String string) {
            //将字符串转换成Bitmap类型
            Bitmap bitmap = null;
            try {
                byte[] bitmapArray;
                bitmapArray = Base64.decode(string, Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }


        /*
         * 保存二维码--个人链接
         * */
        @JavascriptInterface
        public void savePersonCode(String resultData) {
            new Thread() {
                @Override
                public void run() {
                    //需要在子线程中处理的逻辑
                    downBitmap(resultData);
                }
            }.start();

        }


        /*
         * 打开登录界面
         * */
        @JavascriptInterface
        public void showLoginDialog() {

            Globals.log("xxxxxx打开登录界面");
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    WhiteImmersionBar();
                    barLogin = 1;
                }
            });

        }

        /*
         * 关闭登录界面
         * */
        @JavascriptInterface
        public void closeLoginDialog() {
            Globals.log("xxxxxx关闭登录界面" + barType);
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    if (barType == 0) {
                        RedImmersionBar();
                    } else if (barType == 1) {
                        GrayImmersionBar();
                    } else {
                        WhiteImmersionBar();
                    }
                }
            });

        }

    }

}



package com.leo.auction.base;


/**
 * Created by Leo on 2017/3/8.
 */
public class Constants {


    public static boolean TestState = false;

    public static String BASE_URL = "https://w.taojianlou.com/ut/";//测试
    //    public static String BASE_URL = "https://w.taojianlou.com/";//正式
    private static String WEB_BASE_URL = "https://w.taojianlou.com/";




    public static void init(boolean isTest){  //正式版 true ,
        if (isTest) {
            BASE_URL = "https://w.taojianlou.com/";
        } else {
            BASE_URL = "https://w.taojianlou.com/ut/";//测试
        }
        TestState = isTest;
    }



    public static class Nouns {   //名词类，文件名，
        //app更新下载本地的文件名
        public final static String APK_NAME = "auction.apk";
        //log
        public final static String LOG_TAG = "Leo";

        //SharedPreferences 缓存文件
        public final static String SHARE_NAME = "auction_share";
        //greenDao 数据库名字
        public final static String DB_NAME = "auction_db";


        //数据库加密key
        public final static String AES_KEY = "HI9EIS6DJKF1FKDE7EOPLKJUI6NBGHY0";
        //oss返回体加密key
        public final static String OSS_KEY = "U2FsdGVkX1/ICQ5ijcEUsICSSOEe0ps9";



        //友盟 key
        public static String QQAPPID = "101731440";
        public static String QQAPPKEY = "f06eae4b68d8f8ddde0f2fb3616cbc25";
        public static String WEIXINAPPID = "wx083c4267d81c8961";
        public static String WEIXINAPPKEY = "2dc8dd30dd5d0db67e09af2e55dea168";









    }


    public static class Var{

        //变量
        public static boolean ISLOGIN = false;//是否处于登录状态
        public static int HOME_TYPE = 0 ;//用于记录是homefragment 片段中具体哪个
        public static int SHOP_TYPE = 0 ;//用于记录是商家店铺主页 片段中具体哪个
        public static String HOME_SORT_TYPE = "HOME_SORT_TYPE" ;//用于记录是分类id





        //参数
        public final static int LAYOUT_TYPE_HEAD = 0 ;
        public final static int LAYOUT_TYPE = 1 ;
        public final static String LIST_NUMBER = "20" ;
        public final static int LIST_NUMBER_INT = 20 ;
        public final static String COMMON_PROTOCOL = "common_Protocol";
        public final static String HAS_ADDRESS = "has_address";

    }



    public static class RequestCode{

        public static int ReturnRequest_Upload_Pic= 9001;//意见反馈 上传图片(相册,照相)
        public static int RETURNREQUEST_REFRESH_LUCKYDRAWDETAIL= 9002;//抽奖详情
        public static int RETURNREQUEST_REFRESH_UPDATENAME= 9003;//修改用户名
        public static int RETURNREQUEST_REFRESH_UPDATEPHONE= 9004;//修改手机号码
        public static int RETURNREQUEST_REFRESH_UPDATEADDRESS= 9005;//修改地址
        public static int RETURNREQUEST_REFRESH_SETTING_ADDRESS= 9006;//修改地址
        public static int RETURNREQUEST_REFRESH_SELECT_RECEIVE_ADDRESS= 9007;//修改地址(订单结算页面)
        public static int RETURNREQUEST_REFRESH_SELECT_RECEIVE_ADDRESS_ORDER_DETAIL= 9008;//修改地址(订单详情页面)
        public static int RETURNREQUEST_REFRESH_ORDER_LIST= 9009;//订单详情 操作 刷新订单列表页面
        public static int RETURNREQUEST_CLOSE_ORDER_DETAIL= 9010;//订单详情 操作 关闭页面
        public static int RETURNREQUEST_ALBUM_VIDEO= 9011;//从相册获取视频
        public static int RETURNREQUEST_RECORD_VIDEO= 9012;//录制视频
        public static int RETURNREQUEST_ADD_PRIZE= 9013;//添加抽奖奖品
        public static int RETURNREQUEST_SHOP_SETTING_HEAD= 9014;//店铺设置头像
        public static int RETURNREQUEST_REFRESH_SELLER_SETTING_ADDRESS= 9015;//修改退货地址
        public static int RETURNREQUEST_REFRESH_SUPER_COIN_PAGER= 9016;//超级币转换 通知超级币页面更新
        public static int RETURNREQUEST_REFRESH_ASSET_DETAILS_PAGER= 9017;//超级币转换 通知资产明细面更新
        public static int RETURNREQUEST_REFRESH_PRODUCT_DETAIL_FOLLOW= 9018;//商家店铺关注
        public static int RETURNREQUEST_SELLER_AGREE_REFUND= 9019;//同意退款
        public static int RETURNREQUEST_SELLER_REFUSE_REFUND= 9020;//拒绝退款
        public static int RETURNREQUEST_REFRESH_WARRANTLIST= 9021;//缴纳记录更新
        public static int RETURNREQUEST_REFRESH_SELLER_SETTING_RECEIVE_ADDRESS= 9022;//修改收货地址
        public static int RETURNREQUEST_GET_SCAN_QRCODE= 9023;//扫码识别出来的二维码
        public static int RETURNREQUEST_IM_SEND_PRODUCT_QRCODE= 9024;//im发送商品

        public static int RETURNREQUEST_REFRESH_SELECT_RECEIVE_ADDRESS_SUP= 9025;//直播供货修改地址

    }





    public static class SharePerKey {
        public final static String DEVICE_KEY = "device";
        public final static String CHANNEL_KEY = "channel";
        public final static String LOGIN_KEY = "login"; //登录key
        public final static String LOGIN_TIME_KEY = "login_time";
        public final static String LOGIN_NAME_KEY = "login_name";
        public final static String LOGIN_PWD_KEY = "login_pwd";

    }


    public static class Action {

        public static String ACTION_LOGIN = "action.login";//登录超时跳转登录页
        public static String ACTION_REFRESH_HOME = "send.refresh.home";//刷新首页
        public static String ACTION_REFRESH_HOME_ALL= "send.refresh.home.all";//刷新首页
        public static String ACTION_OPTION_LOGIN_ACTIVITY = "option.login_activity";//操作登录页面--关闭登录


        public static String ACTION_HOME_SEARCH = "ACTION_HOME_SEARCH";//刷新搜索

        public static String ACTION_VERIFIED_LOGIN = "option.receiver_verified_login";//h5验证码





        public static String SEND_REFRESH_COLLECTION = "send.refresh.collection";//刷新收藏页面
        public static String SEND_REFRESH_HOME = "send.refresh.home";//刷新首页
        public static String SEND_REFRESH_SUPERPURCHASE = "send.refresh.superpurchase";//刷新超级购
        public static String SEND_REFRESH_DAILY_LOTTERY = "send.refresh.daily_lottery";//刷新天天抽检页面
        public static String SEND_REFRESH_FOLLOW_LIST = "send.refresh.follow_list";//刷新关注列表页面
        public static String SEND_REFRESH_PRODUCT_DETAIL = "send.refresh.product_detail";//刷新商品详情页
        public static String SEND_REFRESH_ORDER_LIST = "send.refresh.order_list";//刷新订单列表
        public static String SEND_REFRESH_SELLER_ORDER_LIST = "send.refresh.seller_order_list";//刷新卖家订单列表
        public static String SEND_REFRESH_MIAN_SHOPPINGCAR = "send.refresh.mian_shoppingcar";//刷新购物车页面
        public static String SEND_REFRESH_COMMODITY_MANAGEMENTFRAGMENT = "send.refresh.commodity_managementFragment";//刷新商品页面
        public static String SEND_REFRESH_SHOPPINGCARTFRAGMENT = "send.refresh.shoppingCartFragment";//刷新购物车页面
        public static String SEND_REFRESH_SHOPPINGCARTLIVE = "send.refresh.shoppingCartLive";//刷新购物车页面
        public static String SEND_REFRESH_LIVESHOPGOODS = "send.refresh.live.shop.goods";//直播供货
        public static String SEND_REFRESH_LIVESHOPSUP = "send.refresh.live.shop.sup";// 供货记录
        public static String SEND_REFRESH_LIVESHOPSALE = "send.refresh.live.shop.sale";// 供货记录
        public static String SEND_REFRESH_CANCELLATIONDEPOSITDETAILSFRAGMENT = "send.refresh.cancellationDepositDetailsFragment";//刷新消保金列表页面
        public static String SEND_START_MAINACTIVITY_0 = "start.mainactivity_0";//跳转首页
        public static String SEND_OPTION_LOGIN_ACTIVITY = "option.login_activity";//操作登录页面
        public static String SEND_OPTION_RECEIVER_RECALL = "option.receiver_recall";//获取到撤回消息的通知
        public static String SEND_MINE_FAN_LIST = "option.receiver_mine_fans";//粉丝列表搜索
        public static String SEND_VERIFIED_LOGIN = "option.receiver_verified_login";//h5验证码

    }



    //web请求api
    public static class WebApi {
        public static String HOMEPAGE_RULE_AGREEMENT_URL =WEB_BASE_URL+"rule/agreement.html";// 规则说明
        public static String HOMEPAGE_RULE_VIPSERVICE_URL =WEB_BASE_URL+"rule/vipservice.html";// 用户协议
        public static String HOMEPAGE_RULE_PRIVACYPROTECTION_URL =WEB_BASE_URL+"rule/privacyprotection.html";// 隐私保护政策
    }



    public static class Api {   //接口api 地址

        public static String domain = BASE_URL + "auction/";
        //公共参数，等级图片
        public static String COMMON_URL = domain + "platform/common-params";





        //oss
        public static String OSS_FOLDER = BASE_URL+"Android";
        public static String OSS_FOLDER_IMG_GOODS = OSS_FOLDER+"/goods/img";
        public static String OSS_FOLDER_IMG_USER = OSS_FOLDER+"/user/img";
        public static String OSS_FOLDER_VIDEO = OSS_FOLDER+"/video";
        public static String OSS_FOLDER_IM_AUDIO_USER = OSS_FOLDER+"/im/audio";
        public static String OSS_FOLDER_IM_VIDEO_USER = OSS_FOLDER+"/im/video";
        public static String OSS_FOLDER_WATERMASK_USER = OSS_FOLDER+"/watermask";

        //登录注册
        public static String HOMEPAGE_SEND_SMS_URL = BASE_URL + "client/" + "sms/send";//发送验证码
        public static String HOMEPAGE_USER_PHONE_LOGIN_URL = domain + "user/phoneLogin";//用户登录
        public static String HOMEPAGE_USER_DEFAULT_LOGIN_URL = domain + "user/default-login";//用户静默登录
        public static String HOMEPAGE_USER_WX_LOGIN_URL = domain + "user/wx-login";//  微信授权登录
        public static String USER_URL = domain + "user/info";//



        //首页全部
        public static String HOME_ALL_URL = domain + "instance/all";//
        public static String HOME_UNITARY_URL = domain + "instance/unitary";//
        public static String HOME_PICK_URL = domain + "instance/pick-leaky";//
        public static String HOME_NEWEST_URL = domain + "instance/newest";//
        public static String HOME_ABOUT_URL = domain + "instance/about-intercept";//
        public static String HOME_SEARCH_MU_URL = domain + "instance/search-multiple";//
        public static String HOME_SEARCH_NEW_URL = domain + "instance/search-newest";//
        public static String HOME_SEARCH_INT_URL = domain + "instance/search-about-intercept";//


        //商家列表
        public static String HOME_SEARCH_SUPPLIER_URL = domain + "supplier";//
        public static String SHOP_URL = domain + "shop/info";//
        public static String SHOP_PICK_URL = domain + "shop/pick-leaky";//
        public static String SHOP_NEWEST_URL = domain + "shop/newest";//
        public static String SHOP_INTERCEPT_URL = domain + "shop/about-intercept";//
        public static String SHOP_HOT_URL = domain + "shop/hot";//



        //分类
        public static String SORT_SORT_URL = domain + "category/list";//
        public static String SORT_MULTIPLE_URL = domain + "category/multiple";//
        public static String SORT_NEWST_URL = domain + "category/newest";//
        public static String SORT_INTERCEPT_URL = domain + "category/about-intercept";//



        //上拍商品详情
        public static String GOODS_DETAIL_URL = domain + "instance/info";//
        public static String GOODS_DETAIL_BID_URL = domain + "bid";//




        //消息
        public static String NEWS_SYS_URL = domain + "notice";//










    }


}

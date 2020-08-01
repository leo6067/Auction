package com.leo.auction.base;


/**
 * Created by Leo on 2017/3/8.
 */
public class Constants {

    public static boolean logGone = false;
    public static String BASE_URL = "";
    public static String WEB_BASE_URL = "";


    //正式版 true , 地址切换
    public static void init(boolean  releaseVersion){
        if (releaseVersion) {
            BASE_URL = "https://cd.taojianlou.com/";
            WEB_BASE_URL = "https://cd.taojianlou.com/";
        } else {
            BASE_URL = "https://cd.taojianlou.com/ut/";//测试
            WEB_BASE_URL = "https://cd.taojianlou.com/ut/";
        }
        logGone = releaseVersion;
    }



    public static class Nouns {   //名词类，文件名，



        public final static String PACK_NAME = "com.leo.auction";

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
        public static String WEIXINAPPID = "wx083c4267d81c8961"; //7.10 新版
        public static String WEIXINAPPKEY = "2dc8dd30dd5d0db67e09af2e55dea168";


    }


    public static class Var{

        //变量
        public static boolean ISLOGIN = false;//是否处于登录状态
//        public static int HOME_TYPE = 0 ;//用于记录是homefragment 片段中具体哪个     // 5开始是关注
        public static int SHOP_TYPE = 0 ;//用于记录是商家店铺主页 片段中具体哪个
        public static int FOCUS_TYPE = 0 ;//用于记录是关注Tab 片段中具体哪个
        public static int MINE_TYPE = 0 ;//用于记录是个人中心是 买入 还是卖出订单
        public static String HOME_SORT_TYPE = "HOME_SORT_TYPE" ;//用于记录是分类id

        public static int PPGL_SORT_TYPE = 0 ;//用于记录是拍品管理当前是哪个分类
        public static int PPGL_SORT_VALUE = 0 ;//用于记录是拍品管理当前是哪个分类接口分类值

        public static int MONEY_NO_PWD = 300 ;//设置免密支付金额


        public static String AA = "00A" ;
        public static String BB = "00B" ;






        //参数
        public final static int LAYOUT_TYPE_HEAD = 0 ;
        public final static int LAYOUT_TYPE = 1 ;
        public final static String LIST_NUMBER = "25" ;
        public final static int LIST_NUMBER_INT = 25 ;
        public final static String LIST_NUMBER_F = "5" ;
        public final static int LIST_NUMBER_INT_F= 5 ;

        public final static String COMMON_PROTOCOL = "common_Protocol";
        public final static String HAS_ADDRESS = "has_address";


        public final static String LIST_MAX = "300";

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
        public static String ACTION_MANAGEMENT_TYPE = "ACTION_MANAGEMENT_TYPE";//拍卖管理竞拍中已截拍等



        public static String ACTION_VERIFIED_LOGIN = "option.receiver_verified_login";//h5验证码

        //首页类别刷新
        public static String ACTION_HOME_TYPE = "ACTION_HOME_TYPE";
        //店铺首页类别刷新
        public static String ACTION_SHOP_TYPE = "ACTION_SHOP_TYPE";

        //关注页面类别
        public static String ACTION_FOCUS_TYPE = "ACTION_FOCUS_TYPE";


        //用户行为
        public static String ACTION_ACTION  = "1";  //     1-首页 2-分类频道 3-店铺推荐  4-关注-拍品 5-参拍  6-足迹  7-收藏



        //商品详情刷新
        public static String ACTION_DETAIL_REFRESH = "ACTION_DETAIL_REFRESH";




        public static String SEND_REFRESH_ORDER_LIST = "send.refresh.order_list";//刷新订单列表


    }



    //web请求api
    public static class WebApi {
        public static String HOMEPAGE_RULE_AGREEMENT_URL =WEB_BASE_URL+"rule/agreement.html";// 规则说明
        public static String HOMEPAGE_RULE_VIPSERVICE_URL =WEB_BASE_URL+"rule/vipservice.html";// 用户协议
        public static String HOMEPAGE_RULE_PRIVACYPROTECTION_URL =WEB_BASE_URL+"rule/privacyprotection.html";// 隐私保护政策
        public static String HOMEPAGE_SUBSIDY_URL =WEB_BASE_URL+"auction-web/pages/sub/bysubsidy/index?isMargin=4&subsidyToken=";// 百亿补贴地址
        public static String WEB_MINE_URL =WEB_BASE_URL+"auction-web/pages/personal/personal?isMargin=4&subsidyToken=";// 我的


        public static String WEB_REPROCT_URL =WEB_BASE_URL+"auction-web/pages/sub/complaint/list?isMargin=4&productInstanceCode=";// 举报
        public static String SHARE_PRODUCT_URL =WEB_BASE_URL+"auction-web/pages/sub/product/productDetail?productInstanceCode=";// 分享拍品详情
        public static String SHARE_SHOP_URL =WEB_BASE_URL+"auction-web/pages/sub/mercahnt/index?shopUri=";// 分享拍品详情
        public static String QRCODE_URL =WEB_BASE_URL+"auction-web?tpm_shareAgentId=";// 生成二维码
        public static String SORT_URL =WEB_BASE_URL+"auction-web/pages/category/category?tpm_shareAgentId=";// 分享 分类


        public static String YZM_URL =" https://w.taojianlou.com/super-store/hd2.html";// 登录验证码






    }



    public static class Api {   //接口api 地址

        public static String domain = BASE_URL + "auction/";
        //公共参数，等级图片
        public static String COMMON_URL = domain + "platform/common-params";

        //智能解析地址
        public static String ADDRESS_SMART_URL = "https://w.taojianlou.com/express/address-search";//



        //短信验证
        public static String SMS_URL = domain+"platform/sms";


        //文件
        public static String FILE_DEL = domain+"upload/del-file";

        //版本更新
        public static String VERSION_URL = domain+"platform/version";


        //用户行为
        public static String ACTION_USER = domain+"action/user";

        //退出登录
        public static String LOGINOUT_URL = domain+"user/logout";


        //协议
        public static String SCENE_URL = domain+"strategy/scene";


        //生成分享二维码
        public static String QCODE_URL = domain+"spread/qrcode";

        //oss
        public static String OSS_FOLDER = "Android";
        public static String OSS_COMMON_URL = domain+"platform/oss";
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
        public static String HOME_SUBSIDY_URL = domain + "instance/subsidy";//
        public static String HOME_UNITARY_URL = domain + "instance/unitary";//
        public static String HOME_PICK_URL = domain + "instance/pick-leaky";//
        public static String HOME_NEWEST_URL = domain + "instance/newest";//
        public static String HOME_ABOUT_URL = domain + "instance/about-intercept";//

        public static String HOME_SECOND_URL = domain + "instance/second-hand";// 二手
        public static String HOME_ALL_PRODUCT_URL = domain + "instance/all-product";// 万物拍
        public static String HOME_SEARCH_MU_URL = domain + "instance/search-multiple";//
        public static String HOME_SEARCH_NEW_URL = domain + "instance/search-newest";//
        public static String HOME_SEARCH_INT_URL = domain + "instance/search-about-intercept";//



        //关注
        public static String SORT_FOLLOW_URL = domain + "follow/product";//
        public static String SORT_FOLLOW_SHOP_URL = domain + "follow/shop";//
        public static String SORT_COLLECT_URL = domain + "collect";//
        public static String SORT_PARTAKE_URL = domain + "follow/partake";//
        public static String SORT_FOOT_PARTAKE_URL = domain + "follow/footmark";//




        //商家列表
        public static String HOME_SEARCH_SUPPLIER_URL = domain + "supplier";//
        public static String SHOP_URL = domain + "shop/info";//
        public static String SHOP_PICK_URL = domain + "shop/pick-leaky";// 捡漏
        public static String SHOP_NEWEST_URL = domain + "shop/newest";//
        public static String SHOP_INTERCEPT_URL = domain + "shop/about-intercept";//
        public static String SHOP_HOT_URL = domain + "shop/hot";//  热门



        //分类
        public static String SORT_SORT_URL = domain + "category/list";//
        public static String SORT_MULTIPLE_URL = domain + "category/multiple";//
        public static String SORT_NEWST_URL = domain + "category/newest";//
        public static String SORT_INTERCEPT_URL = domain + "category/about-intercept";//



        //上拍商品详情
        public static String GOODS_DETAIL_URL = domain + "instance/info";//
        public static String GOODS_DETAIL_BID_URL = domain + "bid";//出价  //列表
        public static String GOODS_DETAIL_ATTR_URL = domain + "product/attribute";//
        public static String GOODS_NEWEST_URL = domain + "product/newest";//
        public static String GOODS_STORE_URL = domain + "product/store-goods";//
//        public static String GOODS_REPROCT_URL = domain + "report";//








        //消息
        public static String NEWS_SYS_URL = domain + "notice";//



        //地址
        public static String ADDRESS_URL = domain + "address";//修改
        public static String ADDRESS_INFO_URL = domain + "address/info";//
        public static String ADDRESS_DISTRICT_URL = domain + "address/district-children";//




        //余额
        public static String BALANCE_URL = domain + "balance/info";//
        public static String BALANCE_EXEMPT_URL = domain + "user/balance-exempt";//
        public static String BALANCE_WITHDRAWNUM_URL = domain + "balance/withdraw-num";//
        public static String BALANCE_CATEGORY_URL = domain + "category/balance";//
        public static String BALANCE_WITHDRAW_URL = domain + "balance/withdraw";//
        public static String BALANCE_DETAIL_URL = domain + "balance/detail";//
        public static String BALANCE_LOG_URL = domain + "balance/log";//



        //密码
        public static String PAY_PWD_URL = domain + "user/pay-pwd";//
        public static String RESET_PWD_URL = domain + "user/reset-pay-pwd";//




        //实名认证
        public static String REAL_NAME_URL = domain + "user/real-name";//




        //拍卖管理
        public static String CATE_PRODUCT_URL = domain + "category/product";//
        public static String PRODUCT_URL = domain + "product";//
        public static String PRODUCT_DRAFT_URL = domain + "product/draft";//
        public static String GOODS_TIME_URL = domain + "product/time-node";//
        public static String GOODS_UPPER_URL = domain + "product/upper";
        //下架
        public static String GOODS_LOWER_URL = domain + "product/lower";//

        //最新上拍
        public static String INSTANCE_NEWEST_URL = domain + "instance/instance-newest";//



        //支付
        public static String PAY_ORDER_URL = domain + "pay/unified-order";//




        //订单
        public static String ORDER_LIST_URL = domain + "order/list";//

        //猜你喜欢
        public static String ORDER_LIKE_URL = domain + "order/you-like";//




        //提交评价  //获取评价详情
        public static String ORDER_ESTIMATE_URL = domain + "order/estimate";//

        //获取评价详情
        public static String ORDER_INFO_URL = domain + "order/info";//

        //提醒发货
        public static String ORDER_REMIND_SEND_URL = domain + "order/remind-send";//
        //延迟发货
        public static String ORDER_DELAY_SEND_URL = domain + "order/delay-send";
        //确认发货
        public static String ORDER_CONFIRM_SEND_URL = domain + "order/confirm-send";
        //确认收货
        public static String ORDER_CONFIRM_TAKE_URL = domain + "order/confirm-take";
        //延迟收货
        public static String ORDER_DELAY_CONFIRM_TAKE_URL = domain + "order/delay-confirm-take";
       //当面交易
        public static String ORDER_FACE_TRADE_URL = domain + "order/face-trade";
        //延迟付款
        public static String ORDER_DELAY_PAY_URL = domain + "order/delay-pay";


        //修改单号
        public static String ORDER_EXPRESS_URL = domain + "order/express";

        //取消退款
        public static String REFUND_CANCEL_URL = domain + "refund/cancel";



        //百亿补贴
        public static String SUBSIDY_URL = domain + "subsidy";
        public static String SUBSIDY_LIST_URL = domain + "subsidy/suspension-list";
        public static String SUBSIDY_LIST_PRICE_URL = domain + "subsidy/suspension-list-count";


        //生产推荐二维码
        public static String SPREAD_QRCODE_URL = domain + "spread/qrcode";




        //是否关注店铺
        public static String SHOP_FOCUS_URL = domain + "follow/is-follow";
        public static String FOCUS_URL = domain + "follow";
    }


}

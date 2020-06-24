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



    }


    public static class Api {   //接口api 地址


        public static String domain = BASE_URL + "auction/";

        public static String COMMON_URL = domain + "platform/common-params";


        //登录注册
        public static String HOMEPAGE_SEND_SMS_URL = BASE_URL + "client/" + "sms/send";//发送验证码
        public static String HOMEPAGE_USER_PHONE_LOGIN_URL = domain + "user/phoneLogin";//用户登录
        public static String HOMEPAGE_USER_DEFAULT_LOGIN_URL = domain + "user/default-login";//用户静默登录
        public static String HOMEPAGE_USER_WX_LOGIN_URL = domain + "user/wx-login";//  微信授权登录



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









    }


}

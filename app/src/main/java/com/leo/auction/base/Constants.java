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
        public static boolean SHOW_IM_PAGER = true;//是否展示im模块
        public static int STB_MINE_SELECT_TAG = -1;//记录需要展示的tab栏目 -1表示还没设置过tab选中的栏目 0代表选择过我是买家 1代表选择过我是卖家
        public final static String COMMON_PROTOCOL = "common_Protocol";
        public final static String HAS_ADDRESS = "has_address";

        public static int HOME_TYPE = 0 ;//用于记录是homefragment 片段中具体哪个

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
        public static String SEND_REFRESH_HOME = "send.refresh.home";//刷新首页
        public static String SEND_REFRESH_COLLECTION = "send.refresh.collection";//刷新收藏页面
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


    public static class Api {   //接口api 地址

        public final static String domain = BASE_URL + "auction/";
        public final static String Login_key_URL = domain + "member/GetKey.ashx";//3.1.1域名路径
        public final static String GetCode_URL = domain + "member/register.ashx?type=GetCode";//请求验证码
        public final static String judgeCodeNew_URL = domain + "member/register.ashx?type=judgeCodeNew";//验证验证码

        public static String HOMEPAGE_PLATFORM_OSS_URL = domain + "platform/oss";// 获取OSS相关信息
        public static String HOMEPAGE_ADS_URL = domain + "content/v2/list";//首页广告
        public static String HOMEPAGE_SORT_URL = domain + "category/v2/list";//首页分类
        public static String HOMEPAGE_GOODS_LIST_URL = domain + "goods/list";//首页商品列表
        public static String HOMEPAGE_GOODS_CATE_LIST_URL = domain + "goods/category-list";//分类商品列表
        public static String HOMEPAGE_SUPER_PURCHASE_LIST_URL = domain + "activity/ssp/list";//超级购列表
        public static String HOMEPAGE_SEND_SMS_URL = domain + "sms/send";//发送验证码
        public static String HOMEPAGE_USER_PHONE_LOGIN_URL = domain + "user/phoneLogin";//用户登录
        public static String HOMEPAGE_USER_DEFAULT_LOGIN_URL = domain + "user/default-login";//用户静默登录
        public static String HOMEPAGE_USER_INFO_URL = domain + "user/info";//获取用户信息
        public static String HOMEPAGE_DAILY_LOTTERY_MARQUEE_SWIPER_URL = domain + "prize/swiper";//天天抽奖跑马灯数据
        public static String HOMEPAGE_DAILY_LOTTERY_MARQUEE_URL = domain + "prize/balance-num";//天天抽奖今日剩余抽奖次数
        public static String HOMEPAGE_DAILY_LOTTERY_LIST_URL = domain + "prize/list";//天天抽奖奖品列表
        public static String HOMEPAGE_GOODS_DESC_URL = domain + "goods/desc";//商品详情头部页面
        public static String HOMEPAGE_GOODS_DESC_SHOP_LIST_URL = domain + "goods/shop-list";//商品详情店铺推荐页面
        public static String HOMEPAGE_GOODS_COLLECT_URL = domain + "collect/goods";// 收藏商品
        public static String HOMEPAGE_GOODS_COLLECT_LIST_URL = domain + "collect/list";// 收藏商品列表
        public static String HOMEPAGE_GOODS_COLLECT_ISCOLLECT_URL = domain + "collect/iscollect";// 是否收藏
        public static String HOMEPAGE_FOLLOW_ISFOLLOW_URL = domain + "follow/isfollow";// 是否关注
        public static String HOMEPAGE_FOLLOW_SHOP_URL = domain + "follow/shop";// 是否关注
        public static String HOMEPAGE_PRIZE_WIN_LIST_URL = domain + "prize/win-list";// 中奖公示
        public static String HOMEPAGE_PRIZE_JOIN_LIST_URL = domain + "prize/join-list";// 参与记录
        public static String HOMEPAGE_PRIZE_INFO_URL = domain + "prize/info";// 抽奖详情
        public static String HOMEPAGE_PRIZE_JOIN_URL = domain + "prize/join";// 参与抽奖
        public static String HOMEPAGE_PRIZE_EXCHANG_LOTTERY_NUM_URL = domain + "kucoin-task/convert";// 兑换抽奖次数
        public static String HOMEPAGE_PRIZE_DAILY_TASK_LIST_URL = domain + "kucoin-task/list";//  每日任务列表
        public static String HOMEPAGE_PRIZE_DAILY_TASK_SIGNIN_URL = domain + "kucoin-task/signin";//每日签到
        public static String HOMEPAGE_CATEGORY_LIST_URL = domain + "category/list";//全部分类
        public static String HOMEPAGE_SUPPLIER_INFO_URL = domain + "supplier/info";//商家首页头部
        public static String HOMEPAGE_SUPPLIER_APPLY_URL = domain + "supplier/apply";//商户申请
        public static String HOMEPAGE_USER_PERSONSET_URL = domain + "user/personset";//个人设置
        public static String HOMEPAGE_ADDRESS_ADD_URL = domain + "address/add";//添加收货地址
        public static String HOMEPAGE_ADDRESS_LIST_URL = domain + "address/user";//收货地址
        public static String HOMEPAGE_ADDRESS_DEL_URL = domain + "address/del";//删除收货地址
        public static String HOMEPAGE_ADDRESS_EDIT_URL = domain + "address/edit";//更新收货地址
        public static String HOMEPAGE_DISTRICT_CHILDREN_LIST_URL = domain + "district/children";//子级行政区划
        public static String HOMEPAGE_SELECT_GOODS_LIST_URL = domain + "activity/yxhw/list";//优选好物列表
        public static String HOMEPAGE_KUCOIN_INFO_URL = domain + "kucoin/info";//金币信息
        public static String HOMEPAGE_FOLLOW_LIST_URL = domain + "follow/list";//关注列表
        public static String HOMEPAGE_FOLLOW_TOP_URL = domain + "follow/top";//关注置顶
        public static String HOMEPAGE_ACTION_FOOTMARK_URL = domain + "action/footmark";//用户足迹
        public static String HOMEPAGE_ACTION_CLICK_URL = domain + "action/click";// 商品点击
        public static String HOMEPAGE_USER_ADDPAYPWD_URL = domain + "user/addPayPwd";// 添加支付密码
        public static String HOMEPAGE_USER_RESETPAYPWD_URL = domain + "user/resetPayPwd";// 重置支付密码
        public static String HOMEPAGE_USER_BALANCEEXEMPT_URL = domain + "user/balanceExempt";// 余额免密支付
        public static String HOMEPAGE_SUPPLIER_APPLICATIONINFO_URL = domain + "supplier/applicationInfo";// 查看商户申请
        public static String HOMEPAGE_BALANCE_CATEGORY_URL = domain + "balance/category";// 余额明细分类
        public static String HOMEPAGE_BALANCE_LIST_URL = domain + "balance/list";// 余额明细
        public static String HOMEPAGE_QUERY_ADDRESS_URL = domain + "address/user";// 查询收货地址
        public static String HOMEPAGE_ORDER_COMPUTE_POSTAGE_URL = domain + "order/compute-postage";// 计算订单最终邮费
        public static String HOMEPAGE_ORDER_COMPUTE_POSTAGE_URL_V2 = domain + "order/compute-postage-v2";// 计算订单最终邮费
        public static String HOMEPAGE_ORDER_ADD_URL = domain + "order/add";//添加订单
        public static String HOMEPAGE_ORDER_ADD_URL_V = domain + "order/add-v2";//添加订单
        public static String HOMEPAGE_ORDER_BALANCE_PAY_URL = domain + "pay/unified-order";//余额支付 （微信支付获取支付参数）
        public static String HOMEPAGE_ORDER_BALANCE_PAY_URL_V = domain + "order/pay-v2";//余额支付 （微信支付获取支付参数）
        public static String HOMEPAGE_ORDER_LIST_URL = domain + "order/list";//订单列表
        public static String HOMEPAGE_ORDER_DESC_URL = domain + "order/desc";//订单详情
        public static String HOMEPAGE_ORDER_DESC_EXPRESS_ALIQUERY_URL = BASE_URL + "express/ali-query";//物流轨迹
        public static String HOMEPAGE_ORDER_EDIT_ADDRESS_URL = domain + "order/edit-address";//修改订单地址
        public static String HOMEPAGE_ORDER_CANCEL_URL = domain + "order/cancel";//取消订单
        public static String HOMEPAGE_ORDER_CONFIRM_TAKE_URL = domain + "order/confirm-take";//确认收货
        public static String HOMEPAGE_ORDER_COMMENT_URL = domain + "order/comment";// 订单评价
        public static String HOMEPAGE_ORDER_COMMENT_INFO_URL = domain + "order/comment-info";// 订单评价详情
        public static String HOMEPAGE_ORDER_REFUND_APPLICATION_URL = domain + "refund/application";// 申请退款
        public static String HOMEPAGE_ORDER_EXPRESS_USUALLY_URL = domain + "order/express-usually";// 获取卖家常用的六个快递
        public static String HOMEPAGE_ORDER_REFUND_DETAIL_URL = domain + "refund/info";// 售后详情
        public static String HOMEPAGE_ORDER_REFUND_CANCEL_URL = domain + "refund/cancel";// 取消退款
        public static String HOMEPAGE_ORDER_REFUND_SEND_URL = domain + "refund/send";// 退货
        public static String HOMEPAGE_ORDER_REFUND_INTERVENE_URL = domain + "refund/intervene";// 客服介入
        public static String HOMEPAGE_USER_FEEDBACK_URL = domain + "user/feedback";// 意见反馈
        public static String HOMEPAGE_PRIZE_ORDER_DETAIL_URL = domain + "prize-order/info";// 抽奖订单详情
        public static String HOMEPAGE_PRIZE_ORDER_USER_CONFIRM_URL = domain + "prize-order/user-confirm";// 抽奖订单买家确认
        public static String HOMEPAGE_PRIZE_ORDER_CONFIRM_TAKE_URL = domain + "prize-order/confirm-take";//  抽奖订单确认收货
        public static String HOMEPAGE_PRIZE_ORDER_COMMENT_URL = domain + "prize-order/comment";//  抽奖订单评价
        public static String HOMEPAGE_PRIZE_ORDER_COMMENT_INFO_URL = domain + "prize-order/comment-info";//  抽奖订单评价详情
        public static String HOMEPAGE_SUPPLIER_FANSLIST_URL = domain + "supplier/fansList";//  粉丝列表
        public static String HOMEPAGE_SUPPLIER_FANSEXCLUSIVE_URL = domain + "supplier/fansExclusive";//  专属粉丝列表

        public static String HOMEPAGE_SUPPLIER_FANSLIST_URL_v2 = domain + "supplier/v2/fansList";//  粉丝列表
        public static String HOMEPAGE_SUPPLIER_FANSEXCLUSIVE_URL_v2 = domain + "supplier/v2/fansExclusive";//  专属粉丝列表

        public static String HOMEPAGE_ORDER_CONFIRM_SHIP_URL = domain + "order/confirm-ship";//  确认发货
        public static String HOMEPAGE_ORDER_EDIT_EXPRESS_URL = domain + "order/edit-express";//  修改物流单号
        public static String HOMEPAGE_ORDER_REFUND_AGREE_URL = domain + "refund/agree";//  同意退款
        public static String HOMEPAGE_ORDER_REFUND_REJECT_URL = domain + "refund/reject";//   拒绝
        public static String HOMEPAGE_ORDER_REFUND_CONFIRM_SEND_URL = domain + "refund/confirm-send";//   收货
        public static String HOMEPAGE_PRIZE_ORDER_SELLER_CONFIRM_URL = domain + "prize-order/seller-confirm";//   抽奖订单卖家确认
        public static String HOMEPAGE_CATEGORY_PUB_LIST_URL = domain + "category/pub-list";//   分类列表(发布时使用)
        public static String HOMEPAGE_GOODS_PUBLISH_URL = domain + "goods/publish";//   商品发布
        public static String HOMEPAGE_GOODS_NEWEST_URL = domain + "goods/newest";//   获取发布的最新商品
        public static String HOMEPAGE_PROTOCOL_LIST_URL = domain + "protocol/list";//   协议列表
        public static String HOMEPAGE_PROTOCOL_INFO_URL = domain + "protocol/info";//   协议详情
        public static String HOMEPAGE_SUPPLIER_LIST_URL = domain + "goods/supplier-list";//   商品管理列表
        public static String HOMEPAGE_GOODS_LOWERSHELF_URL = domain + "goods/lowershelf";//   商品下架
        public static String HOMEPAGE_GOODS_UPPERSHELF_URL = domain + "goods/uppershelf";//   商品上架
        public static String HOMEPAGE_SUPPLIER_EXISTS_CATEGORY_URL = domain + "supplier/exists-category";//   商家所有存在商品的二级分类列表
        public static String HOMEPAGE_GOODS_EDIT_URL = domain + "goods/edit";//   商品修改
        public static String HOMEPAGE_MYPRIZE_LIST_URL = domain + "my-prize/list";//   奖品列表
        public static String HOMEPAGE_MYPRIZE_ADDGOODS_URL = domain + "my-prize/addgoods";//   添加奖品
        public static String HOMEPAGE_MYPRIZE_EDITGOODS_URL = domain + "my-prize/editgoods";//   修改奖品
        public static String HOMEPAGE_MYPRIZE_INFO_URL = domain + "my-prize/info";//   奖品详情
        public static String HOMEPAGE_MYPRIZE_START_URL = domain + "my-prize/start";//   开启抽奖
        public static String HOMEPAGE_EXTEND_SSP_LIST_URL = domain + "extend/ssp-list";//   超级购列表
        public static String HOMEPAGE_EXTEND_SSP_RECOMMEND_URL = domain + "activity/ssp/join";//   超级购列表推荐 取消推荐
        public static String HOMEPAGE_EXTEND_YXHW_LIST_URL = domain + "extend/yxhw-list";//  优选好物列表
        public static String HOMEPAGE_EXTEND_YXHW_RECOMMEND_URL = domain + "activity/yxhw/join";//  优选好物列表推荐 取消推荐
        public static String HOMEPAGE_CART_ADD_URL = domain + "cart/add";//  添加到购物车
        public static String HOMEPAGE_CART_LIST_URL = domain + "cart/list";//  购物车列表
        public static String HOMEPAGE_CART_DEL_URL = domain + "cart/del";//  购物车删除商品
        public static String HOMEPAGE_CART_EDIT_URL = domain + "cart/edit";//  编辑购物车
        public static String HOMEPAGE_QRCODE_GENERATE_URL = domain + "qrcode/generate-v2";//  生成二维码
        public static String HOMEPAGE_USER_FIXSUPPLIER_URL = domain + "user/fixsupplier";//  固定我的为商家
        public static String HOMEPAGE_USER_WX_LOGIN_URL = domain + "user/wx-login";//  微信授权登录
        public static String HOMEPAGE_USER_LOGOUT_URL = domain + "user/logout";//   登出
        public static String HOMEPAGE_WARRANT_LIST_URL = domain + "warrant/list";//   消保金明细
        public static String HOMEPAGE_KUCOIN_CATEGORY_URL = domain + "kucoin/category";//   金币明细分类
        public static String HOMEPAGE_KUCOIN_LIST_URL = domain + "kucoin/list";//   金币明细列表
        public static String HOMEPAGE_KUCOIN_WITHDRAW_URL = domain + "kucoin/withdraw";//   金币提现
        public static String HOMEPAGE_EXPRESS_ADDRESS_SEARCH_URL = BASE_URL + "express/address-search";//   一键识别地址
        public static String HOMEPAGE_BALANCE_WITHDRAW_URL = domain + "balance/withdraw";//   余额提现
        public static String HOMEPAGE_BALANCE_WITHDRAWNUM_URL = domain + "balance/withdraw-num";//   当日提现次数
        public static String HOMEPAGE_BALANCE_RECEIVER_SHIP_URL = domain + "balance/receiver-ship";//   待发货,待收货的金额
        public static String HOMEPAGE_WARRANT_CATEGORY_URL = domain + "warrant/category";//    消保金明细分类列表
        public static String HOMEPAGE_WARRANT_ADD_URL = domain + "warrant/add";//    生成消保金单号
        public static String HOMEPAGE_WARRANT_RETRACTABLE_URL = domain + "warrant/retractable";//    消保金缴纳记录列表
        public static String HOMEPAGE_WARRANT_THAW_URL = domain + "warrant/thaw";//    申请解冻消保金
        public static String HOMEPAGE_PAY_CHECK_URL = domain + "pay/check";//     查询交易订单
        public static String HOMEPAGE_QRCODE_GENERATE_GOODS_URL = domain + "qrcode/generate";//     生成二维码
        public static String HOMEPAGE_GOODS_CLEAR_URL = domain + "goods/clear";//     清空草稿箱
        public static String HOMEPAGE_GOODS_A_LIST_URL = domain + "goods/a-list";//      用户上级代理商品列表
        public static String HOMEPAGE_ORDER_CANCEL_REASON_URL = domain + "order/cancel-reason";//      取消订单原因列表
        public static String HOMEPAGE_VERSION_URL = domain + "version";//      版本更新
        public static String HOMEPAGE_UPLOAD_USER_WATERMARK_URL = domain + "upload/user/watermark";//      图片上传带平台水印
        public static String HOMEPAGE_SHARE_SET_URL = domain + "share-set";//      分享设置
        public static String HOMEPAGE_ACTION_LOG_URL = domain + "action/log";//      用户记录
        public static String HOMEPAGE_USER_AGENT_URL = domain + "user/agent";//      获取代理申请信息
        public static String HOMEPAGE_USER_BINDPHONE_URL = domain + "user/bind-phone";//      手机号是否被绑定过
        public static String HOMEPAGE_PARTNER_INFO_URL = domain + "partner/info";//      获取合伙人信息
        public static String HOMEPAGE_PARTNER_CATEGORY_URL = domain + "partner/category";//      合伙人金币分类
        public static String HOMEPAGE_PARTNER_COIN_URL = domain + "partner/coin";//      合伙人金币明细
        public static String HOMEPAGE_PARTNER_WITHDRAW_NUM_URL = domain + "partner/withdraw-num";//      当日提现次数
        public static String HOMEPAGE_PARTNER_WITHDRAW_URL = domain + "partner/withdraw";//      合伙人金币提现

        public static String HOMEPAGE_IS_SUPPLIER_URL = domain + "supplier";//      是否是商家
        public static String HOMEPAGE_USER_DIALOG_CONNECT_URL = domain + "user/dialog-connect";//      会话连接
        public static String HOMEPAGE_NOTICE_NEW_URL = domain + "notice/new";//      获取最新通知
        public static String HOMEPAGE_NOTICE_LIST_URL = domain + "notice/list";//      系统消息
        public static String HOMEPAGE_UPLOAD_DELFILE_URL = domain + "upload/del-file";//      删除oss上面的图片
        public static String HOMEPAGE_SENSITIVE_WORD_URL = domain + "sensitive/word";//       敏感词获取
        public static String HOMEPAGE_SENSITIVE_CHAT_URL = domain + "sensitive/chat";//       聊天记录校验
        public static String HOMEPAGE_SUPER_POPULARITY_GOODS_LIST_URL = domain + "activity/crq/list";//超人气商品列表
        public static String HOMEPAGE_LIVE_BROADCAST_GOODS_LIST_URL = domain + "activity/live-broadcast";//直播带货商品列表


        public static String MINE_LIVE_SHOP_URL = domain + "site/shop";// 站点店铺列表---供货发货地址 退货地址
        public static String MINE_LIVE_INVENTORY_URL = domain + "borrow/inventory";// 供货记录 Get 获取该商家供货清单记录 按时间降序排序..供货详情
        public static String MINE_LIVE_SOLD_URL = domain + "borrow/sold-note";// 销售记录
        public static String MINE_LIVE_SOLD_COUNT_URL = domain + "borrow/sold-note-count";// 销售记录的统计


        //供货详情/client/borrow/cancel
        public static String MINE_LIVE_SUP_CANCEL_URL = domain + "borrow/cancel";// 取消
        public static String MINE_LIVE_SUP_BACK_URL = domain + "borrow/back";// 申请退回
        public static String MINE_LIVE_SUP_CANCEL_BACK_URL = domain + "borrow/cancel-back";// 申请退回


        //直播借货下单
        public static String MINE_LIVE_SUP_CATEGORY_URL = domain + "activity/borrow-category";// 商品类别悬浮
        public static String MINE_LIVE_CART_URL = domain + "cart/num";// 购物车数量
        public static String MINE_LIVE_COMMON_URL = domain + "platform/common-params";// 公共参数
        public static String HOME_LIVE_BROADCAST_URL = domain + "activity/live-broadcast";// 直播借货商场搜索
        public static String MINE_BORROW_ACTION_URL = domain + "borrow/action";// 供货行为
        public static String MINE_BORROW_ACTION_COUNT_URL = domain + "borrow/action-count";// 供货行为数量金额统计


        public static String MINE_AGENT_URL = domain + "partner/agent";// 申请代理



        //首页全部
        public static String HOME_ALL_URL = domain + "instance/all";//






    }


}

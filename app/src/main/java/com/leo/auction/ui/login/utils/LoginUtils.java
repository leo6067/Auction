package com.leo.auction.ui.login.utils;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.utils.EmptyUtils;
import com.blankj.utilcode.util.SPUtils;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.model.LoginModel;
import com.leo.auction.ui.login.model.UserInfoModel;


import org.litepal.LitePal;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.dgonline_android.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/23
 * 描    述：
 * ================================================
 */
public class LoginUtils {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static LoginUtils instance = new LoginUtils();
    }

    /**
     * 私有的构造函数
     */
    private LoginUtils() {}

    public static LoginUtils getInstance() {
        return LoginUtils.SingletonHolder.instance;
    }

    public boolean isLogin() {
        UserInfoModel userInfoModel = LitePal.findFirst(UserInfoModel.class);
        if (userInfoModel == null) {
            return false;
        } else {
            return true;
        }
    }

    //登录成功之后需要做的操作
    public void loginSuccess(LoginModel.DataBean dataBean, ILoginOperation iLoginOperation){
        LoginModel.DataBean.InfoBean info = dataBean.getInfo();
        LoginModel.DataBean.OrderInfoBean orderInfo = dataBean.getOrderInfo();
        LoginModel.DataBean.SupplierInfoBean supplierInfo = dataBean.getSupplierInfo();
        int noPayNum = 0, receiveNum = 0, sendNum = 0, serviceNum = 0;
        if (orderInfo != null && orderInfo.getOrderStatisticsBean01() != null) {
            noPayNum = orderInfo.getOrderStatisticsBean01().getNoPayNum();
            receiveNum = orderInfo.getOrderStatisticsBean01().getReceiveNum();
            sendNum = orderInfo.getOrderStatisticsBean01().getSendNum();
            serviceNum = orderInfo.getOrderStatisticsBean01().getServiceNum();
        }
        int seller_noPayNum = 0, seller_receiveNum = 0, seller_sendNum = 0, seller_serviceNum = 0;
        if (orderInfo != null && orderInfo.getOrderStatisticsBean02() != null) {
            seller_noPayNum = orderInfo.getOrderStatisticsBean02().getNoPayNum();
            seller_receiveNum = orderInfo.getOrderStatisticsBean02().getReceiveNum();
            seller_sendNum = orderInfo.getOrderStatisticsBean02().getSendNum();
            seller_serviceNum = orderInfo.getOrderStatisticsBean02().getServiceNum();
        }

        UserInfoModel userInfoBean = new UserInfoModel(info.getId(), info.getNickname(), dataBean.getToken(),
                info.getPayPwd(), info.getBalanceExempt(), info.getType(), info.getUsableBalance(),
                info.getSupplierId(), info.getShopUri(), info.getUserId(), info.getFixedSupplier(),
                noPayNum, receiveNum, sendNum, serviceNum, seller_noPayNum, seller_receiveNum, seller_sendNum,
                seller_serviceNum, info.getWarrantBalance(), info.getHeadimg(),
                supplierInfo!=null? EmptyUtils.strEmpty(supplierInfo.getQrcode()):"",info.getPhone(),info.isCompanyAuth(),
                info.getPartner(),info.getCoinNum(),info.isDialogConnect());

        //1.删除数据库的数据
        LitePal.deleteAll(UserInfoModel.class);
        //2.保存数据到 用户数据表中
        userInfoBean.save();

        if (info.getAddress() != null && info.getAddress().getShipAddress() != null && !EmptyUtils.isEmpty(info.getAddress().getShipAddress().getAddressId())) {
            SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("ShipAddress", JSON.toJSONString(info.getAddress().getShipAddress()));
        } else {
            SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("ShipAddress", "");
        }

        if (info.getAddress() != null && info.getAddress().getReturnAddress() != null && !EmptyUtils.isEmpty(info.getAddress().getReturnAddress().getAddressId())) {
            SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("ReturnAddress", JSON.toJSONString(info.getAddress().getReturnAddress()));
        } else {
            SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("ReturnAddress", "");
        }

        iLoginOperation.onCompleted(info.isDialogConnect());
    }

    public interface ILoginOperation{
        void onCompleted(boolean isDialogConnect);
    }

}

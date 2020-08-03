package com.leo.auction.ui.login;

import com.leo.auction.base.BaseModel;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.utils.Globals;


import okhttp3.Call;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/2/9
 * 描    述：用户行为记录工具类
 * ================================================
 */
public class UserActionUtils {
    /*
     * channelType:频道类型 //'频道类型 1-首页 2-分类频道 3-店铺推荐  4-关注-拍品 5-参拍  6-足迹  7-收藏
     * actionType:'动作类型//'动作类型  1-点击  2-收藏 3-出价  4-分享 5-分享新用户 6-分享朋友圈  7-分享QQ
     *productInstanceId:'商品标识',//必须

     *status:'	状态 1-(默认)  2-取消
     */
    public static void actionLog(String channelType, String actionType, String productInstanceId,  String status) {

        BaseModel.sendActionLogRequest(channelType, actionType, productInstanceId, status, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                Globals.log("action log xxxxx" + resultData);

            }
        });
    }


}

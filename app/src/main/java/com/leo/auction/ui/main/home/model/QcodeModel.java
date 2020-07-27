package com.leo.auction.ui.main.home.model;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.HashMap;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/24
 * 描    述： 二维码
 * 修    改：
 * ===============================================
 */
public class QcodeModel {


    /*type=1&page=https://cd.taojianlou.com/ut/auction-web?tpm_shareAgentId=361736173131&refresh=true*/
    /*	type
业务场景   1-推荐粉丝  2-推荐商家  3-拍品详情
4-超级仓库商品详情*/
    public static void httpGetQcode(String type,String page,boolean refresh,HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type",type);
        hashMap.put("page",page);
        hashMap.put("refresh",refresh+"");
        HttpRequest.httpGetString(Constants.Api.QCODE_URL,hashMap,httpCallback);


    }




}

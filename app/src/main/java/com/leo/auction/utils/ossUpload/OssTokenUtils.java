package com.leo.auction.utils.ossUpload;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.utils.DesUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.LogUtils;
import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.login.model.OssTokenModel;
import com.leo.auction.ui.main.mine.model.DecryOssDataModel_table;

import org.litepal.LitePal;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android_im
 * 包    名：com.tjl.super_warehouse.utils.ossUpload
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/3/13 0013
 * 描    述：获取oss数据
 * ================================================
 */
public class OssTokenUtils {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static OssTokenUtils instance = new OssTokenUtils();
    }

    /**
     * 私有的构造函数
     */
    private OssTokenUtils() {}

    public static OssTokenUtils getInstance() {
        return OssTokenUtils.SingletonHolder.instance;
    }

    //获oss请求的必要参数
    public void geOssTokenPre(IOssToken iOssToken) {
        OssTokenModel.sendOssTokenRequest("OssTokenModel", new CustomerJsonCallBack<OssTokenModel>() {
            @Override
            public void onRequestError(OssTokenModel returnData, String msg) {
                iOssToken.onError();
            }

            @Override
            public void onRequestSuccess(OssTokenModel returnData) {
                if (returnData.getData() != null) {
                    String decryptData = "";
                    try {
                        decryptData = DesUtil.decrypt(returnData.getData().getEncryptedData(), Constants.Nouns.OSS_KEY);
                    } catch (Exception e) {
                        LogUtils.e("======"+e.getMessage());
                    }

                    if (!EmptyUtils.isEmpty(decryptData)) {
                        DecryOssDataModel decryOssDataModel = JSON.parseObject(decryptData, DecryOssDataModel.class);
                        DecryOssDataModel_table decryOssDataModel_table=new DecryOssDataModel_table(decryOssDataModel);
                        //1.删除数据库的数据
                        LitePal.deleteAll(DecryOssDataModel_table.class);
                        //2.保存数据到 用户数据表中
                        decryOssDataModel_table.save();
                        iOssToken.onSuccess(decryOssDataModel_table);
                    } else {
                        ToastUtils.showShort("oss数据有误");
                        iOssToken.onError();
                    }
                }else {
                    iOssToken.onError();
                }
            }
        });
    }

    public void getOSSData(IOssToken iOssToken){
        DecryOssDataModel_table decryOssDataModel_table= LitePal.findFirst(DecryOssDataModel_table.class);
        if (decryOssDataModel_table==null){
            geOssTokenPre(iOssToken);
        }else {
            iOssToken.onSuccess(decryOssDataModel_table);
        }
    }

    public interface IOssToken {
        void onError();
        void onSuccess(DecryOssDataModel_table decryOssDataModel_table);
    }

}

package com.leo.auction.ui.main.mine.model;

import com.leo.auction.utils.ossUpload.DecryOssDataModel;

import org.litepal.annotation.Encrypt;
import org.litepal.crud.LitePalSupport;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.mine.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/8 0008
 * 描    述：
 * ================================================
 */
public class DecryOssDataModel_table extends LitePalSupport {
    /**
     * accessKeyId : 帐号
     * secret : 密码
     * endPoint : 请求OSS的节点
     * domain : 可访问的域名
     * bucketName : 桶名
     */
    private int id;

    @Encrypt(algorithm = AES)
    private String accessKeyId;
    @Encrypt(algorithm = AES)
    private String secret;
    @Encrypt(algorithm = AES)
    private String endPoint;
    @Encrypt(algorithm = AES)
    private String domain;
    @Encrypt(algorithm = AES)
    private String bucketName;

    public DecryOssDataModel_table(DecryOssDataModel decryOssDataModel) {
        this.accessKeyId = decryOssDataModel.getAccessKeyId();
        this.secret = decryOssDataModel.getSecret();
        this.endPoint = decryOssDataModel.getEndPoint();
        this.domain = decryOssDataModel.getDomain();
        this.bucketName = decryOssDataModel.getBucketName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}

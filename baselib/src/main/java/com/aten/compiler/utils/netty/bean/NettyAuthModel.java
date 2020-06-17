package com.aten.compiler.utils.netty.bean;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.compiler.utils.netty.bean
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/6/20
 * 描   述：Netty 认证的model
 * ================================================
 */
public class NettyAuthModel {
    /**
     * nonce : qzXyYX5inG4tDnXaFJSdepHVjIV5Gb7f
     * sign : 9929DC7FD329451C4FFE4DEC3A6F821B
     * userId : 0
     * time : 1560947135936
     * token : 6a5773ec3d1c4b27b213811ae93327aa
     */

    private String nonce;
    private String sign;
    private String userId;
    private String time;
    private String token;

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NettyAuthModel(String nonce, String sign, String userId, String time, String token) {
        this.nonce = nonce;
        this.sign = sign;
        this.userId = userId;
        this.time = time;
        this.token = token;
    }

    @Override
    public String toString() {
        return "NettyAuthModel{" +
                "nonce='" + nonce + '\'' +
                ", sign='" + sign + '\'' +
                ", userId='" + userId + '\'' +
                ", time='" + time + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

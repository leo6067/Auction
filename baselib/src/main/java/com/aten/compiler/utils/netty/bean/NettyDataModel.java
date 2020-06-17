package com.aten.compiler.utils.netty.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangmingshuang
 * @since 2019/5/30
 */
public class NettyDataModel {
    private static final int VERSION = 1;
    /**
     * 命令
     */
    private int id;
    /**
     * 版本
     */
    private int version;
    /**
     * 内容长度
     */
    private int contextLength;
    /**
     * 内容数据
     */
    private byte[] context;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContextLength() {
        return contextLength;
    }

    public void setContextLength(int contextLength) {
        this.contextLength = contextLength;
    }

    public byte[] getContext() {
        return context;
    }

    public void setContext(byte[] context) {
        this.context = context;
    }

    public static NettyDataModel newRequest(int id, byte[] context) {
        NettyDataModel body = new NettyDataModel();
        body.id = id;
        body.version = VERSION;
        body.context = context;
        return body;
    }

    public static NettyDataModel newRequest(int id) {
        NettyDataModel body = new NettyDataModel();
        body.id = id;
        body.version = VERSION;
        return body;
    }

    @Override
    public String toString() {
        return "NettyDataModel{" +
                "id=" + id +
                ", version=" + version +
                ", contextLength=" + contextLength +
                ", context=" +JSONObject.parse(context)+
                '}';
    }
}

package com.huantansheng.easyphotos.utils.video;

import android.graphics.Bitmap;

public class ReleaseVideoModel {
    private String tag;//视频的标记 0是正常压缩上传后的数据 1视频添加的数据 2 网络上的数据
    private Bitmap imgPath;//视频第一帧的图片
    private String imgPath2;//视频第一帧的图片（网络图片）
    private String videoPath;//视频地址
    private String uploadCompleteStatus="0";//0 未压缩 未上传 1压缩未上传 2压缩上传
    private String width;//视频宽
    private String height;//视频高


    public ReleaseVideoModel(String tag, Bitmap ingPath, String imgPath2, String videoPath, String width, String height) {
        this.tag = tag;
        this.imgPath = ingPath;
        this.imgPath2=imgPath2;
        this.videoPath=videoPath;
        this.width=width;
        this.height=height;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Bitmap getImgPath() {
        return imgPath;
    }

    public void setImgPath(Bitmap imgPath) {
        this.imgPath = imgPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImgPath2() {
        return imgPath2;
    }

    public void setImgPath2(String imgPath2) {
        this.imgPath2 = imgPath2;
    }


    public String getUploadCompleteStatus() {
        return uploadCompleteStatus;
    }

    public void setUploadCompleteStatus(String uploadCompleteStatus) {
        this.uploadCompleteStatus = uploadCompleteStatus;
    }
}

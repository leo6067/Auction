package com.leo.auction.ui.main.home.model;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.home.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/15
 * 描    述：
 * ================================================
 */
public class PicGridNineModel {
    private String isVideo;//1d代表视频0代表图片
    private String videoPath;//视频地址
    private String image;//图片地址

    public PicGridNineModel(String isVideo, String videoPath, String image) {
        this.isVideo = isVideo;
        this.videoPath = videoPath;
        this.image = image;
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo = isVideo;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

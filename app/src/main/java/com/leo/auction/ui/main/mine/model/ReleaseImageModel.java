package com.leo.auction.ui.main.mine.model;

import java.io.File;

public class ReleaseImageModel {
    private String tag;//数据类型标记 0是正常数据的标记 1是添加图标的标记 2是已上传的网络图片标记
    private File file;//存储已经压缩的图片file
    public String imgPth;//已上传得图片
    private boolean isUploadComplete=false;//是否上传完成
    public int width;//图片宽度
    public int height;//图片高度

    public ReleaseImageModel(String tag, File file, int width, int height, String imgPth) {
        this.tag = tag;
        this.file = file;
        this.width = width;
        this.height = height;
        this.imgPth = imgPth;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImgPth() {
        return imgPth;
    }

    public void setImgPth(String imgPth) {
        this.imgPth = imgPth;
    }

    public boolean isUploadComplete() {
        return isUploadComplete;
    }

    public void setUploadComplete(boolean uploadComplete) {
        isUploadComplete = uploadComplete;
    }
}

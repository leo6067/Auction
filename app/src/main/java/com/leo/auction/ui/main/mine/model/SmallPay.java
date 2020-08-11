package com.leo.auction.ui.main.mine.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/11
 * 描    述： 直接下单小程序
 * 修    改：
 * ===============================================
 */
public class SmallPay {
    private String  title,price, goodsId, paynumber, stock, totalPrice;

    private List<String>  img;
    private boolean  isyu;

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getPaynumber() {
        return paynumber;
    }

    public void setPaynumber(String paynumber) {
        this.paynumber = paynumber;
    }

    public boolean isIsyu() {
        return isyu;
    }

    public void setIsyu(boolean isyu) {
        this.isyu = isyu;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }



}

package com.leo.auction.ui.order.model;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class OrderHolder extends BaseViewHolder {
    private long hasTime;//记录倒计时的时间


    public long getHasTime() {
        return hasTime;
    }

    public void setHasTime(long hasTime) {
        this.hasTime = hasTime;
    }

    public OrderHolder(View view) {
        super(view);
    }

}

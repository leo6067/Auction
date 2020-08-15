package com.leo.auction.ui.main.mine.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.FanModel;
import com.ruffian.library.widget.RTextView;

import java.util.List;

import okhttp3.Call;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/12
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class FanAdapter extends BaseQuickAdapter<FanModel.DataBean, BaseViewHolder> {
    public FanAdapter() {
        super(R.layout.item_fan);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, FanModel.DataBean fanModel) {
        baseViewHolder.setText(R.id.item_name,fanModel.getUser().getNickname());
        RTextView textView = baseViewHolder.getView(R.id.item_action);
        ImageView imageView = baseViewHolder.getView(R.id.item_head);
        GlideUtils.loadImg(fanModel.getUser().getHeadImg(),imageView);


        if (fanModel.isFollowEnable()){
            textView.setVisibility(View.VISIBLE);
        }else {
            textView.setVisibility(View.GONE);
        }



        if (fanModel.isFollow()){
            textView.setText("互相关注");
            textView.getHelper().setBorderColorNormal(Color.parseColor("#666666"));
            textView.setTextColor(Color.parseColor("#666666"));
        }else {
            textView.setText("关注");
            textView.getHelper().setBorderColorNormal(R.color.home_title_bg);
            textView.setTextColor(Color.parseColor("#7c1313"));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    follow(fanModel.getUser().getId()+"",textView);
                }
            });

        }



    }



    private void follow(String accountId,RTextView textView){

                BaseModel.httpPostFocus(accountId, "1", new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                        if (baseModel.getResult().isSuccess()) {
                            textView.setText("互相关注");
                            textView.getHelper().setBorderColorNormal(Color.parseColor("#666666"));
                            textView.getHelper().setTextColorNormal(Color.parseColor("#666666"));
                        } else {
                            ToastUtils.showShort(baseModel.getResult().getMessage());
                        }
                    }
                });

    }



}

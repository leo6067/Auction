package com.leo.auction.ui.main.home.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.SpannableStringUtils;

/**
 * ================================================
 * 项目名称：Android-frame
 * 包   名：com.demo.android_frame.ui.home.adapter
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/10/17
 * 描   述：
 * ================================================
 */
public class HomeSearchAdapter extends BaseQuickAdapter<HomeListModel.DataBean, BaseViewHolder> {


    private int screenWidth;

    public HomeSearchAdapter(int screenWidth) {
        super(R.layout.item_home_list_all, null);
        this.screenWidth = screenWidth;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final HomeListModel.DataBean item) {

//        helper.setIsRecyclable(false);
        ImageView iv = helper.getView(R.id.iv);


        TextView tvProductTitle = helper.getView(R.id.tv_product_title);
        TextView tvProductPrice = helper.getView(R.id.tv_product_price);
        TextView tvProductAgentPrice = helper.getView(R.id.tv_product_agent_price);
        TextView tvFreeShipping = helper.getView(R.id.tv_free_shipping);
        TextView tvRefund = helper.getView(R.id.tv_refund);


        FrameLayout aflShared = helper.getView(R.id.afl_shared);
        ImageView ivShared = helper.getView(R.id.iv_shared);
        FrameLayout aflCollection = helper.getView(R.id.afl_collection);
        ImageView ivCollection = helper.getView(R.id.iv_collection);
        ImageView btPriceIv = helper.getView(R.id.bt_price_iv);
        TextView btPriceTv = helper.getView(R.id.bt_price);


        tvFreeShipping.setText(item.getDistributeType().equals("1") ? "包邮" : "到付");
        tvRefund.setVisibility(item.isRefund() ? View.VISIBLE : View.GONE);  //包退
        btPriceIv.setVisibility(item.isSubsidyProduct()? View.VISIBLE : View.GONE);
        btPriceTv.setVisibility(item.isSubsidyProduct()? View.VISIBLE : View.GONE);

        if (item.isSubsidyProduct()){
            btPriceTv.setText("补贴"+item.getSubsidyMoney()+"元");
        }



        tvProductTitle.setText(EmptyUtils.strEmpty(item.getTitle()));
        tvProductPrice.setText(SpannableStringUtils.getBuilder("￥")
                .setForegroundColor(Color.parseColor("#7c1313")).setXProportion((float) 1.0)
                .append(item.getCurrentPrice()).setXProportion((float) 1.3).setForegroundColor(Color.parseColor("#7c1313"))
                .append("已出价").setForegroundColor(Color.parseColor("#708090"))
                .append(item.getBidNum())
                .append("次").setForegroundColor(Color.parseColor("#708090"))
                .create());


        String path = item.getFirstPic();
        //1.已知图片宽高得情况
//        int width = screenWidth / 2;

        iv.setTag(R.id.iv, path);

//        GlideUtils.loadImg(iv.getContext(),iv);
        iv.setAlpha(1f);
        GlideUtils.loadImg(path,iv);

//        Glide.with(iv.getContext()).load(path)
//                .transition(DrawableTransitionOptions.withCrossFade(800))
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .dontAnimate()
////                .placeholder(R.drawable.interim_morena)
//                .signature(new ObjectKey(System.currentTimeMillis()))
//                .skipMemoryCache(true)
////                .override(width,width)
//
//                .into(new Target<Drawable>() {
//                    @Override
//                    public void onLoadStarted(@Nullable Drawable placeholder) {
//
//                    }
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
////                        Globals.log("xxxxxxxxxxxxx onResourceReady  ");
////                        if (iv.getTag(R.id.iv) != path) {
////                            return;
////                        }
//                        iv.setImageDrawable(resource);
//                        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                        iv.setAlpha(1f);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//
//                    @Override
//                    public void getSize(@NonNull SizeReadyCallback cb) {
//
//                    }
//
//                    @Override
//                    public void removeCallback(@NonNull SizeReadyCallback cb) {
//
//                    }
//
//                    @Override
//                    public void setRequest(@Nullable Request request) {
////                        Globals.log("xxxxxxxxxxxxx onResourceReady  request");
//                    }
//
//                    @Nullable
//                    @Override
//                    public Request getRequest() {
//                        return null;
//                    }
//
//                    @Override
//                    public void onStart() {
////                        Globals.log("xxxxxxxxxxxxx onResourceReady  onStart");
//                    }
//
//                    @Override
//                    public void onStop() {
//
//                    }
//
//                    @Override
//                    public void onDestroy() {
//
//                    }
//                });


        aflShared.setTag(item);


        helper.itemView.setTag(R.id.tag_1, item.getProductInstanceId());

    }


}

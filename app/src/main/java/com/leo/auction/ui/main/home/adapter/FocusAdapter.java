package com.leo.auction.ui.main.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.transformation.RoundedCornersTransformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.SpannableStringUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

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
public class FocusAdapter extends BaseQuickAdapter<HomeListModel.DataBean, BaseViewHolder> {


    private int screenWidth;

    public FocusAdapter(int screenWidth) {
        super(R.layout.item_focus_list, null);
        this.screenWidth = screenWidth;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeListModel.DataBean item) {

//        helper.setIsRecyclable(false);
        ImageView iv = helper.getView(R.id.iv);


        TextView tvProductTitle = helper.getView(R.id.tv_product_title);
        TextView tvProductPrice = helper.getView(R.id.tv_product_price);

        TextView tvFreeShipping = helper.getView(R.id.tv_free_shipping);
        TextView tvRefund = helper.getView(R.id.tv_refund);

        FrameLayout aflShared = helper.getView(R.id.afl_shared);

        ImageView btPriceIv = helper.getView(R.id.bt_price_iv);
        TextView btPriceTv = helper.getView(R.id.bt_price);
        TextView overTv = helper.getView(R.id.item_over);


        tvFreeShipping.setText(item.getDistributeType().equals("1") ? "包邮" : "到付");
        tvRefund.setVisibility(item.isRefund() ? View.VISIBLE : View.GONE);  //包退
        btPriceIv.setVisibility(item.isSubsidyProduct() ? View.VISIBLE : View.GONE);
        btPriceTv.setVisibility(item.isSubsidyProduct() ? View.VISIBLE : View.GONE);

        if (item.isSubsidyProduct()) {
            btPriceTv.setText("补贴" + item.getSubsidyMoney() + "元");
        }

        try {
            if (item.getStatus() != 1) {
                tvProductPrice.setVisibility(View.GONE);
                overTv.setVisibility(View.VISIBLE);
            }
        } catch (NullPointerException e) {

        }


        tvProductTitle.setText(EmptyUtils.strEmpty(item.getTitle()));
        tvProductPrice.setText(SpannableStringUtils.getBuilder("￥")
                .setForegroundColor(Color.parseColor("#7c1313")).setXProportion((float) 1.0)
                .append(item.getCurrentPrice()).setXProportion((float) 1.3).setForegroundColor(Color.parseColor("#7c1313"))
                .append("已出价").setForegroundColor(Color.parseColor("#708090"))
                .append(item.getBidNum())
                .append("次").setForegroundColor(Color.parseColor("#708090"))
                .create());


        //获取图片的宽高  https://file.taojianlou.com/goods/U94kMQDwGn6VsmyehlqjA5kEb0ghE0IN.jpg?image=640,640
        String imgPath = "", imgPathJiequ = "";
        double picWidth = 1, picHeight = 1;

        if (item.getFirstPic() != null && !item.getFirstPic().isEmpty() && item.getFirstPic().contains("?image=")) {
            imgPath = item.getFirstPic();
            imgPathJiequ = imgPath.substring(imgPath.indexOf("?image=") + 7);
        }

        if (!EmptyUtils.isEmpty(imgPathJiequ)) {
            String[] imgPathSplit = imgPathJiequ.split(",");
            picWidth = Integer.valueOf(imgPathSplit[0]);
            picHeight = Integer.valueOf(imgPathSplit[1]);
        }

        //1.已知图片宽高得情况
        int width = screenWidth / 2;

        int height = (int) (width * (picHeight / picWidth));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        iv.setLayoutParams(layoutParams);


        String path = item.getFirstPic();
        if (path instanceof String) {
            if (((String) path).contains("?")) {
                path = String.valueOf(path) + "&x-oss-process=image/resize,s_320";
            } else {
                path = String.valueOf(path) + "?x-oss-process=image/resize,s_320";
            }
        }

//        iv.setImageResource(R.drawable.interim_morena);
//        iv.setScaleType(ImageView.ScaleType.CENTER);
//        iv.setAlpha(0.05f);


        iv.setTag(R.id.iv, path);
        String finalPath = path;
        Glide.with(iv.getContext()).load(path)
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .dontAnimate()
//                .placeholder(R.drawable.interim_morena)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .skipMemoryCache(true)
                .transform(new CenterCrop(), new RoundedCornersTransformation((int) mContext.getResources().getDimension(R.dimen.dp_7),
                        0, RoundedCornersTransformation.CornerType.TOP))
                .override(width, height)
                .into(new Target<Drawable>() {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        Globals.log("xxxxxxxxxxxxx onResourceReady  ");
                        if (iv.getTag(R.id.iv) != finalPath) {
                            return;
                        }
                        iv.setImageDrawable(resource);
                        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        iv.setAlpha(1f);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {
//                        Globals.log("xxxxxxxxxxxxx onResourceReady  request");
                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }

                    @Override
                    public void onStart() {
//                        Globals.log("xxxxxxxxxxxxx onResourceReady  onStart");
                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }
                });


        aflShared.setTag(item);


        helper.itemView.setTag(R.id.tag_1, item.getProductInstanceId());


    }


}

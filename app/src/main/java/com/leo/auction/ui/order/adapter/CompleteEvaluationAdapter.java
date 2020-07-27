package com.leo.auction.ui.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.ratingbar.ScaleRatingBar;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.ui.order.model.OrderCommentInfoModel;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.order.adapter
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/12
 * 描    述：
 * ================================================
 */
public class CompleteEvaluationAdapter extends BaseQuickAdapter<OrderCommentInfoModel.DataBeanX, BaseViewHolder> {
    private final IEvaluation iEvaluation;

    public CompleteEvaluationAdapter(IEvaluation iEvaluation) {
        super(R.layout.layout_complete_evaluation_item, null);
        this.iEvaluation=iEvaluation;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderCommentInfoModel.DataBeanX itemsBean) {
        CircleImageView civOrderHead= helper.getView(R.id.civ_order_head);
        TextView civOrderShopName = helper.getView(R.id.civ_order_shop_name);
        RadiusImageView rivProductPic = helper.getView(R.id.item_head);
        TextView itemTitle = helper.getView(R.id.item_title);
        TextView itemTime = helper.getView(R.id.item_time);
        TextView itemPrice = helper.getView(R.id.item_price);

        ScaleRatingBar srbStandard = helper.getView(R.id.srb_standard);
        CustomeRecyclerView crlLabel= helper.getView(R.id.crl_label);
        TextView tvContent = helper.getView(R.id.tv_content);
        RecyclerView crlEvaluationPic = helper.getView(R.id.crl_evaluation_pic);

        OrderCommentInfoModel.DataBeanX.OrderBean.ItemsBean OrderItemBean = itemsBean.getOrder().getItems().get(0);   //暂时一个


        GlideUtils.loadImg(itemsBean.getOrder().getHeadImg(), civOrderHead);
        civOrderShopName.setText(EmptyUtils.strEmpty(itemsBean.getOrder().getNickname()));
        GlideUtils.loadImg(OrderItemBean.getFirstPic(), rivProductPic);
        itemTitle.setText(EmptyUtils.strEmpty(OrderItemBean.getTitle()));


        String friendlyTimeSpanByNow = TimeUtils.getFriendlyTimeSpanByNow(itemsBean.getOrder().getCreateTime());

        itemTime.setText("成交时间 ："+EmptyUtils.strEmpty(friendlyTimeSpanByNow));
        itemPrice.setText("成交金额 ：￥" +itemsBean.getOrder().getPayment());

        srbStandard.setRating(Float.valueOf(itemsBean.getData().getScore()));
        tvContent.setText(EmptyUtils.strEmpty(itemsBean.getData().getContent()));




        //初始化评价标签
        crlLabel.setHasFixedSize(true);
        crlLabel.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        CompleteEvaluationLableAdapter evaluationLableAdapter= new CompleteEvaluationLableAdapter();
        crlLabel.setAdapter(evaluationLableAdapter);
        evaluationLableAdapter.setNewData(itemsBean.getData().getLabels());


        //初始化评价图片
        crlEvaluationPic.setHasFixedSize(true);
        crlEvaluationPic.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        final EvaluationPicAdapter evaluationPicAdapter = new EvaluationPicAdapter();

        crlEvaluationPic.setAdapter(evaluationPicAdapter);
        evaluationPicAdapter.setmOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(R.id.tag_1);
                iEvaluation.onEvaluationPicItem(evaluationPicAdapter,position);
            }
        });
        evaluationPicAdapter.clearImgViews();
        evaluationPicAdapter.setNewData(itemsBean.getData().getImages());
    }

    public  interface IEvaluation{
        void onEvaluationPicItem(EvaluationPicAdapter evaluationPicAdapter, int position);
    }
}

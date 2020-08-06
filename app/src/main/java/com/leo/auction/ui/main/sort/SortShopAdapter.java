package com.leo.auction.ui.main.sort;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.swipeMenuLayout.SwipeMenuLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.CommonModel;
import com.ruffian.library.widget.RImageView;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.sort
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/10
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class SortShopAdapter extends BaseQuickAdapter<SortShopModel.DataBean, BaseViewHolder> {

    private SortOnListener mSortOnListener;

    public SortShopAdapter(SortOnListener mSortOnListener) {
        super(R.layout.item_sort_shop, null);
        this.mSortOnListener = mSortOnListener;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SortShopModel.DataBean dataBean) {


        //强制关闭复用
        baseViewHolder.setIsRecyclable(false);
        ImageView mItemHead = baseViewHolder.getView(R.id.item_head);
        GlideUtils.loadImg(dataBean.getProductUser().getHeadImg(), mItemHead);


        ImageView mItemLevel = baseViewHolder.getView(R.id.item_level);
        LinearLayout mItemSwipe = baseViewHolder.getView(R.id.swipe_layout);

        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();
        String[] myLevelPicS = commonJson.getSeller_level_pic().get(0).split("seller_level_");
        String LevelUrl = myLevelPicS[0] + "seller_level_" + dataBean.getProductUser().getSellerLevel() + ".png";
        GlideUtils.loadImg(LevelUrl, mItemLevel);

        TextView mItemName = baseViewHolder.getView(R.id.item_name);
        mItemName.setText(dataBean.getProductUser().getNickname());

        TextView mItemScore = baseViewHolder.getView(R.id.item_score);
        mItemScore.setText("评分  " + dataBean.getProductUser().getRate());

        TextView mItemFan = baseViewHolder.getView(R.id.item_fan);
        mItemFan.setText("粉丝数  " + dataBean.getProductUser().getFansNum());

        TextView mItemNext = baseViewHolder.getView(R.id.item_next);
        if (dataBean.getNewestNum() > 99) {
            mItemNext.setText("上新99+件");
        } else {
            mItemNext.setText("上新" + dataBean.getNewestNum() + "件");
        }

        TextView mItemCancel = baseViewHolder.getView(R.id.item_cancel);
        TextView mItemTop = baseViewHolder.getView(R.id.item_top);


        mItemSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = baseViewHolder.getAdapterPosition();
                mSortOnListener.soreItemListener(layoutPosition);
            }
        });


        int topType;  //2-置顶  4-取消置顶
        if (dataBean.isTop()) {
            mItemTop.setText("取消置顶");
            topType = 4;
        } else {
            topType = 2;
            mItemTop.setText("置顶");
        }

        String finalTopType = topType + "";
        mItemTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseModel.httpPostFocus(dataBean.getProductUser().getId() + "", finalTopType, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                        if (baseModel.getResult().isSuccess()) {
                            ToastUtils.showShort("操作成功");
                            if (finalTopType.equals("4")) {
                                mItemTop.setText("置顶");
                                dataBean.setTop(false);
                            } else {
                                mItemTop.setText("取消置顶");
                                dataBean.setTop(true);
                            }
                            mSortOnListener.soreOnListener();
                        } else {
                            ToastUtils.showShort(baseModel.getResult().getMessage());
                        }
                    }
                });
            }
        });


        mItemCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseModel.httpPostFocus(dataBean.getProductUser().getId() + "", "0", new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                        if (baseModel.getResult().isSuccess()) {
                            ToastUtils.showShort("取消成功");
                            remove(baseViewHolder.getAdapterPosition());
                        } else {
                            ToastUtils.showShort(baseModel.getResult().getMessage());
                        }
                    }
                });
            }
        });
    }


    public interface SortOnListener {

        void soreOnListener();

        void soreItemListener(int layoutPosition);

    }
}

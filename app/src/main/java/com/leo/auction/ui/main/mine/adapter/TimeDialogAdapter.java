package com.leo.auction.ui.main.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.ui.main.mine.model.AuctionTimeModel;
import com.leo.auction.ui.main.mine.model.TimeDialogModel;
import com.leo.auction.utils.GlideUtils;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RTextView;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.adapter
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/4
 * 描    述： 截拍时间的适配器
 * 修    改：
 * ===============================================
 */
public class TimeDialogAdapter extends BaseMultiItemQuickAdapter<TimeDialogModel, BaseViewHolder> {

    private Context mContext;
    private int mSelectedPosition = 0;

    List<TimeDialogModel> mModelList;

    private InterTimeClick mInterTimeClick;

    public TimeDialogAdapter(Context context, List<TimeDialogModel> data, InterTimeClick interTimeClick) {
        super(data);
        // 绑定 layout 对应的 type
        addItemType(Constants.Var.LAYOUT_TYPE_HEAD, R.layout.item_time_head);
        addItemType(Constants.Var.LAYOUT_TYPE, R.layout.item_time_content);
        mContext = context;
        mModelList = data;
        mInterTimeClick = interTimeClick;
    }


    public void setSelect(int position) {
        mModelList.get(mSelectedPosition).setSelect(false);
        mModelList.get(position).setSelect(true);
        mSelectedPosition = position;
        notifyDataSetChanged();
    }



    @Override
    protected void convert(@NonNull BaseViewHolder helper, TimeDialogModel item) {


        switch (helper.getItemViewType()) {
            case Constants.Var.LAYOUT_TYPE_HEAD:
                helper.setText(R.id.item_title, item.getTypeName());
                helper.setText(R.id.item_delay, item.getDelayText());


                break;
            case Constants.Var.LAYOUT_TYPE:
                LinearLayout itemLin = helper.getView(R.id.item_lin);
                TextView itemTime = helper.getView(R.id.item_time);
                itemTime.setText(item.getShowText());
                if (item.isSelect()) {

                    itemLin.setBackgroundColor(mContext.getResources().getColor(R.color.home_title_bg));
                    itemTime.setTextColor(mContext.getResources().getColor(R.color.home_title_bg));
                } else {


                    itemTime.setTextColor(mContext.getResources().getColor(R.color.black));
                    itemLin.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
                }


                itemTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mInterTimeClick.timeItemClick(item);
                    }
                });
                break;
            default:
                break;
        }
    }


    public interface InterTimeClick {
        void timeItemClick(TimeDialogModel position);
    }


}

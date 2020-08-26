package com.leo.auction.ui.main.mine.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.base.BottomBaseDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.mine.adapter.TimeDialogAdapter;
import com.leo.auction.ui.main.mine.model.AuctionTimeModel;
import com.leo.auction.ui.main.mine.model.TimeDialogModel;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;


/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.dialog
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/4
 * 描    述： 截拍时间
 * 修    改：
 * ===============================================
 */
public class TimeDialog extends BottomBaseDialog<TimeDialog> {

    private Context mContext;
    private ArrayList<TimeDialogModel> todayBeans = new ArrayList<>();
    private InterTimeDialog mInterTimeDialog;

    public TimeDialog(@NonNull Context context, ArrayList<TimeDialogModel> todayBean, InterTimeDialog interTimeDialog) {
        super(context);
        mContext = context;
        todayBeans = todayBean;
        mInterTimeDialog = interTimeDialog;
    }


    @Override
    public View onCreateView() {
        View view = View.inflate(mContext, R.layout.dialog_time, null);

        RTextView cancelTv = view.findViewById(R.id.dialog_cancel);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (todayBeans.get(position).getItemType() == Constants.Var.LAYOUT_TYPE) {
                    return 1;
                } else {
                    return 3;
                }
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        TimeDialogAdapter timeDialogAdapter = new TimeDialogAdapter(mContext, todayBeans, new TimeDialogAdapter.InterTimeClick() {
            @Override
            public void timeItemClick(TimeDialogModel item) {

                mInterTimeDialog.itemTimeClick(item);
                dismiss();

            }
        });

        timeDialogAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                timeDialogAdapter.setSelect(i);
                todayBeans.get(i).setSelect(true);
            }
        });
        recyclerView.setAdapter(timeDialogAdapter);

        view.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(10)));

        return view;
    }

    @Override
    public void initView() {


    }


    public interface InterTimeDialog {

        void itemTimeClick(TimeDialogModel position);
    }


}

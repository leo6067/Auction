package com.leo.auction.ui.main.mine.banlance;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.TimeWheelUtils;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.banlance.bubblePopup.CustomBubblePopup02;
import com.leo.auction.ui.main.mine.banlance.model.AmountDetailsModel;
import com.leo.auction.ui.main.mine.banlance.model.BalanceCategoryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmountDetailsFragment extends BaseRecyclerViewFragment implements SubTitleAdapter.ISubTitleListener {


    @BindView(R.id.rl_sub_title)
    RecyclerView rlSubTitle;
    @BindView(R.id.all_right)
    LinearLayout allRight;


    private ArrayList<BalanceCategoryModel.DataBean.ChildrenBean> childrenTabData = new ArrayList<>();
    private SubTitleAdapter subTitleAdapter;
    private String parentType = "";
    private String changeType = "";
    private String startTime = "";
    private String endTime = "";

    private CustomBubblePopup02 customBubblePopup;


    public AmountDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_amount_details;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enableLazyLoad();
    }

    @Override
    protected void initAdapter() {
        mAdapter = new AmountDetailsAdapter();
    }

    @Override
    public void initData() {
        parentType = getArguments().getString("id");
        childrenTabData = getArguments().getParcelableArrayList("childrenTabData");
        super.initData();
        //设置子标题的数据
        rlSubTitle.setHasFixedSize(true);
        rlSubTitle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        subTitleAdapter = new SubTitleAdapter(this);
        rlSubTitle.setAdapter(subTitleAdapter);
        subTitleAdapter.setNewData(childrenTabData);

        mAdapter.setEmptyView(R.layout.layout_empty_view_02, recyclerView);

        showWaitDialog();
        onRefresh(refreshLayout);
    }

    @Override
    public void initEvent() {
        super.initEvent();

        setSmartHasRefreshOrLoadMore();
        setLoadMore();

        ((AmountDetailsAdapter) mAdapter).setOnItemListener(mOnItemListener);
    }

    @Override
    public void getData() {

        AmountDetailsModel.httpAmountDetailsModel(mPageNum, parentType, changeType, startTime, endTime, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                AmountDetailsModel returnData = JSONObject.parseObject(resultData, AmountDetailsModel.class);
                List<AmountDetailsModel.DataBean> datas = returnData.getData();
                if (datas != null) {
                    if (mPageNum == 1) {
                        mAdapter.setNewData(datas);
                    } else {
                        mAdapter.addData(datas);
                        mAdapter.loadMoreComplete();
                    }


                    if (returnData.getData().isEmpty()) {
                        mPageNum = 1;
                    } else if (returnData.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                        mAdapter.loadMoreEnd(true);
                    } else {
                        mAdapter.loadMoreEnd();
                    }
                }
            }
        });

    }

    @OnClick({R.id.all_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_right:
                showChooseInvitationTypePop();
                break;
        }
    }

    //显示时间筛选的弹框
    private void showChooseInvitationTypePop() {
        TimeWheelUtils.getInstance().isShowDay(true, true, true,
                false, true, true);
        customBubblePopup = new CustomBubblePopup02(getContext(), startTime, endTime, new CustomBubblePopup02.OnPopListener() {

            @Override
            public void onStartTime(final TextView tvStart) {
                TimeWheelUtils.getInstance().showTimeWheel(getContext(), "日期", new TimeWheelUtils.TimeWheelClickListener() {
                    @Override
                    public void onchooseDate(String dateInfo) {
                        startTime = dateInfo;
                        tvStart.setText(dateInfo);
                        customBubblePopup.dismiss();
                        onRefresh(refreshLayout);
                    }
                });
            }

            @Override
            public void onEndTime(final TextView tvEnd) {
                TimeWheelUtils.getInstance().showTimeWheel(getContext(), "日期", new TimeWheelUtils.TimeWheelClickListener() {
                    @Override
                    public void onchooseDate(String dateInfo) {
                        endTime = dateInfo;
                        tvEnd.setText(dateInfo);
                        customBubblePopup.dismiss();
                        onRefresh(refreshLayout);
                    }
                });
            }
        });
        customBubblePopup
                .gravity(Gravity.BOTTOM)
                .anchorView(allRight)
                .triangleWidth(15)
                .triangleHeight(6)
                .showAnim(null)
                .dismissAnim(null)
                .show();
    }

    @Override
    public void onSubTitleItemListener(String changeType) {
        startTime = "";
        endTime = "";
        this.changeType = changeType;
        onRefresh(refreshLayout);
    }

    //订单item点击
    private View.OnClickListener mOnItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String orderId = (String) v.getTag();
            if (!EmptyUtils.isEmpty(orderId)) {
                BalanceDetailActivity.newIntance(getContext(), orderId, "1");
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (customBubblePopup != null && customBubblePopup.isShowing()) {
            customBubblePopup.dismiss();
            customBubblePopup = null;
        }
    }

    public static AmountDetailsFragment newIntance(String id, ArrayList<BalanceCategoryModel.DataBean.ChildrenBean> childrenTabData) {
        AmountDetailsFragment amountDetailsFragment = new AmountDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putParcelableArrayList("childrenTabData", childrenTabData);
        amountDetailsFragment.setArguments(bundle);
        return amountDetailsFragment;
    }
}

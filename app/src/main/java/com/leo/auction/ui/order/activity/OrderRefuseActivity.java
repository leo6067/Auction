package com.leo.auction.ui.order.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.RadiusImageView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.ruffian.library.widget.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//拒绝退款  ---或者同意退款
public class OrderRefuseActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.item_head)
    RadiusImageView mItemHead;
    @BindView(R.id.item_title)
    TextView mItemTitle;
    @BindView(R.id.item_num)
    TextView mItemNum;
    @BindView(R.id.item_hint_sfk)
    TextView mItemHintSfk;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.order_price)
    TextView mOrderPrice;
    @BindView(R.id.order_remark)
    EditText mOrderRemark;
    @BindView(R.id.order_img_recycler)
    RecyclerView mOrderImgRecycler;
    @BindView(R.id.order_commit)
    RTextView mOrderCommit;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order_refuse);
    }



    @OnClick(R.id.order_commit)
    public void onViewClicked() {
    }
}

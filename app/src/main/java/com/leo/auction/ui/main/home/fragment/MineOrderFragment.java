package com.leo.auction.ui.main.home.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.base.BaseFragment;
import com.leo.auction.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineOrderFragment extends BaseFragment {


    @BindView(R.id.iv_wait_pay)
    ImageView mIvWaitPay;
    @BindView(R.id.all_order_wait_pay)
    LinearLayout mAllOrderWaitPay;
    @BindView(R.id.iv_send_good)
    ImageView mIvSendGood;
    @BindView(R.id.all_order_send_good)
    LinearLayout mAllOrderSendGood;
    @BindView(R.id.all_order_received_good)
    LinearLayout mAllOrderReceivedGood;
    @BindView(R.id.all_after_sale)
    LinearLayout mAllAfterSale;
    @BindView(R.id.all_order)
    LinearLayout mAllOrder;
    @BindView(R.id.ll_top01)
    LinearLayout mLlTop01;
    @BindView(R.id.mine_zcmx)
    TextView mMineZcmx;
    @BindView(R.id.mine_cjck)
    TextView mMineCjck;
    @BindView(R.id.mine_fbpp)
    TextView mMineFbpp;
    @BindView(R.id.mine_ppgl)
    TextView mMinePpgl;
    @BindView(R.id.mine_tgzx)
    TextView mMineTgzx;
    @BindView(R.id.mine_gzsm)
    TextView mMineGzsm;
    @BindView(R.id.mine_kf)
    TextView mMineKf;
    @BindView(R.id.mine_setting)
    TextView mMineSetting;


    public MineOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_order;
    }


    @Override
    public void initData() {
        super.initData();


    }

    @OnClick({R.id.iv_wait_pay, R.id.all_order_wait_pay, R.id.iv_send_good, R.id.all_order_send_good, R.id.all_order_received_good, R.id.all_after_sale, R.id.all_order, R.id.ll_top01, R.id.mine_zcmx, R.id.mine_cjck, R.id.mine_fbpp, R.id.mine_ppgl, R.id.mine_tgzx, R.id.mine_gzsm, R.id.mine_kf, R.id.mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wait_pay:
            case R.id.all_order_wait_pay:
                break;
            case R.id.iv_send_good:
            case R.id.all_order_send_good:
                break;
            case R.id.all_order_received_good:
            case R.id.all_after_sale:
                break;
            case R.id.all_order:
                break;
            case R.id.ll_top01:
                break;
            case R.id.mine_zcmx:
                break;
            case R.id.mine_cjck:
                break;
            case R.id.mine_fbpp:
                break;
            case R.id.mine_ppgl:
                break;
            case R.id.mine_tgzx:
                break;
            case R.id.mine_gzsm:
                break;
            case R.id.mine_kf:
                break;
            case R.id.mine_setting:
                break;
        }
    }
}

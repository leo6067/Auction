package com.leo.auction.ui.main.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.base.ActivityManager;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.YCRedDotView;
import com.leo.auction.R;
import com.leo.auction.base.BaseAppContext;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.main.mine.activity.AssetDetailActivity;
import com.leo.auction.ui.main.mine.activity.AuctionManagementActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.activity.SettingActivity;
import com.leo.auction.ui.main.mine.activity.SuperHouseActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.activity.OrderActivity;
import com.leo.auction.ui.order.fragment.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindView(R.id.iv_dsh)
    ImageView mIvDsh;
    @BindView(R.id.iv_server)
    ImageView mIvServer;
    @BindView(R.id.iv_order)
    ImageView mIvOrder;


    private int isSeller;

    public MineOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_order;
    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见
            initData();
        } else {
            //不可见
        }
    }


    @Override
    public void initData() {
        super.initData();
        isSeller = getArguments().getInt("isSeller", 1);   // 1：买家  2 卖家

        //创建红点View(待付款)
        YCRedDotView rdWaitPay = new YCRedDotView(BaseAppContext.getInstance());
        rdWaitPay.setTargetView(mIvWaitPay);
        rdWaitPay.setBadgeMargin(12, 0, 0, 0);
        //创建红点View(待发货)
        YCRedDotView rdSendGood = new YCRedDotView(BaseAppContext.getInstance());
        rdSendGood.setTargetView(mIvSendGood);
        rdSendGood.setBadgeMargin(12, 0, 0, 0);
        //创建红点View(待收货)
        YCRedDotView rdReceivedGood = new YCRedDotView(BaseAppContext.getInstance());
        rdReceivedGood.setTargetView(mIvDsh);
        rdReceivedGood.setBadgeMargin(12, 0, 0, 0);

        //创建红点View(售后)
        YCRedDotView  rdAfterSale = new YCRedDotView(BaseAppContext.getInstance());
        rdAfterSale.setTargetView(mIvServer);
        rdAfterSale.setBadgeMargin(12, 0, 0, 0);


        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();


        try {
            mMineZcmx.setText(userJson.getBalance()+"元");
            if( Constants.Var.MINE_TYPE == 0){   //买入
                int noPayNum = userJson.getBuyerOrderCount().getNoPayNum();
                int sendNum = userJson.getBuyerOrderCount().getSendNum();
                int receiveNum = userJson.getBuyerOrderCount().getReceiveNum();
                int serviceNum = userJson.getBuyerOrderCount().getServiceNum();
                rdWaitPay.setBadgeCount(noPayNum);
                rdSendGood.setBadgeCount(sendNum);
                rdReceivedGood.setBadgeCount(receiveNum);
                rdAfterSale.setBadgeCount(serviceNum);
            }else {
                int noPayNum = userJson.getSellerOrderCount().getNoPayNum();
                int sendNum = userJson.getSellerOrderCount().getSendNum();
                int receiveNum = userJson.getSellerOrderCount().getReceiveNum();
                int serviceNum = userJson.getSellerOrderCount().getServiceNum();
                rdWaitPay.setBadgeCount(noPayNum);
                rdSendGood.setBadgeCount(sendNum);
                rdReceivedGood.setBadgeCount(receiveNum);
                rdAfterSale.setBadgeCount(serviceNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @OnClick({R.id.iv_wait_pay, R.id.all_order_wait_pay, R.id.iv_send_good, R.id.all_order_send_good, R.id.all_order_received_good, R.id.all_after_sale, R.id.all_order, R.id.ll_top01, R.id.mine_zcmx, R.id.mine_cjck, R.id.mine_fbpp, R.id.mine_ppgl, R.id.mine_tgzx, R.id.mine_gzsm, R.id.mine_kf, R.id.mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wait_pay:
            case R.id.all_order_wait_pay: //全部订单: status 不传,  待付款status=1    待发货: status=2 待收货 : status=4 待评价 : status=8   售后: status = 192 (64-售后 + 128-退款导致关闭 )
                OrderActivity.newIntance(getActivity(),1,isSeller,"1");
//                 OrderFragment.newIntance("1",isSeller);
                break;
            case R.id.iv_send_good:
            case R.id.all_order_send_good:
                OrderActivity.newIntance(getActivity(),2,isSeller,"2");
//                OrderFragment.newIntance("2",isSeller);
                break;
            case R.id.all_order_received_good:
                OrderActivity.newIntance(getActivity(),3,isSeller,"4");
//                OrderFragment.newIntance("4",isSeller);
                break;
            case R.id.all_after_sale:
                OrderActivity.newIntance(getActivity(),4,isSeller,"8");
//                OrderFragment.newIntance("8",isSeller);
                break;
            case R.id.all_order:
                OrderActivity.newIntance(getActivity(),0,isSeller,"");
//                OrderFragment.newIntance("",isSeller);
                break;

            case R.id.mine_zcmx:

                ActivityManager.JumpActivity(getActivity(), AssetDetailActivity.class);
                break;
            case R.id.mine_cjck:
                ActivityManager.JumpActivity(getActivity(), SuperHouseActivity.class);
                break;
            case R.id.mine_fbpp://发布拍品
                ActivityManager.JumpActivity(getActivity(), CommodityReleaseActivity.class);
                break;
            case R.id.mine_ppgl://拍品管理
                ActivityManager.JumpActivity(getActivity(), AuctionManagementActivity.class);
                break;
            case R.id.mine_tgzx:
                break;
            case R.id.mine_gzsm:
                break;
            case R.id.mine_kf:
                break;
            case R.id.mine_setting:
                ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                break;
        }
    }



    public static MineOrderFragment newIntance(int isSeller) {  //角度  1-买家角度(默认1)   2-卖家角度
        MineOrderFragment mineOrderFragment = new MineOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("isSeller", isSeller);
        mineOrderFragment.setArguments(bundle);
        return mineOrderFragment;
    }

}

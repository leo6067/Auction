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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.ActivityManager;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.widget.YCRedDotView;
import com.leo.auction.R;
import com.leo.auction.base.BaseAppContext;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.activity.AssetDetailActivity;
import com.leo.auction.ui.main.mine.activity.AuctionManagementActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.activity.PromotionCenterActivivty;
import com.leo.auction.ui.main.mine.activity.SettingActivity;
import com.leo.auction.ui.main.mine.activity.SuperHouseActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.activity.OrderActivity;
import com.leo.auction.ui.order.fragment.OrderFragment;
import com.leo.auction.ui.web.AgentWebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

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


    private int isSeller;// 1：买家  2 卖家

    private String webUrl;
    private UserModel.DataBean mUserJson;

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
        if (getActivity()!=null){
        UserModel.httpUpdateUser(getActivity());

        }

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
        YCRedDotView rdAfterSale = new YCRedDotView(BaseAppContext.getInstance());
        rdAfterSale.setTargetView(mIvServer);
        rdAfterSale.setBadgeMargin(12, 0, 0, 0);


        mUserJson = BaseSharePerence.getInstance().getUserJson();



        try {
            mMineZcmx.setText(mUserJson.getBalance() + "元");
            if (Constants.Var.MINE_TYPE == 0) {   //买入
                int noPayNum = mUserJson.getBuyerOrderCount().getNoPayNum();
                int sendNum = mUserJson.getBuyerOrderCount().getSendNum();
                int receiveNum = mUserJson.getBuyerOrderCount().getReceiveNum();
                int serviceNum = mUserJson.getBuyerOrderCount().getServiceNum();
                rdWaitPay.setBadgeCount(noPayNum);
                rdSendGood.setBadgeCount(sendNum);
                rdReceivedGood.setBadgeCount(receiveNum);
                rdAfterSale.setBadgeCount(serviceNum);
            } else {
                int noPayNum = mUserJson.getSellerOrderCount().getNoPayNum();
                int sendNum = mUserJson.getSellerOrderCount().getSendNum();
                int receiveNum = mUserJson.getSellerOrderCount().getReceiveNum();
                int serviceNum = mUserJson.getSellerOrderCount().getServiceNum();
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
//                OrderActivity.newIntance(getActivity(),1,isSeller,"1");
//                 OrderFragment.newIntance("1",isSeller);

//                if (isSeller ==1){// 1：买家  2 卖家
                webUrl = Constants.WebApi.MINE_DFK + isSeller + mUserJson.getNestedToken();
                httpUserWeb("待付款", webUrl);

                break;
            case R.id.iv_send_good:
            case R.id.all_order_send_good:
//                OrderActivity.newIntance(getActivity(), 2, isSeller, "2");
//                OrderFragment.newIntance("2",isSeller);

                webUrl = Constants.WebApi.MINE_DFH + isSeller + mUserJson.getNestedToken();
                httpUserWeb("待发货", webUrl);
                break;
            case R.id.all_order_received_good:
//                OrderActivity.newIntance(getActivity(), 3, isSeller, "4");
//                OrderFragment.newIntance("4",isSeller);
                webUrl = Constants.WebApi.MINE_DSH + isSeller + mUserJson.getNestedToken();
                httpUserWeb("待收货", webUrl);
                break;
            case R.id.all_after_sale:
//                OrderActivity.newIntance(getActivity(), 4, isSeller, "8");
//                OrderFragment.newIntance("8",isSeller);
                webUrl = Constants.WebApi.MINE_SH + isSeller + mUserJson.getNestedToken();
                httpUserWeb("售后", webUrl);
                break;
            case R.id.all_order:
//                OrderActivity.newIntance(getActivity(), 0, isSeller, "");
//                OrderFragment.newIntance("",isSeller);
                webUrl = Constants.WebApi.MINE_QB + isSeller + mUserJson.getNestedToken();
                httpUserWeb("全部订单", webUrl);
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

                ActivityManager.JumpActivity(getActivity(), PromotionCenterActivivty.class);
                break;
            case R.id.mine_gzsm:  //超级学堂

                break;
            case R.id.mine_kf:  //客服
                break;
            case R.id.mine_setting:
                ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                break;
        }
    }


    public void httpUserWeb(String title, String mUrl) {


        UserModel.httpUpdateUser(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("url", mUrl);
                com.leo.auction.base.ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
            }
        });
    }


    public static MineOrderFragment newIntance(int isSeller) {  //角度  1-买家角度(默认1)   2-卖家角度
        MineOrderFragment mineOrderFragment = new MineOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("isSeller", isSeller);
        mineOrderFragment.setArguments(bundle);
        return mineOrderFragment;
    }

}

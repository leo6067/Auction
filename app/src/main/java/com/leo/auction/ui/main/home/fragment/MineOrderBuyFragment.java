package com.leo.auction.ui.main.home.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.ActivityManager;
import com.aten.compiler.base.BaseFragment;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.YCRedDotView;
import com.leo.auction.R;
import com.leo.auction.base.BaseAppContext;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.AgreementActivity;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.model.SceneModel;
import com.leo.auction.ui.main.mine.activity.AuctionManagementActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.activity.IdentityActivity;
import com.leo.auction.ui.main.mine.activity.PromotionCenterActivivty;
import com.leo.auction.ui.main.mine.activity.SettingActivity;
import com.leo.auction.ui.main.mine.activity.SuperHouseActivity;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineOrderBuyFragment extends BaseFragment {


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
    @BindView(R.id.tv_butie)
    TextView mMineBYBT;
    @BindView(R.id.mine_cjck)
    LinearLayout mMineCjck;
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
    @BindView(R.id.mine_mfkd)
    TextView mMineMfkd;
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
    private HashMap<String, Object> mWarnHash;
    private DialogUtils dialogUtils;

    public MineOrderBuyFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_order;
    }


    @Override
    public void initData() {
        super.initData();
        dialogUtils = new DialogUtils();
    }


    @Override
    public void onResume() {
        super.onResume();

        isSeller = getArguments().getInt("isSeller", 1);   // 1：买家  2 卖家

        //创建红点View(待付款)
        YCRedDotView rdWaitPay = new YCRedDotView(BaseAppContext.getInstance());
        rdWaitPay.setTargetView(mIvWaitPay);
        rdWaitPay.setBadgeMargin(-50, 0, 0, 0);
        //创建红点View(待发货)
        YCRedDotView rdSendGood = new YCRedDotView(BaseAppContext.getInstance());
        rdSendGood.setTargetView(mIvSendGood);
        rdSendGood.setBadgeMargin(-50, 0, 0, 0);
        //创建红点View(待收货)
        YCRedDotView rdReceivedGood = new YCRedDotView(BaseAppContext.getInstance());
        rdReceivedGood.setTargetView(mIvDsh);
        rdReceivedGood.setBadgeMargin(-50, 0, 0, 0);

        //创建红点View(售后)
        YCRedDotView rdAfterSale = new YCRedDotView(BaseAppContext.getInstance());
        rdAfterSale.setTargetView(mIvServer);
        rdAfterSale.setBadgeMargin(-50, 0, 0, 0);

        mUserJson = BaseSharePerence.getInstance().getUserJson();

        try {

            mMineBYBT.setText(mUserJson.getSubsidyMoney()+"元");
            mMineZcmx.setText(mUserJson.getBalance() + "元");
            //买入
            int noPayNum = mUserJson.getBuyerOrderCount().getNoPayNum();
            int sendNum = mUserJson.getBuyerOrderCount().getSendNum();
            int receiveNum = mUserJson.getBuyerOrderCount().getReceiveNum();
            int serviceNum = mUserJson.getBuyerOrderCount().getServiceNum();
            rdWaitPay.setBadgeCount(noPayNum);
            rdWaitPay.setSingleLine(true);
            rdWaitPay.setLines(1);
            rdSendGood.setBadgeCount(sendNum);
            rdSendGood.setSingleLine(true);
            rdSendGood.setLines(1);
            rdReceivedGood.setBadgeCount(receiveNum);
            rdReceivedGood.setSingleLine(true);
            rdReceivedGood.setLines(1);
            rdAfterSale.setBadgeCount(serviceNum);
            rdAfterSale.setSingleLine(true);
            rdAfterSale.setLines(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (EmptyUtils.isEmpty(mUserJson.getExclusiveFansNum()+"") ||mUserJson.getExclusiveFansNum() < 50) {   // 1 买家 2 卖家
                mMineCjck.setVisibility(View.GONE);
                mMineFbpp.setVisibility(View.GONE);
                mMinePpgl.setVisibility(View.GONE);
                mMineMfkd.setVisibility(View.VISIBLE);
            } else {
                mMineCjck.setVisibility(View.VISIBLE);
                mMineFbpp.setVisibility(View.VISIBLE);
                mMinePpgl.setVisibility(View.VISIBLE);
                mMineMfkd.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        if (getActivity() != null) {
            UserModel.httpUpdateUser(getActivity());
        }


    }


    @OnClick({R.id.mine_mfkd, R.id.tv_butie, R.id.iv_wait_pay, R.id.all_order_wait_pay, R.id.iv_send_good, R.id.all_order_send_good, R.id.all_order_received_good, R.id.all_after_sale, R.id.all_order, R.id.ll_top01, R.id.mine_zcmx, R.id.mine_cjck, R.id.mine_fbpp, R.id.mine_ppgl, R.id.mine_tgzx, R.id.mine_gzsm, R.id.mine_kf, R.id.mine_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_wait_pay:
            case R.id.all_order_wait_pay: //全部订单: status 不传,  待付款status=1    待发货: status=2 待收货 : status=4 待评价 : status=8   售后: status = 192 (64-售后 + 128-退款导致关闭 )
//
                httpUserWeb("待付款");
                break;
            case R.id.iv_send_good:
            case R.id.all_order_send_good:
                httpUserWeb("待发货");
                break;
            case R.id.all_order_received_good:

//                webUrl = Constants.WebApi.MINE_DSH + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                httpUserWeb("待收货");
                break;
            case R.id.all_after_sale:

                httpUserWeb("售后");
                break;
            case R.id.all_order:

//                webUrl = Constants.WebApi.MINE_QB + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                httpUserWeb("全部订单");
                break;

            case R.id.tv_butie:

                UserModel.httpUpdateUser(new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                        BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
                        Bundle bundle = new Bundle();
                        bundle.putString("title", "TOP百亿补贴");
                        bundle.putString("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL + userModel.getData().getNestedToken());
                        ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
                    }
                });
                break;
            case R.id.mine_zcmx:  //资产明细
                httpUserWeb("资产明细");
//                ActivityManager.JumpActivity(getActivity(), AssetDetailActivity.class);
                break;
            case R.id.mine_cjck: //超级仓库

                if (EmptyUtils.isEmpty(mUserJson.getUsername())) {
                    mWarnHash = new HashMap<>();
                    mWarnHash.put("title", "提示");
                    mWarnHash.put("content", "您当前没有发布权限,请先完成实名认证。");
                    mWarnHash.put("ok", "去认证");
                    mWarnHash.put("okColor", "#7c1313");
                    WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
                    warningDialog.show();
                    warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                        @Override
                        public void onWarningOk() {
                            ActivityManager.JumpActivity(getActivity(), IdentityActivity.class);
                        }

                        @Override
                        public void onWaringCancel() {
                        }
                    });
                    return;
                }

//
                if (mUserJson.getLimitProductFansNum() > mUserJson.getExclusiveFansNum()) {   //粉丝规则

                        mWarnHash = new HashMap<>();
                        mWarnHash.put("title", "提示");
                        mWarnHash.put("content", "您当前没有发布权限,请查看说明如何免费获取发布权限。");
                        mWarnHash.put("ok", "去查看");
                        mWarnHash.put("okColor", "#7c1313");
                        WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
                        warningDialog.show();
                        warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                            @Override
                            public void onWarningOk() {
                                showAgreeDialog("6");
                            }

                            @Override
                            public void onWaringCancel() {
                            }
                        });

                    return;
                }

                if (!mUserJson.isStoreEnable()) {  //超级仓库发布权限
//                    mWarnHash = new HashMap<>();
//                    mWarnHash.put("title", "提示");
//                    mWarnHash.put("content", "您当前没有发布权限,请查看说明如何免费获取发布权限。");
//                    mWarnHash.put("ok", "去查看");
//                    mWarnHash.put("okColor", "#7c1313");
//                    WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
//                    warningDialog.show();
//                    warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
//                        @Override
//                        public void onWarningOk() {
//
//                            showAgreeDialog("6");
//                        }
//
//                        @Override
//                        public void onWaringCancel() {
//                        }
//                    });
                    httpUserWeb("开店申请");
                    return;
                }


                ActivityManager.JumpActivity(getActivity(), SuperHouseActivity.class);
                break;
            case R.id.mine_fbpp://发布拍品

                if (EmptyUtils.isEmpty(mUserJson.getUsername())) {
                    mWarnHash = new HashMap<>();
                    mWarnHash.put("title", "提示");
                    mWarnHash.put("content", "您当前没有发布权限,请先完成实名认证。");
                    mWarnHash.put("ok", "去认证");
                    mWarnHash.put("okColor", "#7c1313");
                    WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
                    warningDialog.show();
                    warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                        @Override
                        public void onWarningOk() {
                            ActivityManager.JumpActivity(getActivity(), IdentityActivity.class);
                        }

                        @Override
                        public void onWaringCancel() {
                        }
                    });
                    return;
                }
                if (mUserJson.getLimitProductFansNum() > mUserJson.getExclusiveFansNum()) {   //粉丝规则
                    mWarnHash = new HashMap<>();
                    mWarnHash.put("title", "提示");
                    mWarnHash.put("content", "您当前没有发布权限,请查看说明如何免费获取发布权限。");
                    mWarnHash.put("ok", "去查看");
                    mWarnHash.put("okColor", "#7c1313");
                    WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
                    warningDialog.show();
                    warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                        @Override
                        public void onWarningOk() {
                            showAgreeDialog("6");
                        }

                        @Override
                        public void onWaringCancel() {
                        }
                    });

                    return;
                }

//                if (mUserJson.getLimitProductFansNum() > mUserJson.getExclusiveFansNum()) {   //粉丝规则
//                    showAgreeDialog("6");
//                    return;
//                }
                ActivityManager.JumpActivity(getActivity(), CommodityReleaseActivity.class);
                break;
            case R.id.mine_ppgl://拍品管理
                ActivityManager.JumpActivity(getActivity(), AuctionManagementActivity.class);
                break;
            case R.id.mine_tgzx:

                ActivityManager.JumpActivity(getActivity(), PromotionCenterActivivty.class);
                break;
            case R.id.mine_gzsm:  //超级学堂


                httpUserWeb("超级学堂");

                break;
            case R.id.mine_kf:  //客服
                break;

            case R.id.mine_mfkd:  //免费开店

                if (EmptyUtils.isEmpty(mUserJson.getUsername())) {
                    mWarnHash = new HashMap<>();
                    mWarnHash.put("title", "提示");
                    mWarnHash.put("content", "您当前没有发布权限,请先完成实名认证。");
                    mWarnHash.put("ok", "去认证");
                    mWarnHash.put("okColor", "#7c1313");
                    WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
                    warningDialog.show();
                    warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                        @Override
                        public void onWarningOk() {
                            ActivityManager.JumpActivity(getActivity(), IdentityActivity.class);
                        }

                        @Override
                        public void onWaringCancel() {
                        }
                    });
                    return;
                }


                if (mUserJson.getLimitProductFansNum() > mUserJson.getExclusiveFansNum()) {   //粉丝规则

                    mWarnHash = new HashMap<>();
                    mWarnHash.put("title", "提示");
                    mWarnHash.put("content", "您当前没有发布权限,请查看说明如何免费获取发布权限。");
                    mWarnHash.put("ok", "去查看");
                    mWarnHash.put("okColor", "#7c1313");
                    WarningDialog warningDialog = new WarningDialog(getActivity(), mWarnHash);
                    warningDialog.show();
                    warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                        @Override
                        public void onWarningOk() {

                            showAgreeDialog("6");
                        }

                        @Override
                        public void onWaringCancel() {
                        }
                    });

                    return;
                }


                if (!mUserJson.isStoreEnable()) {  //超级仓库发布权限

                    httpUserWeb("开店申请");
                    return;
                }




                break;
            case R.id.mine_setting:
                ActivityManager.JumpActivity(getActivity(), SettingActivity.class);
                break;
        }
    }

    public void httpUserWeb(String title) {


        UserModel.httpUpdateUser(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                UserModel userModel = JSONObject.parseObject(resultData, UserModel.class);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(userModel.getData()));
                Globals.log("xxxxxx获取最新用户信息" + userModel.toString());
                Globals.log("xxxxxx获取最新用户信息 02 " + userModel.getData().getNestedToken());
                mUserJson = userModel.getData();
                Globals.log("xxxxxx获取最新用户信息 01 " + mUserJson.toString());
                Globals.log("xxxxxx获取最新用户信息 03 " + mUserJson.getNestedToken());
                String httpUrl = "";
                switch (title) {
                    case "待付款":
                        httpUrl = Constants.WebApi.MINE_DFK + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                        break;
                    case "待发货":
                        httpUrl = Constants.WebApi.MINE_DFH + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                        break;
                    case "待收货":
                        httpUrl = Constants.WebApi.MINE_DSH + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                        break;
                    case "售后":
                        httpUrl = Constants.WebApi.MINE_SH + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                        break;
                    case "全部订单":
                        httpUrl = Constants.WebApi.MINE_QB + isSeller + "&subsidyToken=" + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                        break;
                    case "资产明细":
                        httpUrl = Constants.WebApi.MINE_ZCMX + mUserJson.getNestedToken() + "&userId=" + mUserJson.getUserId();
                        break;
                    case "开店申请": //权限
                        httpUrl = Constants.WebApi.MINE_QXSQ;

                        break;
                    case  "超级学堂":
                        httpUrl =  Constants.WebApi.MINE_CJXT;
                        break;
                }


                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("url", httpUrl);

                Globals.log("xxxxxx httpUrl" + title  +httpUrl);

                ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
            }
        });
    }


    //出价 隐私 协议 政策
    private void showAgreeDialog(String type) {

        showWaitDialog();
        SceneModel.httpGetScene(type, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SceneModel sceneModel = JSONObject.parseObject(resultData, SceneModel.class);
                if (sceneModel.getData() == null) {
                    return;
                }
                int redirectType = sceneModel.getData().getRedirectType(); //1-富文本  2-H5页面

                if (redirectType == 1) {
                    dialogUtils.showRuleProtocolDialog(getActivity(),
                            sceneModel.getData().getContent(), new RuleProtocolDialog.IButtonListener() {
                                @Override
                                public void onClose() {
                                    dialogUtils.dissRuleProtocolDialog();
                                }
                            });
                } else {

                    String url = sceneModel.getData().getH5Url();
                    if (sceneModel.getData().getH5Url().contains("?")) {
                        url += "&isMargin=4";
                    } else {
                        url += "?isMargin=4";
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("title", "协议");
                    bundle.putString("url",url);
                    ActivityManager.JumpActivity(getActivity(), AgentWebActivity.class, bundle);
                }
            }
        });
    }


    public static MineOrderBuyFragment newIntance(int isSeller) {  //角度  1-买家角度(默认1)   2-卖家角度
        MineOrderBuyFragment mineOrderFragment = new MineOrderBuyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("isSeller", isSeller);
        mineOrderFragment.setArguments(bundle);
        return mineOrderFragment;
    }

}

package com.leo.auction.ui.main.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.easyPay.EasyPay;
import com.aten.compiler.utils.easyPay.callback.IPayCallback;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.aten.compiler.widget.expandTextView.ExpandableTextView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.AgreementActivity;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.SharedActvity;

import com.leo.auction.ui.main.home.adapter.DetailBidAdapter;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.adapter.HomeXYAdapter;
import com.leo.auction.ui.main.home.adapter.PicGridNineAdapter;
import com.leo.auction.ui.main.home.dialog.BidDialog;
import com.leo.auction.ui.main.home.dialog.EarnestDialog;
import com.leo.auction.ui.main.home.dialog.PayPwdBoardUtils;
import com.leo.auction.ui.main.home.model.BidListModel;
import com.leo.auction.ui.main.home.model.BidModel;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.ui.main.home.model.PayModel;
import com.leo.auction.ui.main.home.model.PicGridNineModel;
import com.leo.auction.ui.main.home.model.SceneModel;
import com.leo.auction.ui.main.home.web.HWebViewActivity;
import com.leo.auction.ui.main.mine.activity.CommodityReleaseActivity;
import com.leo.auction.ui.main.mine.activity.VideoPlayerActivity;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.model.GoodDetailModel;
import com.leo.auction.ui.main.mine.model.ProductDetailHeadModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.activity.GoodOrderActivity;
import com.leo.auction.ui.order.activity.OrderConfirmActivity;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.utils.DateTimeUtils;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.SpannableStringUtils;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class AuctionDetailActivity extends BaseActivity implements PicGridNineAdapter.IGridNine, CountdownView.OnCountdownEndListener, EarnestDialog.InterEarnestPay, PayPwdBoardUtils.IPayType, SetPaypwdUtils.IComplete {
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.action_recycler)
    RecyclerView mActionRecycler;
    @BindView(R.id.goods_recycler)
    RecyclerView mGoodsRecycler;

    @BindView(R.id.subsidy_lin)
    LinearLayout mSubsidyLin;
    @BindView(R.id.tab_home)
    TextView mTabHome;
    @BindView(R.id.tab_focus)
    TextView mTabFocus;
    @BindView(R.id.tab_jd)
    TextView mTabJd;
    @BindView(R.id.tab_news)
    TextView mTabNews;
    @BindView(R.id.tab_mine)
    TextView mTabMine;
    @BindView(R.id.detail_online)
    TextView mDetailOnline;


    @BindView(R.id.detail_more)
    TextView mDetailMore;
    private RImageView mDetailHead;
    private ImageView mDetailLevel;
    private TextView mDetailName;
    private TextView mDetailMark;
    private TextView mDetailFan;
    private CustomeRecyclerView mDetailAttributes;
    private ExpandableTextView mMEpContent;
    private CustomeRecyclerView mDetailNine;
    private RTextView mDetailYou;
    private RTextView mDetailTui;
    private RTextView mDetailFu;
    private TextView mDetailTime;
    private TextView mDetailTs;
    private ImageView mDetailCollect;
    private TextView mDetailShare;
    private TextView mDetailYouTui;
    private CountdownView mDetailEnd;

    private RTextView mDetailBid;
    private TextView mDetailStartPrice;
    private TextView mDetailRange;
    private TextView mDetailUp;
    private TextView mDetailIng;
    private PicGridNineAdapter mPicGridNineAdapter;

    ArrayList<String> nineImgList = new ArrayList<>();
    List<GoodsDetailModel.DataBean.BidBean> mBidBeanList = new ArrayList<>();
    private DetailBidAdapter mDetailBidAdapter;


    private String goodCode = "";
    private String goodId = "";

    private int lastPrice = 0;
    private int rangePrice = 0;
    private int bidPrice = 0;
    private String mGoodsCode;
    private HomeXYAdapter mHomeAdapter;


    //截拍时间
    private long interTime = 0;
    private long totalTime = 0;
    private int bidPage = 1;
    private int mOnePrice;

    private String shopUri = "";
    private String shopName = "";
    private BidModel.DataBean mBidModelData;
    private PayPwdBoardUtils mPayPwdBoardUtils;
    private GoodsDetailModel mGoodsDetailModel;

    private boolean isGoWxPay = false;//是否进入微信支付
    private UserModel.DataBean mUserJson;
    private DialogUtils dialogUtils;
    private BidDialog mBidDialog;


    BroadCastReceiveUtils mBroadCastReceiveUtils = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            initData();

        }
    };


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_auction_detail);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    public void initView() {
        super.initView();
        mTitleBar.setTitle("拍品详情");


//        View headLayout = LayoutInflater.from(this).inflate(R.layout.include_detail_head, null);
        mDetailHead = findViewById(R.id.detail_head);
        mDetailLevel = findViewById(R.id.detail_level);
        mDetailName = findViewById(R.id.detail_name);
        mDetailMark = findViewById(R.id.detail_mark);
        mDetailFan = findViewById(R.id.detail_fan);
        mDetailAttributes = findViewById(R.id.detail_attributes);
        mMEpContent = findViewById(R.id.ep_content);
        mDetailNine = findViewById(R.id.detail_nine);
        mDetailYou = findViewById(R.id.detail_you);
        mDetailTui = findViewById(R.id.detail_tui);
        mDetailFu = findViewById(R.id.detail_fu);
        mDetailTime = findViewById(R.id.detail_time);
        mDetailTs = findViewById(R.id.detail_ts);
        mDetailCollect = findViewById(R.id.detail_collect);
        mDetailShare = findViewById(R.id.detail_share);
        mDetailYouTui = findViewById(R.id.detail_you_tui);
        mDetailEnd = findViewById(R.id.detail_end);
        mDetailBid = findViewById(R.id.detail_bid);
        mDetailStartPrice = findViewById(R.id.detail_start_price);
        mDetailRange = findViewById(R.id.detail_range);
        mDetailUp = findViewById(R.id.detail_up);
        mDetailIng = findViewById(R.id.detail_ing);


        mDetailEnd.setOnCountdownEndListener(this);


        //设置九宫图
        mDetailNine.setHasFixedSize(true);
        mDetailNine.setNestedScrollingEnabled(false);
        mDetailNine.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mPicGridNineAdapter = new PicGridNineAdapter(AuctionDetailActivity.this);
        mDetailNine.setAdapter(mPicGridNineAdapter);


        //竞价列表
        mActionRecycler.setLayoutManager(new LinearLayoutManager(AuctionDetailActivity.this));
//        mActionRecycler.addItemDecoration(new LinearLayoutDivider(AuctionDetailActivity.this,1,getResources().getColor(R.color.gray)));
        //添加头部
        mDetailBidAdapter = new DetailBidAdapter();
//        mDetailBidAdapter.addHeaderView(headLayout);
        mActionRecycler.setAdapter(mDetailBidAdapter);


        //展开全文
        mMEpContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                RxClipboardTool.copyText(ProductDetailActivity.this, EmptyUtils.isEmpty(copyText) ? epContent.getmContent().toString() : copyText);
                return true;
            }
        });


        //投诉 举报
        mDetailTs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.log("xxxxxxx  mTitleStr 01"  );
                Bundle bundle = new Bundle();
                bundle.putString("title", "投诉");
                bundle.putString("url",Constants.WebApi.WEB_REPROCT_URL + mGoodsDetailModel.getData().getProductInstanceCode());
                ActivityManager.JumpActivity(AuctionDetailActivity.this, AgentWebActivity.class, bundle);
            }
        });
        //收藏
        mDetailCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean collect = mGoodsDetailModel.getData().isCollect();
                int collectType = 0;
                if (collect) {
                    collectType = 0;
                } else {
                    collectType = 1;
                }
                BaseModel.httpCollectGood(mGoodsDetailModel.getData().getProductInstanceId(), collectType, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {
                    }

                    @Override
                    public void httpResponse(String resultData) {
                        BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                        if (baseModel.getResult().isSuccess()) {
                            //更新收藏列表
                            BroadCastReceiveUtils.sendLocalBroadCast(AuctionDetailActivity.this, Constants.Action.ACTION_FOCUS_TYPE);
                            if (collect) {  // 0-取消 1-收藏
                                mDetailCollect.setImageResource(R.drawable.goods_uncollect);
                                ToastUtils.showShort("取消收藏成功");
                                mGoodsDetailModel.getData().setCollect(false);
                                UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "2", mGoodsDetailModel.getData().getProductInstanceId() + "", "2");
                            } else {
                                mDetailCollect.setImageResource(R.drawable.goods_collect);
                                ToastUtils.showShort("收藏成功");
                                mGoodsDetailModel.getData().setCollect(true);
                                UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "2", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
                            }
                        } else {
                            ToastUtils.showShort("操作失败");
                        }
                    }
                });
            }
        }); //分享
        mDetailShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAction();
            }
        });


        //下架
        mDetailOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toString = mDetailOnline.getText().toString();
                if ("已下架".equals(toString)) {
                    return;
                }
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("title", "");
                hashMap.put("content", "是否确认下架该拍品");
                WarningDialog warningDialog = new WarningDialog(AuctionDetailActivity.this, hashMap);
                warningDialog.show();
                warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                    @Override
                    public void onWarningOk() {
                        httpDownAuction(mGoodsDetailModel.getData().getProductInstanceCode());
                    }

                    @Override
                    public void onWaringCancel() {

                    }
                });
            }
        });


        //查看更多
        mDetailMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidPage++;
                httpBidList(false);
            }
        });


        //更新价格
        mDetailUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidPage = 1;
                httpBidList(false);
            }
        });


        mSubsidyLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUserJson == null) {
                    LoginActivity.newIntance(AuctionDetailActivity.this, 0);
                    ToastUtils.showShort("请先登录");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("title", "TOP百亿补贴");
                bundle.putString("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL + mUserJson.getNestedToken());
                ActivityManager.JumpActivity(AuctionDetailActivity.this, AgentWebActivity.class, bundle);
            }
        });


//        mGoodsRecycler.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
        DisplayMetrics dm = getResources().getDisplayMetrics();
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mGoodsRecycler.setLayoutManager(staggeredGridLayoutManager);


//        GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mGoodsRecycler.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));


        mHomeAdapter = new HomeXYAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_20)) * 4);
        mHomeAdapter.setHeaderAndEmpty(true);
        ((SimpleItemAnimator) mGoodsRecycler.getItemAnimator()).setSupportsChangeAnimations(false);
        mHomeAdapter.setHasStableIds(true);
        mGoodsRecycler.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {

                HomeListModel.DataBean json = (HomeListModel.DataBean) mHomeAdapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("goodsCode", json.getProductInstanceCode());
                finish();
                ActivityManager.JumpActivity(AuctionDetailActivity.this, AuctionDetailActivity.class, bundle);

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void initData() {
        super.initData();
        mGoodsCode = getIntent().getExtras().getString("goodsCode");
        mUserJson = BaseSharePerence.getInstance().getUserJson();
        mPayPwdBoardUtils = new PayPwdBoardUtils();
        dialogUtils = new DialogUtils();
        httpDetail();

        BroadCastReceiveUtils.registerLocalReceiver(this, Constants.Action.ACTION_DETAIL_REFRESH, mBroadCastReceiveUtils);
    }


    private void httpDetail() {
        showWaitDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productInstanceCode", mGoodsCode);
        HttpRequest.httpGetString(Constants.Api.GOODS_DETAIL_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                mGoodsDetailModel = JSONObject.parseObject(resultData, GoodsDetailModel.class);
                if (mUserJson != null) {
                    UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "1", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
                }
                upUIdata(mGoodsDetailModel.getData());
                httpGoodsList(mGoodsDetailModel.getData().getProductUser().getUserId());
            }
        });
    }


    //下架
    private void httpDownAuction(String productInstanceCode) {
        showWaitDialog();
        BaseModel.httpDownAuction(productInstanceCode, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    ToastUtils.showShort("下架成功");
                    mDetailOnline.setText("已下架");
                    mDetailOnline.setTextColor(getResources().getColor(R.color.home_text));
                    mDetailIng.setText("竞拍结束");
                    mDetailEnd.stop();
                    mDetailEnd.allShowZero();

                    httpDetail();
                } else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }
            }
        });
    }


    //分享按钮点击
    private void shareAction() {
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if (userJson == null) {
            showShortToast("请先登录");
            LoginActivity.newIntance(AuctionDetailActivity.this, 0);
            return;
        }

//        Constants.Action.ACTION_ACTION  mGoodsDetailModel.getData()
//
        GoodsDetailModel.DataBean detailModelData = mGoodsDetailModel.getData();
        String path = Constants.WebApi.SHARE_PRODUCT_URL + detailModelData.getProductInstanceCode()
                + "&shareAgentId=" + userJson.getUserId();
        String type = "3";//1-推荐粉丝  2-推荐商家  3-拍品详情 4-超级仓库商品详情


        ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareTitlelList();

        Random ra = new Random();
        int anInt = ra.nextInt(shareShopTitlelList.size());

        String shareName = "【" + detailModelData.getProductUser().getNickname() + "】" + shareShopTitlelList.get(anInt) + "【" + detailModelData.getTitle() + "】";

        String sharedText = mMEpContent.getmContent().toString();

        SharedModel sharedModel = new SharedModel(shareName, sharedText, detailModelData.getImages() == null ? "" : detailModelData.getImages().get(0),
                detailModelData.getCurrentPrice() + "", detailModelData.getProductUser().getHeadImg(), type, path, detailModelData.getProductInstanceCode(), userJson.getUserId(),
                Constants.Action.ACTION_ACTION);

//        SharedActvity.newIntance(AuctionDetailActivity.this, sharedModel, sharedText, "0");

        if (mGoodsDetailModel.getData().getVideo() != null && !mGoodsDetailModel.getData().getVideo().isEmpty()) {
            SharedActvity.newIntance(AuctionDetailActivity.this, sharedModel, nineImgList, sharedText + path, mGoodsDetailModel.getData().getVideo());
        } else {
            SharedActvity.newIntance(AuctionDetailActivity.this, sharedModel, nineImgList, sharedText + path, "");
        }


    }


    private void upUIdata(GoodsDetailModel.DataBean goodsDetailModel) {

        //是否是补贴商品
        if (mGoodsDetailModel.getData().isSubsidyProduct()) {
            mSubsidyLin.setVisibility(View.VISIBLE);
        } else {
            mSubsidyLin.setVisibility(View.GONE);
        }


        goodCode = goodsDetailModel.getProductInstanceCode();
        goodId = String.valueOf(goodsDetailModel.getProductInstanceId());

        shopUri = goodsDetailModel.getProductUser().getUserId();
        shopName = goodsDetailModel.getProductUser().getNickname();
        GlideUtils.loadImg(goodsDetailModel.getProductUser().getHeadImg(), mDetailHead);

        mDetailName.setText(goodsDetailModel.getProductUser().getNickname());
        mDetailMark.setText("评分 " + goodsDetailModel.getProductUser().getRate());
        mDetailFan.setText("粉丝 " + goodsDetailModel.getProductUser().getFansNum());

        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();

        String[] myLevelVPicS = commonJson.getSeller_level_pic().get(0).split("seller_level_");
        String vipUrl = myLevelVPicS[0] + "seller_level_" + goodsDetailModel.getProductUser().getSellerLevel() + ".png";
        GlideUtils.loadImgDefault(vipUrl, mDetailLevel);

        ArrayList<PicGridNineModel> mPicList = new ArrayList<>();
        nineImgList.clear();

        //1判断是否有视频
        if (goodsDetailModel.getVideo() != null && !goodsDetailModel.getVideo().isEmpty()) {
            PicGridNineModel picGridNineModel01 = new PicGridNineModel("1", goodsDetailModel.getVideo(),
                    goodsDetailModel.getCutPic());
            mPicList.add(picGridNineModel01);
            mPicGridNineAdapter.setHasVideoSize(1);
            if (goodsDetailModel.getImages().size() >= 9) {
                for (String s : goodsDetailModel.getImages().subList(0, 8)) {
                    nineImgList.add(s);
                }
            } else {
                nineImgList = goodsDetailModel.getImages();
            }
        } else {
            nineImgList = goodsDetailModel.getImages();
        }

        for (int i = 0; i < nineImgList.size(); i++) {
            PicGridNineModel picGridNineModel = new PicGridNineModel("0", "", nineImgList.get(i));
            mPicList.add(picGridNineModel);
        }

        //设置列表数据
        mPicGridNineAdapter.clearImgViews();
        mPicGridNineAdapter.setNewData(mPicList);


        boolean collect = mGoodsDetailModel.getData().isCollect();

        if (collect) {
            mDetailCollect.setImageResource(R.drawable.goods_collect);
        } else {
            mDetailCollect.setImageResource(R.drawable.goods_uncollect);
        }


        String content = "【品名】" + goodsDetailModel.getTitle() + "\n";
        for (GoodsDetailModel.DataBean.AttributesBean attribute : goodsDetailModel.getAttributes()) {
            if (!EmptyUtils.isEmpty(attribute.getValue())
                    && !"其他".equals(attribute.getValue())
                    && !"类型".equals(attribute.getTitle())) {
                content += "【" + attribute.getTitle() + "】" + attribute.getValue() + "\n";
            }
        }


        content += "【介绍】" + goodsDetailModel.getContent() + " ";
        mMEpContent.setContent(content);


        //  distributeType:'1-包邮 2-到付',
        //    refund:'1-包退 0-不包退',
        mDetailYou.setText(goodsDetailModel.getDistributeType() == 1 ? "包邮" : "到付");
        mDetailTui.setVisibility(goodsDetailModel.isRefund() ? View.VISIBLE : View.GONE);

        mDetailTime.setText(TimeUtils.millis2String(goodsDetailModel.getCreateTime(), "MM月dd日 HH:mm"));


        if (goodsDetailModel.getStartPrice() != null && goodsDetailModel.getStartPrice().length() > 0 && !goodsDetailModel.getStartPrice().equals("0")) {
            mDetailStartPrice.setText(SpannableStringUtils.getBuilder("起拍价： ").append("￥" + goodsDetailModel.getStartPrice())
                    .setXProportion((float) 1.2).setForegroundColor(getResources().getColor(R.color.home_title_bg)).create());
        } else {
            mDetailStartPrice.setVisibility(View.GONE);
        }

        mDetailRange.setText(SpannableStringUtils.getBuilder("加价幅度：").append("￥" + goodsDetailModel.getMarkupRange())
                .setXProportion((float) 1.2).setForegroundColor(getResources().getColor(R.color.home_title_bg)).create());


        int detailModelStatus = goodsDetailModel.getStatus();
        mDetailBidAdapter.setBidStaus(detailModelStatus);//根据状态显示竞拍列表的第一个状态


        //竞价列表
        mBidBeanList = goodsDetailModel.getBid();
        mDetailBidAdapter.setNewData(mBidBeanList);
        mDetailBidAdapter.notifyDataSetChanged();
        rangePrice = goodsDetailModel.getMarkupRange();  //加价幅度
        lastPrice = goodsDetailModel.getCurrentPrice();
        String startPrice = goodsDetailModel.getStartPrice();
        mOnePrice = Integer.parseInt(startPrice);

        if (lastPrice == 0) {  //第一次出价
            if (mOnePrice == 0) {
                bidPrice = rangePrice;
            } else {
                bidPrice = mOnePrice;
            }
        }

        if (detailModelStatus == 1) {  //竞拍中 1-竞拍中  2-在线付款 4-当面交易 8-未付款  16-流拍 32-未及时付款 64-已下架 128-退款
            mDetailBid.setText("出个价");
            mDetailBid.setBackgroundColor(getResources().getColor(R.color.home_title_bg));

            //出价
            mDetailBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先更新价格 再弹出出价窗口
                    bidPage = 1;
                    mUserJson = BaseSharePerence.getInstance().getUserJson();
                    if (mUserJson == null) {
                        Globals.log("xxxxxx出价02");
                        ToastUtils.showShort("请先登录");
                        LoginActivity.newIntance(AuctionDetailActivity.this, 0);
                        return;
                    }
                    UserModel.httpUpdateUser(AuctionDetailActivity.this);

                    if (goodsDetailModel.getProductUser().getUserId().equals(mUserJson.getUserId())) {
                        ToastUtils.showShort("不允许对自己的拍品出价");
                        return;
                    }

                    httpBidList(true);
                }
            });
        } else if (detailModelStatus == 8) {
            if (mBidBeanList.size() > 0) {
                String userId = mBidBeanList.get(0).getUserAccountId() + "";
                if (userId.equals(mUserJson.getId() + "")) {
                    mDetailBid.setText("立即付款");
                    mDetailIng.setText("竞拍结束");
                    mDetailBid.setBackgroundColor(getResources().getColor(R.color.home_title_bg));
                }

                mDetailBid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {// {"subsidy":[{"orderCode":"132123131","subsidyLimit":"12"}]}
                        Bundle bundle = new Bundle();
                        bundle.putString("orderCode", goodsDetailModel.getOrder().getOrderCode());
                        bundle.putString("scene", "order");
                        bundle.putString("productInstanceCode", goodsDetailModel.getProductInstanceCode());
                        //是否是补贴商品
                        if (mGoodsDetailModel.getData().isSubsidyProduct() && mBidBeanList.get(0).getBidPrice() ==2 ) {  //金额要大于等于2
                            bundle.putString("subsidyLimit", mGoodsDetailModel.getData().getSubsidyMoney());
//                            Globals.log("xxxxxx  mBidBeanList.get(0).getBidPrice()01 " +mBidBeanList.get(0).getBidPrice());
                        }else if (mGoodsDetailModel.getData().isSubsidyProduct() &&  mBidBeanList.get(0).getBidPrice() >2) {  //金额要大于等于2
                            bundle.putString("subsidyLimit", mGoodsDetailModel.getData().getSubsidyMoney());
//                            Globals.log("xxxxxx  mBidBeanList.get(0).getBidPrice()010  " +mBidBeanList.get(0).getBidPrice());
                        }else {
                            bundle.putString("subsidyLimit","");
                        }
                        ActivityManager.JumpActivity(AuctionDetailActivity.this, GoodOrderActivity.class, bundle);
                        finish();
                    }
                });
                return;
            }

        } else {
            String timeToString = DateTimeUtils.timeToString(goodsDetailModel.getInterceptTime() + "", "yyyy-MM-dd HH:mm:ss");
            mDetailBid.setText("拍卖已结束   " + timeToString);
            mDetailBid.setBackgroundColor(getResources().getColor(R.color.home_pick));
        }


        if (mUserJson !=null){
            if (goodsDetailModel.getProductUser().getUserId().equals(mUserJson.getUserId()) && detailModelStatus == 1 && mBidBeanList.size() == 0) {
                mDetailOnline.setVisibility(View.VISIBLE);
            } else if (goodsDetailModel.getProductUser().getUserId().equals(mUserJson.getUserId()) && detailModelStatus == 64 && mBidBeanList.size() == 0) {
                mDetailOnline.setText("已下架");
                mDetailIng.setText("竞拍结束");
                mDetailEnd.start(0);
                mDetailEnd.stop();
                mDetailOnline.setTextColor(getResources().getColor(R.color.home_text));
                return;
            }

        } else {
            mDetailOnline.setVisibility(View.GONE);
        }




        long countDowntime = goodsDetailModel.getInterceptTime() - System.currentTimeMillis();
        int delayTime = goodsDetailModel.getDelayTime();


        if (countDowntime <= 0) {
            mDetailIng.setText("竞拍结束");
            String timeToString = DateTimeUtils.timeToString(goodsDetailModel.getInterceptTime() + "", "yyyy-MM-dd HH:mm:ss");
            mDetailBid.setText("拍卖已结束   " + timeToString);
            mDetailEnd.start(0);
            mDetailEnd.stop();
            mDetailBid.setBackgroundColor(getResources().getColor(R.color.home_pick));
            return;
        }

        mDetailEnd.start(countDowntime);

        mDetailEnd.setOnCountdownIntervalListener(3000, new CountdownView.OnCountdownIntervalListener() {
            @Override
            public void onInterval(CountdownView cv, long remainTime) { //当前时间
                if (remainTime < delayTime) {
                    mDetailIng.setText("延迟竞拍");
                }
            }
        });


//        } else if (detailModelStatus == 2) {
//            mDetailBid.setText("在线付款");
//        } else if (detailModelStatus == 4) {
//            mDetailBid.setText("当面交易");
//        } else if (detailModelStatus == 8) {
//            mDetailBid.setClickable(false);
//            mDetailBid.setText("未付款");
//        } else if (detailModelStatus == 16) {
//            mDetailBid.setText("流拍");
//            mDetailBid.setClickable(false);
//            mDetailBid.getHelper().setBackgroundColorNormal(R.color.collect_text);
//        } else if (detailModelStatus == 32) {
//            mDetailBid.setClickable(false);
//            mDetailBid.setText("未及时付款");
//        } else if (detailModelStatus == 64) {
//            mDetailBid.setText("已下架");
//            mDetailBid.setClickable(false);
//            mDetailBid.getHelper().setBackgroundColorNormal(R.color.collect_text);
//        } else if (detailModelStatus == 128) {
//            mDetailBid.setClickable(false);
//            mDetailBid.setText("退款");
//        }


//        mDetailBidAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                bidPage++;
//            }
//        }, mActionRecycler);
    }


    //获取竞价列表
    private void httpBidList(boolean showBid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("instanceCode", goodCode);
        hashMap.put("pageNum", String.valueOf(bidPage));

        hashMap.put("pageSize", Constants.Var.LIST_NUMBER_F);
        showWaitDialog();

        HttpRequest.httpGetString(Constants.Api.GOODS_DETAIL_BID_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();

                BidListModel bidListModel = JSONObject.parseObject(resultData, BidListModel.class);
                List<GoodsDetailModel.DataBean.BidBean> data = bidListModel.getData();
                mDetailBidAdapter.loadMoreComplete();
                if (data.size() > 0) {
                    bidPrice = data.get(0).getBidPrice() + rangePrice;  //最新价格
                    lastPrice = data.get(0).getBidPrice();
                    if (bidPage == 1) {
                        mDetailBidAdapter.setNewData(data);
                    } else {
                        mDetailBidAdapter.addData(data);
                    }

                    if (data.size() >= Constants.Var.LIST_NUMBER_INT_F) {
                        mDetailMore.setVisibility(View.VISIBLE);
                    } else {
                        mDetailMore.setVisibility(View.GONE);
                    }

                } else {
                    if (mOnePrice == 0) {
                        bidPrice = rangePrice;
                    } else {
                        bidPrice = mOnePrice;
                    }
                    mDetailMore.setVisibility(View.GONE);
                }
                if (showBid) {
                    showBidDialog();
                }
            }
        });
    }


    public void showBidDialog() {
        int userLevel = mUserJson.getLevel();
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("lastPrice", lastPrice);
        hashMap.put("rangePrice", rangePrice);
        hashMap.put("bidPrice", bidPrice);
        hashMap.put("userLevel", userLevel);
        mBidDialog = new BidDialog(AuctionDetailActivity.this, hashMap, new BidDialog.InterBidDialog() {
            @Override
            public void onItemOkClick(String price) {
                httpBid(price);
                mBidDialog.dismiss();
                bidPrice = Integer.valueOf(price);
            }

            @Override
            public void onItemCDJY() {
                mBidDialog.dismiss();
                showAgreeDialog("2");
            }

            @Override
            public void onItemYSZC() {
                mBidDialog.dismiss();
                showAgreeDialog("3");
            }
        });
        mBidDialog.show();
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
                    dialogUtils.showRuleProtocolDialog(AuctionDetailActivity.this,
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

                    Intent intent = new Intent(AuctionDetailActivity.this, AgreementActivity.class);
                    intent.putExtra("title", "协议");
                    intent.putExtra("url", url);
                    intent.putExtra("hasNeedTitleBar", true);
                    intent.putExtra("hasNeedRightView", false);
                    intent.putExtra("hasNeedLeftView", true);
                    startActivity(intent);
                }
            }
        });
    }


    public void httpBid(String price) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productInstanceId", goodId);
        hashMap.put("productInstanceCode", goodCode);
        hashMap.put("bidPrice", price);
        hashMap.put("currentPrice", lastPrice + "");
        showWaitDialog();
        HttpRequest.httpPostString(Constants.Api.GOODS_DETAIL_BID_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                JSONObject jsonObject = JSONObject.parseObject(resultData);
                String result = jsonObject.getString("result");
                JSONObject json = JSONObject.parseObject(result);
                if (!json.getBoolean("success")) {
                    ToastUtils.showShort(json.getString("message"));
                    return;
                }


                BidModel bidModel = JSONObject.parseObject(resultData, BidModel.class);
                if (bidModel.getData().getBssCode() == 105) {
                    ToastUtils.showShort("请缴纳保证金");
                    mBidModelData = bidModel.getData();
                    HashMap<String, String> mHash = new HashMap<>();
                    EarnestDialog earnestDialog = new EarnestDialog(AuctionDetailActivity.this, mHash, AuctionDetailActivity.this);
                    earnestDialog.show();
                } else {
                    ToastUtils.showShort("出价成功");
                    UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "3", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
                }
                httpDetail();
            }
        });
    }


    public void httpGoodsList(String shopUri) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shopUri", shopUri);
        hashMap.put("pageNum", "1");
        hashMap.put("pageSize", "6");
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.SHOP_NEWEST_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                HomeListModel homeListModel = JSONObject.parseObject(resultData, HomeListModel.class);
                mHomeAdapter.setNewData(homeListModel.getData());
                mHomeAdapter.notifyDataSetChanged();
            }
        });
    }


    //商品详情 视频点击事件
    @Override
    public void onVideoClick(String videoPath, String thumbImageView) {
        VideoPlayerActivity.newIntance(AuctionDetailActivity.this, videoPath, false);
    }

    //商品详情 图片点击事件
    @Override
    public void onImgClick(int pos) {
        ImageView[] imgViews = (ImageView[]) mPicGridNineAdapter.getImgViews().toArray(new ImageView[mPicGridNineAdapter.getImgViews().size()]);
        String[] imgListsData = nineImgList.toArray(new String[nineImgList.size()]);
        ImageShowActivity.startImageActivity(AuctionDetailActivity.this, imgViews, imgListsData, pos, true);
    }

    @Override
    public void onEnd(CountdownView cv) {
        mDetailIng.setText("竞拍结束");
        GoodsDetailModel.DataBean goodsDetailModelData = mGoodsDetailModel.getData();
        String timeToString = DateTimeUtils.timeToString(goodsDetailModelData.getInterceptTime() + "", "yyyy-MM-dd HH:mm:ss");
        mDetailBid.setText("拍卖已结束" + timeToString);
        mDetailBid.setClickable(false);
        mDetailBid.setBackgroundColor(getResources().getColor(R.color.home_pick));
        httpDetail();
    }


    /*保证金支付*/
    @Override
    public void earnestPay() {
//        利用回调 到详情页支付

        if (mBidModelData != null) {    //支付保证金情况
            ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonUsedData.getInstance().getOrderPayTypeData(
                    mUserJson.getBalance(), String.valueOf(mBidModelData.getMoney()));
            mPayPwdBoardUtils.showPayTypeDialog(AuctionDetailActivity.this, String.valueOf(mBidModelData.getMoney()), orderPayTypeModels, AuctionDetailActivity.this);
        } else {//支付商品情况
            ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonUsedData.getInstance().getOrderPayTypeData(
                    mUserJson.getBalance(), bidPrice + "");
            mPayPwdBoardUtils.showPayTypeDialog(AuctionDetailActivity.this, String.valueOf(bidPrice), orderPayTypeModels, AuctionDetailActivity.this);
        }


    }

    /*
     *
     * 支付*/
    @Override
    public void choosePayType(int pos) {


        mPayPwdBoardUtils.dismissPayTypeDialog();
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();

        int payMoney = 0;
        if (mBidModelData != null) {
            payMoney = mBidModelData.getMoney();
        } else {
            payMoney = bidPrice;
        }
        if (pos == 0) {
            double moneyTag = new BigDecimal(userJson.getBalance()).subtract(new BigDecimal(payMoney)).doubleValue();
            if (moneyTag >= 0) {
                //说明余额够支付
                if (payMoney > 300 || EmptyUtils.isEmpty(userJson.getBalanceExempt())) {
                    //余额密码支付
                    mPayPwdBoardUtils.showPayPasswordDialog(AuctionDetailActivity.this,
                            String.valueOf(payMoney), this);
                } else {
                    //免密支付
                    balance("", userJson.getBalanceExempt());
                }
            } else {
                //余额不够支付
                showShortToast("余额不足");
                showWaitDialog();
                wxPay();
            }
        } else if (pos == 1) {
            showWaitDialog();
            wxPay();
        }
    }


    @Override
    public void onComplete(String text) {
        balance(text, "");
    }


    //余额支付
    public void balance(String payPwd, String exempt) {
        mPayPwdBoardUtils.dismissPayPasswordDialog();
        int payMoney = 0;
        if (mBidModelData != null) {
            payMoney = mBidModelData.getMoney();
        } else {
            payMoney = bidPrice;
        }


        UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "3", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
        PayModel.httpPay(2, "bid", payMoney + "", mBidModelData.getTradeNo(), null, payPwd, exempt, null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                PayModel payModel = JSONObject.parseObject(resultData, PayModel.class);
                if (payModel.getResult().isSuccess()) {
                    setPaySuccess();
                } else {
                    ToastUtils.showShort(payModel.getResult().getMessage());
                }
            }
        });
    }

    //微信支付
    private void wxPay() {
        int payMoney = 0;
        if (mBidModelData != null) {
            payMoney = mBidModelData.getMoney();
        } else {
            payMoney = bidPrice;
        }
        mPayPwdBoardUtils.dismissPayPasswordDialog();

        UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "3", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
        PayModel.httpPay(1, "bid", payMoney + "",  mBidModelData.getTradeNo(), null, "", "", null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                PayModel returnData = JSONObject.parseObject(resultData, PayModel.class);
                PayModel.DataBean.WxBean data = returnData.getData().getWx();
                //实例化微信支付策略
                WXPay wxPay = WXPay.getInstance();
                //构造微信订单实体。一般都是由服务端直接返回。
                WXPayBean wxPayBean = new WXPayBean(Constants.Nouns.WEIXINAPPID, data.getMchId(), data.getPrepayId(), data.getPackages(),
                        data.getNonceStr(), data.getTimeStamp(), data.getPaySign());
                isGoWxPay = true;
                //策略场景类调起支付方法开始支付，以及接收回调。
                //策略场景类调起支付方法开始支付，以及接收回调。
                EasyPay.pay(wxPay, AuctionDetailActivity.this, wxPayBean, new IPayCallback() {
                    @Override
                    public void success() {
                        isGoWxPay = false;
                        setPaySuccess();
                    }

                    @Override
                    public void failed(int code, String message) {
                        isGoWxPay = false;
                        showShortToast("支付失败");
                    }

                    @Override
                    public void cancel() {
                        isGoWxPay = false;
                        showShortToast("支付取消");
                    }
                });
            }
        });
    }

    //支付成功后的操作
    private void setPaySuccess() {
//        Intent intent = new Intent();
//        intent.putExtra("OperationType", "balancePay");
//        intent.putExtra("id", id);
//        setResult(RESULT_OK, intent);
//        showWaitDialog();
////        getData();
//        payInputPwdBoardUtils.dismissPayPasswordDialog();
//
//        OrderStatusActivity.newIntance(AuctionDetailActivity.this,info.getShopUri(),"0");
//        finish();

        httpBid(bidPrice + "");
    }


    @OnClick({R.id.tab_home, R.id.tab_focus, R.id.tab_jd, R.id.tab_news, R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                MainActivity.newIntance(AuctionDetailActivity.this, 0);
                break;
            case R.id.tab_focus:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AuctionDetailActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AuctionDetailActivity.this, MainActivity.class);
//                MainActivity.newIntance(AuctionDetailActivity.this, 4);
                ActivityManager.mainActivity.setCurrent(2);
                break;
            case R.id.tab_jd:
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", shopUri);
                bundle.putString("shopName", shopName);
                ActivityManager.JumpActivity(AuctionDetailActivity.this, ShopActivity.class, bundle);
                break;
            case R.id.tab_news:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AuctionDetailActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AuctionDetailActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(3);
                break;
            case R.id.tab_mine:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AuctionDetailActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AuctionDetailActivity.this, MainActivity.class);
//                MainActivity.newIntance(AuctionDetailActivity.this, 4);
                ActivityManager.mainActivity.setCurrent(4);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(this, mBroadCastReceiveUtils);
    }
}



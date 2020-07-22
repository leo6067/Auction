package com.leo.auction.ui.main.home.activity;

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
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.CommonlyUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.WebViewActivity;
import com.leo.auction.ui.main.home.adapter.DetailBidAdapter;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
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
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.order.model.OrderPayTypeModel;
import com.leo.auction.utils.SetPaypwdUtils;
import com.leo.auction.utils.SpannableStringUtils;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.leo.auction.utils.wxPay.WXPay;
import com.leo.auction.utils.wxPay.WXPayBean;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    List<String> nineImgList = new ArrayList<>();
    List<GoodsDetailModel.DataBean.BidBean> mBidBeanList = new ArrayList<>();
    private DetailBidAdapter mDetailBidAdapter;
    ArrayList<PicGridNineModel> mPicList = new ArrayList<>();


    private String goodCode = "";
    private String goodId = "";

    private int lastPrice = 0;
    private int rangePrice = 0;
    private int bidPrice = 0;
    private String mGoodsCode;
    private HomeAdapter mHomeAdapter;


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

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_auction_detail);
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


        //投诉
        mDetailTs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AuctionDetailActivity.this, WebViewActivity.class);
                intent.putExtra("title", "投诉");
                intent.putExtra("url", Constants.WebApi.WEB_REPROCT_URL);
                intent.putExtra("hasNeedTitleBar", true);
                intent.putExtra("hasNeedRightView", false);
                intent.putExtra("hasNeedLeftView", true);
                startActivity(intent);

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
                Intent intent = new Intent(AuctionDetailActivity.this, WebViewActivity.class);
                intent.putExtra("title", "TOP百亿补贴");
                intent.putExtra("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL+ mGoodsDetailModel.getData().getProductInstanceCode());
                intent.putExtra("hasNeedTitleBar", true);
                intent.putExtra("hasNeedRightView", false);
                intent.putExtra("hasNeedLeftView", true);
                startActivity(intent);
            }
        });


        mGoodsRecycler.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_20), 2));
        DisplayMetrics dm = getResources().getDisplayMetrics();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGoodsRecycler.setLayoutManager(staggeredGridLayoutManager);
        mHomeAdapter = new HomeAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_20)) * 4);
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
    public void initData() {
        super.initData();
        mGoodsCode = getIntent().getExtras().getString("goodsCode");
        mPayPwdBoardUtils = new PayPwdBoardUtils();
        httpDetail();
        UserModel.httpUpdateUser();
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
                upUIdata(mGoodsDetailModel.getData());
                UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "1", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
                httpGoodsList();
            }
        });
    }



    //分享按钮点击
    private void shareAction(){


        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();


        if (userJson == null){
            showShortToast("请先登录");
            LoginActivity.newIntance(AuctionDetailActivity.this);
            return;
        }





            SharedModel sharedModel = new SharedModel(mGoodsDetailModel.getData().getImages() == null ? "" : mGoodsDetailModel.getData().getImages().get(0),
                    mGoodsDetailModel.getData().getTitle(), mGoodsDetailModel.getData().getCurrentPrice()+"", userJson.getNickname(), userJson.getHeadImg(),
                    String.valueOf(mGoodsDetailModel.getData().getProductInstanceId()), "2", "productdetail", String.valueOf(mGoodsDetailModel.getData().getProductInstanceId()), mGoodsDetailModel.getData().getProductInstanceCode(),
                    userJson.getUserId(), Constants.Action.ACTION_ACTION,
                    String.valueOf(mGoodsDetailModel.getData().getProductInstanceId()), "pages/transfer/qrcode/qrcode");
            String sharedText =   mMEpContent.getmContent().toString() ;
//            if (mGoodsDetailModel.getData().getVideo() != null && !mGoodsDetailModel.getData().getVideo().isEmpty()) {
//                SharedActvity02.newIntance(AuctionDetailActivity.this, sharedModel, mPicList, sharedText, mGoodsDetailModel.getData().getVideo());
//            } else {
//                SharedActvity02.newIntance(AuctionDetailActivity.this, sharedModel, mPicList, sharedText, "");
//            }




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


        nineImgList = goodsDetailModel.getImages();
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


        String content = "【品名】" + goodsDetailModel.getTitle() + "\n【介绍】" + goodsDetailModel.getContent() + " ";
        mMEpContent.setContent(content);


        //  distributeType:'1-包邮 2-到付',
        //    refund:'1-包退 0-不包退',
        mDetailYou.setText(goodsDetailModel.getDistributeType() == 1 ? "包邮" : "到付");
        mDetailTui.setVisibility(goodsDetailModel.isRefund() ? View.VISIBLE : View.GONE);

        mDetailTime.setText(TimeUtils.millis2String(goodsDetailModel.getCreateTime(), "MM月dd日 HH:mm"));


        if (goodsDetailModel.getStartPrice() != null && goodsDetailModel.getStartPrice().length() > 0) {
            mDetailStartPrice.setText(SpannableStringUtils.getBuilder("起拍价： ").append("￥" + goodsDetailModel.getStartPrice())
                    .setXProportion((float) 1.2).setForegroundColor(getResources().getColor(R.color.home_title_bg)).create());
        } else {
            mDetailStartPrice.setVisibility(View.GONE);
        }

        mDetailRange.setText(SpannableStringUtils.getBuilder("加价幅度：").append("￥" + goodsDetailModel.getMarkupRange())
                .setXProportion((float) 1.2).setForegroundColor(getResources().getColor(R.color.home_title_bg)).create());


        long countDowntime = goodsDetailModel.getInterceptTime() - System.currentTimeMillis();
        int delayTime = goodsDetailModel.getDelayTime();

        mDetailEnd.start(countDowntime);

        mDetailEnd.setOnCountdownIntervalListener(3000, new CountdownView.OnCountdownIntervalListener() {
            @Override
            public void onInterval(CountdownView cv, long remainTime) { //当前时间
                if (remainTime < delayTime) {
                    mDetailIng.setText("延迟竞拍");
                }
            }
        });


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
            //出价
            mDetailBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先更新价格 再弹出出价窗口
                    bidPage = 1;
                    httpBidList(true);
                }
            });
        } else if (detailModelStatus == 2) {
            mDetailBid.setText("在线付款");
        } else if (detailModelStatus == 4) {
            mDetailBid.setText("当面交易");
        } else if (detailModelStatus == 8) {
            mDetailBid.setClickable(false);
            mDetailBid.setText("未付款");
        } else if (detailModelStatus == 16) {
            mDetailBid.setText("流拍");
            mDetailBid.setClickable(false);
            mDetailBid.getHelper().setBackgroundColorNormal(R.color.collect_text);
        } else if (detailModelStatus == 32) {
            mDetailBid.setClickable(false);
            mDetailBid.setText("未及时付款");
        } else if (detailModelStatus == 64) {
            mDetailBid.setText("已下架");
            mDetailBid.setClickable(false);
            mDetailBid.getHelper().setBackgroundColorNormal(R.color.collect_text);
        } else if (detailModelStatus == 128) {
            mDetailBid.setClickable(false);
            mDetailBid.setText("退款");
        }


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
//        hashMap.put("pageNum", String.valueOf(bidPage));
        hashMap.put("pageNum", "1");
        hashMap.put("pageSize", Constants.Var.LIST_MAX);
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
                    if (bidPage == 1) {
                        mDetailBidAdapter.setNewData(data);

                    } else {
                        mDetailBidAdapter.addData(data);
                    }



//                    if (bidPage > 1 && data.isEmpty()) {
//                        if (mDetailBidAdapter.getData().size() > Constants.Var.LIST_NUMBER_INT) {
//                            mDetailBidAdapter.loadMoreEnd(true);
//                        } else {
//                            mDetailBidAdapter.loadMoreEnd();
//                        }
//                    }
                } else {
                    if (mOnePrice == 0) {
                        bidPrice = rangePrice;
                    } else {
                        bidPrice = mOnePrice;
                    }
                }
                if (showBid) {
                    showBidDialog();
                }
            }
        });
    }


    public void showBidDialog() {
        int userLevel = BaseSharePerence.getInstance().getUserJson().getLevel();
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("lastPrice", lastPrice);
        hashMap.put("rangePrice", rangePrice);
        hashMap.put("bidPrice", bidPrice);
        hashMap.put("userLevel", userLevel);
        BidDialog bidDialog = new BidDialog(AuctionDetailActivity.this, hashMap, new BidDialog.InterBidDialog() {
            @Override
            public void onItemOkClick(String price) {
                httpBid(price);
                bidPrice = Integer.valueOf(price);
            }
        });
        bidDialog.show();
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


    public void httpGoodsList() {
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
        mDetailBid.setClickable(false);
        mDetailBid.getHelper().setBackgroundColorNormal(R.color.collect_text);
    }


    /*保证金支付*/
    @Override
    public void earnestPay() {

//        利用回调 到详情页支付
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        ArrayList<OrderPayTypeModel> orderPayTypeModels = CommonlyUsedData.getInstance().getOrderPayTypeData(
                userJson.getBalance(), String.valueOf(mBidModelData.getMoney()));
        mPayPwdBoardUtils.showPayTypeDialog(AuctionDetailActivity.this, String.valueOf(mBidModelData.getMoney()), orderPayTypeModels, AuctionDetailActivity.this);
    }

    /*
     *
     * 支付*/
    @Override
    public void choosePayType(int pos) {
        mPayPwdBoardUtils.dismissPayTypeDialog();
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        if (pos == 0) {
            double moneyTag = new BigDecimal(userJson.getBalance()).subtract(new BigDecimal(mBidModelData.getMoney())).doubleValue();
            if (moneyTag >= 0) {
                //说明余额够支付
                if (mBidModelData.getMoney() > 300 || EmptyUtils.isEmpty(userJson.getBalanceExempt())) {
                    //余额密码支付
                    mPayPwdBoardUtils.showPayPasswordDialog(AuctionDetailActivity.this,
                            String.valueOf(mBidModelData.getMoney()), this);
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

        UserActionUtils.actionLog("0", "3", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
        PayModel.httpPay(2, "bid", mBidModelData.getMoney(), mBidModelData.getTradeNo(), null, payPwd, exempt, null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                mPayPwdBoardUtils.dismissPayPasswordDialog();
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
        UserActionUtils.actionLog("0", "3", mGoodsDetailModel.getData().getProductInstanceId() + "", "1");
        PayModel.httpPay(1, "bid", mBidModelData.getMoney(), mBidModelData.getTradeNo(), null, "", "", null, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                mPayPwdBoardUtils.dismissPayPasswordDialog();
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

                MainActivity.newIntance(AuctionDetailActivity.this,0);

                break;
            case R.id.tab_focus:

                MainActivity.newIntance(AuctionDetailActivity.this,2);
                break;
            case R.id.tab_jd:
                Bundle bundle = new Bundle();
                bundle.putString("shopUri", shopUri);
                bundle.putString("shopName", shopName);
                ActivityManager.JumpActivity(AuctionDetailActivity.this, ShopActivity.class, bundle);
                break;
            case R.id.tab_news:

                MainActivity.newIntance(AuctionDetailActivity.this,3);
                break;
            case R.id.tab_mine:

                MainActivity.newIntance(AuctionDetailActivity.this,4);
                break;
        }
    }
}



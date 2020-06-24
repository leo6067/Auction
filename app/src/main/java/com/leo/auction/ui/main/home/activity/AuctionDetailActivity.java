package com.leo.auction.ui.main.home.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxClipboardTool;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.expandTextView.ExpandableTextView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.adapter.DetailBidAdapter;
import com.leo.auction.ui.main.home.adapter.PicGridNineAdapter;
import com.leo.auction.ui.main.home.model.BidListModel;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.leo.auction.ui.main.home.model.PicGridNineModel;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class AuctionDetailActivity extends BaseActivity implements PicGridNineAdapter.IGridNine {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.action_recycler)
    RecyclerView mActionRecycler;
    @BindView(R.id.goods_recycler)
    RecyclerView mGoodsRecycler;
    private RImageView mDetailHead;
    private RTextView mDetailLevel;
    private TextView mDetailTitle;
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
    private TextView mDetailEnd;
    private TextView mDetailBid;
    private TextView mDetailStartPrice;
    private TextView mDetailRange;
    private TextView mDetailUp;
    private PicGridNineAdapter mPicGridNineAdapter;

    List<String> nineImgList = new ArrayList<>();
    List<GoodsDetailModel.DataBean.BidBean> mBidBeanList = new ArrayList<>();
    private DetailBidAdapter mDetailBidAdapter;
    ArrayList<PicGridNineModel> mPicList = new ArrayList<>();


    private String goodCode = "";
    private String goodId = "";

    private String lastPrice = "0";
    private String rangePrice = "0";


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_auction_detail);
    }


    @Override
    public void initView() {
        super.initView();
        mTitleBar.setTitle("拍品详情");
        View headLayout = LayoutInflater.from(this).inflate(R.layout.include_detail_head, null);
        mDetailHead = headLayout.findViewById(R.id.detail_head);
        mDetailLevel = headLayout.findViewById(R.id.detail_level);
        mDetailTitle = headLayout.findViewById(R.id.detail_title);
        mDetailMark = headLayout.findViewById(R.id.detail_mark);
        mDetailFan = headLayout.findViewById(R.id.detail_fan);
        mDetailAttributes = headLayout.findViewById(R.id.detail_attributes);
        mMEpContent = headLayout.findViewById(R.id.ep_content);
        mDetailNine = headLayout.findViewById(R.id.detail_nine);
        mDetailYou = headLayout.findViewById(R.id.detail_you);
        mDetailTui = headLayout.findViewById(R.id.detail_tui);
        mDetailFu = headLayout.findViewById(R.id.detail_fu);
        mDetailTime = headLayout.findViewById(R.id.detail_time);
        mDetailTs = headLayout.findViewById(R.id.detail_ts);
        mDetailCollect = headLayout.findViewById(R.id.detail_collect);
        mDetailShare = headLayout.findViewById(R.id.detail_share);
        mDetailYouTui = headLayout.findViewById(R.id.detail_you_tui);
        mDetailEnd = headLayout.findViewById(R.id.detail_end);
        mDetailBid = headLayout.findViewById(R.id.detail_bid);
        mDetailStartPrice = headLayout.findViewById(R.id.detail_start_price);
        mDetailRange = headLayout.findViewById(R.id.detail_range);
        mDetailUp = headLayout.findViewById(R.id.detail_up);


        //设置九宫图
        mDetailNine.setHasFixedSize(true);
        mDetailNine.setNestedScrollingEnabled(false);
        mDetailNine.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mPicGridNineAdapter = new PicGridNineAdapter(AuctionDetailActivity.this);
        mDetailNine.setAdapter(mPicGridNineAdapter);


        //竞价列表
        mActionRecycler.setLayoutManager(new LinearLayoutManager(AuctionDetailActivity.this));
        //添加头部
        mDetailBidAdapter = new DetailBidAdapter();
        mDetailBidAdapter.addHeaderView(headLayout);
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

            }
        });
        //收藏
        mDetailCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                httpBidList();
            }
        });



    }


    @Override
    public void initData() {
        super.initData();
        String goodsCode = getIntent().getExtras().getString("goodsCode");
        httpDetail(goodsCode);

    }


    private void upUIdata(GoodsDetailModel.DataBean goodsDetailModel) {

        goodCode = goodsDetailModel.getProductInstanceCode();
        goodId = String.valueOf(goodsDetailModel.getProductInstanceId());


        GlideUtils.loadImg(goodsDetailModel.getProductUser().getHeadImg(), mDetailHead);
        mDetailLevel.setText(goodsDetailModel.getProductUser().getRate() + "");  //等级
        mDetailTitle.setText(goodsDetailModel.getTitle());
        mDetailMark.setText("评分 " + goodsDetailModel.getProductUser().getRate() + "");
        mDetailFan.setText(goodsDetailModel.getProductUser().getFansNum() + "");


        nineImgList = goodsDetailModel.getImages();
        for (int i = 0; i < nineImgList.size(); i++) {
            PicGridNineModel picGridNineModel = new PicGridNineModel("0", "", nineImgList.get(i));
            mPicList.add(picGridNineModel);
        }

        //设置列表数据
        mPicGridNineAdapter.clearImgViews();
        mPicGridNineAdapter.setNewData(mPicList);


//        mDetailAttributes = headLayout.findViewById(R.id.detail_attributes);


        String content = "【介绍】" + goodsDetailModel.getContent() + " ";
        mMEpContent.setContent(content);


        //  distributeType:'1-包邮 2-到付',
        //    refund:'1-包退 0-不包退',
        mDetailYou.setText(goodsDetailModel.getDistributeType() == 1 ? "包邮" : "到付");
//        mDetailTui.setVisibility(goodsDetailModel.getRefund().equals("1") ? View.VISIBLE : View.GONE);
        mDetailTime.setText(goodsDetailModel.getTime().getShowText());

//        mDetailYouTui = headLayout.findViewById(R.id.detail_you_tui);
        mDetailEnd.setText("距离结束 ：");
        mDetailStartPrice.setText(goodsDetailModel.getStartPrice());
        mDetailRange.setText(goodsDetailModel.getMarkupRange());


        int detailModelStatus = goodsDetailModel.getStatus();
        mDetailBidAdapter.setBidStaus(detailModelStatus);//1-竞拍中  2-在线付款 4-当面交易 8-未付款  16-流拍 32-未及时付款 64-已下架 128-退款

        //竞价列表
        mBidBeanList = goodsDetailModel.getBid();
        mDetailBidAdapter.addData(mBidBeanList);
        mDetailBidAdapter.notifyDataSetChanged();


        if (detailModelStatus == 1) {
            if (mBidBeanList.size() > 0) {
                lastPrice = mBidBeanList.get(0).getBidPrice();  //最新价格
            }
            rangePrice = goodsDetailModel.getMarkupRange();  //加价幅度


            mDetailBid.setText("出价");
            //出价
            mDetailBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else {
            mDetailBid.setClickable(false);
            mDetailBid.setText("已下架");
        }
    }


    private void httpDetail(String goodsCode) {

        showWaitDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productInstanceCode", goodsCode);
        HttpRequest.httpGetString(Constants.Api.GOODS_DETAIL_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                GoodsDetailModel goodsDetailModel = JSONObject.parseObject(resultData, GoodsDetailModel.class);
                upUIdata(goodsDetailModel.getData());
            }
        });
    }


    private int bidPage = 1;


    //获取竞价列表
    private void httpBidList() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("instanceCode", goodCode);
        hashMap.put("pageNum", String.valueOf(bidPage));
        hashMap.put("pageSize", String.valueOf(20));


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
                if (data.size() > 0) {
                    lastPrice = data.get(0).getBidPrice();  //最新价格
                }


                if (bidPage == 1) {
                    mDetailBidAdapter.setNewData(data);
                } else {
                    mDetailBidAdapter.addData(data);
                    mDetailBidAdapter.loadMoreComplete();
                }


                if (bidPage > 1 && data.isEmpty()) {
                    if (mDetailBidAdapter.getData().size() < 10) {
                        mDetailBidAdapter.loadMoreEnd(true);
                    } else {
                        mDetailBidAdapter.loadMoreEnd();
                    }
                }
            }
        });


    }


    public void httpBid() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("productInstanceId", goodId);
        hashMap.put("productInstanceCode", goodCode);
        hashMap.put("bidPrice", "");
        hashMap.put("currentPrice", "");


        showWaitDialog();
        HttpRequest.httpPostString(Constants.Api.GOODS_DETAIL_BID_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();


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
}



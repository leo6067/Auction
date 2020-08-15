package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.SharedActvity;
import com.leo.auction.ui.main.home.activity.AuctionDetailActivity;
import com.leo.auction.ui.main.home.activity.ImageShowActivity;
import com.leo.auction.ui.main.home.adapter.PicGridNineAdapter;
import com.leo.auction.ui.main.home.model.PicGridNineModel;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssImglistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssVideolistAdapter;
import com.leo.auction.ui.main.mine.adapter.UpperAdapter;
import com.leo.auction.ui.main.mine.model.GoodDetailModel;
import com.leo.auction.ui.main.mine.model.ReleaseEditModel;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.ui.main.mine.model.SmallPay;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


//超级仓库 拍品详情

public class GoodDetailActivity extends BaseActivity implements PicGridNineAdapter.IGridNine {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.recyclerView_attr)
    RecyclerView mRecyclerViewAttr;
    @BindView(R.id.recyclerView_img)
    RecyclerView mRecyclerViewImg;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.item_num)
    TextView mItemNum;
    @BindView(R.id.item_sort)
    TextView mItemSort;
    @BindView(R.id.item_pay)
    TextView mItemPay;
    @BindView(R.id.item_share)
    TextView mItemShare;
    @BindView(R.id.item_down)
    TextView mItemDown;
    @BindView(R.id.item_release)
    TextView mItemRelease;


    private UpperAdapter mUpperAdapter;
    List<ReleaseEditModel.DataBean.AttributesBean> mAttributesBeans = new ArrayList<>();


    private PicGridNineAdapter mPicGridNineAdapter;


    private List<String> mNineImgList;
    private GoodDetailModel.DataBean mGoodsDetailModelData;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_good_detail);
    }


    @Override
    public void initView() {
        super.initView();
        mTitleBar.setTitle("拍品详情");
    }

    @Override
    public void initData() {
        super.initData();
        String goodId = getIntent().getStringExtra("goodId");
        mRecyclerViewAttr.setLayoutManager(new LinearLayoutManager(GoodDetailActivity.this));
        mUpperAdapter = new UpperAdapter(); //商品详情属性
        mRecyclerViewAttr.setAdapter(mUpperAdapter);
        httpGoodDetail(goodId);
    }

    private void httpGoodDetail(String goodsId) {

        showWaitDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goodsId", goodsId);
        HttpRequest.httpGetString(Constants.Api.GOODS_STORE_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                GoodDetailModel goodsDetailModel = JSONObject.parseObject(resultData, GoodDetailModel.class);
                mGoodsDetailModelData = goodsDetailModel.getData();
//                imageList = goodsDetailModelData.getImages();
//                videoStr = goodsDetailModelData.getVideo();
//                cutPicStr = goodsDetailModelData.getCutPic();




                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(mGoodsDetailModelData.getTitle());
                mAttributesBeans.add(attributesBeanA);
                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(mGoodsDetailModelData.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(mGoodsDetailModelData.getCategoryName());
                mAttributesBeans.add(attributesBeanC);



                List<GoodDetailModel.DataBean.AttributesBean> attributes = mGoodsDetailModelData.getAttributes();
                for (int i = 0; i < attributes.size(); i++) {
                    ReleaseEditModel.DataBean.AttributesBean attributesBean = new ReleaseEditModel.DataBean.AttributesBean();
                    attributesBean.setId(attributes.get(i).getId());
                    attributesBean.setLength(attributes.get(i).getLength());
                    attributesBean.setOption(attributes.get(i).getOption());
                    attributesBean.setTab(attributes.get(i).getTab());
                    attributesBean.setTitle(attributes.get(i).getTitle());
                    attributesBean.setValue(attributes.get(i).getValue());
                    mAttributesBeans.add(attributesBean);
                }

                ReleaseEditModel.DataBean.AttributesBean attributesBeanD = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanD.setTitle("描述");
                attributesBeanD.setValue("");
                mAttributesBeans.add(attributesBeanD);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanE = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanE.setTitle("描述内容");
                attributesBeanE.setValue(mGoodsDetailModelData.getContent());
                mAttributesBeans.add(attributesBeanE);


                mUpperAdapter.setNewData(mAttributesBeans);
                mUpperAdapter.notifyDataSetChanged();
                initImgAdapter(mGoodsDetailModelData);

                mItemPrice.setText(mGoodsDetailModelData.getAgentPrice());
                mItemNum.setText(mGoodsDetailModelData.getStock());
                mItemSort.setText(mGoodsDetailModelData.getCategoryName());

            }
        });
    }

    private void initImgAdapter(GoodDetailModel.DataBean goodsDetailModel) {


        //设置九宫图
        mRecyclerViewImg.setHasFixedSize(true);
        mRecyclerViewImg.setNestedScrollingEnabled(false);
        mRecyclerViewImg.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mPicGridNineAdapter = new PicGridNineAdapter(this);
        mRecyclerViewImg.setAdapter(mPicGridNineAdapter);

        ArrayList<PicGridNineModel> mPicList = new ArrayList<>();
        mNineImgList = new ArrayList<>();

        //1判断是否有视频
        if (goodsDetailModel.getVideo() != null && !goodsDetailModel.getVideo().isEmpty()) {
            PicGridNineModel picGridNineModel01 = new PicGridNineModel("1", goodsDetailModel.getVideo(),
                    goodsDetailModel.getCutPic());
            mPicList.add(picGridNineModel01);
            mPicGridNineAdapter.setHasVideoSize(1);
            if (goodsDetailModel.getImages().size() >= 9) {
                for (String s : goodsDetailModel.getImages().subList(0, 8)) {
                    mNineImgList.add(s);
                }
            } else {
                mNineImgList = goodsDetailModel.getImages();
            }
        } else {
            mNineImgList = goodsDetailModel.getImages();
        }

        for (int i = 0; i < mNineImgList.size(); i++) {
            PicGridNineModel picGridNineModel = new PicGridNineModel("0", "", mNineImgList.get(i));
            mPicList.add(picGridNineModel);
        }

        //设置列表数据
        mPicGridNineAdapter.clearImgViews();
        mPicGridNineAdapter.setNewData(mPicList);
    }


    @OnClick({R.id.item_pay, R.id.item_share, R.id.item_down, R.id.item_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_pay:
                goWxSmall();
                break;
            case R.id.item_share:
                ArrayList<String> shareShopTitlelList = CommonUsedData.getInstance().getShareTitlelList();

                Random ra = new Random();
                int anInt = ra.nextInt(shareShopTitlelList.size());

                String shareName = "【" + mGoodsDetailModelData.getSupplier().getNickname() + "】" + shareShopTitlelList.get(anInt) + "【" + mGoodsDetailModelData.getTitle() + "】";
//                SharedModel sharedModel = new SharedModel(shareName, mGoodsDetailModelData.getContent(), mGoodsDetailModelData.getImages() == null ? "" : mGoodsDetailModelData.getImages().get(0),
//                        mGoodsDetailModelData.getAgentPrice() + "", mGoodsDetailModelData.getSupplier().getHeadImg(), "4", mGoodsDetailModelData.get, mGoodsDetailModelData.getGoodsId()+"", userJson.getUserId(),
//                        Constants.Action.ACTION_ACTION);

//        SharedActvity.newIntance(AuctionDetailActivity.this, sharedModel, sharedText, "0");

//                if (mGoodsDetailModel.getData().getVideo() != null && !mGoodsDetailModel.getData().getVideo().isEmpty()) {
//                    SharedActvity.newIntance(AuctionDetailActivity.this, sharedModel, nineImgList, sharedText + path, mGoodsDetailModel.getData().getVideo());
//                } else {
//                    SharedActvity.newIntance(AuctionDetailActivity.this, sharedModel, nineImgList, sharedText + path, "");
//                }




                break;
            case R.id.item_down:
                break;
            case R.id.item_release:
                Bundle bundle = new Bundle();
                bundle.putString("value", mGoodsDetailModelData.getGoodsId());
                bundle.putString("type", "2");
                bundle.putString("AuctionType", "3");  //已失效
                ActivityManager.JumpActivity(GoodDetailActivity.this, AuctionUpperActivity.class, bundle);

                break;
        }
    }


    public void goWxSmall() {

//        String encode= "";
//        SmallPay smallPay = new SmallPay();
//        smallPay.setTitle(mGoodsDetailModelData.getTitle());
//        smallPay.setGoodsId(mGoodsDetailModelData.getGoodsId());
//        smallPay.setImg(mGoodsDetailModelData.getImages());
//        smallPay.setIsyu(mGoodsDetailModelData.isToPay());
//        smallPay.setPrice(mGoodsDetailModelData.getAgentPrice());
//        smallPay.setStock(mGoodsDetailModelData.getStock());
//
//        String jsonString = JSONObject.toJSONString(smallPay);
//
//        try {
//            encode  = URLEncoder.encode(jsonString, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        String appId = Constants.Nouns.WEIXINAPPID; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(GoodDetailActivity.this, appId);

        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = Constants.Nouns.WEIXINA_SMALL; // 填小程序原始id
        req.path = "pages/products/productdetail/productdetail?id=" + mGoodsDetailModelData.getGoodsId();                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
        Globals.log("xxxxxxxx" + req.path);


        api.sendReq(req);
    }


    @Override
    public void onVideoClick(String videoPath, String thumbImageView) {
        VideoPlayerActivity.newIntance(GoodDetailActivity.this, videoPath, false);
    }

    @Override
    public void onImgClick(int pos) {
        ImageView[] imgViews = (ImageView[]) mPicGridNineAdapter.getImgViews().toArray(new ImageView[mPicGridNineAdapter.getImgViews().size()]);
        String[] imgListsData = mNineImgList.toArray(new String[mNineImgList.size()]);
        ImageShowActivity.startImageActivity(GoodDetailActivity.this, imgViews, imgListsData, pos, true);
    }
}

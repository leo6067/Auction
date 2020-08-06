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
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


//超级仓库 拍品详情

public class GoodDetailActivity extends BaseActivity implements PicGridNineAdapter.IGridNine{


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
                GoodDetailModel.DataBean goodsDetailModelData = goodsDetailModel.getData();
//                imageList = goodsDetailModelData.getImages();
//                videoStr = goodsDetailModelData.getVideo();
//                cutPicStr = goodsDetailModelData.getCutPic();
                List<GoodDetailModel.DataBean.AttributesBean> attributes = goodsDetailModelData.getAttributes();
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
                mUpperAdapter.setNewData(mAttributesBeans);
                mUpperAdapter.notifyDataSetChanged();
                initImgAdapter(goodsDetailModelData);

                mItemPrice.setText(goodsDetailModelData.getAgentPrice());
                mItemNum.setText(goodsDetailModelData.getStock());
                mItemSort.setText(goodsDetailModelData.getCategoryName());

            }
        });
    }

    private void initImgAdapter( GoodDetailModel.DataBean goodsDetailModel){


        //设置九宫图
        mRecyclerViewImg.setHasFixedSize(true);
        mRecyclerViewImg.setNestedScrollingEnabled(false);
        mRecyclerViewImg.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mPicGridNineAdapter = new PicGridNineAdapter( this);
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
                break;
            case R.id.item_share:
                break;
            case R.id.item_down:
                break;
            case R.id.item_release:
                break;
        }
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

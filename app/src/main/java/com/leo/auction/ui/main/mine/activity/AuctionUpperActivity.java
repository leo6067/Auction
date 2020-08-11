package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.DateUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.util.TimeUtils;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.Constants;
import com.leo.auction.common.widget.LinearLayoutDivider;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.leo.auction.ui.main.mine.adapter.ReleaseAttributeAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssImglistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssVideolistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseSortAttributeMoreAdapter;
import com.leo.auction.ui.main.mine.adapter.UpperAdapter;
import com.leo.auction.ui.main.mine.dialog.TimeDialog;
import com.leo.auction.ui.main.mine.model.AuctionTimeModel;
import com.leo.auction.ui.main.mine.model.GoodDetailModel;
import com.leo.auction.ui.main.mine.model.NewestModel;

import com.leo.auction.ui.main.mine.model.ReleaseEditModel;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.ui.main.mine.model.TimeDialogModel;
import com.leo.auction.utils.Globals;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AuctionUpperActivity extends BaseActivity {   // CompressUploadPicUtils.IChoosePic, CompressUploadVideoUtils.IChooseVideo


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.item_release)
    RTextView mItemRelease;
    @BindView(R.id.item_detail)
    TextView mItemDetail;
    @BindView(R.id.item_recycler)
    CustomeRecyclerView mItemRecycler;
    @BindView(R.id.item_time)
    TextView mItemTime;
    @BindView(R.id.item_start)
    EditText mItemStart;
    @BindView(R.id.item_range)
    EditText mItemRange;
    @BindView(R.id.item_remark)
    EditText mItemRemark;
    @BindView(R.id.item_radio_you)
    RadioButton mItemRadioYou;
    @BindView(R.id.item_radio_fu)
    RadioButton mItemRadioFu;
    @BindView(R.id.item_radio_group)
    RadioGroup mItemRadioGroup;
    @BindView(R.id.lin_ps)
    LinearLayout mLinPs;

    @BindView(R.id.lin_tui)
    LinearLayout mLinTui;

    @BindView(R.id.item_recycler_image)
    CustomeRecyclerView rvImglist;
    @BindView(R.id.item_recycler_video)
    CustomeRecyclerView rvVideolist;

    private String mGoodId, soureType, timeType, timeNode, auctionType, timeNodeId = "";
    private UpperAdapter mUpperAdapter;
    List<ReleaseEditModel.DataBean.AttributesBean> mAttributesBeans = new ArrayList<>();

    private ReleasePostOssImglistAdapter postImglistAdapter;//商品图片适配器
    private ReleasePostOssVideolistAdapter postVideolistAdapter;//商品视频适配器

    List<String> imageList = new ArrayList<>();
    private String videoStr = "";
    private String cutPicStr = "";

    private NewestModel.DataBean.TimeBean mTimeDialogModel;

    String categoryId, comment, content, title;
    int distributeType;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_auction_upper);
    }

    @Override
    public void initView() {
        super.initView();

        mItemRecycler.addItemDecoration(new LinearLayoutDivider(AuctionUpperActivity.this, 2, getResources().getColor(R.color.color_f2f2f2)));
        mItemRecycler.setLayoutManager(new LinearLayoutManager(AuctionUpperActivity.this));
        mUpperAdapter = new UpperAdapter(); //商品详情
        mItemRecycler.setAdapter(mUpperAdapter);


    }

    @Override
    public void initData() {
        super.initData();

        mTitleBar.setTitle("发布拍品");
        mGoodId = getIntent().getExtras().getString("value");
        soureType = getIntent().getExtras().getString("type");
        auctionType = getIntent().getExtras().getString("AuctionType");


        if ("2".equals(soureType)) {
            mLinPs.setVisibility(View.GONE);
            mLinTui.setVisibility(View.GONE);
        } else {
            mLinPs.setVisibility(View.VISIBLE);
            mLinTui.setVisibility(View.VISIBLE);
        }


        if (auctionType.equals("3")) {
            if ("2".equals(soureType)) {  //超级仓库的
                httpGoodDetail(mGoodId);
            } else {  //
                httpDetail(mGoodId);
            }
            httpNewest();
        } else {  //草稿箱
            httpGetData(mGoodId);
            httpNewestDraft();
        }


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
                imageList = goodsDetailModelData.getImages();
                videoStr = goodsDetailModelData.getVideo();
                cutPicStr = goodsDetailModelData.getCutPic();
                title = goodsDetailModelData.getTitle();
                mAttributesBeans.clear();
                List<GoodDetailModel.DataBean.AttributesBean> attributes = goodsDetailModelData.getAttributes();

                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(title);
                mAttributesBeans.add(attributesBeanA);
                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(goodsDetailModelData.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(goodsDetailModelData.getCategoryName());
                mAttributesBeans.add(attributesBeanC);

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
                attributesBeanE.setValue(goodsDetailModelData.getContent());
                mAttributesBeans.add(attributesBeanE);

                mUpperAdapter.setNewData(mAttributesBeans);
                mUpperAdapter.notifyDataSetChanged();
                initDetail();
            }
        });
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
                GoodsDetailModel.DataBean goodsDetail = goodsDetailModel.getData();
                imageList = goodsDetail.getImages();
                videoStr = goodsDetail.getVideo();
                cutPicStr = goodsDetail.getCutPic();
                title = goodsDetail.getTitle();
                mAttributesBeans.clear();
                List<GoodsDetailModel.DataBean.AttributesBean> attributes = goodsDetail.getAttributes();

                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(title);
                mAttributesBeans.add(attributesBeanA);
                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(goodsDetail.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(goodsDetail.getCategoryName());
                mAttributesBeans.add(attributesBeanC);
                for (int i = 0; i < attributes.size(); i++) {
                    ReleaseEditModel.DataBean.AttributesBean attributesBean = new ReleaseEditModel.DataBean.AttributesBean();
                    attributesBean.setId(attributes.get(i).getId());
                    attributesBean.setLength(attributes.get(i).getLength());
                    attributesBean.setOption(attributes.get(i).getOption());
                    attributesBean.setTab(attributes.get(i).getTab());
                    attributesBean.setTitle(attributes.get(i).getTitle());
                    attributesBean.setValue(attributes.get(i).getValue());
                    attributesBean.setTags(attributes.get(i).getTags());
                    mAttributesBeans.add(attributesBean);
                }


                ReleaseEditModel.DataBean.AttributesBean attributesBeanD = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanD.setTitle("描述");
                attributesBeanD.setValue("");
                mAttributesBeans.add(attributesBeanD);


                ReleaseEditModel.DataBean.AttributesBean attributesBeanE = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanE.setTitle("描述内容");
                attributesBeanE.setValue(goodsDetail.getContent());
                mAttributesBeans.add(attributesBeanE);

                mUpperAdapter.setNewData(mAttributesBeans);
                mUpperAdapter.notifyDataSetChanged();
                initDetail();
            }
        });
    }


    public void httpGetData(String mGoodId) {
        showWaitDialog();
        ReleaseEditModel.httpReleaseEditGet(mGoodId, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                ReleaseEditModel mReleaseEditModel = JSONObject.parseObject(resultData, ReleaseEditModel.class);
                ReleaseEditModel.DataBean mReleaseEditModelData = mReleaseEditModel.getData();

                imageList = mReleaseEditModelData.getImages();
                videoStr = mReleaseEditModelData.getVideo();
                cutPicStr = mReleaseEditModelData.getCutPic();
                categoryId = mReleaseEditModelData.getCategoryId();
                comment = mReleaseEditModelData.getContent();
                title = mReleaseEditModelData.getTitle();
                distributeType = mReleaseEditModelData.getDistributeType();
                content = mReleaseEditModelData.getContent();

                mAttributesBeans.clear();
                List<ReleaseEditModel.DataBean.AttributesBean> attributes = mReleaseEditModelData.getAttributes();


                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(title);
                mAttributesBeans.add(attributesBeanA);
                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(mReleaseEditModelData.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(mReleaseEditModelData.getCategoryName());
                mAttributesBeans.add(attributesBeanC);

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
                attributesBeanE.setValue(mReleaseEditModelData.getContent());
                mAttributesBeans.add(attributesBeanE);

                mUpperAdapter.setNewData(mAttributesBeans);
                mUpperAdapter.notifyDataSetChanged();
                initDetail();

            }
        });
    }


    public void httpNewest() { //已失效 回写
        NewestModel.httpNewest(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                NewestModel mNewestModel = JSONObject.parseObject(resultData, NewestModel.class);
                initUi(mNewestModel);
            }
        });

    }


    public void httpNewestDraft() { //草稿箱
        NewestModel.httpNewestDraft(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {

                NewestModel mNewestModel = JSONObject.parseObject(resultData, NewestModel.class);

                initUi(mNewestModel);
            }
        });
    }


    public void initUi(NewestModel mNewestModel) {
        mTimeDialogModel = mNewestModel.getData().getTime();


        if (mTimeDialogModel.getType().equals("quick")) {
            mItemTime.setHint("快速截拍 " + mTimeDialogModel.getShowText());
        }

        if (mTimeDialogModel.getType().equals("today")) {
            mItemTime.setHint(DateUtil.getStringDate("MM月dd日") + "  " + mTimeDialogModel.getShowText());
        }

        if (mTimeDialogModel.getType().equals("tomorrow")) {
            mItemTime.setHint(DateUtil.getStringTomorrowDate("MM月dd日") + "  " + mTimeDialogModel.getShowText());
        }

        if (mTimeDialogModel.getType().equals("after_tomorrow")) {
            mItemTime.setHint(DateUtil.getStringDayDate("MM月dd日", 2) + "  " + mTimeDialogModel.getShowText());
        }

        try {
            mItemStart.setText(mNewestModel.getData().getStartPrice() + "");
            mItemRange.setText(mNewestModel.getData().getMarkupRange() + "");
            mItemRemark.setText(mNewestModel.getData().getComment());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mNewestModel.getData().getDistributeType() == 1) { //包邮
            mItemRadioYou.setChecked(true);
        } else {
            mItemRadioFu.setChecked(true);
        }


    }

    private void initDetail() {

        //初始化商品图片
        rvImglist.setHasFixedSize(true);
        rvImglist.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        postImglistAdapter = new ReleasePostOssImglistAdapter();
        rvImglist.setAdapter(postImglistAdapter);

        //初始化商品视频
        rvVideolist.setHasFixedSize(true);
        rvVideolist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        postVideolistAdapter = new ReleasePostOssVideolistAdapter();
        rvVideolist.setAdapter(postVideolistAdapter);
        if (imageList.size() > 0) {
            for (String image : imageList) {
                ReleaseImageModel releaseImageModel = new ReleaseImageModel("2", null, 0, 0, image);
                releaseImageModel.setUploadComplete(true);
                postImglistAdapter.getData().add(releaseImageModel);
            }
            postImglistAdapter.clearImgViews();
            postImglistAdapter.notifyDataSetChanged();
        }

        if (cutPicStr.length() > 0 && videoStr.length() > 0) {
            ReleaseVideoModel releaseVideoModel = new ReleaseVideoModel("2", null, cutPicStr, videoStr, "", "");

            postVideolistAdapter.getData().add( releaseVideoModel);
            postVideolistAdapter.notifyDataSetChanged();
        }


    }


    public void httpUpper() {
        String startPricr = mItemStart.getText().toString();
        String range = mItemRange.getText().toString();
        if (range.length() == 0) {
            ToastUtils.showShort("请输入加价幅度");
            return;
        }


        BaseModel.httpUpper(mAttributesBeans, categoryId,
                comment, content, cutPicStr,
                distributeType, mGoodId, imageList,
                range, soureType, startPricr,
                timeNode, timeNodeId, timeType,
                title, videoStr,
                new HttpRequest.HttpCallback() {
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


    private void showTimeWindow() {

        AuctionTimeModel.httpTimeModel(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                AuctionTimeModel auctionTimeModel = JSONObject.parseObject(resultData, AuctionTimeModel.class);
                AuctionTimeModel.DataBean data = auctionTimeModel.getData();
                ArrayList<TimeDialogModel> TimeDialogModelLists = new ArrayList<>();


                //快速截拍
                TimeDialogModel quickBean = new TimeDialogModel();
                quickBean.setDelayText(data.getQuick().getDelayText());
                quickBean.setDelayTime(data.getQuick().getDelayTime());
                quickBean.setInterceptId(data.getQuick().getInterceptId());
                quickBean.setType(data.getQuick().getType());
                quickBean.setTypeName(data.getQuick().getTypeName());
                quickBean.setItemType(Constants.Var.LAYOUT_TYPE_HEAD);
                TimeDialogModelLists.add(quickBean);

                for (int i = 0; i < data.getQuick().getTimeNodes().size(); i++) {
                    TimeDialogModel timeNodesBeanXXX = new TimeDialogModel();
                    timeNodesBeanXXX.setShowText(data.getQuick().getTimeNodes().get(i).getShowText());
                    timeNodesBeanXXX.setTimeNode(data.getQuick().getTimeNodes().get(i).getTimeNode());
                    timeNodesBeanXXX.setTimeNodeId(data.getQuick().getTimeNodes().get(i).getTimeNodeId());
                    timeNodesBeanXXX.setItemType(Constants.Var.LAYOUT_TYPE);
                    timeNodesBeanXXX.setTimeType("quick");
                    if (data.getQuick().getTimeNodes().get(i).getTimeNodeId() == mTimeDialogModel.getTimeNodeId()) {
                        timeNodesBeanXXX.setSelect(true);
                    }
                    TimeDialogModelLists.add(timeNodesBeanXXX);
                }


                //今天
                TimeDialogModel todayBean = new TimeDialogModel();
                todayBean.setDelayText(data.getToday().getDelayText());
                todayBean.setDelayTime(data.getToday().getDelayTime());
                todayBean.setInterceptId(data.getToday().getInterceptId());
                todayBean.setType(data.getToday().getType());
                todayBean.setTypeName(data.getToday().getTypeName());
                quickBean.setItemType(Constants.Var.LAYOUT_TYPE_HEAD);
                TimeDialogModelLists.add(todayBean);
                for (int i = 0; i < data.getToday().getTimeNodes().size(); i++) {
                    TimeDialogModel timeNodesBeanXXX = new TimeDialogModel();
                    timeNodesBeanXXX.setShowText(data.getToday().getTimeNodes().get(i).getShowText());
                    timeNodesBeanXXX.setTimeNode(data.getToday().getTimeNodes().get(i).getTimeNode());
                    timeNodesBeanXXX.setTimeNodeId(data.getToday().getTimeNodes().get(i).getTimeNodeId());
                    timeNodesBeanXXX.setItemType(Constants.Var.LAYOUT_TYPE);
                    timeNodesBeanXXX.setTimeType("today");
                    if (data.getToday().getTimeNodes().get(i).getTimeNodeId() == mTimeDialogModel.getTimeNodeId()) {
                        timeNodesBeanXXX.setSelect(true);
                    }
                    TimeDialogModelLists.add(timeNodesBeanXXX);
                }


                //明天
                TimeDialogModel tomorrowBean = new TimeDialogModel();
                tomorrowBean.setDelayText(data.getTomorrow().getDelayText());
                tomorrowBean.setDelayTime(data.getTomorrow().getDelayTime());
                tomorrowBean.setInterceptId(data.getTomorrow().getInterceptId());
                tomorrowBean.setType(data.getTomorrow().getType());
                tomorrowBean.setTypeName(data.getTomorrow().getTypeName());
                tomorrowBean.setItemType(Constants.Var.LAYOUT_TYPE_HEAD);
                TimeDialogModelLists.add(tomorrowBean);
                for (int i = 0; i < data.getTomorrow().getTimeNodes().size(); i++) {
                    TimeDialogModel timeNodesBeanXXX = new TimeDialogModel();
                    timeNodesBeanXXX.setShowText(data.getTomorrow().getTimeNodes().get(i).getShowText());
                    timeNodesBeanXXX.setTimeNode(data.getTomorrow().getTimeNodes().get(i).getTimeNode());
                    timeNodesBeanXXX.setTimeNodeId(data.getTomorrow().getTimeNodes().get(i).getTimeNodeId());
                    timeNodesBeanXXX.setItemType(Constants.Var.LAYOUT_TYPE);
                    timeNodesBeanXXX.setTimeType("tomorrow");
                    if (data.getTomorrow().getTimeNodes().get(i).getTimeNodeId() == mTimeDialogModel.getTimeNodeId()) {
                        timeNodesBeanXXX.setSelect(true);
                    }
                    TimeDialogModelLists.add(timeNodesBeanXXX);
                }


                //后天
                TimeDialogModel affterTomorrowBean = new TimeDialogModel();
                affterTomorrowBean.setDelayText(data.getAfter_tomorrow().getDelayText());
                affterTomorrowBean.setDelayTime(data.getAfter_tomorrow().getDelayTime());
                affterTomorrowBean.setInterceptId(data.getAfter_tomorrow().getInterceptId());
                affterTomorrowBean.setType(data.getAfter_tomorrow().getType());
                affterTomorrowBean.setTypeName(data.getAfter_tomorrow().getTypeName());
                affterTomorrowBean.setItemType(Constants.Var.LAYOUT_TYPE_HEAD);
                TimeDialogModelLists.add(affterTomorrowBean);
                for (int i = 0; i < data.getAfter_tomorrow().getTimeNodes().size(); i++) {
                    TimeDialogModel timeNodesBeanXXX = new TimeDialogModel();
                    timeNodesBeanXXX.setShowText(data.getAfter_tomorrow().getTimeNodes().get(i).getShowText());
                    timeNodesBeanXXX.setTimeNode(data.getAfter_tomorrow().getTimeNodes().get(i).getTimeNode());
                    timeNodesBeanXXX.setTimeNodeId(data.getAfter_tomorrow().getTimeNodes().get(i).getTimeNodeId());
                    timeNodesBeanXXX.setItemType(Constants.Var.LAYOUT_TYPE);
                    timeNodesBeanXXX.setTimeType("after_tomorrow");
                    if (data.getAfter_tomorrow().getTimeNodes().get(i).getTimeNodeId() == mTimeDialogModel.getTimeNodeId()) {
                        timeNodesBeanXXX.setSelect(true);
                    }
                    TimeDialogModelLists.add(timeNodesBeanXXX);
                }


                TimeDialog timeDialog = new TimeDialog(AuctionUpperActivity.this, TimeDialogModelLists, new TimeDialog.InterTimeDialog() {
                    @Override
                    public void itemTimeClick(TimeDialogModel timeDialogModel) {
                        timeType = timeDialogModel.getTimeType();
                        timeNode = timeDialogModel.getTimeNode() + "";
                        timeNodeId = timeDialogModel.getTimeNodeId() + "";
                    }
                });

                timeDialog.getWindow().setGravity(Gravity.BOTTOM);
                timeDialog.show();

            }
        });
    }


    @OnClick({R.id.item_release, R.id.item_detail, R.id.item_time, R.id.item_start, R.id.item_range})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_release:
                httpUpper();
                break;
            case R.id.item_detail:
                if (mItemRecycler.getVisibility() == View.VISIBLE) {
                    mItemRecycler.setVisibility(View.GONE);
                    rvImglist.setVisibility(View.GONE);
                    rvVideolist.setVisibility(View.GONE);
                } else {
                    mItemRecycler.setVisibility(View.VISIBLE);
                    rvImglist.setVisibility(View.VISIBLE);
                    rvVideolist.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.item_time:
                if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
                    return;
                }
                showTimeWindow();
                break;

        }
    }

}

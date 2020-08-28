package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.ActivityManager;
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
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.common.widget.LinearLayoutDivider;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.leo.auction.ui.main.home.model.SceneModel;
import com.leo.auction.ui.main.mine.adapter.ReleaseAttributeAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssImglistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssVideolistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseSortAttributeMoreAdapter;
import com.leo.auction.ui.main.mine.adapter.UpperAdapter;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.dialog.TimeDialog;
import com.leo.auction.ui.main.mine.model.AuctionTimeModel;
import com.leo.auction.ui.main.mine.model.GoodDetailModel;
import com.leo.auction.ui.main.mine.model.NewestModel;

import com.leo.auction.ui.main.mine.model.ReleaseEditModel;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.ui.main.mine.model.TimeDialogModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.leo.auction.ui.web.AgentWebAppActivity;
import com.leo.auction.utils.DialogUtils;
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
    @BindView(R.id.recyclerView_lin)
    LinearLayout mLinRecycler;

    @BindView(R.id.lin_tui)
    LinearLayout mLinTui;
    @BindView(R.id.good_video)
    TextView mGoodVideo;

    @BindView(R.id.item_recycler_image)
    CustomeRecyclerView rvImglist;
    @BindView(R.id.item_recycler_video)
    CustomeRecyclerView rvVideolist;

    private String mGoodId, soureType, timeType, timeNode, auctionType = "";


    private int timeNodeId;

    private UpperAdapter mUpperAdapter;
    List<ReleaseEditModel.DataBean.AttributesBean> mAttributesBeans = new ArrayList<>();
    List<ReleaseEditModel.DataBean.AttributesBean> mAttributesBeanView = new ArrayList<>();

    private ReleasePostOssImglistAdapter postImglistAdapter;//商品图片适配器
    private ReleasePostOssVideolistAdapter postVideolistAdapter;//商品视频适配器

    List<String> imageList = new ArrayList<>();
    private String videoStr = "";
    private String cutPicStr = "";

    private NewestModel.DataBean.TimeBean mTimeDialogModel;

    String categoryId, comment, content, title;
    int distributeType = 1;
    private DialogUtils dialogUtils;

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
    public void onResume() {
        super.onResume();


    }

    @Override
    public void initData() {
        super.initData();

        mTitleBar.setTitle("发布拍品");
        mGoodId = getIntent().getExtras().getString("value");
        soureType = getIntent().getExtras().getString("type");
        auctionType = getIntent().getExtras().getString("AuctionType");


        BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION_VALUE,mGoodId);
        BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION_TYPE,soureType);
        BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION_AUCTIONTYPE,auctionType);

        Globals.log("xxxxxx backLogin 000111222233 444    "+mGoodId  + soureType  +  auctionType);
        UserModel.DataBean mUserJson = BaseSharePerence.getInstance().getUserJson();


        if (mUserJson == null) {

            Globals.log("xxxxxx backLogin 000111"+mGoodId  + soureType  +  auctionType);
            BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION,"AuctionUpperActivity");
            AgentWebAppActivity.newIntance(AuctionUpperActivity.this, 0, Constants.WEB_APP_URL);
            finish();
            return;
        }
        Globals.log("xxxxxx backLogin 000111 22" );
        HashMap<String, Object> mWarnHash = new HashMap<>();

        if (EmptyUtils.isEmpty(mUserJson.getUsername())) {
            mWarnHash = new HashMap<>();
            mWarnHash.put("title", "提示");
            mWarnHash.put("content", "您当前没有发布权限,请先完成实名认证。");
            mWarnHash.put("ok", "去认证");
            mWarnHash.put("okColor", "#7c1313");
            WarningDialog warningDialogAA = new WarningDialog(AuctionUpperActivity.this, mWarnHash);
            warningDialogAA.show();
            warningDialogAA.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                @Override
                public void onWarningOk() {
//                    ActivityManager.JumpActivity(AuctionUpperActivity.this, IdentityActivity.class);
                    BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION,"AuctionUpperActivity");
                    AgentWebAppActivity.newIntance(AuctionUpperActivity.this, 0, Constants.WebApi.RELNAME_WEB);
                    finish();

                }

                @Override
                public void onWaringCancel() {
                    finish();

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
            WarningDialog warningDialog = new WarningDialog(AuctionUpperActivity.this, mWarnHash);
            warningDialog.show();
            warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                @Override
                public void onWarningOk() {
                    BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION,"AuctionUpperActivity");
                    showAgreeDialog("6");
                }

                @Override
                public void onWaringCancel() {
                    finish();
                }
            });
            return;
        }



        if (!mUserJson.isStoreEnable()) {   //超级仓库权限

            BaseSharePerence.getInstance().putString(Constants.Nouns.WEB_ACTION,"AuctionUpperActivity");
            AgentWebAppActivity.newIntance(AuctionUpperActivity.this, 0, Constants.WebApi.MINE_SUPER);
            finish();

//            mWarnHash = new HashMap<>();
//            mWarnHash.put("title", "提示");
//            mWarnHash.put("content", "您当前没有发布权限,请查看说明如何免费获取发布权限。");
//            mWarnHash.put("ok", "去查看");
//            mWarnHash.put("okColor", "#7c1313");
//            WarningDialog warningDialog = new WarningDialog(AuctionUpperActivity.this, mWarnHash);
//            warningDialog.show();
//            warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
//                @Override
//                public void onWarningOk() {
//                    AgentWebAppActivity.newIntance(AuctionUpperActivity.this, 0, Constants.WebApi.MINE_SUPER);
//                }
//
//                @Override
//                public void onWaringCancel() {
//                    finish();
//                }
//            });
//            return;
        }


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
        dialogUtils = new DialogUtils();

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
                categoryId = goodsDetailModelData.getCategoryId() + "";
                content = goodsDetailModelData.getContent();
                mAttributesBeans.clear();
                mAttributesBeanView.clear();
                List<GoodDetailModel.DataBean.AttributesBean> attributes = goodsDetailModelData.getAttributes();

                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(title);
                mAttributesBeans.add(attributesBeanA);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(goodsDetailModelData.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);
                mAttributesBeanView.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(goodsDetailModelData.getCategoryName());
                mAttributesBeans.add(attributesBeanC);
                mAttributesBeanView.add(attributesBeanC);

                for (int i = 0; i < attributes.size(); i++) {
                    ReleaseEditModel.DataBean.AttributesBean attributesBean = new ReleaseEditModel.DataBean.AttributesBean();
                    attributesBean.setId(attributes.get(i).getId());
                    attributesBean.setLength(attributes.get(i).getLength());
                    attributesBean.setOption(attributes.get(i).getOption());
                    attributesBean.setTab(attributes.get(i).getTab());
                    attributesBean.setTitle(attributes.get(i).getTitle());
                    attributesBean.setValue(attributes.get(i).getValue());
                    mAttributesBeans.add(attributesBean);
                    mAttributesBeanView.add(attributesBean);
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
                categoryId = goodsDetail.getCategoryId() + "";
                content = goodsDetail.getContent();
                mAttributesBeans.clear();
                mAttributesBeanView.clear();
                List<GoodsDetailModel.DataBean.AttributesBean> attributes = goodsDetail.getAttributes();

                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(title);
                mAttributesBeans.add(attributesBeanA);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(goodsDetail.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);
                mAttributesBeanView.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(goodsDetail.getCategoryName());
                mAttributesBeans.add(attributesBeanC);
                mAttributesBeanView.add(attributesBeanC);
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
                    mAttributesBeanView.add(attributesBean);
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
                content = mReleaseEditModelData.getContent();
                title = mReleaseEditModelData.getTitle();


                mAttributesBeans.clear();
                mAttributesBeanView.clear();
                List<ReleaseEditModel.DataBean.AttributesBean> attributes = mReleaseEditModelData.getAttributes();


                ReleaseEditModel.DataBean.AttributesBean attributesBeanA = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanA.setTitle("品名");
                attributesBeanA.setValue(title);
                mAttributesBeans.add(attributesBeanA);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanB = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanB.setTitle("大类");
                attributesBeanB.setValue(mReleaseEditModelData.getParentCategoryName());
                mAttributesBeans.add(attributesBeanB);
                mAttributesBeanView.add(attributesBeanB);

                ReleaseEditModel.DataBean.AttributesBean attributesBeanC = new ReleaseEditModel.DataBean.AttributesBean();
                attributesBeanC.setTitle("小类");
                attributesBeanC.setValue(mReleaseEditModelData.getCategoryName());
                mAttributesBeans.add(attributesBeanC);
                mAttributesBeanView.add(attributesBeanC);

                for (int i = 0; i < attributes.size(); i++) {
                    ReleaseEditModel.DataBean.AttributesBean attributesBean = new ReleaseEditModel.DataBean.AttributesBean();
                    attributesBean.setId(attributes.get(i).getId());
                    attributesBean.setLength(attributes.get(i).getLength());
                    attributesBean.setOption(attributes.get(i).getOption());
                    attributesBean.setTab(attributes.get(i).getTab());
                    attributesBean.setTitle(attributes.get(i).getTitle());
                    attributesBean.setValue(attributes.get(i).getValue());
                    mAttributesBeans.add(attributesBean);
                    mAttributesBeanView.add(attributesBean);
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

        timeNode = mTimeDialogModel.getTimeNode() + "";
        timeNodeId = mTimeDialogModel.getTimeNodeId();
        timeType = mTimeDialogModel.getType() + "";


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

        if (EmptyUtils.isEmpty(videoStr)) {
            mGoodVideo.setVisibility(View.GONE);
        } else {
            mGoodVideo.setVisibility(View.VISIBLE);
            ReleaseVideoModel releaseVideoModel = new ReleaseVideoModel("2", null, cutPicStr, videoStr, "", "");
            postVideolistAdapter.getData().add(releaseVideoModel);
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

        if (timeType.length() == 0) {
            ToastUtils.showShort("请选择截拍时间");
            return;
        }






        BaseModel.httpUpper(   mAttributesBeanView , categoryId,
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
                        BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                        if (baseModel.getResult().isSuccess()) {
                            showShortToast("拍品发布成功");

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    goFinish();
                                }
                            }, 2000);

                        } else {
                            showShortToast(baseModel.getResult().getMessage());
                        }

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
                    dialogUtils.showRuleProtocolDialog(AuctionUpperActivity.this,
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

                    bundle.putString("httpUrl", url);
                    bundle.putInt("backPager",0);

                    ActivityManager.JumpActivity(AuctionUpperActivity.this, AgentWebAppActivity.class, bundle);
                    finish();
                }
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
                    timeNodesBeanXXX.setTypeName(data.getQuick().getTypeName());
                    if (data.getQuick().getTimeNodes().get(i).getTimeNodeId() == timeNodeId) {
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
                    timeNodesBeanXXX.setTypeName(data.getToday().getTypeName());
                    if (data.getToday().getTimeNodes().get(i).getTimeNodeId() == timeNodeId) {
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
                    timeNodesBeanXXX.setTypeName(data.getTomorrow().getTypeName());
                    if (data.getTomorrow().getTimeNodes().get(i).getTimeNodeId() == timeNodeId) {
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
                    timeNodesBeanXXX.setTypeName(data.getAfter_tomorrow().getTypeName());
                    if (data.getAfter_tomorrow().getTimeNodes().get(i).getTimeNodeId() == timeNodeId) {
                        timeNodesBeanXXX.setSelect(true);
                    }
                    TimeDialogModelLists.add(timeNodesBeanXXX);
                }


                TimeDialog timeDialog = new TimeDialog(AuctionUpperActivity.this, TimeDialogModelLists, new TimeDialog.InterTimeDialog() {
                    @Override
                    public void itemTimeClick(TimeDialogModel timeDialogModel) {
                        timeType = timeDialogModel.getTimeType();
                        timeNode = timeDialogModel.getTimeNode() + "";
                        timeNodeId = timeDialogModel.getTimeNodeId();

                        mItemTime.setText(timeDialogModel.getTypeName() + timeDialogModel.getShowText());
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
                if (mLinRecycler.getVisibility() == View.VISIBLE) {

                    mLinRecycler.setVisibility(View.GONE);
                } else {

                    mLinRecycler.setVisibility(View.VISIBLE);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

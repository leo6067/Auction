package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.DesUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.OssUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.VibratorUtils;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.ZzHorizontalProgressBar;
import com.aten.compiler.widget.text.MoneyValueFilter;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.OssTokenModel;
import com.leo.auction.ui.main.home.activity.ImageShowActivity;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.ui.main.mine.IReleaseSortChoose;
import com.leo.auction.ui.main.mine.adapter.ReleaseAttributeAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseOneSortAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssImglistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssVideolistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseSortAttributeMoreAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseTwoSortAdapter;
import com.leo.auction.ui.main.mine.dialog.ReleaseProtocolDialog;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.dialog.TimeDialog;
import com.leo.auction.ui.main.mine.model.AuctionTimeModel;
import com.leo.auction.ui.main.mine.model.NewestReleaseProductModel;
import com.leo.auction.ui.main.mine.model.ProtocolInfoModel;
import com.leo.auction.ui.main.mine.model.ReleaseAuctionAttrModel;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.ui.main.mine.model.TimeDialogModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.TextLightUtils;
import com.leo.auction.utils.TextOptionUtils;
import com.leo.auction.utils.layoutManager.CenterLayoutManager;
import com.leo.auction.utils.ossUpload.CompressUploadPicUtils;
import com.leo.auction.utils.ossUpload.CompressUploadVideoUtils;
import com.leo.auction.utils.ossUpload.DecryOssDataModel;
import com.leo.auction.utils.ossUpload.OssVideoUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class CommodityReleaseActivity extends BaseActivity implements IReleaseSortChoose, CompressUploadPicUtils.IChoosePic, CompressUploadVideoUtils.IChooseVideo {

    @BindView(R.id.crl_one_sort)
    CustomeRecyclerView crlOneSort;
    @BindView(R.id.iv_open_close_01)
    ImageView ivOpenClose01;
    @BindView(R.id.crl_two_sort)
    CustomeRecyclerView crlTwoSort;
    @BindView(R.id.iv_open_close_02)
    ImageView ivOpenClose02;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_imglist)
    CustomeRecyclerView rvImglist;
    @BindView(R.id.rv_videolist)
    CustomeRecyclerView rvVideolist;
    @BindView(R.id.et_selling_price)
    EditText etSellingPrice;  //起拍价
    @BindView(R.id.et_supply_price)
    EditText etSupplyPrice;  //加价幅度

    @BindView(R.id.et_remarks)
    EditText etRemarks;

    @BindView(R.id.goods_jpsj)
    TextView goodsJpsj;

    @BindView(R.id.ll_attribute_contain)
    LinearLayout llAttributeContain;
    @BindView(R.id.zpb_progress)
    ZzHorizontalProgressBar zpbProgress;

    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_agree)
    TextView tvAgree;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.baoyou)
    RadioButton mRadioYou;

    @BindView(R.id.daofu)
    RadioButton mRadioFu;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.stb_release)
    SuperButton mStbRelease;
    @BindView(R.id.fl_open_close_01)
    FrameLayout mFlOpenClose01;
    @BindView(R.id.fl_open_close_02)
    FrameLayout mFlOpenClose02;
    @BindView(R.id.arl_agree)
    LinearLayout mArlAgree;

    private ReleaseOneSortAdapter releaseOneSortAdapter;//大类的适配器
    private ReleaseTwoSortAdapter releaseTwoSortAdapter;//小类的适配器
    private List<SortLeftModel.DataBean> dataOneBeans;//记录大类的数据
    private SortLeftModel.DataBean releaseOneSortData;//记录选中的大类数据
    private List<SortLeftModel.DataBean.ChildrenBean> dataTwoBeans;//记录小类的数据
    private SortLeftModel.DataBean.ChildrenBean releaseTwoSortData;//记录选中的小类数据
    private CenterLayoutManager centerOneSortLayoutManager;
    private CenterLayoutManager centerTwoSortLayoutManager;
    private ReleasePostOssImglistAdapter postImglistAdapter;//商品图片适配器
    private ReleasePostOssVideolistAdapter postVideolistAdapter;//商品视频适配器
    private List<ReleaseAuctionAttrModel.DataBean> attributes = new ArrayList<>();//商品属性信息
    private UserModel.DataBean userInfoModel;
    private NewestReleaseProductModel newestReleaseProduct;
    private String isPublish = "1";//发布的类型 默认是发布1 保存0
    private DecryOssDataModel decryOssDataModel;//oss上传需要的必备参数

    private CompressUploadPicUtils uploadPicUtils;
    private CompressUploadVideoUtils uploadVideoUtils;
    private OssUtils ossUtils;
    private OssVideoUtils ossVideoUtils;
    private DialogUtils dialogUtils;
    private TextLightUtils textLightUtils;

    private String timeNode = "";  //时间节点
    private String distributeType = "";//1-包邮  2-到付
    private String sourceType = "1";  // 1-自行发布 2-产品库

    private String timeType = ""; //快速截拍
    private String goodId = ""; //




    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_commodity_release);
    }


    @Override
    public void initData() {
        mTitleBar.setTitle("发布拍品");
        uploadPicUtils = new CompressUploadPicUtils();
        uploadVideoUtils = new CompressUploadVideoUtils();
        textLightUtils = new TextLightUtils();
        super.initData();
        userInfoModel = BaseSharePerence.getInstance().getUserJson();
        //初始化大类的数据
        crlOneSort.setHasFixedSize(true);
        centerOneSortLayoutManager = new CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        crlOneSort.setLayoutManager(centerOneSortLayoutManager);
        releaseOneSortAdapter = new ReleaseOneSortAdapter(this);
        releaseOneSortAdapter.setItemWidth(true);
        crlOneSort.setAdapter(releaseOneSortAdapter);
        //初始化小类的数据
        crlTwoSort.setHasFixedSize(true);
        centerTwoSortLayoutManager = new CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        crlTwoSort.setLayoutManager(centerTwoSortLayoutManager);
        releaseTwoSortAdapter = new ReleaseTwoSortAdapter(this);
        releaseTwoSortAdapter.setItemWidth(true);
        crlTwoSort.setAdapter(releaseTwoSortAdapter);

        //初始化商品图片
        rvImglist.setHasFixedSize(true);
        rvImglist.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        postImglistAdapter = new ReleasePostOssImglistAdapter();
        rvImglist.setAdapter(postImglistAdapter);
        //初始化图片上传
        uploadPicUtils.initChoosePic(CommodityReleaseActivity.this, true, 9, Constants.Api.OSS_FOLDER_IMG_GOODS, this);
        //设置拖拽监听
        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                //holder.setTextColor(R.id.tv, Color.WHITE);
                VibratorUtils.getInstance().vibrator(CommodityReleaseActivity.this, 10);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "drag end");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }
        };
        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(postImglistAdapter) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getAdapterPosition() == postImglistAdapter.getData().size() - 1) {
                    // 不可拖动，不可滑动
                    return makeMovementFlags(0, 0);
                }
                return super.getMovementFlags(recyclerView, viewHolder);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
                // 不可拖动其他 Item 与 target Item 交换位置
                if (target.getAdapterPosition() == postImglistAdapter.getData().size() - 1) {
                    return false;
                }
                return super.onMove(recyclerView, source, target);
            }
        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(rvImglist);
        postImglistAdapter.enableDragItem(mItemTouchHelper);
        postImglistAdapter.setOnItemDragListener(listener);

        //初始化商品视频
        rvVideolist.setHasFixedSize(true);
        rvVideolist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        postVideolistAdapter = new ReleasePostOssVideolistAdapter();
        rvVideolist.setAdapter(postVideolistAdapter);
        //初始化视频上传
        uploadVideoUtils.initChooseVideo(CommodityReleaseActivity.this, this);

        dialogUtils = new DialogUtils();

        TextLightUtils textLightUtils = new TextLightUtils();
        textLightUtils.setHighlightColor(this, tvAgree, 14, new TextLightUtils.onClickableSpan() {
            @Override
            public void onClickable(String title, String url) {
                getProtocolInfo(1);
            }
        });

        showWaitDialog();
        if ("0".equals(SPUtils.getInstance(Constants.Var.COMMON_PROTOCOL).getString("isAgree", "0"))) {
            cbCheck.setChecked(false);
            cbCheck.setEnabled(true);
            getProtocolInfo(0);
        } else {
            hideWaitDialog();
            cbCheck.setChecked(true);
            cbCheck.setEnabled(false);
        }

        ossUtils = new OssUtils();
        ossVideoUtils = new OssVideoUtils();

        getNewestReleaseProductData();

        geOssToken();
    }

    //获取协议容
    private void getProtocolInfo(int type) {
        ProtocolInfoModel.sendProtocolInfoRequest(TAG, "", "1", new CustomerJsonCallBack<ProtocolInfoModel>() {
            @Override
            public void onRequestError(ProtocolInfoModel returnData, String msg) {
                hideWaitDialog();
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(ProtocolInfoModel returnData) {
                hideWaitDialog();
                if (returnData.getData() != null && returnData.getData().getContent() != null) {
                    if (type == 0) {
                        dialogUtils.showReleaseProtocolDialog(CommodityReleaseActivity.this,
                                returnData.getData().getContent(), new ReleaseProtocolDialog.IButtonListener() {
                                    @Override
                                    public void onCancle() {
                                        SPUtils.getInstance(Constants.Var.COMMON_PROTOCOL).put("isAgree", "0");
                                        cbCheck.setChecked(false);
                                        cbCheck.setEnabled(true);
                                    }

                                    @Override
                                    public void onAgree() {
                                        SPUtils.getInstance(Constants.Var.COMMON_PROTOCOL).put("isAgree", "1");
                                        cbCheck.setChecked(true);
                                        cbCheck.setEnabled(false);
                                    }
                                });
                    } else {
                        dialogUtils.showRuleProtocolDialog(CommodityReleaseActivity.this,
                                returnData.getData().getContent(), new RuleProtocolDialog.IButtonListener() {
                                    @Override
                                    public void onClose() {
                                        dialogUtils.dissRuleProtocolDialog();
                                    }
                                });
                    }
                }
            }
        });
    }


    @Override
    public void initEvent() {
        super.initEvent();
        //设置商品图片的点击事件
        postImglistAdapter.setmOnLastImgListener(mOnLastImgListener);
        postImglistAdapter.setmOnImgsItemListener(mOnImgsItemListener);
        postImglistAdapter.setmOnImgsItemDeleteListener(mOnImgsItemDeleteListener);
        //设置商品视频的点击事件
        postVideolistAdapter.setmOnLastImgListener(mOnLastVideoListener);
        postVideolistAdapter.setmOnImgsItemListener(mOnVideosItemListener);
        postVideolistAdapter.setmOnImgsItemDeleteListener(mOnVideosItemDeleteListener);

        //设置销售价格的监听
        textLightUtils.onTextChangedListener(etSellingPrice, "9990000");
        etSellingPrice.setFilters(new InputFilter[]{new MoneyValueFilter()});
        //设置供货价格的监听
        etSupplyPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSupplyPrice.setFilters(new InputFilter[]{new MoneyValueFilter()});
        //设置加价幅度的监听
        textLightUtils.onTextChangedListener(etSupplyPrice, "9999");
//        //设置运费价格的监听
//        textLightUtils.onTextChangedListener(etFreight, "99");
//        etFreight.setFilters(new InputFilter[]{new MoneyValueFilter()});
//
//        sbtFreeShipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    etFreight.setText("0.00");
//                    RxTool.setEditTextCursorLocation(etFreight);
//                }
//            }
//        });


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRadioYou.getId()) {
                    distributeType = "1";
                }

                if (checkedId == mRadioFu.getId()) {
                    distributeType = "2";
                }
            }
        });


        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.getInstance(Constants.Var.COMMON_PROTOCOL).put("isAgree", "1");
                    cbCheck.setEnabled(false);
                }
            }
        });


    }

    //获oss请求的必要参数
    private void geOssToken() {

        OssTokenModel.sendOssTokenRequest(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                OssTokenModel ossTokenModel = JSONObject.parseObject(resultData, OssTokenModel.class);
                if (ossTokenModel.getData() != null) {
                    String decryptData = "";
                    try {
                        decryptData = DesUtil.decrypt(ossTokenModel.getData().getEncryptedData(), Constants.Nouns.OSS_KEY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (!EmptyUtils.isEmpty(decryptData)) {
                        decryOssDataModel = JSON.parseObject(decryptData, DecryOssDataModel.class);
                    } else {
                        ToastUtils.showShort("oss数据有误");
                    }
                }
            }
        });

    }

    //获取最新发布得商品数据
    private void getNewestReleaseProductData() {

        showWaitDialog();
        NewestReleaseProductModel.httpNewestRelease(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
                getOneSortData();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                NewestReleaseProductModel newestReleaseProductModel = JSONObject.parseObject(resultData, NewestReleaseProductModel.class);
                if (newestReleaseProductModel != null) {
                    setPageInitData(newestReleaseProductModel);
                } else {
                    getOneSortData();
                }
            }
        });
    }


    //设置页面得初始数据
    private void setPageInitData(NewestReleaseProductModel info) {
        this.newestReleaseProduct = info;
//        etTitle.setText(EmptyUtils.strEmpty(info.getTitle()));
//        etContent.setText(EmptyUtils.strEmpty(info.getContent()));
////        etSellingPrice.setText(EmptyUtils.strEmpty(info.getPrice()));
////        etSupplyPrice.setText(EmptyUtils.strEmpty(info.getAgentPrice()));
//        etStock.setText(EmptyUtils.strEmpty(info.getStock()));
////        etRemarks.setText(EmptyUtils.strEmpty(info.getComment()));
//        etFreight.setText(EmptyUtils.strEmpty(info.getFreight()));
//        sbtFreeShipping.setChecked(info.isFreeShip());
//        sbtToPay.setChecked(info.isToPay());


//        List<NewestReleaseProductModel.AttributesBean> attributes = info.getAttributes();



        getOneSortData();
    }

    //获取大类的数据
    private void getOneSortData() {

        showWaitDialog();
        SortLeftModel.httpSort(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SortLeftModel returnData = JSONObject.parseObject(resultData, SortLeftModel.class);
                if (returnData.getData() != null && !returnData.getData().isEmpty()) {
                    dataOneBeans = returnData.getData();
                    if (newestReleaseProduct != null && !EmptyUtils.isEmpty(newestReleaseProduct.getParentCategoryId())) {
                        boolean tag = false;
                        int position = 0;
                        for (int i = 0; i < dataOneBeans.size(); i++) {
                            if (dataOneBeans.get(i).getId().equals(newestReleaseProduct.getParentCategoryId())) {
                                dataOneBeans.get(i).setSelected(true);
                                releaseOneSortData = dataOneBeans.get(i);
                                position = i;
                                tag = true;
                                break;
                            }
                        }

                        if (tag) {
                            if (!ivOpenClose01.isSelected()) {
                                centerOneSortLayoutManager.smoothScrollToPosition(crlOneSort, new RecyclerView.State(), position);
                            }
                        } else {
                            dataOneBeans.get(0).setSelected(true);
                            releaseOneSortData = dataOneBeans.get(0);
                        }
                    } else {
                        dataOneBeans.get(0).setSelected(true);
                        releaseOneSortData = dataOneBeans.get(0);
                    }
                    releaseOneSortAdapter.setNewData(dataOneBeans);
                    getTwoSortData(0); //默认选中0
                }
            }
        });
    }


    //获取小类的数据
    private void getTwoSortData(int onePosition) {
        dataTwoBeans = dataOneBeans.get(onePosition).getChildren();
        if (newestReleaseProduct != null && !EmptyUtils.isEmpty(newestReleaseProduct.getCategoryId())) {
            boolean tag = false;
            int position = 0;
            for (int i = 0; i < dataTwoBeans.size(); i++) {
                if (dataTwoBeans.get(i).getId().equals(newestReleaseProduct.getCategoryId())) {
                    dataTwoBeans.get(i).setSelect(true);
                    releaseTwoSortData = dataTwoBeans.get(i);
                    tag = true;
                    break;
                }
            }
            if (tag) {
                if (!ivOpenClose02.isSelected()) {
                    centerTwoSortLayoutManager.smoothScrollToPosition(crlTwoSort, new RecyclerView.State(), position);
                }
            } else {
                dataTwoBeans.get(0).setSelect(true);
                releaseTwoSortData = dataTwoBeans.get(0);
            }
        } else {
            dataTwoBeans.get(0).setSelect(true);
            releaseTwoSortData = dataTwoBeans.get(0);
        }

        releaseTwoSortAdapter.setNewData(dataTwoBeans);
        getAttributeData(dataTwoBeans.get(0).getId());
    }


    //获取属性信息
    private void getAttributeData(String twoId) {

        showWaitDialog();
        ReleaseAuctionAttrModel.httpReleaseAttr(twoId, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                ReleaseAuctionAttrModel releaseAuctionAttrModel = JSONObject.parseObject(resultData, ReleaseAuctionAttrModel.class);
                setAttributeData(releaseAuctionAttrModel.getData());
            }
        });


    }

    //设置属性信息
    private void setAttributeData(List<ReleaseAuctionAttrModel.DataBean> attributesBeans) {
        this.attributes = attributesBeans;
        llAttributeContain.removeAllViews();

        if (attributesBeans == null) {
            return;
        }

        for (ReleaseAuctionAttrModel.DataBean attribute : attributesBeans) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_release_attribute_item, llAttributeContain, false);
            TextView tvAttriName = view.findViewById(R.id.tv_attri_name);
            EditText etAttriValue = view.findViewById(R.id.et_attri_value);
            final CustomeRecyclerView crlAttriList = view.findViewById(R.id.crl_attri_list);
            final TextView tvMore = view.findViewById(R.id.tv_more);
            final CustomeRecyclerView crlAttriListMore = view.findViewById(R.id.crl_attri_list_more);

            tvAttriName.setText("【" + attribute.getTitle() + "】");
            //判断展示输入框 还是属性值列表
            if (attribute.getTags() != null && !attribute.getTags().isEmpty()) {
                etAttriValue.setVisibility(View.GONE);
                crlAttriList.setVisibility(View.VISIBLE);
                tvMore.setVisibility(View.VISIBLE);

                if (newestReleaseProduct != null && newestReleaseProduct.getAttributes() != null) {
                    for (NewestReleaseProductModel.AttributesBean newestReleaseProductAttribute : newestReleaseProduct.getAttributes()) {
                        if (newestReleaseProductAttribute.getTitle().equals(attribute.getTitle())) {
                            for (ReleaseAuctionAttrModel.DataBean.TagsBean tag : attribute.getTags()) {
                                if (tag != null && tag.getName().equals(newestReleaseProductAttribute.getValue())) {
                                    tag.setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                }

                if (attribute.getTags().size() == 1) {
                    attribute.getTags().get(0).setSelect(true);
                }

                //初始化属性值列表的适配器
                final ReleaseAttributeAdapter releaseAttributeAdapter = new ReleaseAttributeAdapter();
                //初始化属性值列表（更多）的适配器
                final ReleaseSortAttributeMoreAdapter releaseSortAttributeMoreAdapter = new ReleaseSortAttributeMoreAdapter();

                //设置属性值列表
                crlAttriList.setHasFixedSize(true);
                crlAttriList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                releaseAttributeAdapter.setChooseListener(new ReleaseAttributeAdapter.IAttribute() {
                    @Override
                    public void chooseAttribute(int pos) {
                        if (releaseSortAttributeMoreAdapter.getmSelectedReleaseSortData() != null) {
                            releaseSortAttributeMoreAdapter.getmSelectedReleaseSortData().setSelect(false);
                            releaseSortAttributeMoreAdapter.getmSelectedView().setSelected(false);
                        }
                        releaseSortAttributeMoreAdapter.setSelectPos(pos);
                    }
                });
                crlAttriList.setAdapter(releaseAttributeAdapter);
                releaseAttributeAdapter.setNewData(attribute.getTags());

                //设置属性值列表（更多）
                crlAttriListMore.setHasFixedSize(true);
                crlAttriListMore.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
                releaseSortAttributeMoreAdapter.setChooseListener(new ReleaseSortAttributeMoreAdapter.ISortAttributeMore() {
                    @Override
                    public void sortAttributeMore(int pos) {
                        if (releaseAttributeAdapter.getmSelectedReleaseSortData() != null) {
                            releaseAttributeAdapter.getmSelectedReleaseSortData().setSelect(false);
                            releaseAttributeAdapter.getmSelectedView().setSelected(false);
                        }
                        releaseAttributeAdapter.setSelectPos(pos);
                        crlAttriList.smoothScrollToPosition(pos);
                    }
                });
                crlAttriListMore.setAdapter(releaseSortAttributeMoreAdapter);
                releaseSortAttributeMoreAdapter.setNewData(attribute.getTags());
            } else {
                etAttriValue.setVisibility(View.VISIBLE);
                crlAttriList.setVisibility(View.GONE);
                tvMore.setVisibility(View.GONE);

                //设置属性值 提示语
                etAttriValue.setHint(attribute.getTab());
                if ((EmptyUtils.isEmpty(attribute.getLength()) ? 0 : Integer.valueOf(attribute.getLength())) > 0) {
                    etAttriValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Integer.valueOf(EmptyUtils.strEmpty(attribute.getLength())))});
                }

                if (newestReleaseProduct != null && newestReleaseProduct.getAttributes() != null) {
                    for (NewestReleaseProductModel.AttributesBean newestReleaseProductAttribute : newestReleaseProduct.getAttributes()) {
                        if (newestReleaseProductAttribute.getTitle().equals(attribute.getTitle())) {
                            etAttriValue.setText(newestReleaseProductAttribute.getValue());
                        }
                    }
                }
            }

            tvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ("更多".equals(tvMore.getText().toString().trim())) {
                        tvMore.setText("收起");
                        crlAttriListMore.setVisibility(View.VISIBLE);
                    } else {
                        tvMore.setText("更多");
                        crlAttriListMore.setVisibility(View.GONE);
                    }
                }
            });

            llAttributeContain.addView(view);
        }
    }

    @OnClick({R.id.fl_open_close_01, R.id.fl_open_close_02, R.id.tv_save, R.id.goods_jpsj, R.id.stb_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_open_close_01:
                if (ivOpenClose01.isSelected()) {
                    ivOpenClose01.setSelected(false);
                    releaseOneSortAdapter.setItemWidth(true);
                    crlOneSort.setLayoutManager(centerOneSortLayoutManager);
                } else {
                    ivOpenClose01.setSelected(true);
                    releaseOneSortAdapter.setItemWidth(false);
                    crlOneSort.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
                }
                releaseOneSortAdapter.notifyDataSetChanged();
                break;
            case R.id.fl_open_close_02:
                if (ivOpenClose02.isSelected()) {
                    ivOpenClose02.setSelected(false);
                    releaseTwoSortAdapter.setItemWidth(true);
                    crlTwoSort.setLayoutManager(centerTwoSortLayoutManager);
                } else {
                    ivOpenClose02.setSelected(true);
                    releaseTwoSortAdapter.setItemWidth(false);
                    crlTwoSort.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
                }
                releaseTwoSortAdapter.notifyDataSetChanged();
                break;

            case R.id.goods_jpsj:
                showTimeWindow();
                break;

            case R.id.tv_save:
                isPublish = "1";
                preRelease();
                break;
            case R.id.stb_release:
                isPublish = "2";
                preRelease();
                break;
        }
    }

    @Override
    public void onOneSortChoose(SortLeftModel.DataBean oneSortData, int position) {
        releaseOneSortData = oneSortData;
        if (!ivOpenClose01.isSelected()) {
            centerOneSortLayoutManager.smoothScrollToPosition(crlOneSort, new RecyclerView.State(), position);
        }

        getTwoSortData(position);
    }

    @Override
    public void onTwoSortChoose(SortLeftModel.DataBean.ChildrenBean oneSortData, int position) {
        releaseTwoSortData = oneSortData;
        if (!ivOpenClose02.isSelected()) {
            centerTwoSortLayoutManager.smoothScrollToPosition(crlTwoSort, new RecyclerView.State(), position);
        }
        getAttributeData(releaseTwoSortData.getId());
    }

    //选择发布图片的类型选择点击事件
    private View.OnClickListener mOnLastImgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (decryOssDataModel != null) {
                if (zpbProgress.getVisibility() == View.GONE) {
                    uploadPicUtils.showChoosePicTypeDialog(decryOssDataModel);
                } else {
                    ToastUtils.showShort(zpbProgress.getTag() + "正在上传，请稍等");
                }
            } else {
                ToastUtils.showShort("oss数据请求，稍等会，再试下。还不行，则需要联系客服");
            }
        }
    };

    //图片的类型选择点击事件
    private View.OnClickListener mOnImgsItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.tag_1);
            ImageView[] imgViews = (ImageView[]) postImglistAdapter.getImgViews().toArray(new ImageView[postImglistAdapter.getImgViews().size()]);
            String[] imgListsData = new String[postImglistAdapter.getData().size() - 1];
            for (int i = 0; i < postImglistAdapter.getData().size() - 1; i++) {
                imgListsData[i] = postImglistAdapter.getData().get(i).getFile().getAbsolutePath();
            }
            ImageShowActivity.startImageActivity(CommodityReleaseActivity.this, imgViews, imgListsData, position);
        }
    };

    //图片的删除点击事件
    private View.OnClickListener mOnImgsItemDeleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ReleaseImageModel item = (ReleaseImageModel) v.getTag(R.id.tag_1);
            final int pos = (int) v.getTag(R.id.tag_2);
            //判断图片是否已上传完成
            if (item.isUploadComplete()) {



                BaseModel.httpDeteleFile(item.getImgPth(), null, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {

                    }
                });

            }

//                ossUtils.initOssOption(CommodityReleaseActivity.this, decryOssDataModel.getAccessKeyId(), decryOssDataModel.getSecret(), "",
//                        decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName());
//                ossUtils.deleteOssObject(item.getImgPth(), new OssUtils.OssDeleteListener() {
//                    @Override
//                    public void deleteSuccess() {
//                        hideWaitDialog();
//                        postImglistAdapter.clearImgViews();
//                        postImglistAdapter.getData().remove(pos);
//                        postImglistAdapter.notifyDataSetChanged();
//                    }
//                });
//            } else {
//                ToastUtils.showShort("图片还未上传完成");
//            }

             postImglistAdapter.clearImgViews();
                        postImglistAdapter.getData().remove(pos);
                        postImglistAdapter.notifyDataSetChanged();
        }
    };

    //选择发布视频的类型选择点击事件
    private View.OnClickListener mOnLastVideoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (decryOssDataModel != null) {
                if (zpbProgress.getVisibility() == View.GONE) {
                    uploadVideoUtils.showChooseVideoTypeDialog(decryOssDataModel);
                } else {
                    ToastUtils.showShort(zpbProgress.getTag() + "正在上传，请稍等");
                }
            } else {
                ToastUtils.showShort("oss数据请求，稍等会，再试下。还不行，则需要联系客服");
            }
        }
    };

    //视频的类型选择点击事件
    private View.OnClickListener mOnVideosItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ReleaseVideoModel item = (ReleaseVideoModel) v.getTag(R.id.tag_2);
            switch (item.getUploadCompleteStatus()) {
                case "0"://未压缩 未上传
//                    ToastUtils.showShort("视频还在处理中");

                    VideoPlayerActivity.newIntance(CommodityReleaseActivity.this, item.getVideoPath(), false);
                    break;
                case "1"://压缩 未上传
                    VideoPlayerActivity.newIntance(CommodityReleaseActivity.this, item.getVideoPath(), true);
                    break;
                case "2"://压缩 上传
                    VideoPlayerActivity.newIntance(CommodityReleaseActivity.this, item.getVideoPath(), false);
                    break;
            }
        }
    };

    //视频的删除点击事件
    private View.OnClickListener mOnVideosItemDeleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final ReleaseVideoModel item = (ReleaseVideoModel) v.getTag(R.id.tag_1);
            final int pos = (int) v.getTag(R.id.tag_2);
//            if ("2".equals(item.getUploadCompleteStatus())) {
//                showWaitDialog();
//                ossVideoUtils.initOssOption(CommodityReleaseActivity.this, decryOssDataModel.getAccessKeyId(), decryOssDataModel.getSecret(), "",
//                        decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName());
//                ossVideoUtils.deleteOssObject(postVideolistAdapter.getData().get(pos).getVideoPath(), new OssVideoUtils.OssDeleteListener() {
//                    @Override
//                    public void deleteSuccess() {
//                        hideWaitDialog();
//                        postVideolistAdapter.getData().remove(pos);
//                        postVideolistAdapter.notifyDataSetChanged();
//                    }
//                });
//            } else {
//                ToastUtils.showShort("视频正在处理中");
//            }

            BaseModel.httpDeteleFile(item.getVideoPath(), null, new HttpRequest.HttpCallback() {
                @Override
                public void httpError(Call call, Exception e) {

                }

                @Override
                public void httpResponse(String resultData) {

                }
            });
            postVideolistAdapter.getData().remove(pos);
            postVideolistAdapter.notifyDataSetChanged();
        }
    };

    //图片压缩完回调
    @Override
    public void onCompressedResult(ArrayList<ReleaseImageModel> compressResult) {
        postImglistAdapter.clearImgViews();
        postImglistAdapter.setNewData(compressResult);
    }

    @Override
    public void onOssUpResult() {
        Log.e("66666", "图片上传oss完成");
    }

    //图片上传的总进度条
    @Override
    public void onTotalProgress(int totalProgress) {
        zpbProgress.setMax(totalProgress);
    }

    //当前图片上传的进度
    @Override
    public void onProgress(int progress) {
        zpbProgress.setProgress(progress);
    }

    //显示图片上传加载等待框
    @Override
    public void onShowWait() {
        zpbProgress.setTag("图片");
        zpbProgress.setVisibility(View.VISIBLE);
    }

    //隐藏图片上传加载等待框
    @Override
    public void onHideWait() {
        zpbProgress.setVisibility(View.GONE);
    }

    //视频压缩回调
    @Override
    public void onCompressedResult_video
    (ArrayList<ReleaseVideoModel> albumOrCameraChooseDataLists) {
        postVideolistAdapter.setNewData(albumOrCameraChooseDataLists);
    }

    //视频上传成功
    @Override
    public void onOssUpResult_video() {
        Log.e("66666", "视频上传oss完成");
    }

    //视频 图片进度
    @Override
    public void onTotalProgress_video(int totalProgress) {
        zpbProgress.setMax(totalProgress);
    }

    //视频压缩进度 图片上传进度
    @Override
    public void onProgress_video(int progress) {
        zpbProgress.setProgress(progress);
    }

    //显示视频上传加载等待框
    @Override
    public void onShowWait_video() {
        zpbProgress.setTag("视频");
        zpbProgress.setVisibility(View.VISIBLE);
    }

    //隐藏视频上传加载等待框
    @Override
    public void onHideWait_video() {
        zpbProgress.setVisibility(View.GONE);
    }

    //发布前得准备
    private void preRelease() {
        if ("0".equals(SPUtils.getInstance(Constants.Var.COMMON_PROTOCOL).getString("isAgree"))) {
            getProtocolInfo(0);
            return;
        }

        if (EmptyUtils.isEmpty(etTitle.getText().toString()) || etTitle.getText().toString().length() < 5) {
            ToastUtils.showShort("请输入5-30个文字的商品标题");
            return;
        }

        if (EmptyUtils.isEmpty(etContent.getText().toString()) || etContent.getText().toString().length() < 5) {
            ToastUtils.showShort("请输入5-500个文字的商品描述");
            return;
        }

        if (postImglistAdapter.getData().size() == 1) {
            ToastUtils.showShort("请选择商品图片");
            return;
        }

        if (postImglistAdapter.getData().size() < 7) {
            ToastUtils.showShort("商品图片不能少于6张");
            return;
        }

        if (EmptyUtils.isEmpty(etSellingPrice.getText().toString())) {
            ToastUtils.showShort("请设置拍品销售价");
            return;
        }

        if (EmptyUtils.isEmpty(etSupplyPrice.getText().toString())) {
            ToastUtils.showShort("请设置拍品加价幅度");
            return;
        }


        //判断属性信息是否都按要求填写
        if (attributes != null) {
            if (llAttributeContain.getChildCount() != attributes.size()) {
                ToastUtils.showShort("数据有误，请重新进入页面");
                return;
            }
            for (int i = 0; i < attributes.size(); i++) {
                ReleaseAuctionAttrModel.DataBean attribute = attributes.get(i);
                View attributeView = llAttributeContain.getChildAt(i);
                EditText etAttriValue = attributeView.findViewById(R.id.et_attri_value);
                final CustomeRecyclerView crlAttriList = attributeView.findViewById(R.id.crl_attri_list);

                if ("1".equals(attribute.getOption())) {
                    if (attribute.getTags() != null && !attribute.getTags().isEmpty()) {
                        if (((ReleaseAttributeAdapter) crlAttriList.getAdapter()).getmSelectedReleaseSortData() == null) {
                            ToastUtils.showShort("请选择" + attribute.getTitle() + "属性");
                            return;
                        }
                    } else {
                        if (EmptyUtils.isEmpty(etAttriValue.getText().toString())) {
                            ToastUtils.showShort("请输入" + attribute.getTitle());
                            return;
                        }
                    }
                }
            }
        }


        if (zpbProgress.getVisibility() == View.VISIBLE) {
            ToastUtils.showShort(zpbProgress.getTag() + "正在上传，请稍等");
            return;
        }

        release();
    }

    //发布
    private void release() {
        ArrayList<String> ossPaths = new ArrayList<>();//oos上传后最终的图片数据
        ArrayList<String> ossVideoPaths = new ArrayList<>();//oos上传后最终的视频数据
        String cutPic = "";//视频首帧地址

        for (int i = 0; i < postImglistAdapter.getData().size() - 1; i++) {
            ossPaths.add(postImglistAdapter.getData().get(i).imgPth);
        }

        if (postVideolistAdapter.getData() != null && postVideolistAdapter.getData().size() > 1) {
            for (int i = 0; i < postVideolistAdapter.getData().size() - 1; i++) {
                ossVideoPaths.add(postVideolistAdapter.getData().get(i).getVideoPath());
                cutPic = postVideolistAdapter.getData().get(i).getImgPath2();
            }
        }

        ArrayList<ReleaseAuctionAttrModel.DataBean> releaseAttributesModels = new ArrayList<>();
        for (int i = 0; i < (attributes == null ? 0 : attributes.size()); i++) {
            ReleaseAuctionAttrModel.DataBean attribute = attributes.get(i);
            View attributeView = llAttributeContain.getChildAt(i);
            EditText etAttriValue = attributeView.findViewById(R.id.et_attri_value);
            final CustomeRecyclerView crlAttriList = attributeView.findViewById(R.id.crl_attri_list);

            String value = "";
            if (attribute.getTags() != null && !attribute.getTags().isEmpty()) {
                if (((ReleaseAttributeAdapter) crlAttriList.getAdapter()).getmSelectedReleaseSortData() != null) {
                    value = ((ReleaseAttributeAdapter) crlAttriList.getAdapter()).getmSelectedReleaseSortData().getName();
                } else {
                    value = "";
                }
            } else {
                value = etAttriValue.getText() == null ? "" : etAttriValue.getText().toString().trim();
            }

            attribute.setValue(value);
            releaseAttributesModels.add(attribute);
        }
        showWaitDialog();

        //发布商品
        BaseModel.httpReleaseGoods(TextOptionUtils.getInstance().subLength(etTitle.getText().toString(), 30),
                TextOptionUtils.getInstance().subLength(etContent.getText().toString(), 500),
                releaseTwoSortData.getId(),
                TextOptionUtils.getInstance().subLength(etRemarks.getText().toString().trim(), 15),
                etSellingPrice.getText().toString(),
                etSupplyPrice.getText().toString(),
                ossVideoPaths, cutPic,
                sourceType, isPublish, distributeType, timeNode, timeType,
                releaseAttributesModels,
                ossPaths, goodId,
                new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {
                        hideWaitDialog();
                    }

                    @Override
                    public void httpResponse(String resultData) {
                        hideWaitDialog();
                        BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                        if (baseModel.getResult().isSuccess()) {
                            ToastUtils.showShort("发布拍品成功");
                        }else {
                            ToastUtils.showShort("发布拍品失败");
                        }
                        cleanRelease();
                        finish();
                    }
                }
        );


    }

    //发布成功 清空发布的数据
    private void cleanRelease() {
        etTitle.setText("");
        etContent.setText("");
        //清空上传的图片
        postImglistAdapter.clearImgViews();
        uploadPicUtils.initChoosePic(CommodityReleaseActivity.this, true, 9, Constants.Api.OSS_FOLDER_IMG_GOODS, this);
        //清空上传的视频
        uploadVideoUtils.initChooseVideo(CommodityReleaseActivity.this, this);

        etSellingPrice.setText("");
        etSupplyPrice.setText("");
        etRemarks.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (uploadPicUtils != null) {
            uploadPicUtils.onDestoryUtil();
        }
        if (uploadVideoUtils != null) {
            uploadVideoUtils.onDestoryUtil();
        }
        if (dialogUtils != null) {
            dialogUtils.dissReleaseProtocolDialog();
            dialogUtils.dissRuleProtocolDialog();
            dialogUtils = null;
        }
    }


    private void showTimeWindow() {
        showWaitDialog();
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
                    TimeDialogModelLists.add(timeNodesBeanXXX);
                }


                TimeDialog timeDialog = new TimeDialog(CommodityReleaseActivity.this, TimeDialogModelLists, new TimeDialog.InterTimeDialog() {
                    @Override
                    public void itemTimeClick(TimeDialogModel timeDialogModel) {
                        timeType = timeDialogModel.getTimeType();
                        timeNode = timeDialogModel.getTimeNodeId() + "";
                    }
                });
                timeDialog.show();
            }
        });


    }


}

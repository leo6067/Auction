package com.leo.auction.ui.main.mine;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.DesUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.OssUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.VibratorUtils;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.ZzHorizontalProgressBar;
import com.aten.compiler.widget.switchButton.SwitchButton;
import com.aten.compiler.widget.text.MoneyValueFilter;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.login.model.OssTokenModel;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.leo.auction.ui.main.home.activity.ImageShowActivity;
import com.leo.auction.ui.main.mine.activity.VideoPlayerActivity;
import com.leo.auction.ui.main.mine.adapter.ReleaseAttributeAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseOneSortAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssImglistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleasePostOssVideolistAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseSortAttributeMoreAdapter;
import com.leo.auction.ui.main.mine.adapter.ReleaseTwoSortAdapter;
import com.leo.auction.ui.main.mine.dialog.ReleaseProtocolDialog;
import com.leo.auction.ui.main.mine.dialog.RuleProtocolDialog;
import com.leo.auction.ui.main.mine.model.CommodityReleaseModel;
import com.leo.auction.ui.main.mine.model.NewestReleaseProductModel;
import com.leo.auction.ui.main.mine.model.ProtocolInfoModel;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.ui.main.mine.model.ReleaseSortModel;
import com.leo.auction.utils.DialogUtils;
import com.leo.auction.utils.TextLightUtils;
import com.leo.auction.utils.TextOptionUtils;
import com.leo.auction.utils.layoutManager.CenterLayoutManager;
import com.leo.auction.utils.ossUpload.CompressUploadPicUtils;
import com.leo.auction.utils.ossUpload.CompressUploadVideoUtils;
import com.leo.auction.utils.ossUpload.DecryOssDataModel;
import com.leo.auction.utils.ossUpload.OssVideoUtils;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    EditText etSellingPrice;
    @BindView(R.id.et_supply_price)
    EditText etSupplyPrice;
    @BindView(R.id.et_stock)
    EditText etStock;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.et_freight)
    EditText etFreight;
    @BindView(R.id.sbt_free_shipping)
    SwitchButton sbtFreeShipping;
    @BindView(R.id.sbt_to_pay)
    SwitchButton sbtToPay;
    @BindView(R.id.ll_attribute_contain)
    LinearLayout llAttributeContain;
    @BindView(R.id.zpb_progress)
    ZzHorizontalProgressBar zpbProgress;
    @BindView(R.id.fl_freight)
    FrameLayout flFreight;
    @BindView(R.id.fl_free_shipping)
    FrameLayout flFreeShipping;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_agree)
    TextView tvAgree;

    private ReleaseOneSortAdapter releaseOneSortAdapter;//大类的适配器
    private ReleaseTwoSortAdapter releaseTwoSortAdapter;//小类的适配器
    private List<ReleaseSortModel.DataBean> dataOneBeans;//记录大类的数据
    private ReleaseSortModel.DataBean releaseOneSortData;//记录选中的大类数据
    private List<ReleaseSortModel.DataBean> dataTwoBeans;//记录小类的数据
    private ReleaseSortModel.DataBean releaseTwoSortData;//记录选中的小类数据
    private CenterLayoutManager centerOneSortLayoutManager;
    private CenterLayoutManager centerTwoSortLayoutManager;
    private ReleasePostOssImglistAdapter postImglistAdapter;//商品图片适配器
    private ReleasePostOssVideolistAdapter postVideolistAdapter;//商品视频适配器
    private ArrayList<ReleaseSortModel.DataBean.AttributesBean> attributes = new ArrayList<>();//商品属性信息
    private UserInfoModel userInfoModel;
    private NewestReleaseProductModel.InfoBean newestReleaseProduct;
    private String isPublish = "1";//发布的类型 默认是发布1 保存0
    private DecryOssDataModel decryOssDataModel;//oss上传需要的必备参数

    private CompressUploadPicUtils uploadPicUtils;
    private CompressUploadVideoUtils uploadVideoUtils;
    private OssUtils ossUtils;
    private OssVideoUtils ossVideoUtils;
    private DialogUtils dialogUtils;
    private TextLightUtils textLightUtils;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_commodity_release);
    }


    @Override
    public void initData() {
        uploadPicUtils = new CompressUploadPicUtils();
        uploadVideoUtils = new CompressUploadVideoUtils();
        textLightUtils = new TextLightUtils();
        super.initData();
        userInfoModel = LitePal.findFirst(UserInfoModel.class);
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
        uploadPicUtils.initChoosePic(CommodityReleaseActivity.this, true, 9, Constants.Api.OSS_FOLDER_IMG_GOODS,this);
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

    //获取最新发布得商品数据
    private void getNewestReleaseProductData() {
        NewestReleaseProductModel.sendNewestReleaseProductRequest(TAG, userInfoModel.getShopUri(), new CustomerJsonCallBack<NewestReleaseProductModel>() {
            @Override
            public void onRequestError(NewestReleaseProductModel returnData, String msg) {
                ToastUtils.showShort(msg);
                getOneSortData();
            }

            @Override
            public void onRequestSuccess(NewestReleaseProductModel returnData) {
                if (returnData.getInfo() != null) {
                    setPageInitData(returnData.getInfo());
                }else {
                    getOneSortData();
                }
            }
        });
    }

    //设置页面得初始数据
    private void setPageInitData(NewestReleaseProductModel.InfoBean info) {
        this.newestReleaseProduct = info;
        etTitle.setText(EmptyUtils.strEmpty(info.getTitle()));
        etContent.setText(EmptyUtils.strEmpty(info.getContent()));
//        etSellingPrice.setText(EmptyUtils.strEmpty(info.getPrice()));
//        etSupplyPrice.setText(EmptyUtils.strEmpty(info.getAgentPrice()));
        etStock.setText(EmptyUtils.strEmpty(info.getStock()));
//        etRemarks.setText(EmptyUtils.strEmpty(info.getComment()));
        etFreight.setText(EmptyUtils.strEmpty(info.getFreight()));
        sbtFreeShipping.setChecked(info.isFreeShip());
        sbtToPay.setChecked(info.isToPay());

        getOneSortData();
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
                String maxSupplyPrice = new BigDecimal(EmptyUtils.isEmpty(etSellingPrice.getText().toString()) ? "0" : etSellingPrice.getText().toString())
                        .divide(new BigDecimal("1.05"), 2, BigDecimal.ROUND_DOWN).toString();
                if (EmptyUtils.isEmpty(maxSupplyPrice)) {
                    etSupplyPrice.setText("0");
                    return;
                }
                if (s != null && EmptyUtils.isEmpty(s.toString())) {
                    return;
                }

                if (new BigDecimal(s.toString()).subtract(new BigDecimal(maxSupplyPrice)).doubleValue() > 0) {
                    etSupplyPrice.setText(maxSupplyPrice);
                    RxTool.setEditTextCursorLocation(etSupplyPrice);
                }
            }
        });
        etSupplyPrice.setFilters(new InputFilter[]{new MoneyValueFilter()});
        //设置库存数量的监听
        textLightUtils.onTextChangedListener(etStock, "9999");
        //设置运费价格的监听
        textLightUtils.onTextChangedListener(etFreight, "99");
        etFreight.setFilters(new InputFilter[]{new MoneyValueFilter()});

        sbtFreeShipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etFreight.setText("0.00");
                    RxTool.setEditTextCursorLocation(etFreight);
                }
            }
        });

        sbtToPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flFreight.setVisibility(View.GONE);
                    flFreeShipping.setVisibility(View.GONE);
                } else {
                    flFreight.setVisibility(View.VISIBLE);
                    flFreeShipping.setVisibility(View.VISIBLE);
                }
                sbtFreeShipping.setChecked(false);
                etFreight.setText("0.00");
                RxTool.setEditTextCursorLocation(etFreight);
            }
        });

        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SPUtils.getInstance(Constants.Var.COMMON_PROTOCOL).put("isAgree", "1");
                    cbCheck.setEnabled(false);
                }
            }
        });
    }

    //获oss请求的必要参数
    private void geOssToken() {
        OssTokenModel.sendOssTokenRequest(TAG, new CustomerJsonCallBack<OssTokenModel>() {
            @Override
            public void onRequestError(OssTokenModel returnData, String msg) {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(OssTokenModel returnData) {
                if (returnData.getData() != null) {
                    String decryptData = "";
                    try {
                        decryptData = DesUtil.decrypt(returnData.getData().getEncryptedData(), Constants.Nouns.OSS_KEY);
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

    //获取大类的数据
    private void getOneSortData() {
        ReleaseSortModel.sendReleaseSortRequest(TAG, "-1", "", "0", new CustomerJsonCallBack<ReleaseSortModel>() {
            @Override
            public void onRequestError(ReleaseSortModel returnData, String msg) {
                hideWaitDialog();
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(ReleaseSortModel returnData) {
                hideWaitDialog();
                if (returnData.getData() != null && !returnData.getData().isEmpty()) {
                    dataOneBeans = returnData.getData();

                    if (newestReleaseProduct != null && !EmptyUtils.isEmpty(newestReleaseProduct.getCategoryParentId())) {
                        boolean tag = false;
                        int position = 0;
                        for (int i = 0; i < dataOneBeans.size(); i++) {
                            if (dataOneBeans.get(i).getId().equals(newestReleaseProduct.getCategoryParentId())) {
                                dataOneBeans.get(i).setSelect(true);
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
                            dataOneBeans.get(0).setSelect(true);
                            releaseOneSortData = dataOneBeans.get(0);
                        }
                    } else {
                        dataOneBeans.get(0).setSelect(true);
                        releaseOneSortData = dataOneBeans.get(0);
                    }
                    releaseOneSortAdapter.setNewData(dataOneBeans);
                    getTwoSortData();
                }
            }
        });
    }

    //获取小类的数据
    private void getTwoSortData() {
        ReleaseSortModel.sendReleaseSortRequest(TAG, releaseOneSortData.getId(), "", "0", new CustomerJsonCallBack<ReleaseSortModel>() {
            @Override
            public void onRequestError(ReleaseSortModel returnData, String msg) {
                hideWaitDialog();
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(ReleaseSortModel returnData) {
                hideWaitDialog();
                if (returnData.getData() != null && !returnData.getData().isEmpty()) {
                    dataTwoBeans = returnData.getData();
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
                    getAttributeData();
                }
            }
        });
    }

    //获取属性信息
    private void getAttributeData() {
        ReleaseSortModel.sendReleaseSortRequest(TAG, releaseOneSortData.getId(), releaseTwoSortData.getId(), "1", new CustomerJsonCallBack<ReleaseSortModel>() {
            @Override
            public void onRequestError(ReleaseSortModel returnData, String msg) {
                hideWaitDialog();
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(ReleaseSortModel returnData) {
                hideWaitDialog();
                if (returnData.getData() != null && !returnData.getData().isEmpty()) {
                    setAttributeData(returnData.getData().get(0).getAttributes());
                }
            }
        });
    }

    //设置属性信息
    private void setAttributeData(ArrayList<ReleaseSortModel.DataBean.AttributesBean> attributesBeans) {
        this.attributes = attributesBeans;
        llAttributeContain.removeAllViews();

        if (attributesBeans==null){
            return;
        }

        for (ReleaseSortModel.DataBean.AttributesBean attribute : attributesBeans) {
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

                if (newestReleaseProduct != null&&newestReleaseProduct.getAttributes()!=null) {
                    for (NewestReleaseProductModel.InfoBean.AttributesBean newestReleaseProductAttribute : newestReleaseProduct.getAttributes()) {
                        if (newestReleaseProductAttribute.getTitle().equals(attribute.getTitle())) {
                            for (ReleaseSortModel.DataBean.AttributesBean.TagsBean tag : attribute.getTags()) {
                                if (tag!=null&&tag.getName().equals(newestReleaseProductAttribute.getValue())) {
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

                if (newestReleaseProduct != null&&newestReleaseProduct.getAttributes()!=null) {
                    for (NewestReleaseProductModel.InfoBean.AttributesBean newestReleaseProductAttribute : newestReleaseProduct.getAttributes()) {
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

    @OnClick({R.id.fl_open_close_01, R.id.fl_open_close_02, R.id.tv_save, R.id.stb_release})
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
            case R.id.tv_save:
                isPublish = "0";
                preRelease();
                break;
            case R.id.stb_release:
                isPublish = "1";
                preRelease();
                break;
        }
    }

    @Override
    public void onOneSortChoose(ReleaseSortModel.DataBean oneSortData, int position) {
        releaseOneSortData = oneSortData;
        if (!ivOpenClose01.isSelected()) {
            centerOneSortLayoutManager.smoothScrollToPosition(crlOneSort, new RecyclerView.State(), position);
        }

        getTwoSortData();
    }

    @Override
    public void onTwoSortChoose(ReleaseSortModel.DataBean oneSortData, int position) {
        releaseTwoSortData = oneSortData;
        if (!ivOpenClose02.isSelected()) {
            centerTwoSortLayoutManager.smoothScrollToPosition(crlTwoSort, new RecyclerView.State(), position);
        }
        getAttributeData();
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
                showWaitDialog();
                ossUtils.initOssOption(CommodityReleaseActivity.this, decryOssDataModel.getAccessKeyId(), decryOssDataModel.getSecret(), "",
                        decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName());
                ossUtils.deleteOssObject(item.getImgPth(), new OssUtils.OssDeleteListener() {
                    @Override
                    public void deleteSuccess() {
                        hideWaitDialog();
                        postImglistAdapter.clearImgViews();
                        postImglistAdapter.getData().remove(pos);
                        postImglistAdapter.notifyDataSetChanged();
                    }
                });
            } else {
                ToastUtils.showShort("图片还未上传完成");
            }
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
                    ToastUtils.showShort("视频还在处理中");
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
            if ("2".equals(item.getUploadCompleteStatus())) {
                showWaitDialog();
                ossVideoUtils.initOssOption(CommodityReleaseActivity.this, decryOssDataModel.getAccessKeyId(), decryOssDataModel.getSecret(), "",
                        decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName());
                ossVideoUtils.deleteOssObject(postVideolistAdapter.getData().get(pos).getVideoPath(), new OssVideoUtils.OssDeleteListener() {
                    @Override
                    public void deleteSuccess() {
                        hideWaitDialog();
                        postVideolistAdapter.getData().remove(pos);
                        postVideolistAdapter.notifyDataSetChanged();
                    }
                });
            } else {
                ToastUtils.showShort("视频正在处理中");
            }
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
    public void onCompressedResult_video(ArrayList<ReleaseVideoModel> albumOrCameraChooseDataLists) {
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

        if (postImglistAdapter.getData().size() <7) {
            ToastUtils.showShort("商品图片不能少于6张");
            return;
        }

        if (EmptyUtils.isEmpty(etSellingPrice.getText().toString())) {
            ToastUtils.showShort("请设置商品销售价");
            return;
        }

        if (EmptyUtils.isEmpty(etSupplyPrice.getText().toString())) {
            ToastUtils.showShort("请设置商品供货价");
            return;
        }

        if (new BigDecimal(etSellingPrice.getText().toString()).subtract(new BigDecimal(etSupplyPrice.getText().toString()).multiply(new BigDecimal(1.05))).doubleValue() < 0) {
            ToastUtils.showShort("销售价不得低于供货价的1.05倍");
            return;
        }

        if (new BigDecimal(etSellingPrice.getText().toString()).subtract(new BigDecimal(etSupplyPrice.getText().toString()).multiply(new BigDecimal(20))).doubleValue() > 0) {
            ToastUtils.showShort("销售价不得高于供货价的20倍");
            return;
        }

        if (EmptyUtils.isEmpty(etStock.getText().toString()) || new BigDecimal(etStock.getText().toString()).doubleValue() < 1) {
            ToastUtils.showShort("请设置1-9999商品库存数量");
            return;
        }
        //判断属性信息是否都按要求填写
        if (attributes != null) {
            if (llAttributeContain.getChildCount() != attributes.size()) {
                ToastUtils.showShort("数据有误，请重新进入页面");
                return;
            }
            for (int i = 0; i < attributes.size(); i++) {
                ReleaseSortModel.DataBean.AttributesBean attribute = attributes.get(i);
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

        //判断是否需要输入运费
        if (!sbtFreeShipping.isChecked() && !sbtToPay.isChecked()) {
            if (EmptyUtils.isEmpty(etFreight.getText().toString())) {
                ToastUtils.showShort("请设置商品运费");
                return;
            }
        }

        if (zpbProgress.getVisibility() == View.VISIBLE) {
            ToastUtils.showShort(zpbProgress.getTag() + "正在上传，请稍等");
            return;
        }

        showWaitDialog();
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

        ArrayList<ReleaseSortModel.DataBean.AttributesBean> releaseAttributesModels = new ArrayList<>();
        for (int i = 0; i < (attributes==null?0:attributes.size()); i++) {
            ReleaseSortModel.DataBean.AttributesBean attribute = attributes.get(i);
            View attributeView = llAttributeContain.getChildAt(i);
            EditText etAttriValue = attributeView.findViewById(R.id.et_attri_value);
            final CustomeRecyclerView crlAttriList = attributeView.findViewById(R.id.crl_attri_list);

            String value="";
            if (attribute.getTags() != null && !attribute.getTags().isEmpty()) {
                if (((ReleaseAttributeAdapter) crlAttriList.getAdapter()).getmSelectedReleaseSortData()!=null){
                    value = ((ReleaseAttributeAdapter) crlAttriList.getAdapter()).getmSelectedReleaseSortData().getName();
                }else {
                    value="";
                }
            } else {
                value = etAttriValue.getText()==null?"":etAttriValue.getText().toString().trim();
            }

            attribute.setValue(value);
            releaseAttributesModels.add(attribute);
        }

        CommodityReleaseModel.sendCommodityReleaseRequest(TAG, releaseTwoSortData.getId(), userInfoModel.getShopUri(),
                TextOptionUtils.getInstance().subLength(etTitle.getText().toString(), 30),
                TextOptionUtils.getInstance().subLength(etContent.getText().toString(), 500),
                etSellingPrice.getText().toString(), etStock.getText().toString(), etSupplyPrice.getText().toString(),
                sbtFreeShipping.isChecked() ? "1" : "0", sbtToPay.isChecked() ? "1" : "0", sbtFreeShipping.isChecked() ? "0" : etFreight.getText().toString(),
                ossPaths, ossVideoPaths, cutPic, releaseAttributesModels, isPublish,
                TextOptionUtils.getInstance().subLength(etRemarks.getText().toString().trim(), 15),
                new CustomerJsonCallBack<CommodityReleaseModel>() {
                    @Override
                    public void onRequestError(CommodityReleaseModel returnData, String msg) {
                        hideWaitDialog();
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onRequestSuccess(CommodityReleaseModel returnData) {
                        hideWaitDialog();
                        if ("1".equals(isPublish)) {
                            ToastUtils.showShort("商品发布成功");
                        } else {
                            ToastUtils.showShort("商品保存成功");
                        }

                        cleanRelease();
                    }
                });
    }

    //发布成功 清空发布的数据
    private void cleanRelease() {
        etTitle.setText("");
        etContent.setText("");
        //清空上传的图片
        postImglistAdapter.clearImgViews();
        uploadPicUtils.initChoosePic(CommodityReleaseActivity.this, true, 9, Constants.Api.OSS_FOLDER_IMG_GOODS,this);
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
            dialogUtils=null;
        }
    }

    public static void newIntance(Context context) {
        Intent intent = new Intent(context, CommodityReleaseActivity.class);
        context.startActivity(intent);
    }
}

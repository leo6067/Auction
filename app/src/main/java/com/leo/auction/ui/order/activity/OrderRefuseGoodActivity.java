//package com.leo.auction.ui.order.activity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.aten.compiler.base.BaseActivity;
//import com.aten.compiler.utils.BroadCastReceiveUtils;
//import com.aten.compiler.utils.DesUtil;
//import com.aten.compiler.utils.EmptyUtils;
//import com.aten.compiler.widget.RadiusImageView;
//import com.aten.compiler.widget.glide.GlideUtils;
//import com.leo.auction.R;
//import com.leo.auction.base.BaseModel;
//import com.leo.auction.base.Constants;
//import com.leo.auction.net.HttpRequest;
//import com.leo.auction.ui.login.model.OssTokenModel;
//import com.leo.auction.ui.main.home.activity.ImageShowActivity;
//import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
//
//import com.leo.auction.ui.order.model.CancleOrderModel;
//import com.leo.auction.ui.order.model.OrderGoodJson;
//import com.leo.auction.ui.order.model.OrderListModel;
//import com.leo.auction.utils.DialogUtils;
//import com.leo.auction.utils.TextOptionUtils;
//import com.leo.auction.utils.ossUpload.DecryOssDataModel;
//import com.leo.auction.utils.ossUpload.UploadPicUtils;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import okhttp3.Call;
//
//
////订单--申请退款  或者 申请退货
//public class OrderRefuseGoodActivity extends BaseActivity implements UploadPicUtils.IChoosePic  {
//
//    @BindView(R.id.ll_contain)
//    LinearLayout llContain;
//    @BindView(R.id.all_aggregate_payment)
//    LinearLayout allAggregatePayment;
//    @BindView(R.id.tv_real_price)
//    TextView tvRealPrice;
//    @BindView(R.id.tv_refund_type)
//    TextView tvRefundType;
//    @BindView(R.id.tv_refund_num)
//    TextView tvRefundNum;
//    @BindView(R.id.et_refund_money)
//    TextView etRefundMoney;
//    @BindView(R.id.tv_refund_reason)
//    TextView tvRefundReason;
//    @BindView(R.id.et_content)
//    EditText etContent;
//    @BindView(R.id.rv_imglist)
//    RecyclerView rvImglist;
//    @BindView(R.id.tv_freight)
//    TextView tvFreight;
//
//    private PostOssImglistAdapter postImglistAdapter;
//
//    private int refundNum=0;//记录退款件数
//    private ArrayList<CancleOrderModel> refundDatas=new ArrayList<>();
//
//    private UploadPicUtils uploadPicUtils;
//    private DecryOssDataModel decryOssDataModel;
//    private DialogUtils dialogUtils;
//
//    @Override
//    protected boolean isImmersionBarEnabled() {
//        return true;
//    }
//
//
//    @Override
//    public void setContentViewLayout() {
//        setContentView(R.layout.activity_order_refuse_good);
//    }
//
//
//    @Override
//    public void initData() {
//
//        uploadPicUtils = new UploadPicUtils();
//        dialogUtils=new DialogUtils();
//        super.initData();
//        getRefundData();
//
//        if ("1".equals(returnRequestModel.getType())){
//            setTitle("退款申请");
//        }else {
//            setTitle("退货申请");
//        }
//
//
//    }
//
//
//    @Override
//    public void initEvent() {
//        super.initEvent();
//
//        postImglistAdapter.setmOnLastImgListener(mOnLastImgListener);
//        postImglistAdapter.setmOnImgsItemListener(mOnImgsItemListener);
//        postImglistAdapter.setmOnImgsItemDeleteListener(mOnImgsItemDeleteListener);
//    }
//
//
//
//    public void getOrderData(){
//
//
//        String order = "";
//
//        OrderGoodJson.httpGetOrderModel(order, new HttpRequest.HttpCallback() {
//            @Override
//            public void httpError(Call call, Exception e) {
//
//            }
//
//            @Override
//            public void httpResponse(String resultData) {
//
//            }
//        });
//
//
//
//    }
//
//
//
//
//    public void UpUI(){
//
//        if (returnRequestModel.getItems()!=null&&!returnRequestModel.getItems().isEmpty()){
////            if (returnRequestModel.getItems().size()>1){
////                allAggregatePayment.setVisibility(View.VISIBLE);
////            }else {
////                allAggregatePayment.setVisibility(View.GONE);
////            }
//
//            allAggregatePayment.setVisibility(View.VISIBLE);
//
//            //设置商品列表数据
//            llContain.removeAllViews();
//            for (OrderListModel.DataBean.ItemsBean itemsBean : returnRequestModel.getItems()) {
//                View productLayout = LayoutInflater.from(this).inflate(R.layout.item_order_product, llContain, false);
//                RadiusImageView rivProductPic = productLayout.findViewById(R.id.riv_product_pic);
//                TextView tvProductTitle = productLayout.findViewById(R.id.tv_product_title);
//                TextView tvTurnoverAmount = productLayout.findViewById(R.id.tv_turnover_amount);
//                TextView tvTurnoverNum = productLayout.findViewById(R.id.tv_turnover_num);
//                TextView tvFreight = productLayout.findViewById(R.id.tv_freight);
//                TextView tvRealPrice = productLayout.findViewById(R.id.tv_real_price);
//
//                GlideUtils.loadImg(itemsBean.getPicPath(), rivProductPic);
//                tvProductTitle.setText(EmptyUtils.strEmpty(itemsBean.getGoodsTitle()));
//                tvTurnoverAmount.setText("成交金额：¥"+itemsBean.getPrice());
//                tvTurnoverNum.setText("件数："+itemsBean.getNum());
//                tvFreight.setText("运费：¥" + itemsBean.getNum());
//
//                tvRealPrice.setText("实付金额：¥" + itemsBean.getTotal());
//
////                if (returnRequestModel.getItems().size()>1){
////                    tvRealPrice.setText("实付金额：¥" + itemsBean.getTotal());
////                }else {
////                    tvRealPrice.setText("实付金额：¥" + returnRequestModel.getPayment());
////                }
//                //------------------------运费暂时隐藏-------------------------------
//                tvTurnoverAmount.setVisibility(View.GONE);
//                tvFreight.setVisibility(View.GONE);
//                //------------------------运费暂时隐藏-------------------------------
//                refundNum+=itemsBean.getNum();
//                llContain.addView(productLayout);
//            }
//
//            if (EmptyUtils.isEmpty(returnRequestModel.getFreight()) || new BigDecimal(returnRequestModel.getFreight()).doubleValue() <= 0) {
//                tvFreight.setVisibility(View.GONE);
//            } else {
//                tvFreight.setVisibility(View.VISIBLE);
//                tvFreight.setText("运费：¥" + returnRequestModel.getFreight());
//            }
//            tvRealPrice.setText("¥"+returnRequestModel.getPayment());
//        }else {
//            allAggregatePayment.setVisibility(View.GONE);
//        }
//
//        if ("1".equals(returnRequestModel.getType())){
//            //申请退款
//            tvRefundType.setText("退款");
//        }else if ("2".equals(returnRequestModel.getType())){
//            //申请退货退款
//            tvRefundType.setText("退货退款");
//        }
//
//        tvRefundNum.setText(refundNum+"件");
//        etRefundMoney.setText(EmptyUtils.strEmpty(String.valueOf(returnRequestModel.getPayment())));
//
//        rvImglist.setHasFixedSize(true);
//        rvImglist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        postImglistAdapter = new PostOssImglistAdapter();
//        rvImglist.setAdapter(postImglistAdapter);
//
//        //初始化图片上传
//        uploadPicUtils.initChoosePic(OrderRefuseGoodActivity.this,false,6,Constants.oss.OSS_FOLDER_IMG_USER,this);
//
//        geOssToken();
//
//
//    }
//
//
//
//
//
//    //获取退款原因的数据
//    private void getRefundData() {
//        CommonlyUsedData.getInstance().getCancleData(TAG,"1".equals(returnRequestModel.getType())?"2":"3", new CommonlyUsedData.IGetCancleData() {
//            @Override
//            public void showWait() {
//                showWaitDialog();
//            }
//
//            @Override
//            public void hideWait() {
//                hideWaitDialog();
//            }
//
//            @Override
//            public void onSuccess(ArrayList<CancleOrderModel> cancleOrderModels) {
//                refundDatas=cancleOrderModels;
//            }
//        });
//    }
//
//    //获oss请求的必要参数
//    private void geOssToken() {
//        OssTokenModel.sendOssTokenRequest(new HttpRequest.HttpCallback() {
//            @Override
//            public void httpError(Call call, Exception e) {
//
//            }
//
//            @Override
//            public void httpResponse(String resultData) {
//
//                OssTokenModel ossTokenModel = JSONObject.parseObject(resultData, OssTokenModel.class);
//
//
//                if (ossTokenModel.getData() != null) {
//                    String decryptData = "";
//                    try {
//                        decryptData = DesUtil.decrypt(ossTokenModel.getData().getEncryptedData(), Constants.Nouns.OSS_KEY);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    if (!EmptyUtils.isEmpty(decryptData)) {
//                        decryOssDataModel = JSON.parseObject(decryptData, DecryOssDataModel.class);
//                    } else {
//                        hideWaitDialog();
//                        showShortToast("oss数据有误");
//                    }
//                }
//            }
//        });
//
//
//    }
//
//    //选择发布图片的类型选择点击事件
//    private View.OnClickListener mOnLastImgListener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            uploadPicUtils.showChoosePicTypeDialog();
//        }
//    };
//
//    //图片的类型选择点击事件
//    private View.OnClickListener mOnImgsItemListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            int position = (int) v.getTag(R.id.tag_1);
//            ImageView[] imgViews = (ImageView[]) postImglistAdapter.getImgViews().toArray(new ImageView[postImglistAdapter.getImgViews().size()]);
//            String[] imgListsData = new String[postImglistAdapter.getData().size() - 1];
//            for (int i = 0; i < postImglistAdapter.getData().size() - 1; i++) {
//                imgListsData[i] =postImglistAdapter.getData().get(i).getFile().getAbsolutePath();
//            }
//            ImageShowActivity.startImageActivity(OrderRefuseGoodActivity.this, imgViews, imgListsData, position);
//        }
//    };
//
//    //图片的删除点击事件
//    private View.OnClickListener mOnImgsItemDeleteListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            final int pos = (int) v.getTag(R.id.tag_2);
//            postImglistAdapter.clearImgViews();
//            postImglistAdapter.getData().remove(pos);
//            postImglistAdapter.notifyDataSetChanged();
//        }
//    };
//
//    @OnClick({R.id.rl_refund_reason, R.id.stb_commit})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.rl_refund_reason:
//                if (refundDatas==null||refundDatas.isEmpty()){
//                    showShortToast("数据有误，请关闭页面重新进入");
//                    return;
//                }
//                dialogUtils.showRefundReasonDialog(OrderRefuseGoodActivity.this,refundDatas,
//                        new DialogUtils.IRefundReason() {
//                            @Override
//                            public void RefundReasonTrue(CancleOrderModel cancleOrderModel) {
//                                tvRefundReason.setText(cancleOrderModel.getText());
//                            }
//                        });
//                break;
//            case R.id.stb_commit:
//                if (EmptyUtils.isEmpty(etRefundMoney.getText().toString().trim())){
//                    showShortToast("请输入要退款的金额");
//                    return;
//                }
//
//                if (EmptyUtils.isEmpty(tvRefundReason.getText().toString().trim())){
//                    showShortToast("请选择退款原因");
//                    return;
//                }
//
//                showWaitDialog();
//                if (postImglistAdapter.getData().size()==1){
//                    commit(new ArrayList<String>());
//                }else {
//                    uploadPicUtils.upReleaseImage(decryOssDataModel);
//                }
//
//                break;
//        }
//    }
//
//    @Override
//    public void onCompressedResult(ArrayList<ReleaseImageModel> albumOrCameraChooseDataLists) {
//        postImglistAdapter.clearImgViews();
//        postImglistAdapter.setNewData(albumOrCameraChooseDataLists);
//    }
//
//    @Override
//    public void onOssUpResult(ArrayList<String> ossPaths) {
//        commit(ossPaths);
//    }
//
//    @Override
//    public void onShowWait() {
//        showWaitDialog();
//    }
//
//    @Override
//    public void onHideWait() {
//        hideWaitDialog();
//    }
//
//    //提交退款申请
//    private void commit(ArrayList<String> imgs){
//        BaseModel.sendOrderRefundApplicationRequest(TAG, returnRequestModel.getOrderId(), returnRequestModel.getType(), etRefundMoney.getText().toString().trim(),
//                TextOptionUtils.getInstance().subLength(etContent.getText().toString().trim(),200),tvRefundReason.getText().toString().trim(),imgs, new CustomerJsonCallBack_v1<BaseModel>() {
//                    @Override
//                    public void onRequestError(BaseModel returnData, String msg) {
//                        hideWaitDialog();
//                        showShortToast(msg);
//                    }
//
//                    @Override
//                    public void onRequestSuccess(BaseModel returnData) {
//                        hideWaitDialog();
//                        showShortToast("提交退款申请成功");
//                        setResult(RESULT_OK);
//                        goFinish();
//                        BroadCastReceiveUtils.sendLocalBroadCast(OrderRefuseGoodActivity.this, Constants.Action.SEND_REFRESH_ORDER_LIST + 64);
//                    }
//                });
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (dialogUtils!=null){
//            dialogUtils.dissRefundReasonDialog();
//        }
//        super.onDestroy();
//
//        if (uploadPicUtils!=null){
//            uploadPicUtils.onDestoryUtil();
//        }
//    }
//
//
//}

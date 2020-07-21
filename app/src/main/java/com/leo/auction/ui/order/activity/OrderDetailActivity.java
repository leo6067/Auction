package com.leo.auction.ui.order.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.allen.library.shape.ShapeTextView;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.order.model.OrderCommentInfoModel;
import com.leo.auction.ui.order.model.OrderEvaluationModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


//订单详情，立即发货
public class OrderDetailActivity extends BaseActivity {


    String orderCode;
    String actionType;
    boolean isSeller;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_order_status)
    ImageView mIvOrderStatus;
    @BindView(R.id.tv_order_status)
    TextView mTvOrderStatus;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_order_reason)
    TextView mTvOrderReason;
    @BindView(R.id.re_order_status)
    RelativeLayout mReOrderStatus;
    @BindView(R.id.civ_order_head)
    CircleImageView mCivOrderHead;
    @BindView(R.id.civ_order_shop_name)
    TextView mCivOrderShopName;
    @BindView(R.id.civ_order_status)
    TextView mCivOrderStatus;
    @BindView(R.id.all_order_product_contain)
    LinearLayout mAllOrderProductContain;
    @BindView(R.id.tv_real_price)
    TextView mTvRealPrice;
    @BindView(R.id.tv_01)
    ShapeTextView mTv01;
    @BindView(R.id.fl_01)
    FrameLayout mFl01;
    @BindView(R.id.tv_02)
    ShapeTextView mTv02;
    @BindView(R.id.fl_02)
    FrameLayout mFl02;
    @BindView(R.id.tv_03)
    ShapeTextView mTv03;
    @BindView(R.id.fl_03)
    FrameLayout mFl03;
    @BindView(R.id.tv_04)
    ShapeTextView mTv04;
    @BindView(R.id.fl_04)
    FrameLayout mFl04;
    @BindView(R.id.ll_btn)
    LinearLayout mLlBtn;
    @BindView(R.id.remark_tv)
    TextView mRemarkTv;
    @BindView(R.id.tv_close)
    ShapeTextView mTvClose;
    @BindView(R.id.crl_courier)
    CustomeRecyclerView mCrlCourier;
    @BindView(R.id.et_courier_number)
    EditText mEtCourierNumber;
    @BindView(R.id.fl_scan_qrcode)
    FrameLayout mFlScanQrcode;
    @BindView(R.id.stb_courier_number)
    SuperButton mStbCourierNumber;
    @BindView(R.id.tv_logistics)
    TextView mTvLogistics;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.name_copy)
    ImageView mNameCopy;
    @BindView(R.id.tv_ems_way)
    TextView mTvEmsWay;
    @BindView(R.id.way_copy)
    ImageView mWayCopy;
    @BindView(R.id.lin_ems_way)
    LinearLayout mLinEmsWay;
    @BindView(R.id.tv_logistics_th)
    TextView mTvLogisticsTh;
    @BindView(R.id.tv_name_th)
    TextView mTvNameTh;
    @BindView(R.id.tv_phone_th)
    TextView mTvPhoneTh;
    @BindView(R.id.name_copy_th)
    ImageView mNameCopyTh;
    @BindView(R.id.tv_ems_way_th)
    TextView mTvEmsWayTh;
    @BindView(R.id.way_copy_th)
    ImageView mWayCopyTh;
    @BindView(R.id.lin_ems_way_th)
    LinearLayout mLinEmsWayTh;
    @BindView(R.id.tv_after)
    TextView mTvAfter;
    @BindView(R.id.after_reason)
    TextView mAfterReason;
    @BindView(R.id.after_time)
    TextView mAfterTime;
    @BindView(R.id.tv_order_id_01)
    TextView mTvOrderId01;
    @BindView(R.id.tv_order_id_02)
    TextView mTvOrderId02;
    @BindView(R.id.tv_order_id_copy)
    ImageView mTvOrderIdCopy;
    @BindView(R.id.tv_payment_transaction_no_01)
    TextView mTvPaymentTransactionNo01;
    @BindView(R.id.tv_payment_transaction_no_02)
    TextView mTvPaymentTransactionNo02;
    @BindView(R.id.arl_payment_transaction_no)
    RelativeLayout mArlPaymentTransactionNo;
    @BindView(R.id.tv_creation_time_01)
    TextView mTvCreationTime01;
    @BindView(R.id.tv_creation_time_02)
    TextView mTvCreationTime02;
    @BindView(R.id.arl_creation_time)
    RelativeLayout mArlCreationTime;
    @BindView(R.id.tv_pay_time_01)
    TextView mTvPayTime01;
    @BindView(R.id.tv_pay_time_02)
    TextView mTvPayTime02;
    @BindView(R.id.arl_pay_time)
    RelativeLayout mArlPayTime;
    @BindView(R.id.tv_delivery_time_01)
    TextView mTvDeliveryTime01;
    @BindView(R.id.tv_delivery_time_02)
    TextView mTvDeliveryTime02;
    @BindView(R.id.arl_delivery_time)
    RelativeLayout mArlDeliveryTime;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order_detail);
    }


    @Override
    public void initData() {
        super.initData();

        Bundle bundle = getIntent().getExtras();
        orderCode = bundle.getString("orderCode");
        isSeller = bundle.getBoolean("isSeller");
        actionType = bundle.getString("actionType");  //查看详情，立即发货，修改单号


        getOrderEvaluation();
    }

    //获取详情 订单评论详情
    private void getOrderEvaluation() {
        showWaitDialog();
        OrderEvaluationModel.httpGetOrdEvaution(orderCode, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                OrderEvaluationModel orderEvaluationModel = JSONObject.parseObject(resultData, OrderEvaluationModel.class);
                initUi(orderEvaluationModel.getData());
            }
        });


    }


    private void initUi(OrderEvaluationModel.DataBeanX dataBeanX) {


    }


    @OnClick({R.id.tv_close, R.id.fl_scan_qrcode, R.id.stb_courier_number, R.id.name_copy, R.id.way_copy, R.id.name_copy_th, R.id.way_copy_th})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                break;
            case R.id.fl_scan_qrcode:
                break;
            case R.id.stb_courier_number:
                break;
            case R.id.name_copy:
                break;
            case R.id.way_copy:
                break;
            case R.id.name_copy_th:
                break;
            case R.id.way_copy_th:
                break;
        }
    }
}

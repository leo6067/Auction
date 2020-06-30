package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.utils.CustomerDialogUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;
import com.blankj.utilcode.util.SPUtils;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.mvp.BaseModel;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.main.mine.adapter.AddressAdapter;
import com.leo.auction.ui.main.mine.model.AddressModel;

import butterknife.OnClick;

public class AddressActivity extends BaseRecyclerViewActivity implements AddressAdapter.IAddressOption {



    private String type, itemClickBackType;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_address);
    }





    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initAdapter() {
        mAdapter = new AddressAdapter(this,itemClickBackType);
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        itemClickBackType = getIntent().getStringExtra("itemClickBackType");
        super.initData();

        if ("1".equals(type)) {
            setTitle("退货地址");
        } else {
            setTitle("收货地址");
        }

        showWaitDialog();
        onRefresh(refreshLayout);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        setSmartHasRefreshOrLoadMore();
        setLoadMore();
    }

    @Override
    public void getData() {
        AddressModel.sendAddressRequest(TAG, String.valueOf(mPageNum), "", "", type, new CustomerJsonCallBack<AddressModel>() {
            @Override
            public void onRequestError(AddressModel returnData, String msg) {
                hideRefreshView();
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(AddressModel returnData) {
                hideRefreshView();
                if (mPageNum == 1) {
                    if (returnData.getAddress() != null && !returnData.getAddress().isEmpty()) {
                        SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("0".equals(type) ? "ShipAddress" : "ReturnAddress", JSON.toJSONString(returnData.getAddress().get(0)));
                    } else {
                        SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("0".equals(type) ? "ShipAddress" : "ReturnAddress", "");
                    }

                    mAdapter.setNewData(returnData.getAddress());
                } else {
                    mAdapter.addData(returnData.getAddress());
                    mAdapter.loadMoreComplete();
                }

                if (mPageNum > 1 && returnData.getAddress().isEmpty()) {
                    if (mAdapter.getData().size() < 10) {
                        mAdapter.loadMoreEnd(true);
                    } else {
                        mAdapter.loadMoreEnd();
                    }
                }
            }
        });
    }

    //地址列表的item点击事件
    @Override
    public void onItemListener(AddressModel.AddressBean item) {
        if ("0".equals(itemClickBackType)) {
            UpdateAddressActivity.newIntance(AddressActivity.this, item, Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATEADDRESS);
        } else if ("1".equals(itemClickBackType)) {
            Intent intent = new Intent();
            intent.putExtra("address", item);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onDeleteListener(String id) {
        CustomerDialogUtils.getInstance().showNormalDialog(AddressActivity.this, true, "删除",
                "是否确认删除改地址", NormalDialog.STYLE_TWO, 2, "取消,确认", new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        CustomerDialogUtils.getInstance().dissNormalDialog();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        CustomerDialogUtils.getInstance().dissNormalDialog();
                        delete(id);
                    }
                });
    }

    //删除地址
    private void delete(String id) {
        showWaitDialog();
//        BaseModel.sendDeleteAddressRequest(TAG, id, new CustomerJsonCallBack<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                ToastUtils.showShort(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                ToastUtils.showShort("删除成功");
//                setResult(RESULT_OK);
//                goFinish();
//            }
//        });
    }

    @OnClick({R.id.stb_add})
    public void onViewClicked(View view) {
        if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
            return;
        }
        switch (view.getId()) {
            case R.id.stb_add:
                IncreaseAddressActivity.newIntance(AddressActivity.this, type, Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATEADDRESS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATEADDRESS && resultCode == RESULT_OK) {
            showWaitDialog();
            onRefresh(refreshLayout);
        }
    }

    @Override
    public void onBackPressed() {
        if ("0".equals(itemClickBackType)&&mAdapter!=null&&!mAdapter.getData().isEmpty()){
            Intent intent = new Intent();
            intent.putExtra("address", ((AddressAdapter)mAdapter).getData().get(0));
            setResult(RESULT_OK, intent);
        }

        super.onBackPressed();
    }

    public static void newIntance(Context context, String type, String itemClickBackType) {
        Intent intent = new Intent(context, AddressActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("itemClickBackType", itemClickBackType);
        context.startActivity(intent);
    }

    //itemClickBackType 列表item点击时操作 0：默认直接修改地址 1：直接把item的数据带返回
    public static void newIntance(Activity activity, String type, String itemClickBackType, int requestCode) {
        Intent intent = new Intent(activity, AddressActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("itemClickBackType", itemClickBackType);
        activity.startActivityForResult(intent, requestCode);
    }
}

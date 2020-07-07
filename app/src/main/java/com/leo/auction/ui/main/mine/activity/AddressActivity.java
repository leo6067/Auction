package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.utils.CustomerDialogUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;
import com.blankj.utilcode.util.SPUtils;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.adapter.AddressAdapter;
import com.leo.auction.ui.main.mine.model.AddressModel;

import java.util.HashMap;

import butterknife.OnClick;
import okhttp3.Call;

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
        mAdapter = new AddressAdapter(this, itemClickBackType);
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


        onRefresh(refreshLayout);
    }


    @Override
    public void getData() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNum", String.valueOf(mPageNum));
        hashMap.put("pageSize", Constants.Var.LIST_NUMBER);
        hashMap.put("status", "00B");
        hashMap.put("type", type);
        showWaitDialog();
        HttpRequest.httpGetString(Constants.Api.ADDRESS_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();

                AddressModel addressModel = JSONObject.parseObject(resultData, AddressModel.class);
                if (mPageNum == 1) {
                    if (addressModel.getData() != null && !addressModel.getData().isEmpty()) {
                        SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("0".equals(type) ? "ShipAddress" : "ReturnAddress", JSON.toJSONString(addressModel.getData().get(0)));
                    } else {
                        SPUtils.getInstance(Constants.Var.HAS_ADDRESS).put("0".equals(type) ? "ShipAddress" : "ReturnAddress", "");
                    }
                    mAdapter.setNewData(addressModel.getData());
                } else {
                    mAdapter.addData(addressModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (mPageNum > 1 && addressModel.getData().isEmpty()) {
                    if (mAdapter.getData().size() > Constants.Var.LIST_NUMBER_INT) {
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
    public void onItemListener(AddressModel.DataBean item) {
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
                "是否确认删除地址", NormalDialog.STYLE_TWO, 2, "取消,确认", new OnBtnClickL() {
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
    private void delete(String addressId) {
        showWaitDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("addressId", addressId);
        showWaitDialog();
        HttpRequest.httpDeleteString(Constants.Api.ADDRESS_URL, hashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                ToastUtils.showShort("删除成功");
                setResult(RESULT_OK);
                goFinish();
            }
        });


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
        if ("0".equals(itemClickBackType) && mAdapter != null && !mAdapter.getData().isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("address", ((AddressAdapter) mAdapter).getData().get(0));
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

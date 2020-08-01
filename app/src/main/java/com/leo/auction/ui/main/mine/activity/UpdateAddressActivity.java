package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.switchButton.SwitchButton;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.AddressModel;
import com.leo.auction.ui.main.mine.model.DistrictListModel;
import com.leo.auction.ui.main.mine.model.OneKeyFillingModel;
import com.leo.auction.utils.CityWheelUtils;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.TextOptionUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class UpdateAddressActivity extends BaseActivity {


    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_consignee_name)
    EditText etConsigneeName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_detail_address)
    EditText etDetailAddress;
    @BindView(R.id.et_postal_code)
    EditText etPostalCode;
    @BindView(R.id.et_one_key_filling)
    EditText etOneKeyFilling;
    @BindView(R.id.rl_default)
    RelativeLayout rlDefault;
    @BindView(R.id.sb_default)
    SwitchButton sbDefault;

    private String provinceId, cityId, areaId;
    private String provinceName = "", cityName = "", areaName = "";
    private AddressModel.DataBean item;
    private CityWheelUtils cityWheelUtils;
    private String mType;


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_update_address);
    }

    @Override
    public void initData() {
        item = getIntent().getParcelableExtra("item");
        mType = getIntent().getStringExtra("type");
        super.initData();
        setTitle("编辑地址");
        cityWheelUtils = new CityWheelUtils();
        etConsigneeName.setText(EmptyUtils.strEmpty(item.getLinkman()));


        if(item.getAddr3Name()!= null &&item.getAddr3Name().length() >0  && !item.getAddr3Name().equals("null")){
            tvAddress.setText(item.getAddr1Name() + item.getAddr2Name() + item.getAddr3Name());
        }else {
            tvAddress.setText(item.getAddr1Name() + item.getAddr2Name());
        }

        etPhone.setText(EmptyUtils.strEmpty(item.getPhone()));
        etDetailAddress.setText(EmptyUtils.strEmpty(item.getAddress()));
        etPostalCode.setText(EmptyUtils.strEmpty(item.getCode()));

        provinceId = item.getAddr1();
        provinceName = item.getAddr1Name();
        cityId = item.getAddr2();
        cityName = item.getAddr2Name();
        areaId = item.getAddr3();
        areaName = item.getAddr3Name();

        if ("00B".equals(item.getStatus())) {
            sbDefault.setCheckedImmediatelyNoEvent(true);
        } else {
            sbDefault.setCheckedImmediatelyNoEvent(false);
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @OnClick({R.id.tv_address, R.id.stb_sure, R.id.stb_one_key_filling})
    public void onViewClicked(View view) {
        if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_address:
                cityWheelUtils.showCityWheel(UpdateAddressActivity.this, new CityWheelUtils.CityWheelClickListener() {
                    @Override
                    public void onCity(String provinceId) {
                        getAddressData("2", provinceId);
                    }

                    @Override
                    public void onArea(String cityId) {
                        getAddressData("3", cityId);
                    }

                    @Override
                    public void onchooseCity(int provincePos, DistrictListModel.DataBean provinceItemData,
                                             int cityPos, DistrictListModel.DataBean cityItemData,
                                             int areaPos, DistrictListModel.DataBean areaItemData) {
                        if (provinceItemData != null) {
                            provinceId = provinceItemData.getId();
                            provinceName = provinceItemData.getName();
                        }

                        if (cityItemData != null) {
                            cityId = cityItemData.getId();
                            cityName = cityItemData.getName();
                        }

                        if (areaItemData != null) {
                            areaId = areaItemData.getId();
                            areaName = areaItemData.getName();
                        }

                        tvAddress.setText((provinceItemData == null ? "" : provinceItemData.getName()) +
                                (cityItemData == null ? "" : cityItemData.getName()) +
                                (areaItemData == null ? "" : areaItemData.getName()));
                    }
                });
                getAddressData("1", "");
                break;
            case R.id.stb_sure:
                sureUpdate();
                break;
            case R.id.stb_one_key_filling:
                if (EmptyUtils.isEmpty(etOneKeyFilling.getText().toString())) {
                    return;
                }
                showWaitDialog();
                oneKeyFilling();
                break;
        }
    }

    //获取地区
    private void getAddressData(final String level, String id) {

        showWaitDialog();
        DistrictListModel.sendDistrictListRequest(id, level, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                DistrictListModel returnData = JSONObject.parseObject(resultData, DistrictListModel.class);
                if ("1".equals(level)) {
                    cityWheelUtils.setProvinceData(returnData.getData());
                    if (!returnData.getData().isEmpty()) {
                        getAddressData("2", returnData.getData().get(0).getId());
                    }
                } else if ("2".equals(level)) {
                    cityWheelUtils.setCityData(returnData.getData());
                    if (!returnData.getData().isEmpty()) {
                        getAddressData("3", returnData.getData().get(0).getId());
                    }
                } else if ("3".equals(level)) {
                    cityWheelUtils.setAreaData(returnData.getData());
                }
            }
        });
    }

    //一键识别
    private void oneKeyFilling() {

        showWaitDialog();
        OneKeyFillingModel.sendOneKeyFillingRequest(etOneKeyFilling.getText().toString().trim().replaceAll("\n", ""), new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                OneKeyFillingModel returnData = JSONObject.parseObject(resultData, OneKeyFillingModel.class);
                if (returnData.getData() != null && !returnData.getData().isEmpty()) {
                    OneKeyFillingModel.DataBean oneKeyFillingInfo = returnData.getData().get(0);
                    provinceId = oneKeyFillingInfo.getProvinceId();
                    provinceName = oneKeyFillingInfo.getProvinceName();
                    cityId = oneKeyFillingInfo.getCityId();
                    cityName = oneKeyFillingInfo.getCityName();
                    areaId = oneKeyFillingInfo.getCountyId();
                    areaName = oneKeyFillingInfo.getCountyName();

                    etConsigneeName.setText(TextOptionUtils.getInstance().subLength(EmptyUtils.strEmpty(oneKeyFillingInfo.getName()), 8));
                    etPhone.setText(EmptyUtils.strEmpty(oneKeyFillingInfo.getMobile()));
                    tvAddress.setText(EmptyUtils.strEmpty(oneKeyFillingInfo.getProvinceName()) + EmptyUtils.strEmpty(oneKeyFillingInfo.getCityName()) + EmptyUtils.strEmpty(oneKeyFillingInfo.getCountyName()));
                    etDetailAddress.setText(EmptyUtils.strEmpty(oneKeyFillingInfo.getDetail()));
                }
            }
        });


    }

    //删除地址
    private void delete() {
        showWaitDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("addressId", item.getId());
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

    //确认修改
    private void sureUpdate() {
        if (EmptyUtils.isEmpty(etConsigneeName.getText().toString().trim())) {
            showShortToast("请输入收货人姓名");
            return;
        }

        if (EmptyUtils.isEmpty(etPhone.getText().toString().trim())) {
            showShortToast("请输入手机号码");
            return;
        }

        if (EmptyUtils.isEmpty(tvAddress.getText().toString().trim())) {
            showShortToast("请选择地址");
            return;
        }

        if (EmptyUtils.isEmpty(etDetailAddress.getText().toString().trim())) {
            showShortToast("请输入详细地址");
            return;
        }

        if (EmptyUtils.isEmpty(etPostalCode.getText().toString().trim())) {
            showShortToast("请输入邮政编码");
            return;
        }

        String isDefault = "";
        isDefault = sbDefault.isChecked() ? "1" : "0";


        JSONObject jsonObject = new JSONObject();


        jsonObject.put("linkman", etConsigneeName.getText().toString().trim());
        jsonObject.put("addressId", item.getId());
        jsonObject.put("addr1", provinceId);
        jsonObject.put("addr2", cityId);
        jsonObject.put("addr3", areaId);
        jsonObject.put("phone", etPhone.getText().toString().trim());
        jsonObject.put("address", etDetailAddress.getText().toString().trim());
        jsonObject.put("code", etPostalCode.getText().toString().trim());
        jsonObject.put("defaultAddress", isDefault);
        jsonObject.put("type", mType);


        showWaitDialog();

        HttpRequest.httpPutString(Constants.Api.ADDRESS_URL, jsonObject, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();

                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    AddressModel.DataBean addressBean = new AddressModel.DataBean(provinceId, provinceName, cityId, cityName, areaId, areaName,
                            etDetailAddress.getText().toString().trim(), etPostalCode.getText().toString().trim(), item.getId(),
                            etConsigneeName.getText().toString().trim(), etPhone.getText().toString().trim(), sbDefault.isChecked() ? "00B" : "00A", "");
                    Intent intent = new Intent();
                    intent.putExtra("seleteAddress", addressBean);
                    setResult(RESULT_OK, intent);
                    goFinish();
                    showShortToast("更新地址成功");
                } else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }

            }
        });
    }

    public static void newIntance(Activity activity, String type, AddressModel.DataBean item, int requestCode) {
        Intent intent = new Intent(activity, UpdateAddressActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, requestCode);
    }
}

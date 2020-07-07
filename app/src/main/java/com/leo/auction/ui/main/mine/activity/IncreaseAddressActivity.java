package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxClipboardTool;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.switchButton.SwitchButton;
import com.leo.auction.R;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.AddAddressModel;
import com.leo.auction.ui.main.mine.model.AddressModel;
import com.leo.auction.ui.main.mine.model.DistrictListModel;
import com.leo.auction.ui.main.mine.model.OneKeyFillingModel;
import com.leo.auction.utils.CityWheelUtils;
import com.leo.auction.utils.TextOptionUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class IncreaseAddressActivity extends BaseActivity {


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
    @BindView(R.id.sb_default)
    SwitchButton sbDefault;

    private String provinceId = "", cityId = "", areaId = "", provinceName = "", cityName = "", areaName = "";
    private String type;
    private CityWheelUtils cityWheelUtils;


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_increase_address);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra("type");
        super.initData();
        setTitle("添加地址");
        cityWheelUtils = new CityWheelUtils();

    }


    @Override
    public void onResume() {
        super.onResume();
        this.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                if (RxClipboardTool.getText(IncreaseAddressActivity.this) != null) {
                    String clipboardText = RxClipboardTool.getText(IncreaseAddressActivity.this).toString();
                    if (!EmptyUtils.isEmpty(clipboardText)) {
                        etOneKeyFilling.setText(clipboardText);
                        oneKeyFilling();
                    }
                }
            }
        });
    }


    @Override
    public void initEvent() {
        super.initEvent();
    }

    @OnClick({R.id.tv_address, R.id.stb_one_key_filling, R.id.stb_sure})
    public void onViewClicked(View view) {
        if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_address:
                cityWheelUtils.showCityWheel(IncreaseAddressActivity.this, new CityWheelUtils.CityWheelClickListener() {
                    @Override
                    public void onCity(String provinceId) {
                        getAddressData("2", provinceId);
                    }

                    @Override
                    public void onArea(String cityId) {
                        getAddressData("3", cityId);
                    }

                    @Override
                    public void onchooseCity(int provincePos, DistrictListModel.DistrictsBean provinceItemData,
                                             int cityPos, DistrictListModel.DistrictsBean cityItemData,
                                             int areaPos, DistrictListModel.DistrictsBean areaItemData) {
                        if (provinceItemData != null) {
                            provinceId = provinceItemData.getAddr();
                            provinceName = provinceItemData.getName();
                        }

                        if (cityItemData != null) {
                            cityId = cityItemData.getAddr();
                            cityName = cityItemData.getName();
                        }

                        if (areaItemData != null) {
                            areaId = areaItemData.getAddr();
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

    //一键识别
    private void oneKeyFilling() {


        showWaitDialog();
        OneKeyFillingModel.sendOneKeyFillingRequest(TAG, etOneKeyFilling.getText().toString().trim().replaceAll("\n", ""), new HttpRequest.HttpCallback() {
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
                    provinceId = EmptyUtils.strEmpty(oneKeyFillingInfo.getProvinceId());
                    cityId = EmptyUtils.strEmpty(oneKeyFillingInfo.getCityId());
                    areaId = EmptyUtils.strEmpty(oneKeyFillingInfo.getCountyId());
                    provinceName = EmptyUtils.strEmpty(oneKeyFillingInfo.getProvinceName());
                    cityName = EmptyUtils.strEmpty(oneKeyFillingInfo.getCityName());
                    areaName = EmptyUtils.strEmpty(oneKeyFillingInfo.getCountyName());

                    etConsigneeName.setText(TextOptionUtils.getInstance().subLength(EmptyUtils.strEmpty(oneKeyFillingInfo.getName()), 8));
                    etPhone.setText(EmptyUtils.strEmpty(oneKeyFillingInfo.getMobile()));
                    tvAddress.setText(EmptyUtils.strEmpty(oneKeyFillingInfo.getProvinceName()) + EmptyUtils.strEmpty(oneKeyFillingInfo.getCityName()) + EmptyUtils.strEmpty(oneKeyFillingInfo.getCountyName()));
                    etDetailAddress.setText(EmptyUtils.strEmpty(oneKeyFillingInfo.getDetail()));
                }
            }
        });

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
                    cityWheelUtils.setProvinceData(returnData.getDistricts());
                    for (int i = 0; i < returnData.getDistricts().size(); i++) {
                        if (provinceId.equals(returnData.getDistricts().get(i).getAddr())) {
                            cityWheelUtils.setProvinceSelectItem(i);
                            break;
                        }
                    }

                    if (!returnData.getDistricts().isEmpty()) {
                        getAddressData("2", returnData.getDistricts().get(0).getAddr());
                    }
                } else if ("2".equals(level)) {
                    cityWheelUtils.setCityData(returnData.getDistricts());
                    for (int i = 0; i < returnData.getDistricts().size(); i++) {
                        if (cityId.equals(returnData.getDistricts().get(i).getAddr())) {
                            cityWheelUtils.setCitySelectItem(i);
                            break;
                        }
                    }
                    if (!returnData.getDistricts().isEmpty()) {
                        getAddressData("3", returnData.getDistricts().get(0).getAddr());
                    }
                } else if ("3".equals(level)) {
                    cityWheelUtils.setAreaData(returnData.getDistricts());
                    for (int i = 0; i < returnData.getDistricts().size(); i++) {
                        if (areaId.equals(returnData.getDistricts().get(i).getAddr())) {
                            cityWheelUtils.setAreaSelectItem(i);
                            break;
                        }
                    }
                }
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
        showWaitDialog();
        AddAddressModel.sendAddressRequest(TAG, TextOptionUtils.getInstance().subLength(etConsigneeName.getText().toString().trim(), 8),
                provinceId, cityId, areaId, etPhone.getText().toString().trim(), etDetailAddress.getText().toString().trim(),
                etPostalCode.getText().toString().trim(), sbDefault.isChecked() ? "1" : "0", type, new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {
                        hideWaitDialog();

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        hideWaitDialog();
                        showShortToast("添加地址成功");
//                        AddressModel.DataBean item =new AddressModel.DataBean(provinceId,provinceName,cityId,cityName,areaId,areaName,
//                                etDetailAddress.getText().toString().trim(),etPostalCode.getText().toString().trim(),returnData.getData().getAddressId(),
//                                TextOptionUtils.getInstance().subLength(etConsigneeName.getText().toString().trim(),8),
//                                etPhone.getText().toString().trim(),sbDefault.isChecked() ? "00B" : "00A","");
//                        Intent intent = new Intent();
//                        intent.putExtra("seleteAddress", item);
//                        setResult(RESULT_OK, intent);
                        goFinish();
                    }
                }
        );

    }

    public static void newIntance(Activity activity, String type, int requestCode) {
        Intent intent = new Intent(activity, IncreaseAddressActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, requestCode);
    }
}

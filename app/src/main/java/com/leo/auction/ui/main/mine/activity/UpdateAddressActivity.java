package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.widget.switchButton.SwitchButton;
import com.leo.auction.R;
import com.leo.auction.mvp.BaseModel;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.main.mine.model.AddressModel;
import com.leo.auction.ui.main.mine.model.DistrictListModel;
import com.leo.auction.ui.main.mine.model.OneKeyFillingModel;
import com.leo.auction.utils.CityWheelUtils;
import com.leo.auction.utils.TextOptionUtils;

import butterknife.BindView;
import butterknife.OnClick;

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
    private String provinceName="",cityName="",areaName="";
    private AddressModel.AddressBean item;
    private CityWheelUtils cityWheelUtils;




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
        super.initData();
        setTitle("编辑地址");
        cityWheelUtils=new CityWheelUtils();
        etConsigneeName.setText(EmptyUtils.strEmpty(item.getLinkman()));
        tvAddress.setText(item.getAddr1Name() + item.getAddr2Name() + item.getAddr3Name());
        etPhone.setText(EmptyUtils.strEmpty(item.getPhone()));
        etDetailAddress.setText(EmptyUtils.strEmpty(item.getAddress()));
        etPostalCode.setText(EmptyUtils.strEmpty(item.getCode()));

        provinceId = item.getAddr1();
        provinceName=item.getAddr1Name();
        cityId = item.getAddr2();
        cityName=item.getAddr2Name();
        areaId = item.getAddr3();
        areaName=item.getAddr3Name();

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

    @OnClick({R.id.tv_address, R.id.stb_sure,R.id.stb_one_key_filling})
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
                    public void onchooseCity(int provincePos, DistrictListModel.DistrictsBean provinceItemData,
                                             int cityPos, DistrictListModel.DistrictsBean cityItemData,
                                             int areaPos, DistrictListModel.DistrictsBean areaItemData) {
                        provinceId = provinceItemData.getAddr();
                        provinceName=provinceItemData.getName();
                        cityId = cityItemData.getAddr();
                        cityName=cityItemData.getName();
                        areaId = areaItemData.getAddr();
                        areaName=areaItemData.getName();

                        tvAddress.setText(provinceItemData.getName() + cityItemData.getName() + areaItemData.getName());
                    }
                });
                getAddressData("1", "");
                break;
            case R.id.stb_sure:
                sureUpdate();
                break;
            case R.id.stb_one_key_filling:
                if (EmptyUtils.isEmpty(etOneKeyFilling.getText().toString())){
                    return;
                }
                showWaitDialog();
                oneKeyFilling();
                break;
        }
    }

    //获取地区
    private void getAddressData(final String level, String id) {
        DistrictListModel.sendDistrictListRequest(TAG, id, level, new CustomerJsonCallBack<DistrictListModel>() {
            @Override
            public void onRequestError(DistrictListModel returnData, String msg) {
                showShortToast(msg);
            }

            @Override
            public void onRequestSuccess(DistrictListModel returnData) {
                if ("1".equals(level)) {
                    cityWheelUtils.setProvinceData(returnData.getDistricts());
                    if (!returnData.getDistricts().isEmpty()) {
                        getAddressData("2", returnData.getDistricts().get(0).getAddr());
                    }
                } else if ("2".equals(level)) {
                    cityWheelUtils.setCityData(returnData.getDistricts());
                    if (!returnData.getDistricts().isEmpty()) {
                        getAddressData("3", returnData.getDistricts().get(0).getAddr());
                    }
                } else if ("3".equals(level)) {
                    cityWheelUtils.setAreaData(returnData.getDistricts());
                }
            }
        });
    }

    //一键识别
    private void oneKeyFilling() {
        OneKeyFillingModel.sendOneKeyFillingRequest(TAG,etOneKeyFilling.getText().toString().trim().replaceAll("\n",""),
                new CustomerJsonCallBack<OneKeyFillingModel>() {
                    @Override
                    public void onRequestError(OneKeyFillingModel returnData, String msg) {
                        hideWaitDialog();
                        showShortToast(msg);
                    }

                    @Override
                    public void onRequestSuccess(OneKeyFillingModel returnData) {
                        hideWaitDialog();
                        if (returnData.getData()!=null&&!returnData.getData().isEmpty()){
                            OneKeyFillingModel.DataBean oneKeyFillingInfo = returnData.getData().get(0);
                            provinceId = oneKeyFillingInfo.getProvinceId();
                            provinceName=oneKeyFillingInfo.getProvinceName();
                            cityId = oneKeyFillingInfo.getCityId();
                            cityName=oneKeyFillingInfo.getCityName();
                            areaId = oneKeyFillingInfo.getCountyId();
                            areaName=oneKeyFillingInfo.getCountyName();

                            etConsigneeName.setText(TextOptionUtils.getInstance().subLength(EmptyUtils.strEmpty(oneKeyFillingInfo.getName()),8));
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
//        BaseModel.sendDeleteAddressRequest(TAG, item.getId(), new CustomerJsonCallBack<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                showShortToast(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                showShortToast("删除成功");
//                setResult(RESULT_OK);
//                goFinish();
//            }
//        });
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

        showWaitDialog();
//        BaseModel.sendUpdateAddressRequest(TAG,item.getId() ,etConsigneeName.getText().toString().trim(), provinceId, cityId, areaId,
//                etPhone.getText().toString().trim(), etDetailAddress.getText().toString().trim(), etPostalCode.getText().toString().trim(),
//                isDefault, new CustomerJsonCallBack<BaseModel>() {
//                    @Override
//                    public void onRequestError(BaseModel returnData, String msg) {
//                        hideWaitDialog();
//                        showShortToast(msg);
//                    }
//
//                    @Override
//                    public void onRequestSuccess(BaseModel returnData) {
//                        hideWaitDialog();
//                        showShortToast("更新地址成功");
//                        AddressModel.AddressBean addressBean=new AddressModel.AddressBean(provinceId,provinceName,cityId,cityName,areaId,areaName,
//                                etDetailAddress.getText().toString().trim(),etPostalCode.getText().toString().trim(),item.getId(),
//                                etConsigneeName.getText().toString().trim(),etPhone.getText().toString().trim(),sbDefault.isChecked() ? "00B" : "00A","");
//
//
//                        Intent intent=new Intent();
//                        intent.putExtra("seleteAddress",addressBean);
//                        setResult(RESULT_OK,intent);
//                        goFinish();
//                    }
//                }
//        );
    }

    public static void newIntance(Activity activity, AddressModel.AddressBean item, int requestCode) {
        Intent intent = new Intent(activity, UpdateAddressActivity.class);
        intent.putExtra("item", item);
        activity.startActivityForResult(intent, requestCode);
    }
}
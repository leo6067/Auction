package com.leo.auction.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.customerDialog.BottomDialog;
import com.aten.compiler.widget.customerDialog.BottomDialogUtils;
import com.aten.compiler.widget.wheel.WheelView;
import com.leo.auction.ui.main.mine.model.DistrictListModel;


import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.widget.customerDialog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/10/29
 * 描    述：城市选择wheel
 * ================================================
 */
public class CityWheelUtils {
    private BottomDialogUtils bottomDialogUtils;
    private String cityInfo="";
    private WheelView<DistrictListModel.DataBean> wvProvince,wvCity,wvArea;

    //显示城市选择框
    public void showCityWheel(Context context, final CityWheelClickListener cityWheelClickListener){
        View cityWheelView= View.inflate(context, com.aten.compiler.R.layout.layout_city_wheel_choose2,null);
        ArrayList<WheelView> wvs=new ArrayList<>();
        wvProvince=(WheelView)cityWheelView.findViewById(com.aten.compiler.R.id.wv_province);
        wvCity=(WheelView)cityWheelView.findViewById(com.aten.compiler.R.id.wv_city);
        wvArea=(WheelView)cityWheelView.findViewById(com.aten.compiler.R.id.wv_area);
        wvs.add(wvProvince);wvs.add(wvCity);wvs.add(wvArea);
        setWvOption(context,wvs);

        wvProvince.setCurvedArcDirection(WheelView.CURVED_ARC_DIRECTION_LEFT);
        wvProvince.setCurvedArcDirectionFactor(0.65f);
        wvArea.setCurvedArcDirection(WheelView.CURVED_ARC_DIRECTION_RIGHT);
        wvArea.setCurvedArcDirectionFactor(0.65f);

        wvProvince.setOnItemSelectedListener(new WheelView.OnItemSelectedListener<DistrictListModel.DataBean>() {
            @Override
            public void onItemSelected(WheelView<DistrictListModel.DataBean> wheelView, DistrictListModel.DataBean data, int position) {
                cityWheelClickListener.onCity(data.getId());
            }
        });

        wvCity.setOnItemSelectedListener(new WheelView.OnItemSelectedListener<DistrictListModel.DataBean>() {
            @Override
            public void onItemSelected(WheelView<DistrictListModel.DataBean> wheelView, DistrictListModel.DataBean data, int position) {
                cityWheelClickListener.onArea(data.getId());
            }
        });

        bottomDialogUtils=new BottomDialogUtils(context);
        bottomDialogUtils.showBottomDialogDialog(cityWheelView, "地址", new BottomDialogUtils.BottomClickListener() {
            @Override
            public void onSure(BottomDialog bottomDialog) {
                cityWheelClickListener.onchooseCity(wvProvince==null?null:wvProvince.getSelectedItemPosition(),wvProvince==null?null:wvProvince.getSelectedItemData(),
                        wvCity==null?null:wvCity.getSelectedItemPosition(),wvCity==null?null:wvCity.getSelectedItemData(),
                        wvArea==null?null:wvArea.getSelectedItemPosition(),wvArea==null?null:wvArea.getSelectedItemData());
            }

            @Override
            public void onCancle(BottomDialog bottomDialog) {}
        });
    }

    //设置wheelview的属性
    private void setWvOption(Context context,ArrayList<WheelView> wvs){
        for (WheelView wv : wvs) {
            wv.setVisibleItems(7);
            wv.setAutoFitTextSize(true);
            wv.setSelectedRectColor(Color.parseColor("#1e1e1e"));
            wv.setNormalItemTextColor(Color.parseColor("#808080"));
            wv.setTextSize(18f,true);
            wv.setShowDivider(true);
            wv.setDividerType(WheelView.DIVIDER_TYPE_FILL);
            wv.setDividerColor(context.getResources().getColor(com.aten.compiler.R.color.line02));
            wv.setDividerPaddingForWrap(10, true);
            wv.setDividerHeight(0.5f,true);
            wv.setResetSelectedPosition(true);
        }
    }

    //设置省的数据
    public void setProvinceData(List<DistrictListModel.DataBean> provinceDatas){
        if (wvProvince==null){
            ToastUtils.showShort("数据有误,请重新打开页面！");
        }else {
            wvProvince.setData(provinceDatas);
        }
    }
    //设置市的数据
    public void setCityData(List<DistrictListModel.DataBean> cityDatas){
        if (wvCity==null){
            ToastUtils.showShort("数据有误,请重新打开页面！");
        }else {
            wvCity.setData(cityDatas);
        }
    }
    //设置区的数据
    public void setAreaData(List<DistrictListModel.DataBean> areaDatas){
        if (wvArea==null){
            ToastUtils.showShort("数据有误,请重新打开页面！");
        }else {
            wvArea.setData(areaDatas);
        }
    }

//    //设置省的初始数据
//    public void setProvinceData(String cityId,List<AddressCityModel.DataBean> areaDatas){
//        int cityPos=-1;
//        for (int i = 0; i < areaDatas.size(); i++) {
//            if (cityId.equals(areaDatas.get(i).getAreaId())){
//                cityPos=i;
//            }
//        }
//
//        if (cityPos!=-1&&wvProvince!=null){
//            wvProvince.setSelectedItemPosition(cityPos,true);
//        }
//    }
//
//    //设置记忆选中的数据
//    public void setSelectPos(int provincePos,int cityPos,int areaPos){
//        wvProvince.setSelectedItemPosition(provincePos);
//        wvCity.setSelectedItemPosition(cityPos);
//        wvArea.setSelectedItemPosition(areaPos);
//    }
//
//    //设置市的初始数据
//    public void setCityData(String cityId,List<AddressCityModel.DataBean> areaDatas){
//        int cityPos=-1;
//        for (int i = 0; i < areaDatas.size(); i++) {
//            if (cityId.equals(areaDatas.get(i).getAreaId())){
//                cityPos=i;
//            }
//        }
//
//        if (cityPos!=-1&&wvCity!=null){
//            wvCity.setSelectedItemPosition(cityPos,true);
//        }
//    }
//
//    //设置区的数据
//    public void setAreaData(String cityId,List<AddressCityModel.DataBean> areaDatas){
//        int cityPos=-1;
//        for (int i = 0; i < areaDatas.size(); i++) {
//            if (cityId.equals(areaDatas.get(i).getAreaId())){
//                cityPos=i;
//            }
//        }
//
//        if (cityPos!=-1&&wvArea!=null){
//            wvArea.setSelectedItemPosition(cityPos,true);
//        }
//    }

    //隐藏城市选择框
    public void dissCityWheel(){
        if (bottomDialogUtils!=null){
            bottomDialogUtils.dismissBottomDialogDialog();
        }
    }

    public void setProvinceSelectItem(int position){
        if (wvProvince!=null){
            wvProvince.setSelectedItemPosition(position, true, 1000);
        }
    }

    public void setCitySelectItem(int position){
        if (wvCity!=null){
            wvCity.setSelectedItemPosition(position, true, 1000);
        }
    }

    public void setAreaSelectItem(int position ){
        if (wvArea!=null){
            wvArea.setSelectedItemPosition(position, true, 1000);
        }
    }

    public interface CityWheelClickListener{
        void onCity(String provinceId);//获取城市以及区的数据
        void onArea(String cityId);//获取区的数据
        void onchooseCity(int provincePos, DistrictListModel.DataBean provinceItemData,
                          int cityPos, DistrictListModel.DataBean cityItemData,
                          int areaPos, DistrictListModel.DataBean areaItemData);
    }
}
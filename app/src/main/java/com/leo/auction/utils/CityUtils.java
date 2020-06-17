package com.leo.auction.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.leo.auction.utils.city.CitytsJson;
import com.leo.auction.utils.city.cardbean.JsonBean;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by qwe on 2017/6/6.
 */

public class CityUtils {
    private static ArrayList<CitytsJson.Data> options1;
    private static ArrayList<ArrayList<String>> options2;
    private static ArrayList<ArrayList<ArrayList<String>>> options3;

    public static void initJsonData(ArrayList<CitytsJson.Data> options1Items,
                                    ArrayList<ArrayList<String>> options2Items,ArrayList<ArrayList<ArrayList<String>>> options3Items) {//解析数据
        options1 = options1Items;
        options2 = options2Items;
        options3 = options3Items;
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
//        String JsonDataa = getJson(context, "province_city.json");//获取assets目录下的json文件数据

//        ArrayList<JsonBean> jsonBean = parseData(JsonDataa);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        ArrayList<CitytsJson.Data>  jsonBean = options1 ;
        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCity().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCity().get(c).getRegionName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCity().get(c).getDistrict() == null
                        ||jsonBean.get(i).getCity().get(c).getDistrict().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCity().get(c).getDistrict().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCity().get(c).getDistrict().get(d).getDistrictName();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2.add(CityList);

            /**
             * 添加地区数据
             */
            options3.add(Province_AreaList);
        }
    }

    private static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static ArrayList<JsonBean> getAllCityData(String decrypt) {
        JSONArray jsonArray = JSONArray.parseArray(decrypt);
        ArrayList<JsonBean> detail = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            JsonBean entity = (JsonBean) JSONArray.parseArray(object.toString(),JsonBean.class);
            detail.add(entity);

        }
        return detail;
    }
    public static ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            org.json.JSONArray data = new org.json.JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static ArrayList<CitytsJson.Data> getOptions1Items(){
        return options1;
    }
    public static ArrayList<ArrayList<String>> getOptions2Items(){
        return options2;
    }
    public static ArrayList<ArrayList<ArrayList<String>>> getOptions3Items(){
        return options3;
    }
}

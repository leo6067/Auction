package com.leo.auction.utils.city;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by Leo on 2018/6/25.
 */

public class CitytsJson   {


        private int state;
        private String message;
        private List<Data> data;
        public void setState(int state) {
            this.state = state;
        }
        public int getState() {
            return state;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
        public List<Data> getData() {
            return data;
        }

    @Override
    public String toString() {
        return "CitytsJson{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }







    public static class Data  implements IPickerViewData {

        private String name;
        private List<City> city;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setCity(List<City> city) {
            this.city = city;
        }
        public List<City> getCity() {
            return city;
        }


        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", city=" + city +
                    '}';
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }








    public static class City {

        private String cityid;
        private String RegionName;
        private List<District> district;
        public void setCityid(String cityid) {
            this.cityid = cityid;
        }
        public String getCityid() {
            return cityid;
        }

        public void setRegionName(String RegionName) {
            this.RegionName = RegionName;
        }
        public String getRegionName() {
            return RegionName;
        }

        public void setDistrict(List<District> district) {
            this.district = district;
        }
        public List<District> getDistrict() {
            return district;
        }


        @Override
        public String toString() {
            return "City{" +
                    "cityid='" + cityid + '\'' +
                    ", RegionName='" + RegionName + '\'' +
                    ", district=" + district +
                    '}';
        }
    }











    public static class District {

        private String DistrictName;
        private String ID;
        public void setDistrictName(String DistrictName) {
            this.DistrictName = DistrictName;
        }
        public String getDistrictName() {
            return DistrictName;
        }

        public void setID(String ID) {
            this.ID = ID;
        }
        public String getID() {
            return ID;
        }


        @Override
        public String toString() {
            return "District{" +
                    "DistrictName='" + DistrictName + '\'' +
                    ", ID='" + ID + '\'' +
                    '}';
        }
    }











}

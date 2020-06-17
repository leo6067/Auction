package com.leo.auction.model.home;

import java.util.List;

/**
 * Created by Leo on 2018/7/9.
 */

public class CollectDistrictJson {
    /**
     * state : 0
     * message :
     * data : [[{"DistrictId":607,"DistrictName":"集美"},{"DistrictId":608,"DistrictName":"海沧"},{"DistrictId":609,"DistrictName":"思明"},{"DistrictId":610,"DistrictName":"湖里"},{"DistrictId":611,"DistrictName":"同安"},{"DistrictId":612,"DistrictName":"翔安"},{"DistrictId":1949,"DistrictName":"厦门周边"},{"DistrictId":18345,"DistrictName":"杏林"}]]
     */

    private int state;
    private String message;
    private List<List<DataBean>> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * DistrictId : 607
         * DistrictName : 集美
         */

        private int DistrictId;
        private String DistrictName;

        public int getDistrictId() {
            return DistrictId;
        }

        public void setDistrictId(int DistrictId) {
            this.DistrictId = DistrictId;
        }

        public String getDistrictName() {
            return DistrictName;
        }

        public void setDistrictName(String DistrictName) {
            this.DistrictName = DistrictName;
        }
    }
}

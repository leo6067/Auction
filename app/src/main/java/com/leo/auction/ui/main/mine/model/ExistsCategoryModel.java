package com.leo.auction.ui.main.mine.model;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;


import java.util.List;

public class ExistsCategoryModel {
    /**
     * data : [{"categoryId":"二级分类标识","name":"分类名称","icon":"图片"}]
     * result : {"success":true,"message":"请求成功"}
     */

    private ResultBean result;
    private List<DataBean> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * success : true
         * message : 请求成功
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * categoryId : 二级分类标识
         * name : 分类名称
         * icon : 图片
         */

        private String categoryId;
        private String name;
        private String icon;
        private boolean select;

        public DataBean(String categoryId, String name, String icon, boolean select) {
            this.categoryId = categoryId;
            this.name = name;
            this.icon = icon;
            this.select = select;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }

    public static void sendExistsCategoryRequest(final String TAG, String shopUri, String status,
                                                 final CustomerJsonCallBack<ExistsCategoryModel> callback) {
        JSONObject params=new JSONObject();
        params.put("shopUri",shopUri);
        params.put("status",status);//店铺ID
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_SUPPLIER_EXISTS_CATEGORY_URL, params.toJSONString(), callback);
    }
}

package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSONObject;
import com.leo.auction.net.CustomerJsonCallBack;

import java.util.List;

public class CommodityManagementModel {
    /**
     * data : [{"id":"1","goodsTitle":"商品标题","url":"图片地址","createTime":"","status":"状态","price":"价格","agentPrice":"代理价","stock":"库存","sales":"销量"}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 1
         * goodsTitle : 商品标题
         * url : 图片地址
         * createTime :
         * status : 状态
         * price : 价格
         * agentPrice : 代理价
         * stock : 库存
         * sales : 销量
         *
         * 新增。供货数量  supNum
         */

        private String id;
        private String goodsTitle;
        private String url;
        private String createTime;
        private String status;
        private String price;
        private String agentPrice;
        private int stock;
        private String sales;
        private int supNum;
        private int borrowNum;

        public int getBorrowNum() {
            return borrowNum;
        }

        public void setBorrowNum(int borrowNum) {
            this.borrowNum = borrowNum;
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            id = in.readString();
            goodsTitle = in.readString();
            url = in.readString();
            createTime = in.readString();
            status = in.readString();
            price = in.readString();
            agentPrice = in.readString();
            stock = in.readInt();
            sales = in.readString();
            supNum = in.readInt();
            borrowNum = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getSupNum() {
            return supNum;
        }

        public void setSupNum(int supNum) {
            this.supNum = supNum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsTitle() {
            return goodsTitle;
        }

        public void setGoodsTitle(String goodsTitle) {
            this.goodsTitle = goodsTitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAgentPrice() {
            return agentPrice;
        }

        public void setAgentPrice(String agentPrice) {
            this.agentPrice = agentPrice;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(goodsTitle);
            dest.writeString(url);
            dest.writeString(createTime);
            dest.writeString(status);
            dest.writeString(price);
            dest.writeString(agentPrice);
            dest.writeInt(stock);
            dest.writeString(sales);
            dest.writeInt(supNum);
            dest.writeInt(borrowNum);
        }
    }

    public static void sendCommodityManagementRequest(final String TAG, String pageNum, String sort, String sortField,
                                                      String shopUri, String status, String keyword, String categoryId,
                                                      final CustomerJsonCallBack<CommodityManagementModel> callback) {
        JSONObject params=new JSONObject();
        JSONObject value=new JSONObject();
        value.put("pageNum",pageNum);
        value.put("pageSize","20");
        value.put("sort",sort);//0-升序 1-降序
        value.put("sortField",sortField);//排序字段   时间-intime  销量-sales  价格-price 供货价-agentPrice 库存-stock
        params.put("page",value);
        params.put("shopUri",shopUri);//店铺ID
        params.put("status",status);//商品状态 00A-上架 00B-下架  00C-草稿箱
        params.put("keyword",keyword);//商品标题|商品描述
        params.put("categoryId",categoryId);//分类标识
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_SUPPLIER_LIST_URL, params.toJSONString(), callback);
    }


    public static void sendCommodityManagementRequestV(final String TAG, String pageNum, String sort, String sortField,
                                                      String shopUri, String status, String keyword, String categoryId,
                                                      final CustomerJsonCallBack<CommodityManagementModel> callback) {
        JSONObject params=new JSONObject();
        JSONObject value=new JSONObject();
        value.put("pageNum",pageNum);
        value.put("pageSize","20");
        value.put("sort",sort);//0-升序 1-降序
        value.put("sortField",sortField);//排序字段   时间-intime  销量-sales  价格-price 供货价-agentPrice 库存-stock
        params.put("page",value);
        params.put("shopUri",shopUri);//店铺ID
        params.put("status",status);//商品状态 00A-上架 00B-下架  00C-草稿箱
        params.put("keyword",keyword);//商品标题|商品描述
        params.put("categoryId",categoryId);//分类标识
        params.put("freeShip",0);//分类标识
//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_SUPPLIER_LIST_URL, params.toJSONString(), callback);
    }
}

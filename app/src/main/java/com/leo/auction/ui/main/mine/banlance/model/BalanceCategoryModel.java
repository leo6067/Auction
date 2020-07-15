package com.leo.auction.ui.main.mine.banlance.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.banlance.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/11
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class BalanceCategoryModel {


    /**
     * data : [{"children":[{"id":1,"name":"交易"},{"id":2,"name":"赔偿"},{"id":512,"name":"退还"},{"id":2048,"name":"充值"}],"id":2563,"name":"收款"},{"children":[{"id":16,"name":"提现中"},{"id":32,"name":"已提现"}],"id":48,"name":"提现"},{"children":[{"id":64,"name":"购物"},{"id":128,"name":"赔偿"},{"id":256,"name":"退款"},{"id":1024,"name":"缴纳保证金"}],"id":1472,"name":"支出"},{"children":[{"id":4,"name":"冻结"},{"id":8,"name":"冻结"}],"id":12,"name":"冻结"}]
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592201807339}
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
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1592201807339
         */

        private String code;
        private String message;
        private boolean success;
        private long timestamp;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class DataBean {
        /**
         * children : [{"id":1,"name":"交易"},{"id":2,"name":"赔偿"},{"id":512,"name":"退还"},{"id":2048,"name":"充值"}]
         * id : 2563
         * name : 收款
         */

        private String id;
        private String name;
        private ArrayList<ChildrenBean> children;


        public DataBean() {
        }

        public DataBean(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements Parcelable {
            /**
             * id : 1
             * name : 交易
             */

            private String id;
            private String name;
            private boolean isSelect;


            public ChildrenBean() {
            }

            protected ChildrenBean(Parcel in) {
                id = in.readString();
                name = in.readString();
                isSelect = in.readByte() != 0;
            }

            public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
                @Override
                public ChildrenBean createFromParcel(Parcel in) {
                    return new ChildrenBean(in);
                }

                @Override
                public ChildrenBean[] newArray(int size) {
                    return new ChildrenBean[size];
                }
            };

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public ChildrenBean(String id, String name, boolean isSelect) {
                this.id = id;
                this.name = name;
                this.isSelect = isSelect;
            }


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(name);
                dest.writeByte((byte) (isSelect ? 1 : 0));
            }
        }
    }

    public static void httpBalanceCateMoedel(HttpRequest.HttpCallback httpCallback){

        HashMap<String, String> hashMap = new HashMap<>();

        HttpRequest.httpGetString(Constants.Api.BALANCE_CATEGORY_URL, hashMap,httpCallback);

    }
}

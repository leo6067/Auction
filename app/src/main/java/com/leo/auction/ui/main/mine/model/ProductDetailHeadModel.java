package com.leo.auction.ui.main.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.leo.auction.net.CustomerJsonCallBack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.ui.home.model
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/31 0031
 * 描    述：
 * ================================================
 */
public class ProductDetailHeadModel {
    /**
     * info : {"agentPrice":"8.80","attributes":[{"id":71,"option":1,"tab":"","title":"种类","value":"核桃"}],"categoryId":33,"categoryParentId":31,"comment":"","commentNum":0,"content":"野生狼牙山新籽（核桃）闷尖四座楼 产地：狼牙山 ，尺寸：36-42mm随机发 上色后颜色艳丽，油性高，皮质优秀，样子讨巧，玩过野狼的都知道，这厮极难配对，幸亏大家只爱它的皮质，手感无敌，就是个润，小石头一样坠手。\n 【编号】0796","freeShip":true,"freight":"0.00","id":755,"images":["https://file.taojianlou.com/goods/U94kMQDwGn6VsmyehlqjA5kEb0ghE0IN.jpg?image=640,640","https://file.taojianlou.com/goods/kGr4tt5x1XLb4vC0HkWrpDjANJboojQd.jpg?image=640,640","https://file.taojianlou.com/goods/ee9i6WSusG2l56S2EvgIFKBG8LhjQvBG.jpg?image=640,640","https://file.taojianlou.com/goods/uYPHxd4BEIT7Sh7fYGDnpiIbfNy2CEYG.jpg?image=640,640","https://file.taojianlou.com/goods/ezfH8jFr0k3IIgVt2J35J10HCfvz2ei7.jpg?image=640,640","https://file.taojianlou.com/goods/RWmYima3FlyuVWQPItoJ2PZdYLiFivWc.jpg?image=640,640","https://file.taojianlou.com/goods/xHfhTS09dD0G3Mb2JCwpd2pjhEGi95VJ.jpg?image=640,640","https://file.taojianlou.com/goods/IulQAEr3vfxldVr5nMZ6J1TS9OLTGLCI.jpg?image=640,640","https://file.taojianlou.com/goods/dI0crFR6bCNW7TVXjibS15abOMEXdFzM.jpg?image=640,640"],"price":"9.90","saleCode":"1909151752487461","shopUri":"1908101023RZc9JC","status":"00A","stock":991,"supplierId":13,"title":"【超级购秒杀9.9包邮】野生闷尖四座楼","toPay":false,"videos":["",""]}
     * result : {"message":"请求成功","success":true}
     */

    private InfoBean info;
    private ResultBean result;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class InfoBean implements Parcelable {
        /**
         * agentPrice : 8.80
         * attributes : [{"id":71,"option":1,"tab":"","title":"种类","value":"核桃"}]
         * categoryId : 33
         * categoryParentId : 31
         * comment :
         * commentNum : 0
         * content : 野生狼牙山新籽（核桃）闷尖四座楼 产地：狼牙山 ，尺寸：36-42mm随机发 上色后颜色艳丽，油性高，皮质优秀，样子讨巧，玩过野狼的都知道，这厮极难配对，幸亏大家只爱它的皮质，手感无敌，就是个润，小石头一样坠手。
         【编号】0796
         * freeShip : true
         * freight : 0.00
         * id : 755
         * images : ["https://file.taojianlou.com/goods/U94kMQDwGn6VsmyehlqjA5kEb0ghE0IN.jpg?image=640,640","https://file.taojianlou.com/goods/kGr4tt5x1XLb4vC0HkWrpDjANJboojQd.jpg?image=640,640","https://file.taojianlou.com/goods/ee9i6WSusG2l56S2EvgIFKBG8LhjQvBG.jpg?image=640,640","https://file.taojianlou.com/goods/uYPHxd4BEIT7Sh7fYGDnpiIbfNy2CEYG.jpg?image=640,640","https://file.taojianlou.com/goods/ezfH8jFr0k3IIgVt2J35J10HCfvz2ei7.jpg?image=640,640","https://file.taojianlou.com/goods/RWmYima3FlyuVWQPItoJ2PZdYLiFivWc.jpg?image=640,640","https://file.taojianlou.com/goods/xHfhTS09dD0G3Mb2JCwpd2pjhEGi95VJ.jpg?image=640,640","https://file.taojianlou.com/goods/IulQAEr3vfxldVr5nMZ6J1TS9OLTGLCI.jpg?image=640,640","https://file.taojianlou.com/goods/dI0crFR6bCNW7TVXjibS15abOMEXdFzM.jpg?image=640,640"]
         * price : 9.90
         * saleCode : 1909151752487461
         * shopUri : 1908101023RZc9JC
         * status : 00A
         * stock : 991
         * supplierId : 13
         * title : 【超级购秒杀9.9包邮】野生闷尖四座楼
         * toPay : false
         * videos : ["",""]
         *
         *
         *
         */

        private String agentPrice;
        private String categoryId;
        private String categoryParentId;
        private String comment;
        private int commentNum;
        private String content;
        private boolean freeShip;
        private String freight;
        private int id;
        private String price;
        private String saleCode;
        private String shopUri;
        private String status;
        private int stock;
        private String supplierId;
        private String title;
        private boolean toPay;
        private List<AttributesBean> attributes;
        private ArrayList<String> images=new ArrayList<>();
        private List<String> videos;
        private String cutPic;

        private String heat;
        private boolean thresholdEnable;
        private String thresholdValue;
        private String avgResponseConsignTime;

        private String sellerAccountId;//商家userid
        private String nickname;//商家昵称
        private String headimg;;//商家头像
        private boolean dialogConnect;//是否环信注册
        private String categoryName;
        private String categoryParentName;  //'一级分类名称',
        private String originGoodsId;  //'原商品标识'
        private String goodsType;  //goodsType :'1-普通商品 2-直播借货'

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getCategoryParentName() {
            return categoryParentName;
        }

        public void setCategoryParentName(String categoryParentName) {
            this.categoryParentName = categoryParentName;
        }

        public String getOriginGoodsId() {
            return originGoodsId;
        }

        public void setOriginGoodsId(String originGoodsId) {
            this.originGoodsId = originGoodsId;
        }

        public String getAgentPrice() {
            return agentPrice;
        }

        public void setAgentPrice(String agentPrice) {
            this.agentPrice = agentPrice;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryParentId() {
            return categoryParentId;
        }

        public void setCategoryParentId(String categoryParentId) {
            this.categoryParentId = categoryParentId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isFreeShip() {
            return freeShip;
        }

        public void setFreeShip(boolean freeShip) {
            this.freeShip = freeShip;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaleCode() {
            return saleCode;
        }

        public void setSaleCode(String saleCode) {
            this.saleCode = saleCode;
        }

        public String getShopUri() {
            return shopUri;
        }

        public void setShopUri(String shopUri) {
            this.shopUri = shopUri;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isToPay() {
            return toPay;
        }

        public void setToPay(boolean toPay) {
            this.toPay = toPay;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }

        public List<String> getVideos() {
            return videos;
        }

        public void setVideos(List<String> videos) {
            this.videos = videos;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getCutPic() {
            return cutPic;
        }

        public void setCutPic(String cutPic) {
            this.cutPic = cutPic;
        }

        public String getHeat() {
            return heat;
        }

        public void setHeat(String heat) {
            this.heat = heat;
        }

        public boolean isThresholdEnable() {
            return thresholdEnable;
        }

        public void setThresholdEnable(boolean thresholdEnable) {
            this.thresholdEnable = thresholdEnable;
        }

        public String getThresholdValue() {
            return thresholdValue;
        }

        public void setThresholdValue(String thresholdValue) {
            this.thresholdValue = thresholdValue;
        }

        public String getAvgResponseConsignTime() {
            return avgResponseConsignTime;
        }

        public void setAvgResponseConsignTime(String avgResponseConsignTime) {
            this.avgResponseConsignTime = avgResponseConsignTime;
        }

        public String getSellerAccountId() {
            return sellerAccountId;
        }

        public void setSellerAccountId(String sellerAccountId) {
            this.sellerAccountId = sellerAccountId;
        }

        public boolean isDialogConnect() {
            return dialogConnect;
        }

        public void setDialogConnect(boolean dialogConnect) {
            this.dialogConnect = dialogConnect;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public static class AttributesBean implements Parcelable {
            /**
             * id : 71
             * option : 1
             * tab :
             * title : 种类
             * value : 核桃
             */

            private String id;
            private int option;
            private String tab;
            private String title;
            private String value;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getOption() {
                return option;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public String getTab() {
                return tab;
            }

            public void setTab(String tab) {
                this.tab = tab;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeInt(this.option);
                dest.writeString(this.tab);
                dest.writeString(this.title);
                dest.writeString(this.value);
            }

            public AttributesBean() {
            }

            protected AttributesBean(Parcel in) {
                this.id = in.readString();
                this.option = in.readInt();
                this.tab = in.readString();
                this.title = in.readString();
                this.value = in.readString();
            }

            public static final Creator<AttributesBean> CREATOR = new Creator<AttributesBean>() {
                @Override
                public AttributesBean createFromParcel(Parcel source) {
                    return new AttributesBean(source);
                }

                @Override
                public AttributesBean[] newArray(int size) {
                    return new AttributesBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.agentPrice);
            dest.writeString(this.categoryId);
            dest.writeString(this.categoryParentId);
            dest.writeString(this.comment);
            dest.writeInt(this.commentNum);
            dest.writeString(this.content);
            dest.writeByte(this.freeShip ? (byte) 1 : (byte) 0);
            dest.writeString(this.freight);
            dest.writeInt(this.id);
            dest.writeString(this.price);
            dest.writeString(this.saleCode);
            dest.writeString(this.shopUri);
            dest.writeString(this.status);
            dest.writeInt(this.stock);
            dest.writeString(this.supplierId);
            dest.writeString(this.title);
            dest.writeByte(this.toPay ? (byte) 1 : (byte) 0);
            dest.writeList(this.attributes);
            dest.writeStringList(this.images);
            dest.writeStringList(this.videos);
            dest.writeString(this.nickname);
            dest.writeString(this.headimg);
            dest.writeString(this.cutPic);

            dest.writeString(this.heat);
            dest.writeByte(this.thresholdEnable ? (byte) 1 : (byte) 0);
            dest.writeString(this.thresholdValue);
            dest.writeString(this.avgResponseConsignTime);
            dest.writeString(this.sellerAccountId);
            dest.writeByte(this.dialogConnect ? (byte) 1 : (byte) 0);
            dest.writeString(this.categoryName);
            dest.writeString(this.categoryParentName);
            dest.writeString(this.originGoodsId);
            dest.writeString(this.goodsType);
        }

        public InfoBean() {
        }

        protected InfoBean(Parcel in) {
            this.agentPrice = in.readString();
            this.categoryId = in.readString();
            this.categoryParentId = in.readString();
            this.comment = in.readString();
            this.commentNum = in.readInt();
            this.content = in.readString();
            this.freeShip = in.readByte() != 0;
            this.freight = in.readString();
            this.id = in.readInt();
            this.price = in.readString();
            this.saleCode = in.readString();
            this.shopUri = in.readString();
            this.status = in.readString();
            this.stock = in.readInt();
            this.supplierId = in.readString();
            this.title = in.readString();
            this.toPay = in.readByte() != 0;
            this.attributes = new ArrayList<AttributesBean>();
            in.readList(this.attributes, AttributesBean.class.getClassLoader());
            this.images = in.createStringArrayList();
            this.videos = in.createStringArrayList();
            this.nickname = in.readString();
            this.headimg = in.readString();
            this.cutPic = in.readString();

            this.heat = in.readString();
            this.thresholdEnable = in.readByte() != 0;
            this.thresholdValue = in.readString();
            this.avgResponseConsignTime = in.readString();
            this.sellerAccountId=in.readString();
            this.dialogConnect= in.readByte() != 0;
            this.categoryName=in.readString();
            this.categoryParentName=in.readString();
            this.originGoodsId=in.readString();
            this.goodsType=in.readString();

        }

        public static final Creator<InfoBean> CREATOR = new Creator<InfoBean>() {
            @Override
            public InfoBean createFromParcel(Parcel source) {
                return new InfoBean(source);
            }

            @Override
            public InfoBean[] newArray(int size) {
                return new InfoBean[size];
            }
        };
    }

    public static class ResultBean {
        /**
         * message : 请求成功
         * success : true
         */

        private String message;
        private boolean success;

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
    }

    public static void sendProductDetailHeadRequest(final String TAG, String id, final CustomerJsonCallBack<ProductDetailHeadModel> callback) {
        HashMap<String,String> params=new HashMap<>();
        params.put("id",id);

//        JsonRequestData.requesNetWork(TAG, Constants_Api.Api.HOMEPAGE_GOODS_DESC_URL, JSON.toJSONString(params), callback);
    }
}

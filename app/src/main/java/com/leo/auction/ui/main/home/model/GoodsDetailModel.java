package com.leo.auction.ui.main.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/22
 * 描    述： 上拍商品详情
 * 修    改：
 * ===============================================
 */
public class GoodsDetailModel {


    /**
     * data : {"attributes":[{"id":168,"length":18,"option":1,"tab":"","tags":[{"name":"茶具","tagId":69},{"name":"酒具","tagId":280}],"title":"类别","value":"酒具"}],"bid":[{"bidPrice":"1","createTime":1592807645000,"headImg":"https://file.taojianlou.com/ut/user/1592376005448.png","level":12,"nickname":"小***包","userAccountId":72}],"bidNum":1,"categoryId":62,"categoryName":"茶器","collect":false,"content":"你也不要把门坏你就不要给我","createTime":1592804774000,"currentPrice":"1","delayTime":300,"distributeType":2,"follow":false,"images":["https://file.taojianlou.com/ut/product/1592804761618.png?image=800,800","https://file.taojianlou.com/ut/product/1592804761955.png?image=800,800","https://file.taojianlou.com/ut/product/1592804762212.png?image=800,800","https://file.taojianlou.com/ut/product/1592804762356.png?image=800,800","https://file.taojianlou.com/ut/product/1592804762497.png?image=800,800","https://file.taojianlou.com/ut/product/1592804763093.png?image=800,800","https://file.taojianlou.com/ut/product/1592804764154.png?image=800,800","https://file.taojianlou.com/ut/product/1592804764437.png?image=800,800"],"interceptTime":1592985900000,"markupRange":"1","parentCategoryId":54,"parentCategoryName":"茶艺盆景","productInstanceCode":"5af2988043de8be0eaece1199077ad05","productInstanceId":188,"productUser":{"auctionCoinBalance":"0.00","auctionCoinNum":0,"balance":"0.00","dialogConnect":false,"fansNum":10,"followNum":0,"headImg":"https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg","id":1969,"level":0,"nickname":"贝贝佛珠\u2014转过来的","payPwd":false,"rate":5,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":false,"userId":"1911181748xBeu8P","warrant":"0.00"},"refund":true,"startPrice":"0","status":1,"time":{"interceptTimeDay":2,"showText":"16:00","systemTime":1592823073000,"timeNode":57600,"timeNodeId":8,"type":"after_tomorrow"},"title":"一元拍一元拍"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592823073382}
     */

    private DataBean data;
    private ResultBean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean implements Parcelable {
        /**
         * attributes : [{"id":168,"length":18,"option":1,"tab":"","tags":[{"name":"茶具","tagId":69},{"name":"酒具","tagId":280}],"title":"类别","value":"酒具"}]
         * bid : [{"bidPrice":"1","createTime":1592807645000,"headImg":"https://file.taojianlou.com/ut/user/1592376005448.png","level":12,"nickname":"小***包","userAccountId":72}]
         * bidNum : 1
         * categoryId : 62
         * categoryName : 茶器
         * collect : false
         * content : 你也不要把门坏你就不要给我
         * createTime : 1592804774000
         * currentPrice : 1
         * delayTime : 300
         * distributeType : 2
         * follow : false
         * images : ["https://file.taojianlou.com/ut/product/1592804761618.png?image=800,800","https://file.taojianlou.com/ut/product/1592804761955.png?image=800,800","https://file.taojianlou.com/ut/product/1592804762212.png?image=800,800","https://file.taojianlou.com/ut/product/1592804762356.png?image=800,800","https://file.taojianlou.com/ut/product/1592804762497.png?image=800,800","https://file.taojianlou.com/ut/product/1592804763093.png?image=800,800","https://file.taojianlou.com/ut/product/1592804764154.png?image=800,800","https://file.taojianlou.com/ut/product/1592804764437.png?image=800,800"]
         * interceptTime : 1592985900000
         * markupRange : 1
         * parentCategoryId : 54
         * parentCategoryName : 茶艺盆景
         * productInstanceCode : 5af2988043de8be0eaece1199077ad05
         * productInstanceId : 188
         * productUser : {"auctionCoinBalance":"0.00","auctionCoinNum":0,"balance":"0.00","dialogConnect":false,"fansNum":10,"followNum":0,"headImg":"https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg","id":1969,"level":0,"nickname":"贝贝佛珠\u2014转过来的","payPwd":false,"rate":5,"score":0,"sellerLevel":0,"sellerScore":0,"storeEnable":false,"userId":"1911181748xBeu8P","warrant":"0.00"}
         * refund : true
         * startPrice : 0
         * status : 1
         * time : {"interceptTimeDay":2,"showText":"16:00","systemTime":1592823073000,"timeNode":57600,"timeNodeId":8,"type":"after_tomorrow"}
         * title : 一元拍一元拍
         */

        private int bidNum;
        private int categoryId;
        private String categoryName;
        private boolean collect;
        private String content;
        private long createTime;
        private int currentPrice;
        private int delayTime;
        private int distributeType;
        private boolean follow;
        private long interceptTime;
        private int markupRange;
        private int parentCategoryId;
        private String parentCategoryName;
        private String productInstanceCode;
        private int productInstanceId;
        private ProductUserBean productUser;
        private boolean refund;
        private String startPrice;

        private boolean subsidyProduct;
        private String subsidyMoney;
        private int status;
        private TimeBean time;
        private OrderBean order;

        private String title;
        private String cutPic;
        private String video;
        private List<AttributesBean> attributes;
        private List<BidBean> bid;
        private ArrayList<String> images;

        public DataBean() {
        }



        protected DataBean(Parcel in) {
            bidNum = in.readInt();
            categoryId = in.readInt();
            categoryName = in.readString();
            collect = in.readByte() != 0;
            content = in.readString();
            createTime = in.readLong();
            currentPrice = in.readInt();
            delayTime = in.readInt();
            distributeType = in.readInt();
            follow = in.readByte() != 0;
            interceptTime = in.readLong();
            markupRange = in.readInt();
            parentCategoryId = in.readInt();
            parentCategoryName = in.readString();
            productInstanceCode = in.readString();
            productInstanceId = in.readInt();
            refund = in.readByte() != 0;
            startPrice = in.readString();
            subsidyProduct = in.readByte() != 0;
            subsidyMoney = in.readString();
            status = in.readInt();
            title = in.readString();
            cutPic = in.readString();
            video = in.readString();
            images = in.createStringArrayList();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(bidNum);
            dest.writeInt(categoryId);
            dest.writeString(categoryName);
            dest.writeByte((byte) (collect ? 1 : 0));
            dest.writeString(content);
            dest.writeLong(createTime);
            dest.writeInt(currentPrice);
            dest.writeInt(delayTime);
            dest.writeInt(distributeType);
            dest.writeByte((byte) (follow ? 1 : 0));
            dest.writeLong(interceptTime);
            dest.writeInt(markupRange);
            dest.writeInt(parentCategoryId);
            dest.writeString(parentCategoryName);
            dest.writeString(productInstanceCode);
            dest.writeInt(productInstanceId);
            dest.writeByte((byte) (refund ? 1 : 0));
            dest.writeString(startPrice);
            dest.writeByte((byte) (subsidyProduct ? 1 : 0));
            dest.writeString(subsidyMoney);
            dest.writeInt(status);
            dest.writeString(title);
            dest.writeString(cutPic);
            dest.writeString(video);
            dest.writeStringList(images);
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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }




        public boolean isSubsidyProduct() {
            return subsidyProduct;
        }

        public void setSubsidyProduct(boolean subsidyProduct) {
            this.subsidyProduct = subsidyProduct;
        }

        public String getSubsidyMoney() {
            return subsidyMoney;
        }

        public void setSubsidyMoney(String subsidyMoney) {
            this.subsidyMoney = subsidyMoney;
        }

        public String getCutPic() {
            return cutPic;
        }

        public void setCutPic(String cutPic) {
            this.cutPic = cutPic;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public int getBidNum() {
            return bidNum;
        }

        public void setBidNum(int bidNum) {
            this.bidNum = bidNum;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(int currentPrice) {
            this.currentPrice = currentPrice;
        }

        public int getDelayTime() {
            return delayTime;
        }

        public void setDelayTime(int delayTime) {
            this.delayTime = delayTime;
        }

        public int getDistributeType() {
            return distributeType;
        }

        public void setDistributeType(int distributeType) {
            this.distributeType = distributeType;
        }

        public boolean isFollow() {
            return follow;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        public long getInterceptTime() {
            return interceptTime;
        }

        public void setInterceptTime(long interceptTime) {
            this.interceptTime = interceptTime;
        }

        public int getMarkupRange() {
            return markupRange;
        }

        public void setMarkupRange(int markupRange) {
            this.markupRange = markupRange;
        }

        public int getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(int parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }

        public String getParentCategoryName() {
            return parentCategoryName;
        }

        public void setParentCategoryName(String parentCategoryName) {
            this.parentCategoryName = parentCategoryName;
        }

        public String getProductInstanceCode() {
            return productInstanceCode;
        }

        public void setProductInstanceCode(String productInstanceCode) {
            this.productInstanceCode = productInstanceCode;
        }

        public int getProductInstanceId() {
            return productInstanceId;
        }

        public void setProductInstanceId(int productInstanceId) {
            this.productInstanceId = productInstanceId;
        }

        public ProductUserBean getProductUser() {
            return productUser;
        }

        public void setProductUser(ProductUserBean productUser) {
            this.productUser = productUser;
        }

        public boolean isRefund() {
            return refund;
        }

        public void setRefund(boolean refund) {
            this.refund = refund;
        }

        public String getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public List<BidBean> getBid() {
            return bid;
        }

        public void setBid(List<BidBean> bid) {
            this.bid = bid;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }



        public static class ProductUserBean {
            /**
             * auctionCoinBalance : 0.00
             * auctionCoinNum : 0
             * balance : 0.00
             * dialogConnect : false
             * fansNum : 10
             * followNum : 0
             * headImg : https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg
             * id : 1969
             * level : 0
             * nickname : 贝贝佛珠—转过来的
             * payPwd : false
             * rate : 5.0
             * score : 0
             * sellerLevel : 0
             * sellerScore : 0
             * storeEnable : false
             * userId : 1911181748xBeu8P
             * warrant : 0.00
             */

            private String auctionCoinBalance;
            private int auctionCoinNum;
            private String balance;
            private boolean dialogConnect;
            private int fansNum;
            private int followNum;
            private String headImg;
            private int id;
            private int level;
            private String nickname;
            private boolean payPwd;
            private double rate;
            private int score;
            private int sellerLevel;
            private int sellerScore;
            private boolean storeEnable;
            private String userId;
            private String warrant;

            public String getAuctionCoinBalance() {
                return auctionCoinBalance;
            }

            public void setAuctionCoinBalance(String auctionCoinBalance) {
                this.auctionCoinBalance = auctionCoinBalance;
            }

            public int getAuctionCoinNum() {
                return auctionCoinNum;
            }

            public void setAuctionCoinNum(int auctionCoinNum) {
                this.auctionCoinNum = auctionCoinNum;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public boolean isDialogConnect() {
                return dialogConnect;
            }

            public void setDialogConnect(boolean dialogConnect) {
                this.dialogConnect = dialogConnect;
            }

            public int getFansNum() {
                return fansNum;
            }

            public void setFansNum(int fansNum) {
                this.fansNum = fansNum;
            }

            public int getFollowNum() {
                return followNum;
            }

            public void setFollowNum(int followNum) {
                this.followNum = followNum;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isPayPwd() {
                return payPwd;
            }

            public void setPayPwd(boolean payPwd) {
                this.payPwd = payPwd;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getSellerLevel() {
                return sellerLevel;
            }

            public void setSellerLevel(int sellerLevel) {
                this.sellerLevel = sellerLevel;
            }

            public int getSellerScore() {
                return sellerScore;
            }

            public void setSellerScore(int sellerScore) {
                this.sellerScore = sellerScore;
            }

            public boolean isStoreEnable() {
                return storeEnable;
            }

            public void setStoreEnable(boolean storeEnable) {
                this.storeEnable = storeEnable;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getWarrant() {
                return warrant;
            }

            public void setWarrant(String warrant) {
                this.warrant = warrant;
            }
        }

        public static class TimeBean {
            /**
             * interceptTimeDay : 2
             * showText : 16:00
             * systemTime : 1592823073000
             * timeNode : 57600
             * timeNodeId : 8
             * type : after_tomorrow
             */

            private int interceptTimeDay;
            private String showText;
            private long systemTime;
            private int timeNode;
            private int timeNodeId;
            private String type;

            public int getInterceptTimeDay() {
                return interceptTimeDay;
            }

            public void setInterceptTimeDay(int interceptTimeDay) {
                this.interceptTimeDay = interceptTimeDay;
            }

            public String getShowText() {
                return showText;
            }

            public void setShowText(String showText) {
                this.showText = showText;
            }

            public long getSystemTime() {
                return systemTime;
            }

            public void setSystemTime(long systemTime) {
                this.systemTime = systemTime;
            }

            public int getTimeNode() {
                return timeNode;
            }

            public void setTimeNode(int timeNode) {
                this.timeNode = timeNode;
            }

            public int getTimeNodeId() {
                return timeNodeId;
            }

            public void setTimeNodeId(int timeNodeId) {
                this.timeNodeId = timeNodeId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class AttributesBean {
            /**
             * id : 168
             * length : 18
             * option : 1
             * tab :
             * tags : [{"name":"茶具","tagId":69},{"name":"酒具","tagId":280}]
             * title : 类别
             * value : 酒具
             */

            private int id;
            private int length;
            private int option;
            private String tab;
            private String title;
            private String value;
            private List<TagsBean> tags;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
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

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * name : 茶具
                 * tagId : 69
                 */

                private String name;
                private int tagId;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getTagId() {
                    return tagId;
                }

                public void setTagId(int tagId) {
                    this.tagId = tagId;
                }
            }
        }

        public static class BidBean {
            /**
             * bidPrice : 1
             * createTime : 1592807645000
             * headImg : https://file.taojianlou.com/ut/user/1592376005448.png
             * level : 12
             * nickname : 小***包
             * userAccountId : 72
             */

            private int bidPrice;
            private long createTime;
            private String headImg;
            private int level;
            private String nickname;
            private int userAccountId;


            private String ensureMoney;

            public String getEnsureMoney() {
                return ensureMoney;
            }

            public void setEnsureMoney(String ensureMoney) {
                this.ensureMoney = ensureMoney;
            }

            public int getBidPrice() {
                return bidPrice;
            }

            public void setBidPrice(int bidPrice) {
                this.bidPrice = bidPrice;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getUserAccountId() {
                return userAccountId;
            }

            public void setUserAccountId(int userAccountId) {
                this.userAccountId = userAccountId;
            }
        }

        public static class OrderBean {
            /**
             * bidPrice : 1
             * createTime : 1592807645000
             * headImg : https://file.taojianlou.com/ut/user/1592376005448.png
             * level : 12
             * nickname : 小***包
             * userAccountId : 72
             */

            private String orderCode;

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }
        }


    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1592823073382
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
}

package com.leo.auction.ui.order.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.order.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/8/15
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class WebGoodDetailModel  {


    /**
     * attributes : [{"id":166,"length":18,"option":1,"tab":"","title":"材质","value":"小叶紫檀"},{"id":46,"option":1,"tab":"","title":"类型","value":"摆件"}]
     * bid : [{"bidPrice":"9","createTime":1597472161000,"ensureMoney":"1","headImg":"https://file.taojianlou.com/ut/product/2F8E424D70194899A03C7C6CB52A1B1F.jpg","level":2,"nickname":"这些不是问题","userAccountId":1972},{"bidPrice":"6","createTime":1597472153000,"headImg":"https://file.taojianlou.com/Android/user/img20200730/1596095682994.png?image=430,430","level":2,"nickname":"修改过的名","userAccountId":1964},{"bidPrice":"3","createTime":1597472060000,"ensureMoney":"1","headImg":"https://file.taojianlou.com/ut/product/2F8E424D70194899A03C7C6CB52A1B1F.jpg","level":2,"nickname":"这些不是问题","userAccountId":1972}]
     * bidNum : 3
     * categoryId : 19
     * categoryName : 紫檀 | 黄花梨
     * collect : false
     * content : 品名：小叶紫檀自在观音
     编码：769302/个

     尺寸:观音无底座8.2*5.9*20.4(cm)  重量:329g
     底座13.7*11.2*4.8(cm) 重量:273.4g
     型号:1249tzeb
     观世音，亦译“光世音”、“观世自在”、“观自在”，是阿弥陀佛的左胁侍，西方三圣之一。她以大悲大慈为德性，据称遇难者只要念诵其名号，观音菩萨就会及时“普救众生苦难”。有很多观世音，成为无处不在的菩萨。
     * createTime : 08月15日 14:13
     * currentPrice : 9
     * delayTime : 300
     * distributeType : 1
     * follow : true
     * goodsId : 3431
     * images : ["https://file.taojianlou.com/goods/aFJdBiK4ETpNIYz6cjTYkc1c0Ymvcxr8.jpg?image=1440,960","https://file.taojianlou.com/goods/2iGUB2BgDyZbwC5V0ckZD0XLrAXIt0XT.jpg?image=1440,960","https://file.taojianlou.com/goods/8Sytyx9R66kEJ85AWNliTc49UCL2g92w.jpg?image=1440,960","https://file.taojianlou.com/goods/cJdBIIWR9gHqRiR9pyuXJpHBjV9caydH.jpg?image=1440,960","https://file.taojianlou.com/goods/Jq4VgL5CN8PDHvz6jFiPmPYq3Iq1bbRR.jpg?image=1440,960","https://file.taojianlou.com/goods/4cBLc1bjSUKFBZTqBC6mEPzyciWy9qWf.jpg?image=1440,960","https://file.taojianlou.com/goods/0n0XMnAzlLdflTPF2mOcmzYeraySbfWL.jpg?image=1440,960","https://file.taojianlou.com/goods/vgBqxJFi9H4RIhTOr4C9It362wYX8JsV.jpg?image=1440,960","https://file.taojianlou.com/goods/cqIQCLHPLlzgi8hEqRDT1VqZ0kfMISPz.jpg?image=1440,960"]
     * interceptTime : 1597636800000
     * markupRange : 3
     * parentCategoryId : 13
     * parentCategoryName : 工艺作品
     * productInstanceCode : 88b6a3f53ced024bcc7bad0fc81313ab
     * productInstanceId : 1426
     * productUser : {"auctionCoinBalance":"0.00","auctionCoinNum":0,"auctionFansNum":12,"balance":"0.00","dialogConnect":false,"exclusiveFansNum":52,"fansNum":64,"followNum":0,"headImg":"https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg?x-oss-process=image/resize,s_100","id":1969,"level":0,"nickname":"哈哈哈有意思","online":false,"payPwd":false,"rate":5,"score":0,"sellerLevel":2,"sellerScore":100,"storeEnable":false,"userId":"1911181748xBeu8P","warrant":"0.00"}
     * refund : true
     * startPrice : 0
     * status : 1
     * subsidyMoney : 4
     * subsidyProduct : true
     * time : {"interceptTimeDay":2,"showText":"12:00","systemTime":1597479829000,"timeNode":43200,"timeNodeId":6,"type":"after_tomorrow"}
     * title : 小叶紫檀自在观音
     * isyulist : [true,true,true,true,true,true,true,true,true]
     * interceptTimeText : 2020-08-17 12:00:00
     * contentText : 【品名】小叶紫檀自在观音
     【材质】小叶紫檀
     【介绍】品名：小叶紫檀自在观音
     编码：769302/个

     尺寸:观音无底座8.2*5.9*20.4(cm)  重量:329g
     底座13.7*11.2*4.8(cm) 重量:273.4g
     型号:1249tzeb
     观世音，亦译“光世音”、“观世自在”、“观自在”，是阿弥陀佛的左胁侍，西方三圣之一。她以大悲大慈为德性，据称遇难者只要念诵其名号，观音菩萨就会及时“普救众生苦难”。有很多观世音，成为无处不在的菩萨。
     */

    private int bidNum;
    private int categoryId;
    private String categoryName;
    private boolean collect;
    private String content;
    private String createTime;
    private String currentPrice;
    private int delayTime;
    private int distributeType;
    private boolean follow;
    private int goodsId;
    private long interceptTime;
    private String markupRange;
    private int parentCategoryId;
    private String parentCategoryName;
    private String productInstanceCode;
    private int productInstanceId;
    private ProductUserBean productUser;
    private boolean refund;
    private String startPrice;
    private int status;
    private String subsidyMoney;
    private boolean subsidyProduct;
    private TimeBean time;
    private String title;
    private String interceptTimeText;
    private String contentText;
    private List<AttributesBean> attributes;
    private List<BidBean> bid;
    private ArrayList<String> images;
    private List<Boolean> isyulist;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
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

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public long getInterceptTime() {
        return interceptTime;
    }

    public void setInterceptTime(long interceptTime) {
        this.interceptTime = interceptTime;
    }

    public String getMarkupRange() {
        return markupRange;
    }

    public void setMarkupRange(String markupRange) {
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

    public String getSubsidyMoney() {
        return subsidyMoney;
    }

    public void setSubsidyMoney(String subsidyMoney) {
        this.subsidyMoney = subsidyMoney;
    }

    public boolean isSubsidyProduct() {
        return subsidyProduct;
    }

    public void setSubsidyProduct(boolean subsidyProduct) {
        this.subsidyProduct = subsidyProduct;
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

    public String getInterceptTimeText() {
        return interceptTimeText;
    }

    public void setInterceptTimeText(String interceptTimeText) {
        this.interceptTimeText = interceptTimeText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
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

    public List<Boolean> getIsyulist() {
        return isyulist;
    }

    public void setIsyulist(List<Boolean> isyulist) {
        this.isyulist = isyulist;
    }

    public static class ProductUserBean {
        /**
         * auctionCoinBalance : 0.00
         * auctionCoinNum : 0
         * auctionFansNum : 12
         * balance : 0.00
         * dialogConnect : false
         * exclusiveFansNum : 52
         * fansNum : 64
         * followNum : 0
         * headImg : https://file.taojianlou.com/ut/user/a0ca63942b933ffffca8b73f0ba005f2.jpg?x-oss-process=image/resize,s_100
         * id : 1969
         * level : 0
         * nickname : 哈哈哈有意思
         * online : false
         * payPwd : false
         * rate : 5
         * score : 0
         * sellerLevel : 2
         * sellerScore : 100
         * storeEnable : false
         * userId : 1911181748xBeu8P
         * warrant : 0.00
         */

        private String auctionCoinBalance;
        private int auctionCoinNum;
        private int auctionFansNum;
        private String balance;
        private boolean dialogConnect;
        private int exclusiveFansNum;
        private int fansNum;
        private int followNum;
        private String headImg;
        private int id;
        private int level;
        private String nickname;
        private boolean online;
        private boolean payPwd;
        private int rate;
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

        public int getAuctionFansNum() {
            return auctionFansNum;
        }

        public void setAuctionFansNum(int auctionFansNum) {
            this.auctionFansNum = auctionFansNum;
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

        public int getExclusiveFansNum() {
            return exclusiveFansNum;
        }

        public void setExclusiveFansNum(int exclusiveFansNum) {
            this.exclusiveFansNum = exclusiveFansNum;
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

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public boolean isPayPwd() {
            return payPwd;
        }

        public void setPayPwd(boolean payPwd) {
            this.payPwd = payPwd;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
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
         * showText : 12:00
         * systemTime : 1597479829000
         * timeNode : 43200
         * timeNodeId : 6
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
         * id : 166
         * length : 18
         * option : 1
         * tab :
         * title : 材质
         * value : 小叶紫檀
         */

        private int id;
        private int length;
        private int option;
        private String tab;
        private String title;
        private String value;

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
    }

    public static class BidBean {
        /**
         * bidPrice : 9
         * createTime : 1597472161000
         * ensureMoney : 1
         * headImg : https://file.taojianlou.com/ut/product/2F8E424D70194899A03C7C6CB52A1B1F.jpg
         * level : 2
         * nickname : 这些不是问题
         * userAccountId : 1972
         */

        private String bidPrice;
        private long createTime;
        private String ensureMoney;
        private String headImg;
        private int level;
        private String nickname;
        private int userAccountId;

        public String getBidPrice() {
            return bidPrice;
        }

        public void setBidPrice(String bidPrice) {
            this.bidPrice = bidPrice;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEnsureMoney() {
            return ensureMoney;
        }

        public void setEnsureMoney(String ensureMoney) {
            this.ensureMoney = ensureMoney;
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
}

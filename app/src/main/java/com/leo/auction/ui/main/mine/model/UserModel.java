package com.leo.auction.ui.main.mine.model;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/27
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class UserModel {


    /**
     * data : {"address":{"shipAddress":{"address":"北京市北京市东城区ggg","code":"jjjj","id":5,"linkman":"ghhh","phone":"13489973950","type":0},"returnAddress":{"address":"北京市北京市东城区横坑村","code":"000000","id":268,"linkman":"翁忠杰","phone":"13489973950","type":1}},"fansNum":0,"followNum":0,"headImg":"http://w.taojianlou.com/image/default/head.jpg","id":69,"level":1,"limitType":0,"nickname":"dasdas","payPwd":true,"phone":"13489973950","score":0,"type":1,"userId":"1908091213AS4weP","hxId":"3c50b86a051ec63ccf66d56f6282f935","username":"张三","idCard":"3**************X"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1590728081536}
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

    public static class DataBean {
        /**
         * address : {"shipAddress":{"address":"北京市北京市东城区ggg","code":"jjjj","id":5,"linkman":"ghhh","phone":"13489973950","type":0},"returnAddress":{"address":"北京市北京市东城区横坑村","code":"000000","id":268,"linkman":"翁忠杰","phone":"13489973950","type":1}}
         * fansNum : 0
         * followNum : 0
         * headImg : http://w.taojianlou.com/image/default/head.jpg
         * id : 69
         * level : 1
         * limitType : 0
         * nickname : dasdas
         * payPwd : true
         * phone : 13489973950
         * score : 0
         * type : 1
         * userId : 1908091213AS4weP
         * hxId : 3c50b86a051ec63ccf66d56f6282f935
         * username : 张三
         * idCard : 3**************X
         */

        private AddressBean address;
        private int fansNum;
        private int followNum;
        private String headImg;
        private int id;
        private int level;
        private int limitType;
        private String nickname;
        private boolean payPwd;
        private String phone;
        private int score;
        private int type;
        private String userId;
        private String hxId;
        private String username;
        private String idCard;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
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

        public int getLimitType() {
            return limitType;
        }

        public void setLimitType(int limitType) {
            this.limitType = limitType;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getHxId() {
            return hxId;
        }

        public void setHxId(String hxId) {
            this.hxId = hxId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public static class AddressBean {
            /**
             * shipAddress : {"address":"北京市北京市东城区ggg","code":"jjjj","id":5,"linkman":"ghhh","phone":"13489973950","type":0}
             * returnAddress : {"address":"北京市北京市东城区横坑村","code":"000000","id":268,"linkman":"翁忠杰","phone":"13489973950","type":1}
             */

            private ShipAddressBean shipAddress;
            private ReturnAddressBean returnAddress;

            public ShipAddressBean getShipAddress() {
                return shipAddress;
            }

            public void setShipAddress(ShipAddressBean shipAddress) {
                this.shipAddress = shipAddress;
            }

            public ReturnAddressBean getReturnAddress() {
                return returnAddress;
            }

            public void setReturnAddress(ReturnAddressBean returnAddress) {
                this.returnAddress = returnAddress;
            }

            public static class ShipAddressBean {
                /**
                 * address : 北京市北京市东城区ggg
                 * code : jjjj
                 * id : 5
                 * linkman : ghhh
                 * phone : 13489973950
                 * type : 0
                 */

                private String address;
                private String code;
                private int id;
                private String linkman;
                private String phone;
                private int type;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getLinkman() {
                    return linkman;
                }

                public void setLinkman(String linkman) {
                    this.linkman = linkman;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }

            public static class ReturnAddressBean {
                /**
                 * address : 北京市北京市东城区横坑村
                 * code : 000000
                 * id : 268
                 * linkman : 翁忠杰
                 * phone : 13489973950
                 * type : 1
                 */

                private String address;
                private String code;
                private int id;
                private String linkman;
                private String phone;
                private int type;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getLinkman() {
                    return linkman;
                }

                public void setLinkman(String linkman) {
                    this.linkman = linkman;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1590728081536
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

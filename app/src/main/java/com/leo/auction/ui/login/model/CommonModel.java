package com.leo.auction.ui.login.model;

import java.util.List;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.login.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/6/24
 * 描    述： 公告，等级图片
 * 修    改：
 * ===============================================
 */
public class CommonModel {

    /**
     * data : {"applyAgent_pic1":"https://file.taojianlou.com/user/applyAgent_pic1.png","applyAgent_pic2":"https://file.taojianlou.com/user/applyAgent_pic2.png","levelPic":["https://file.taojianlou.com/user/auction_level_hd_0.png","https://file.taojianlou.com/user/auction_level_hd_1.png","https://file.taojianlou.com/user/auction_level_hd_2.png","https://file.taojianlou.com/user/auction_level_hd_3.png","https://file.taojianlou.com/user/auction_level_hd_4.png","https://file.taojianlou.com/user/auction_level_hd_5.png","https://file.taojianlou.com/user/auction_level_hd_6.png","https://file.taojianlou.com/user/auction_level_hd_7.png","https://file.taojianlou.com/user/auction_level_hd_8.png","https://file.taojianlou.com/user/auction_level_hd_9.png","https://file.taojianlou.com/user/auction_level_hd_10.png","https://file.taojianlou.com/user/auction_level_hd_11.png","https://file.taojianlou.com/user/auction_level_hd_12.png"],"logo":"https://file.taojianlou.com/platform/auction_log.jpg","my_level_pic":["https://file.taojianlou.com/user/auction_level_hd_0.png","https://file.taojianlou.com/user/auction_level_hd_1.png","https://file.taojianlou.com/user/auction_level_hd_2.png","https://file.taojianlou.com/user/auction_level_hd_3.png","https://file.taojianlou.com/user/auction_level_hd_4.png","https://file.taojianlou.com/user/auction_level_hd_5.png","https://file.taojianlou.com/user/auction_level_hd_6.png","https://file.taojianlou.com/user/auction_level_hd_7.png","https://file.taojianlou.com/user/auction_level_hd_8.png","https://file.taojianlou.com/user/auction_level_hd_9.png","https://file.taojianlou.com/user/auction_level_hd_10.png","https://file.taojianlou.com/user/auction_level_hd_11.png","https://file.taojianlou.com/user/auction_level_hd_12.png"],"my_level_v_pic":["https://file.taojianlou.com/user/my_level_v_pic_0.png","https://file.taojianlou.com/user/my_level_v_pic_1.png","https://file.taojianlou.com/user/my_level_v_pic_2.png","https://file.taojianlou.com/user/my_level_v_pic_3.png","https://file.taojianlou.com/user/my_level_v_pic_4.png","https://file.taojianlou.com/user/my_level_v_pic_5.png","https://file.taojianlou.com/user/my_level_v_pic_6.png","https://file.taojianlou.com/user/my_level_v_pic_7.png","https://file.taojianlou.com/user/my_level_v_pic_8.png","https://file.taojianlou.com/user/my_level_v_pic_9.png","https://file.taojianlou.com/user/my_level_v_pic_10.png","https://file.taojianlou.com/user/my_level_v_pic_11.png","https://file.taojianlou.com/user/my_level_v_pic_12.png"],"refundReason_1":["拍错/多拍/不想要","协商一致退款","缺货","未按约定时间发货","其他"],"refundReason_2":["无理由退货","宝贝不满意","质量有问题","客户不满意","其他原因"],"seller_level_pic":["https://file.taojianlou.com/user/seller_level_0.png","https://file.taojianlou.com/user/seller_level_1.png","https://file.taojianlou.com/user/seller_level_2.png","https://file.taojianlou.com/user/seller_level_3.png","https://file.taojianlou.com/user/seller_level_4.png","https://file.taojianlou.com/user/seller_level_5.png","https://file.taojianlou.com/user/seller_level_6.png","https://file.taojianlou.com/user/seller_level_7.png","https://file.taojianlou.com/user/seller_level_8.png","https://file.taojianlou.com/user/seller_level_9.png","https://file.taojianlou.com/user/seller_level_10.png","https://file.taojianlou.com/user/seller_level_11.png","https://file.taojianlou.com/user/seller_level_12.png"],"spread":{"recommend_fans_yl":"https://file.taojianlou.com/user/recommend_fans_yl.jpg","recommend_supplier":"https://file.taojianlou.com/user/spread_1.png","recommend_supplier_yl":"https://file.taojianlou.com/user/recommend_supplier_yl.jpg","spread_default":"https://file.taojianlou.com/user/spread_default.png","recommend_fans":"https://file.taojianlou.com/user/spread_2.png"}}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1594880166135}
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
         * applyAgent_pic1 : https://file.taojianlou.com/user/applyAgent_pic1.png
         * applyAgent_pic2 : https://file.taojianlou.com/user/applyAgent_pic2.png
         * levelPic : ["https://file.taojianlou.com/user/auction_level_hd_0.png","https://file.taojianlou.com/user/auction_level_hd_1.png","https://file.taojianlou.com/user/auction_level_hd_2.png","https://file.taojianlou.com/user/auction_level_hd_3.png","https://file.taojianlou.com/user/auction_level_hd_4.png","https://file.taojianlou.com/user/auction_level_hd_5.png","https://file.taojianlou.com/user/auction_level_hd_6.png","https://file.taojianlou.com/user/auction_level_hd_7.png","https://file.taojianlou.com/user/auction_level_hd_8.png","https://file.taojianlou.com/user/auction_level_hd_9.png","https://file.taojianlou.com/user/auction_level_hd_10.png","https://file.taojianlou.com/user/auction_level_hd_11.png","https://file.taojianlou.com/user/auction_level_hd_12.png"]
         * logo : https://file.taojianlou.com/platform/auction_log.jpg
         * my_level_pic : ["https://file.taojianlou.com/user/auction_level_hd_0.png","https://file.taojianlou.com/user/auction_level_hd_1.png","https://file.taojianlou.com/user/auction_level_hd_2.png","https://file.taojianlou.com/user/auction_level_hd_3.png","https://file.taojianlou.com/user/auction_level_hd_4.png","https://file.taojianlou.com/user/auction_level_hd_5.png","https://file.taojianlou.com/user/auction_level_hd_6.png","https://file.taojianlou.com/user/auction_level_hd_7.png","https://file.taojianlou.com/user/auction_level_hd_8.png","https://file.taojianlou.com/user/auction_level_hd_9.png","https://file.taojianlou.com/user/auction_level_hd_10.png","https://file.taojianlou.com/user/auction_level_hd_11.png","https://file.taojianlou.com/user/auction_level_hd_12.png"]
         * my_level_v_pic : ["https://file.taojianlou.com/user/my_level_v_pic_0.png","https://file.taojianlou.com/user/my_level_v_pic_1.png","https://file.taojianlou.com/user/my_level_v_pic_2.png","https://file.taojianlou.com/user/my_level_v_pic_3.png","https://file.taojianlou.com/user/my_level_v_pic_4.png","https://file.taojianlou.com/user/my_level_v_pic_5.png","https://file.taojianlou.com/user/my_level_v_pic_6.png","https://file.taojianlou.com/user/my_level_v_pic_7.png","https://file.taojianlou.com/user/my_level_v_pic_8.png","https://file.taojianlou.com/user/my_level_v_pic_9.png","https://file.taojianlou.com/user/my_level_v_pic_10.png","https://file.taojianlou.com/user/my_level_v_pic_11.png","https://file.taojianlou.com/user/my_level_v_pic_12.png"]
         * refundReason_1 : ["拍错/多拍/不想要","协商一致退款","缺货","未按约定时间发货","其他"]
         * refundReason_2 : ["无理由退货","宝贝不满意","质量有问题","客户不满意","其他原因"]
         * seller_level_pic : ["https://file.taojianlou.com/user/seller_level_0.png","https://file.taojianlou.com/user/seller_level_1.png","https://file.taojianlou.com/user/seller_level_2.png","https://file.taojianlou.com/user/seller_level_3.png","https://file.taojianlou.com/user/seller_level_4.png","https://file.taojianlou.com/user/seller_level_5.png","https://file.taojianlou.com/user/seller_level_6.png","https://file.taojianlou.com/user/seller_level_7.png","https://file.taojianlou.com/user/seller_level_8.png","https://file.taojianlou.com/user/seller_level_9.png","https://file.taojianlou.com/user/seller_level_10.png","https://file.taojianlou.com/user/seller_level_11.png","https://file.taojianlou.com/user/seller_level_12.png"]
         * spread : {"recommend_fans_yl":"https://file.taojianlou.com/user/recommend_fans_yl.jpg","recommend_supplier":"https://file.taojianlou.com/user/spread_1.png","recommend_supplier_yl":"https://file.taojianlou.com/user/recommend_supplier_yl.jpg","spread_default":"https://file.taojianlou.com/user/spread_default.png","recommend_fans":"https://file.taojianlou.com/user/spread_2.png"}
         */

        private String applyAgent_pic1;
        private String applyAgent_pic2;
        private String logo;
        private SpreadBean spread;
        private List<String> levelPic;
        private List<String> my_level_pic;
        private List<String> my_level_v_pic;
        private List<String> refundReason_1;
        private List<String> refundReason_2;
        private List<String> seller_level_pic;

        public String getApplyAgent_pic1() {
            return applyAgent_pic1;
        }

        public void setApplyAgent_pic1(String applyAgent_pic1) {
            this.applyAgent_pic1 = applyAgent_pic1;
        }

        public String getApplyAgent_pic2() {
            return applyAgent_pic2;
        }

        public void setApplyAgent_pic2(String applyAgent_pic2) {
            this.applyAgent_pic2 = applyAgent_pic2;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public SpreadBean getSpread() {
            return spread;
        }

        public void setSpread(SpreadBean spread) {
            this.spread = spread;
        }

        public List<String> getLevelPic() {
            return levelPic;
        }

        public void setLevelPic(List<String> levelPic) {
            this.levelPic = levelPic;
        }

        public List<String> getMy_level_pic() {
            return my_level_pic;
        }

        public void setMy_level_pic(List<String> my_level_pic) {
            this.my_level_pic = my_level_pic;
        }

        public List<String> getMy_level_v_pic() {
            return my_level_v_pic;
        }

        public void setMy_level_v_pic(List<String> my_level_v_pic) {
            this.my_level_v_pic = my_level_v_pic;
        }

        public List<String> getRefundReason_1() {
            return refundReason_1;
        }

        public void setRefundReason_1(List<String> refundReason_1) {
            this.refundReason_1 = refundReason_1;
        }

        public List<String> getRefundReason_2() {
            return refundReason_2;
        }

        public void setRefundReason_2(List<String> refundReason_2) {
            this.refundReason_2 = refundReason_2;
        }

        public List<String> getSeller_level_pic() {
            return seller_level_pic;
        }

        public void setSeller_level_pic(List<String> seller_level_pic) {
            this.seller_level_pic = seller_level_pic;
        }

        public static class SpreadBean {
            /**
             * recommend_fans_yl : https://file.taojianlou.com/user/recommend_fans_yl.jpg
             * recommend_supplier : https://file.taojianlou.com/user/spread_1.png
             * recommend_supplier_yl : https://file.taojianlou.com/user/recommend_supplier_yl.jpg
             * spread_default : https://file.taojianlou.com/user/spread_default.png
             * recommend_fans : https://file.taojianlou.com/user/spread_2.png
             */

            private String recommend_fans_yl;
            private String recommend_supplier;
            private String recommend_supplier_yl;
            private String spread_default;
            private String recommend_fans;

            public String getRecommend_fans_yl() {
                return recommend_fans_yl;
            }

            public void setRecommend_fans_yl(String recommend_fans_yl) {
                this.recommend_fans_yl = recommend_fans_yl;
            }

            public String getRecommend_supplier() {
                return recommend_supplier;
            }

            public void setRecommend_supplier(String recommend_supplier) {
                this.recommend_supplier = recommend_supplier;
            }

            public String getRecommend_supplier_yl() {
                return recommend_supplier_yl;
            }

            public void setRecommend_supplier_yl(String recommend_supplier_yl) {
                this.recommend_supplier_yl = recommend_supplier_yl;
            }

            public String getSpread_default() {
                return spread_default;
            }

            public void setSpread_default(String spread_default) {
                this.spread_default = spread_default;
            }

            public String getRecommend_fans() {
                return recommend_fans;
            }

            public void setRecommend_fans(String recommend_fans) {
                this.recommend_fans = recommend_fans;
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1594880166135
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

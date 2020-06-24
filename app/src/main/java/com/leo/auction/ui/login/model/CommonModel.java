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
     * data : {"levelPic":["https://file.taojianlou.com/user/auction_level_0.png","https://file.taojianlou.com/user/auction_level_1.png","https://file.taojianlou.com/user/auction_level_2.png","https://file.taojianlou.com/user/auction_level_3.png","https://file.taojianlou.com/user/auction_level_4.png","https://file.taojianlou.com/user/auction_level_5.png","https://file.taojianlou.com/user/auction_level_6.png","https://file.taojianlou.com/user/auction_level_7.png","https://file.taojianlou.com/user/auction_level_8.png","https://file.taojianlou.com/user/auction_level_9.png","https://file.taojianlou.com/user/auction_level_10.png","https://file.taojianlou.com/user/auction_level_11.png","https://file.taojianlou.com/user/auction_level_12.png"],"my_level_pic":["https://file.taojianlou.com/user/auction_level_hd_0.png","https://file.taojianlou.com/user/auction_level_hd_1.png","https://file.taojianlou.com/user/auction_level_hd_2.png","https://file.taojianlou.com/user/auction_level_hd_3.png","https://file.taojianlou.com/user/auction_level_hd_4.png","https://file.taojianlou.com/user/auction_level_hd_5.png","https://file.taojianlou.com/user/auction_level_hd_6.png","https://file.taojianlou.com/user/auction_level_hd_7.png","https://file.taojianlou.com/user/auction_level_hd_8.png","https://file.taojianlou.com/user/auction_level_hd_9.png","https://file.taojianlou.com/user/auction_level_hd_10.png","https://file.taojianlou.com/user/auction_level_hd_11.png","https://file.taojianlou.com/user/auction_level_hd_12.png"],"my_level_v_pic":["https://file.taojianlou.com/user/my_level_v_pic_0.png","https://file.taojianlou.com/user/my_level_v_pic_1.png","https://file.taojianlou.com/user/my_level_v_pic_2.png","https://file.taojianlou.com/user/my_level_v_pic_3.png","https://file.taojianlou.com/user/my_level_v_pic_4.png","https://file.taojianlou.com/user/my_level_v_pic_5.png","https://file.taojianlou.com/user/my_level_v_pic_6.png","https://file.taojianlou.com/user/my_level_v_pic_7.png","https://file.taojianlou.com/user/my_level_v_pic_8.png","https://file.taojianlou.com/user/my_level_v_pic_9.png","https://file.taojianlou.com/user/my_level_v_pic_10.png","https://file.taojianlou.com/user/my_level_v_pic_11.png","https://file.taojianlou.com/user/my_level_v_pic_12.png"],"seller_level_pic":["https://file.taojianlou.com/user/seller_level_0.png","https://file.taojianlou.com/user/seller_level_1.png","https://file.taojianlou.com/user/seller_level_2.png","https://file.taojianlou.com/user/seller_level_3.png","https://file.taojianlou.com/user/seller_level_4.png","https://file.taojianlou.com/user/seller_level_5.png","https://file.taojianlou.com/user/seller_level_6.png","https://file.taojianlou.com/user/seller_level_7.png","https://file.taojianlou.com/user/seller_level_8.png","https://file.taojianlou.com/user/seller_level_9.png","https://file.taojianlou.com/user/seller_level_10.png","https://file.taojianlou.com/user/seller_level_11.png","https://file.taojianlou.com/user/seller_level_12.png"],"logo":"https://file.taojianlou.com/platform/auction_log.jpg"}
     * result : {"code":"0","message":"请求成功","success":true,"timestamp":1592468085372}
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
         * levelPic : ["https://file.taojianlou.com/user/auction_level_0.png","https://file.taojianlou.com/user/auction_level_1.png","https://file.taojianlou.com/user/auction_level_2.png","https://file.taojianlou.com/user/auction_level_3.png","https://file.taojianlou.com/user/auction_level_4.png","https://file.taojianlou.com/user/auction_level_5.png","https://file.taojianlou.com/user/auction_level_6.png","https://file.taojianlou.com/user/auction_level_7.png","https://file.taojianlou.com/user/auction_level_8.png","https://file.taojianlou.com/user/auction_level_9.png","https://file.taojianlou.com/user/auction_level_10.png","https://file.taojianlou.com/user/auction_level_11.png","https://file.taojianlou.com/user/auction_level_12.png"]
         * my_level_pic : ["https://file.taojianlou.com/user/auction_level_hd_0.png","https://file.taojianlou.com/user/auction_level_hd_1.png","https://file.taojianlou.com/user/auction_level_hd_2.png","https://file.taojianlou.com/user/auction_level_hd_3.png","https://file.taojianlou.com/user/auction_level_hd_4.png","https://file.taojianlou.com/user/auction_level_hd_5.png","https://file.taojianlou.com/user/auction_level_hd_6.png","https://file.taojianlou.com/user/auction_level_hd_7.png","https://file.taojianlou.com/user/auction_level_hd_8.png","https://file.taojianlou.com/user/auction_level_hd_9.png","https://file.taojianlou.com/user/auction_level_hd_10.png","https://file.taojianlou.com/user/auction_level_hd_11.png","https://file.taojianlou.com/user/auction_level_hd_12.png"]
         * my_level_v_pic : ["https://file.taojianlou.com/user/my_level_v_pic_0.png","https://file.taojianlou.com/user/my_level_v_pic_1.png","https://file.taojianlou.com/user/my_level_v_pic_2.png","https://file.taojianlou.com/user/my_level_v_pic_3.png","https://file.taojianlou.com/user/my_level_v_pic_4.png","https://file.taojianlou.com/user/my_level_v_pic_5.png","https://file.taojianlou.com/user/my_level_v_pic_6.png","https://file.taojianlou.com/user/my_level_v_pic_7.png","https://file.taojianlou.com/user/my_level_v_pic_8.png","https://file.taojianlou.com/user/my_level_v_pic_9.png","https://file.taojianlou.com/user/my_level_v_pic_10.png","https://file.taojianlou.com/user/my_level_v_pic_11.png","https://file.taojianlou.com/user/my_level_v_pic_12.png"]
         * seller_level_pic : ["https://file.taojianlou.com/user/seller_level_0.png","https://file.taojianlou.com/user/seller_level_1.png","https://file.taojianlou.com/user/seller_level_2.png","https://file.taojianlou.com/user/seller_level_3.png","https://file.taojianlou.com/user/seller_level_4.png","https://file.taojianlou.com/user/seller_level_5.png","https://file.taojianlou.com/user/seller_level_6.png","https://file.taojianlou.com/user/seller_level_7.png","https://file.taojianlou.com/user/seller_level_8.png","https://file.taojianlou.com/user/seller_level_9.png","https://file.taojianlou.com/user/seller_level_10.png","https://file.taojianlou.com/user/seller_level_11.png","https://file.taojianlou.com/user/seller_level_12.png"]
         * logo : https://file.taojianlou.com/platform/auction_log.jpg
         */

        private String logo;
        private List<String> levelPic;
        private List<String> my_level_pic;
        private List<String> my_level_v_pic;
        private List<String> seller_level_pic;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public List<String> getSeller_level_pic() {
            return seller_level_pic;
        }

        public void setSeller_level_pic(List<String> seller_level_pic) {
            this.seller_level_pic = seller_level_pic;
        }
    }

    public static class ResultBean {
        /**
         * code : 0
         * message : 请求成功
         * success : true
         * timestamp : 1592468085372
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

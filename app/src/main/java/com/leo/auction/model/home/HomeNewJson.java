package com.leo.auction.model.home;

import java.util.List;

/**
 * Created by Leo on 2018/7/5.
 */

public class HomeNewJson {

    /**
     * state : 0
     * message :
     * data : [{"id":458,"newstype":0,"newstitle":"推房神器年度优惠活动本月月底即将结束啦！","newscontent":"<p>7月年度会员调回原价，欲够从速，联系您归属的业务员办理，大家奔走相告哈～祝业绩长虹～&nbsp; &nbsp;<\/p><p>黄金版视频房源发布每天10条<\/p><p>白金版视频房源发布每天40条<\/p><p>钻石版视频房源发布每天80条<\/p><p>黄金版 365元/年<\/p><p>白金版 580元/年<\/p><p>钻石版 960元/年<\/p><p><br/><\/p>","newsdate":"/Date(1529897423693)/","stick":1},{"id":419,"newstype":0,"newstitle":"搜房帮与无线搜房帮合并公告","newscontent":"<p>各位用户：<\/p><p>由于搜房帮网站改版升级。[搜房帮] + [无线搜房帮]合并到经纪云。现推房将取消[无线搜房帮]的站点绑定，并下线[无线搜房帮]的所有功能。<\/p><p>若您的[搜房帮]账号与[无线搜房帮]账号不同，请将[无线搜房帮]账号添加至[搜房帮]的站点管理中。<\/p><p>若您的账号相同，则无影响。<\/p><p>给您带来的不便请您谅解。<br/><\/p><p><br/><\/p><p style=\"text-align: right;\">推房敬上<\/p><p><br/><\/p>","newsdate":"/Date(1518054274273)/","stick":1},{"id":417,"newstype":0,"newstitle":"【重要信息】三网发布手机标签房源及小区签到操作须知","newscontent":"<p>各位用户：<\/p><p>近期三网改版导致导致发布重发会缺少手机标签，无法签到。<\/p><p>现在可根据以下操作进行验证设置。设置成功之后可以照常发布带有手机标签的房源、以及小区签到。<\/p><p><br/><\/p><p><img src=\"http://daili.tuifang123.com/ueditor/net/upload/image/20180309/6365618651210828245469588.png\" title=\"安居客APP改版公告_20180309.png\" alt=\"安居客APP改版公告_20180309.png\" width=\"688\" height=\"958\" style=\"width: 688px; height: 958px;\"/><\/p>","newsdate":"/Date(1517381133967)/","stick":1}]
     */

    private int state;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 458
         * newstype : 0
         * newstitle : 推房神器年度优惠活动本月月底即将结束啦！
         * newscontent : <p>7月年度会员调回原价，欲够从速，联系您归属的业务员办理，大家奔走相告哈～祝业绩长虹～&nbsp; &nbsp;</p><p>黄金版视频房源发布每天10条</p><p>白金版视频房源发布每天40条</p><p>钻石版视频房源发布每天80条</p><p>黄金版 365元/年</p><p>白金版 580元/年</p><p>钻石版 960元/年</p><p><br/></p>
         * newsdate : /Date(1529897423693)/
         * stick : 1
         */

        private int id;
        private int newstype;
        private String newstitle;
        private String newscontent;
        private String newsdate;
        private int stick;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNewstype() {
            return newstype;
        }

        public void setNewstype(int newstype) {
            this.newstype = newstype;
        }

        public String getNewstitle() {
            return newstitle;
        }

        public void setNewstitle(String newstitle) {
            this.newstitle = newstitle;
        }

        public String getNewscontent() {
            return newscontent;
        }

        public void setNewscontent(String newscontent) {
            this.newscontent = newscontent;
        }

        public String getNewsdate() {
            return newsdate;
        }

        public void setNewsdate(String newsdate) {
            this.newsdate = newsdate;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
        }
    }
}

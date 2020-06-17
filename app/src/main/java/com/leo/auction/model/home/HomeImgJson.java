package com.leo.auction.model.home;

import java.util.List;

/**
 * Created by Leo on 2018/7/5.
 */

public class HomeImgJson {


    /**
     * state : 0
     * message :
     * data : [{"id":2,"imgUrl":"http://wd2.tuifang123.com/images/app.png","links":""},{"id":1,"imgUrl":"http://wd2.tuifang123.com/images/app.png","links":"https://www.baidu.com/"}]
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

    @Override
    public String toString() {
        return "HomeImgJson{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 2
         * imgUrl : http://wd2.tuifang123.com/images/app.png
         * links :
         */

        private int id;
        private String imgUrl;
        private String links;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinks() {
            return links;
        }

        public void setLinks(String links) {
            this.links = links;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", links='" + links + '\'' +
                    '}';
        }
    }
}
